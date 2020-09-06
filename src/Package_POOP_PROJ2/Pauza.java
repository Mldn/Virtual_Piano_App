package Package_POOP_PROJ2;

public class Pauza extends MuzickiSimbol {

	public Pauza(TRAJANJE tr) {
		super(tr);
	}

	@Override
	public boolean jesteNota() {
		return false;
	}

	@Override
	public boolean jestePauza() {
		return true;
	}

	@Override
	public boolean jesteAkord() {
		return false;
	}
	
	
	public String toString() {
		if(this.GetT() == TRAJANJE.CETVRTINA) return "|" ;
		else return " ";
		
	}

}
