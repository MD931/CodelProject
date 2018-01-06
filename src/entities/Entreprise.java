package entities;

public class Entreprise extends Contact{
	
	private Integer numSiret;
	
	public Entreprise() {
		super();
	}
	
	public Entreprise(Integer numSiret){
		super();
		this.numSiret = numSiret;
	}

	public Entreprise(String firstName, String lastName, String email, Integer numSiret) {
		super(firstName, lastName, email);
		this.numSiret = numSiret;
	}

	public Integer getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(Integer numSiret) {
		this.numSiret = numSiret;
	}
}
