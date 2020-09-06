package Package_POOP_PROJ2;
import java.util.*;



public class Akord extends MuzickiSimbol {

	private List<Nota> note = new ArrayList<Nota>() ;
	
	
	public Akord(TRAJANJE tr) {
		super(tr);
	}
	
	
	
	public List<Nota> GetNote(){
		return note;
	}

	@Override
	public boolean jesteNota() {
		return false;
	}

	@Override
	public boolean jestePauza() {
		return false;
	}

	@Override
	public boolean jesteAkord() {
		return true;
	}
 
	public void dodajNotu(Nota n)  {
		if(n.GetT() != this.GetT());
		note.add(n) ;
	}
	
	public String toString() {
		return "[" + note.toString() + "]" ;
	}
	
	
}

