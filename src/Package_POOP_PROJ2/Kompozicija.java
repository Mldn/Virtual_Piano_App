package Package_POOP_PROJ2;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Kompozicija {
	LinkedList<MuzickiSimbol> simboli = new LinkedList<MuzickiSimbol>() ;
	LinkedList<MuzickiSimbol> pom = new LinkedList<MuzickiSimbol>() ;
	
	public static HashMap<Character, MuzickiSimbol> mapa =  new HashMap<Character, MuzickiSimbol>();
	
	static void ucitaj_mapu(String put) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(put));
			Stream<String> s = br.lines();
			
			Pattern rx = Pattern.compile("([^,]+),([A-Z]#?[2-6]),([0-9][0-9])");
			
			
			
			s.forEach(l->{
				Matcher m = rx.matcher(l);
				while(m.find()) {
					String karakter = m.group(1);
					String opis = m.group(2) ;
					int mb =Integer.parseInt(m.group(3));
					//System.out.println(mb);
					boolean f = (opis.charAt(1) == '#') ;
					Nota n =  new Nota(Nota.TRAJANJE.CETVRTINA,Integer.parseInt(new Character(opis.charAt(opis.length()-1)).toString()) , opis.charAt(0), f , mb ) ;
					n.SetKey(l.charAt(0));
					mapa.put(l.charAt(0), n);
								
				}
				
			});
			
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fajl nije pronadjen...");
		} catch (IOException e) {
		}
	}
	
	public Kompozicija() {}
	
	public Kompozicija(LinkedList<MuzickiSimbol> ms) {
		simboli = ms;
	}
	
	public void ucitaj(String put) {
		simboli.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader(put));
			Stream<String> s = br.lines();
			
			Pattern rx = Pattern.compile("(\\[[^\\]]*\\])?([^\\[]*)?");
			
			
			
			s.forEach(l->{
				Matcher m = rx.matcher(l);
				while(m.find()) {
					try {
						boolean fl = m.group(1).matches(".* .*");
						//System.out.println(m.group(1));
						if(!fl) {
							//System.out.println("Usao");
							Akord a = new Akord(Akord.TRAJANJE.CETVRTINA);
						
							for(char c : m.group(1).toCharArray()) {
								if(c=='[' || c== ']') continue;
								Nota n = new Nota((Nota)(mapa.get(c)));
								a.dodajNotu(n);
							
							}
							simboli.add(a) ;
						}else {
							for(char c : m.group(1).toCharArray()) {
								if(c=='[' || c== ']') continue;
								if(c == ' ') continue ;
								Nota n = new Nota((Nota)mapa.get(c));
								n.setT(Nota.TRAJANJE.OSMINA);
								simboli.add(n);
							}
						}
					}catch(NullPointerException np) {} ;
					
					try {
						for(char c : m.group(2).toCharArray()) {
							if(c == '|') simboli.add(new Pauza(Pauza.TRAJANJE.CETVRTINA)); else
								if( c == ' ') simboli.add(new Pauza(Pauza.TRAJANJE.OSMINA)); else {
									Nota n = new Nota((Nota)mapa.get(c));
									simboli.add(n);
							}
						}
					
					}catch(NullPointerException np) {} ;	
				}
				
			});
			
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fajl nije pronadjen...");
		} catch (IOException e) {
		}
	}
	
	public LinkedList<MuzickiSimbol> GetSimboli() {
		return this.simboli ;
	}
	
	/*public void ispisi() {
		StringBuilder sb = new StringBuilder();
		for(MuzickiSimbol m : this.GetSimboli()) {
			if(m instanceof Nota) {
				System.out.println(((Nota)m).OpisNote());
			}
		}
		System.out.println("KRAJ KOMP");
	}*/
	

	
	/*public static void main(String args[]) {
		
		Kompozicija komp = new Kompozicija() ;
		
		komp.ucitaj("C:\\Users\\Mladen\\source\\repos\\POOP_PROJ1\\POOP_PROJ1\\fur_elise.txt");
		
		TXT.formatiraj(komp);
		
		
	}*/
	
	

}
