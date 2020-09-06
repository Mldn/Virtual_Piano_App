package Package_POOP_PROJ2;

import java.beans.IntrospectionException;

import javax.sound.midi.MidiUnavailableException;

import Package_POOP_PROJ2.MuzickiSimbol.TRAJANJE;

public class KompPleyer extends Thread {

	private Kompozicija komp ;
	private boolean gotova = false;
	private MidiPlayer mp ;
	private boolean radi = false;
	private Ispis i ;
	private Klavir k;
	
	
	public KompPleyer(Kompozicija k, Ispis ii, Klavir kl) throws MidiUnavailableException {
		komp = k ;
		mp = new MidiPlayer() ;
		i = ii;
		this.k = kl ;
	}
	
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				for(MuzickiSimbol m : komp.GetSimboli()) {
					
					
					synchronized (this) {
						if(gotova) break ; 
					}	
					
					
					synchronized (this) {
							if (!radi) wait();
					}
				
						
					
						if(m instanceof Akord) {
							Klavir.akord = true;
							Klavir.ii = 0 ;
							Klavir.jj = 0 ;
							for(Nota n : ((Akord)m).GetNote()) {
								mp.play(n.GetMB());
								Klavir.x = n.getOktava();
								Klavir.x--;
								Klavir.x-- ;
								if(n.getVisina() == 'C') Klavir.g = 0;
								if( n.getVisina() == 'D') Klavir.g = 1;
								if(n.getVisina() == 'E') Klavir.g = 2;
								if(n.getVisina() == 'F') Klavir.g = 3;
								if(n.getVisina() == 'G') Klavir.g = 4;
								if(n.getVisina() == 'A') Klavir.g = 5;
								if( n.getVisina() == 'B') Klavir.g = 6;
								Klavir.xx[Klavir.g][Klavir.x] = 1 ;
								Klavir.gg[Klavir.x] = 1;
							}
							for(int i = 0; i < 5 ; i ++) {
								if(Klavir.gg[i] == 1) {
									Klavir.x = i ;
									PianoOktava po = k.getOkt(i);
									po.repaint() ;
								}
							}
							
							Thread.sleep(750);
							for(Nota n : ((Akord)m).GetNote()) {
								mp.release(n.GetMB());
							}
							Klavir.akord = false; 
							for(int i = 0; i < 5 ; i ++) {
								if(Klavir.gg[i] != 1) {
									PianoOktava po = k.getOkt(i);
									po.repaint() ;
								}
							}
							Klavir.ii = 0 ;
							Klavir.jj = 0 ;
							Klavir.g = 100 ;
							Klavir.x = 100;
							for(int u = 0 ; u < 7; u ++) {
								for(int j = 0 ; j < 5 ; j ++) {
									Klavir.xx[u][j] = 0 ;
								}
							}
							for(int i = 0; i <  5 ; i ++) {
								Klavir.gg[i] = 0 ;
							}
							
						}
						if(m instanceof Nota) {
							Klavir.x = ((Nota)m).getOktava(); Klavir.x-- ; Klavir.x--;
							PianoOktava po = k.getOkt(Klavir.x) ;
							if(((Nota)m).getVisina() == 'C') po.gl = 0;
							if( ((Nota)m).getVisina() == 'D') po.gl = 1;
							if(((Nota)m).getVisina() == 'E') po.gl = 2;
							if(((Nota)m).getVisina() == 'F') po.gl = 3 ;
							if(((Nota)m).getVisina() == 'G') po.gl = 4;
							if(((Nota)m).getVisina() == 'A') po.gl = 5;
							if( ((Nota)m).getVisina() == 'B') po.gl = 6;
							Klavir.flg =(boolean) ((Nota)m).isPovisena();
							
							
							po.repaint();
							
							
							sleep(500);
							if(m.GetT() == TRAJANJE.CETVRTINA) mp.play(((Nota)m).GetMB() , 250);
							else mp.play(((Nota)m).GetMB() , 125);
							//System.out.println(Klavir.x);
							
							
							
							
							for(int i = 0 ; i < 5 ; i ++) {
								if(i != Klavir.x) {
									PianoOktava p = k.getOkt(i);
									p.repaint();
								}
							}
							//po.repaint();
							
						}
						if(m instanceof Pauza) {
							if(m.GetT() == TRAJANJE.CETVRTINA) Thread.sleep(250);
							else Thread.sleep(125);
						}
						Klavir.r ++ ;
						i.repaint(); 
						Klavir.g = 100 ;
						Klavir.x = 100 ;
				}
				Klavir.r = 0 ; 
				radi = false;
				Klavir.g = 100 ;
				if(!gotova) {
					for(int i = 0 ; i < 5 ; i ++ ) {
						PianoOktava oktava = k.getOkt(i);
						oktava.repaint();
					}
						
				}	
				
			}
			sleep(1000) ;
		} catch (InterruptedException ie) {}
		
	}
	
	

	
	public synchronized void kreni() {
		 radi = true; 
		 gotova = false;
		 notify(); 
	}
	
	public synchronized void stani() { 
		radi = false; 
	}
	public synchronized void zaustavi() {
		this.gotova = true;
		for(int i = 0 ; i < 5 ; i ++ ) {
			PianoOktava oktava = k.getOkt(i);
			oktava.repaint();
		}	
		vrati();
	}
	
	public synchronized void prekini() {
		this.gotova = true; 
		interrupt();
	}
	public synchronized void ucitajKomp(Kompozicija k) {
		this.komp = k ;
		this.start();
	}
	public synchronized void startuj() {
		this.start(); 
	}
	
	public void vrati() {
			Klavir.akord = false; 
			Klavir.ii = 0 ;
			Klavir.jj = 0 ;
			Klavir.g = 100 ;
			Klavir.x = 100;
			for(int u = 0 ; u < 7; u ++) {
				for(int j = 0 ; j < 5 ; j ++) {
					Klavir.xx[u][j] = 0 ;
				}
			}
			for(int i = 0; i <  5 ; i ++) {
				Klavir.gg[i] = 0 ;
			}
			
	}
}
