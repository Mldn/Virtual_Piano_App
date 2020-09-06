package Package_POOP_PROJ2;

enum TRAJANJE { CETVRTINA, OSMINA} ;


public abstract class MuzickiSimbol {

	public enum TRAJANJE { CETVRTINA, OSMINA} ;
	
	protected TRAJANJE trajanje ;
	
	public MuzickiSimbol(TRAJANJE tr) {
		trajanje = tr;
	}
	
	public abstract boolean jesteNota() ;
	public abstract boolean jestePauza() ;
	public abstract boolean jesteAkord() ;
	
	public TRAJANJE GetT() {
		return trajanje ;
	}
	
	
	
	
	

}
