package Package_POOP_PROJ2;


public class Nota extends MuzickiSimbol {

	
	private int oktava ;
	private char visina ;
	private boolean povisena;
	static char povisilica = '#'; 
	private char key ; 
	private int mb ;
	
	
	public Nota(TRAJANJE tr , int oktava , char v , boolean p, int m) {
		super(tr) ;
		this.oktava = oktava ;
		this.visina = v ;
		this.povisena = p ;	
		this.mb = m ; 
	}
	
	public Nota (Nota n) {
		super(n.GetT());
		this.oktava = n.oktava;
		this.visina = n.visina ;
		this.povisena = n.povisena ;
		this.key = n.key;
		this.mb = n.mb ;
	}
	
	
	public int GetMB() {
		return this.mb ;
	}

	public void SetKey(char  t) {
		key = t ; 
	}
	
	
	public char GetKey() {
		return key ;
	}
	
	public boolean jestePauza() {
		return false ;
	}
	
	
	public boolean jesteNota() {
		return true ;
	}
	
	public boolean jesteAkord() {
		return false ;
	}


	public int getOktava() {
		return oktava;
	}


	public char getVisina() {
		return visina;
	}


	public boolean isPovisena() {
		return povisena;
	}


	public static char getPovisilica() {
		return povisilica;
	}
	public String OpisNote() {
		return ""+ visina + (povisena?"#" : "")+ oktava;
	}
	
	public String toString() {
		
	//	return ""+ visina + (povisena?"#" : "")+ oktava;
		return ""+ this.key ;
	}
	
	public void setT(TRAJANJE tr) {
		this.trajanje = tr;
	}
	
	public boolean jednake(Nota n) {
		if(this.GetMB() == n.GetMB()) return true;
		else return false;
	}
	
	
	
	
	
	
}
