package Package_POOP_PROJ2;

import java.awt.* ;
import java.awt.event.* ;

public class Ispis extends Canvas {

	
	private double xmin = 0 , xmax = 1000; 
	private double ymin = 0  , ymax = 50 ; 
	private Kompozicija komp ;
	private boolean opis ; 
	
	public Ispis(Kompozicija k, boolean opis) {
		this.setSize(1050/5,200);
		komp = k ; 
		this.opis = opis ;
	}
	
	public void paint(Graphics g) {
		
		int x =(int) this.getX(0) ; int y = 0 ;
		int k = Klavir.r ;
		for(MuzickiSimbol m : this.komp.GetSimboli()) {
			if(--k > 0  ) {
				continue;
			}
			if(m instanceof Nota) {
				
				if(m.GetT() == MuzickiSimbol.TRAJANJE.CETVRTINA) {g.setColor(Color.RED); g.fillRect((int)getX(x),y, (int)getX(50),(int)getY(10) );  }
				else {g.setColor(Color.GREEN);  g.fillRect((int)getX(x),y, (int)getX(25),(int)getY(10) ) ;}
				//System.out.println("Nota");
				g.setColor(Color.BLACK);
				
				if(opis) g.drawString(((Nota)m).OpisNote(), (int)this.getX(x) + (int)this.getX(25), y + 10); 
				else g.drawString(((Nota)m).toString(), (int)this.getX(x) + (int)this.getX(25), y + 10);
				
			}
			if(m instanceof Pauza) {
				if(m.GetT() == MuzickiSimbol.TRAJANJE.CETVRTINA) {g.setColor(Color.red); g.fillRect((int)getX(x),y, (int)getX(50),(int)getY(10) );  }
				else {g.setColor(Color.green);  g.fillRect((int)getX(x),y, (int)getX(25),(int)getY(10) ) ;}
			}
			if(m instanceof Akord) {
				int b = 0 ;
				for(Nota n : ((Akord)m).GetNote()) {
					b++;
				}
				if(m.GetT() == MuzickiSimbol.TRAJANJE.CETVRTINA) {g.setColor(Color.RED); g.fillRect((int)getX(x),y, (int)getX(50),b *(int)getY(10) );  }
				else {g.setColor(Color.GREEN);  g.fillRect((int)getX(x),y, (int)getX(25),b*(int)getY(10) ) ;}
				int j = 10 ;
				String str = "" ;
				g.setColor(Color.BLACK);
				for(Nota n : ((Akord)m).GetNote()) {
					if(opis)  g.drawString(((Nota)n).OpisNote(), (int)this.getX(x) + (int)this.getX(25), y + j);
					else g.drawString(((Nota)n).toString(), (int)this.getX(x) + (int)this.getX(25), y + j);
					j += 40;
				}
				
				//System.out.println(str.toString());
			}
			if(m.GetT() == MuzickiSimbol.TRAJANJE.CETVRTINA) x += (int)getX(50) ;
			else x += (int)getX(25) ;
		}
		y = (int)this.getY(25);
		g.setColor(Color.BLACK) ;
		for(x = (int) this.getX(0) ;(int) this.getX(x)  < (int) this.getX(xmax) ; x += (int)getX(50)) g.drawLine(x, y, x, y + 10);
	}
	
	public double getX(double x) {
		return (  (x - xmin)/ (xmax - xmin) * (this.getWidth() - 1)  ) ;
	}
	
	public double getY(double y) {
		return ( (y-ymin) / (ymax - ymin) * (this.getHeight() - 1) ) ;
	}
	
	public void promena() {
		opis = true ; 
	}
	public void promenaF() {
		opis = false;
	}
	
	public void promenaKomp(Kompozicija k) {
		this.komp = k;
	}

}
