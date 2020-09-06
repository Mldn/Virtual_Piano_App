package Package_POOP_PROJ2;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

public class TXT {

	
	
	public static void formatiraj(Kompozicija k)  {
		
		try {
			PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		
			for(MuzickiSimbol simbol : k.GetSimboli()) {
				if(simbol instanceof Akord) {
					writer.print('[');
					for(Nota n : ((Akord)simbol).GetNote()) {
						writer.print(n.GetKey()) ;
						
					}
					writer.print(']');
				}
				else if(simbol instanceof Pauza) {
				
					if(simbol.GetT() == MuzickiSimbol.TRAJANJE.CETVRTINA ) writer.print('|') ; 
					else writer.print(' ');
					
				}else if(simbol instanceof Nota) {
					Nota n =  (Nota)simbol ; 
					writer.print(n.GetKey()) ;
				}
			}
			
			
			writer.close() ;	
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
