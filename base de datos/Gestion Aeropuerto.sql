DROP DATABASE IF EXISTS Aeropuerto;
CREATE DATABASE Aeropuerto CHARACTER SET utf8mb4;
USE Aeropuerto;

CREATE TABLE Companias (
    id_compania INT PRIMARY KEY AUTO_INCREMENT,
    nombre_compania VARCHAR(50) NOT NULL,
    pais_origen VARCHAR(50)
);

CREATE TABLE Aviones (
    id_avion INT PRIMARY KEY AUTO_INCREMENT,
    modelo VARCHAR(50),
    fabricante VARCHAR(50),
    capacidad INT,
    id_compania INT,
    FOREIGN KEY (id_compania) REFERENCES Companias(id_compania)
);

CREATE TABLE PuertasEmbarque (
    id_puerta_embarque INT PRIMARY KEY AUTO_INCREMENT,
    numero_puerta VARCHAR(10),
    terminal VARCHAR(10)
);

CREATE TABLE Empleados (
    id_empleado INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    cargo ENUM('Piloto', 'Azafata', 'Técnico', 'Administrativo') NOT NULL,
    id_compania INT, 
    fecha_contratacion DATE,
    FOREIGN KEY (id_compania) REFERENCES Companias(id_compania)
);

CREATE TABLE Vuelos (
    id_vuelo INT PRIMARY KEY AUTO_INCREMENT,
    numero_vuelo VARCHAR(20),
    id_compania INT,
    id_avion INT,
    id_piloto INT,
    id_puerta_embarque INT,
    origen VARCHAR(100),
    destino VARCHAR(100),
    hora_salida DATETIME,
    hora_llegada DATETIME,
    estado ENUM('Correcto', 'Retrasado', 'Cancelado') NOT NULL,
    FOREIGN KEY (id_compania) REFERENCES Companias(id_compania),
    FOREIGN KEY (id_avion) REFERENCES Aviones(id_avion),
    FOREIGN KEY (id_piloto) REFERENCES Empleados(id_empleado),
    FOREIGN KEY (id_puerta_embarque) REFERENCES PuertasEmbarque(id_puerta_embarque)
);

CREATE TABLE Pasajeros (
    id_pasajero INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    fecha_nacimiento DATE
);

CREATE TABLE Reservas (
    id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    id_pasajero INT,
    id_vuelo INT,
    fecha_reserva DATETIME,
    asiento VARCHAR(10),
    clase VARCHAR(30),
    FOREIGN KEY (id_pasajero) REFERENCES Pasajeros(id_pasajero),
    FOREIGN KEY (id_vuelo) REFERENCES Vuelos(id_vuelo)
);

SET SQL_SAFE_UPDATES = 0;

INSERT INTO Companias (nombre_compania, pais_origen) VALUES
('AeroExpress', 'España'),
('SkyJet', 'Francia');

INSERT INTO Aviones (modelo, fabricante, capacidad, id_compania) VALUES
('A320', 'Airbus', 180, 1),
('B737', 'Boeing', 160, 2);

INSERT INTO PuertasEmbarque (numero_puerta, terminal) VALUES
('A1', 'T1'),
('B3', 'T2');

INSERT INTO Empleados (nombre, apellido, cargo, id_compania, fecha_contratacion) VALUES
('Carlos', 'López', 'Piloto', 1, '2015-06-01'),
('Lucía', 'Martínez', 'Azafata', 1, '2018-04-15'),
('Jean', 'Dupont', 'Piloto', 2, '2016-09-20'),
('Nora', 'Durand', 'Técnico', 2, '2017-11-10');


INSERT INTO Vuelos (numero_vuelo, id_compania, id_avion, id_piloto, id_puerta_embarque, origen, destino, hora_salida, hora_llegada, estado) VALUES
('AE123', 1, 1, 1, 1, 'Madrid', 'Barcelona', '2025-05-20 08:00:00', '2025-05-20 09:00:00', 'Correcto'),
('SJ456', 2, 2, 3, 2, 'París', 'Roma', '2025-05-21 10:30:00', '2025-05-21 12:30:00', 'Retrasado');

INSERT INTO Pasajeros (nombre, apellido, fecha_nacimiento) VALUES
('Ana', 'Gómez', '1990-05-22'),
('Mario', 'Rossi', '1985-12-10');

INSERT INTO Reservas (id_pasajero, id_vuelo, fecha_reserva, asiento, clase) VALUES
(1, 1, '2025-05-10 14:00:00', '12A', 'Turista'),
(2, 2, '2025-05-12 09:30:00', '3C', 'Business');


-- 1. Listado de los empleados con cargo de 'Piloto'
SELECT id_empleado, nombre, apellido, cargo, fecha_contratacion
FROM Empleados
WHERE cargo = 'Piloto';

-- 2. Empleados de compañías sin vuelos programados en 2023 o 2024
SELECT CONCAT(nombre, ' ', apellido) AS Nombre_emp, fecha_contratacion
FROM Empleados
WHERE id_compania NOT IN (
    SELECT DISTINCT id_compania
    FROM Vuelos
    WHERE YEAR(hora_salida) IN (2023, 2024)
);

-- 3. Azafatas empleadas por compañías de España o Francia
SELECT e.id_empleado, e.nombre, e.apellido, e.cargo, c.pais_origen
FROM Empleados e
JOIN Companias c ON e.id_compania = c.id_compania
WHERE e.cargo = 'Azafata' AND c.pais_origen IN ('España', 'Francia')
ORDER BY c.pais_origen ASC;

-- 4. Top 5 vuelos con mayor número de reservas
SELECT v.numero_vuelo, COUNT(r.id_reserva) AS total_reservas
FROM Vuelos v
JOIN Reservas r ON v.id_vuelo = r.id_vuelo
GROUP BY v.id_vuelo
ORDER BY total_reservas DESC
LIMIT 5;

-- 5. empleados hay por cargo en cada compañía
SELECT c.nombre_compania, e.cargo, COUNT(e.id_empleado) AS total_empleados
FROM Empleados e
JOIN Companias c ON e.id_compania = c.id_compania
GROUP BY c.nombre_compania, e.cargo
ORDER BY c.nombre_compania, e.cargo;

-- 6. Capacidad total y promedio de aviones por compañía
SELECT c.nombre_compania,
       SUM(a.capacidad) AS capacidad_total,
       AVG(a.capacidad) AS capacidad_promedio
FROM Aviones a
JOIN Companias c ON a.id_compania = c.id_compania
GROUP BY c.nombre_compania
ORDER BY capacidad_total DESC;

-- 7. Actualizar el estado de los vuelos con más de 1 reserva a 'Correcto'
UPDATE Vuelos
SET estado = 'Correcto'
WHERE id_vuelo IN (
    SELECT id_vuelo
    FROM Reservas
    GROUP BY id_vuelo
    HAVING COUNT(id_reserva) > 1
);

-- 8. Aumentar la capacidad de los aviones de compañías francesas en 10 asientos
UPDATE Aviones a
JOIN Companias c ON a.id_compania = c.id_compania
SET a.capacidad = a.capacidad + 10
WHERE c.pais_origen = 'Francia';

-- 9. Borrar pasajeros sin reservas
DELETE FROM Pasajeros
WHERE id_pasajero NOT IN (
    SELECT DISTINCT id_pasajero
    FROM Reservas
);

-- 10. Total de reservas por vuelo 
CREATE VIEW Vista_Vuelos_Reservas_Simple AS
SELECT v.numero_vuelo, COUNT(r.id_reserva) AS total_reservas
FROM Vuelos v
LEFT JOIN Reservas r ON v.id_vuelo = r.id_vuelo
GROUP BY v.id_vuelo;

SELECT * FROM Vista_Vuelos_Reservas_Simple;


-- 11.Listado de empleados con su compañía
CREATE VIEW Vista_Empleados_Compania AS
SELECT 
    e.id_empleado,
    CONCAT(e.nombre, ' ', e.apellido) AS nombre_completo,
    e.cargo,
    c.nombre_compania
FROM Empleados e
JOIN Companias c ON e.id_compania = c.id_compania;

SELECT * FROM Vista_Empleados_Compania;

-- 12. Actualizar estado de un vuelo por número de vuelo con validación previa
DELIMITER $$
CREATE PROCEDURE modifica_estado_vuelo(IN num_vuelo VARCHAR(20), IN nuevo_estado ENUM('Correcto', 'Retrasado', 'Cancelado'))
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Vuelos WHERE numero_vuelo = num_vuelo) THEN
        SELECT CONCAT('No existe el vuelo con número: ', num_vuelo) AS mensaje;
    ELSE
        UPDATE Vuelos
        SET estado = nuevo_estado
        WHERE numero_vuelo = num_vuelo;
        SELECT CONCAT('Estado del vuelo ', num_vuelo, ' actualizado a ', nuevo_estado) AS mensaje;
    END IF;
END $$
DELIMITER ;

CALL modifica_estado_vuelo('AE123', 'Retrasado');

-- 13. Mostrar información completa de un vuelo según su número con validación
DELIMITER $$
CREATE PROCEDURE ver_info_vuelo(
    IN num_vuelo VARCHAR(20)
)
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Vuelos WHERE numero_vuelo = num_vuelo) THEN
        SELECT CONCAT('No existe el vuelo con número: ', num_vuelo) AS mensaje;
    ELSE
        SELECT * FROM Vuelos WHERE numero_vuelo = num_vuelo;
    END IF;
END $$
DELIMITER ;

CALL ver_info_vuelo('AE123');

-- 14. Determinar estado temporal de un vuelo según su fecha de salida
DELIMITER $$
CREATE FUNCTION estado_vuelo(fecha_salida DATETIME)
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    IF fecha_salida IS NULL THEN
        RETURN 'Fecha no válida';
    ELSEIF fecha_salida < NOW() THEN
        RETURN 'Vuelo ya ha pasado';
    ELSEIF fecha_salida > NOW() THEN
        RETURN 'Vuelo próximo';
    ELSE
        RETURN 'Vuelo en curso';
    END IF;
END $$
DELIMITER ;

SELECT numero_vuelo, hora_salida, estado_vuelo(hora_salida) AS estado_actual FROM Vuelos;

-- 15. Validar que la hora de salida de un vuelo no sea en el pasado
DELIMITER $$
CREATE TRIGGER before_insert_vuelo_check_fecha
BEFORE INSERT ON Vuelos
FOR EACH ROW
BEGIN
    IF NEW.hora_salida < NOW() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La hora de salida no puede ser en el pasado.';
    END IF;
END $$
DELIMITER ;

-- 16. Registrar automáticamente la fecha y hora de creación de un nuevo empleado

DELIMITER $$
CREATE TRIGGER after_insert_empleado
AFTER INSERT ON Empleados
FOR EACH ROW
BEGIN
    UPDATE Empleados
    SET fecha_creacion = NOW()
    WHERE id_empleado = NEW.id_empleado;
END $$
DELIMITER ;
