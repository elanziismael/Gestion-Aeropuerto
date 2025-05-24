package clases;

public class PuertaEmbarque {
	
	 private int id;
	 private String numero;
	 private String terminal;

	    public PuertaEmbarque(int id, String numero, String terminal) {
	        this.id = id;
	        this.numero = numero;
	        this.terminal = terminal;
	    }
	    
	    

	    public void setId(int id) {
			this.id = id;
		}



		public void setNumero(String numero) {
			this.numero = numero;
		}



		public void setTerminal(String terminal) {
			this.terminal = terminal;
		}



		public int getId() {
	        return id;
	    }

	    public String getNumero() {
	        return numero;
	    }

	    public String getTerminal() {
	        return terminal;
	    }

	    @Override
	    public String toString() {
	        return "PuertaEmbarque{" +
	                "id=" + id +
	                ", numero='" + numero + '\'' +
	                ", terminal='" + terminal + '\'' +
	                '}';
	    }

}
