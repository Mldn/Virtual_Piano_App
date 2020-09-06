package Package_POOP_PROJ2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.midi.MidiUnavailableException;


public class PianoOktava extends Canvas  {

	private double xmin = 0 , xmax = 7*15; 
	private double ymin = 400  , ymax = 1000 ; 
	private MidiPlayer md ;
	private Klavir k ;
	private Ispis i;
	public int gl = -1 ;
	

	public static final String slova = "1234567890qwertyuiopasdfghjklzxcvbn";
	
	public static final String slova1 = "!@$%^*(QWETYIOPSDGHJLZCVB";
	
	
	
	public PianoOktava(Klavir k, Ispis i) throws MidiUnavailableException {
		this.setSize(1050/5,200);
		md = new MidiPlayer() ;
		this.k = k ;
		this.i = i;
		
		
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				for(int i = 0 ; i < 5 ; i++) {
					PianoOktava o = k.getOkt(i);
					o.repaint();
				}
				
				
				try {
				
					Character c = arg0.getKeyChar();
					Nota n = (Nota)Kompozicija.mapa.get(c);
					
					
					Klavir.x = ((Nota)n).getOktava(); Klavir.x-- ; Klavir.x--;
					PianoOktava po = k.getOkt(Klavir.x) ;
					if(n.getVisina() == 'C') po.gl = 0;
					if(n.getVisina() == 'D') po.gl = 1;
					if(n.getVisina() == 'E') po.gl = 2;
					if(n.getVisina() == 'F') po.gl = 3 ;
					if(n.getVisina() == 'G') po.gl = 4;
					if(n.getVisina() == 'A') po.gl = 5;
					if(n.getVisina() == 'B') po.gl = 6;
					Klavir.flg =(boolean) ((Nota)n).isPovisena();
					
					
					po.repaint();
					
					
					md.play(n.GetMB(), 250);
				
					//Klavir.x=100;
					Klavir.g = 100 ;
					
					
					
					if(n.jednake(Klavir.global)) {
						Klavir.r ++ ;
						Klavir.globali++ ;
						if(Klavir.globalK.simboli.get(Klavir.globali) instanceof Pauza) {
							Klavir.globali++;
							Klavir.r++;
						}
						Klavir.global =(Nota) Klavir.globalK.simboli.get(Klavir.globali);
						i.repaint();
					}
					
					if(Klavir.snimanje) {
						Klavir.snimak.add(n);
					}
				}catch (Exception e) {}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(int i = 0 ; i < 5 ; i++) {
					PianoOktava o = k.getOkt(i);
					o.repaint();
				}
				int br = k.klavir.indexOf(PianoOktava.this);
				int nota = arg0.getX() / (PianoOktava.this.getWidth() / 7 ) ; 
				System.out.println(nota);
				int ii = 0;
				for(int x =(int) PianoOktava.this.getX(0) ; x < (int)PianoOktava.this.getX(80)  ; x += (int)PianoOktava.this.getX(15) ) {
					if(x>getX(25) && x<getX(34)) continue;
					if(arg0.getX() < x) break;
					ii++ ;
				}
				Nota n = null ;
				if(arg0.getY() > PianoOktava.this.getHeight()/2) n = (Nota) Kompozicija.mapa.get(new Character(slova.charAt(br*7 + nota))) ;
				else n = (Nota) Kompozicija.mapa.get(new Character(slova1.charAt(br*5 + ii))) ;
				try {
					Klavir.x = ((Nota)n).getOktava(); Klavir.x-- ; Klavir.x--;
					PianoOktava po = k.getOkt(Klavir.x) ;
					if(n.getVisina() == 'C') po.gl = 0;
					if(n.getVisina() == 'D') po.gl = 1;
					if(n.getVisina() == 'E') po.gl = 2;
					if(n.getVisina() == 'F') po.gl = 3 ;
					if(n.getVisina() == 'G') po.gl = 4;
					if(n.getVisina() == 'A') po.gl = 5;
					if(n.getVisina() == 'B') po.gl = 6;
					Klavir.flg =(boolean) ((Nota)n).isPovisena();
					
					
					po.repaint();
					
					
					
					md.play(n.GetMB(), 250);
					if(Klavir.snimanje) {
						Klavir.snimak.add(n);
					}
			}catch (Exception e) {}
			Klavir.x = 100;
			Klavir.g = 100 ;
		
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		//System.out.println("Radi");
		g.setColor(Color.BLACK);
		if(!Klavir.akord) {
			for(int x =(int) this.getX(0) , i = 0  ; x < (int)this.getX(105)  ; x += (int)getX(15) , i ++) {
					if(!Klavir.flg) {
						if(gl == i) { 
							g.setColor(Color.RED);
							gl = -1;
						}
						else g.setColor(Color.WHITE);
					} else g.setColor(Color.WHITE);
					g.fillRect(x, 0,  (int)getX(15) , this.getHeight());
					//System.out.println(getWidth());
					//g.drawLine(x,0 ,x,this.getHeight()) ;
					g.setColor(Color.BLACK); 
					g.drawRect(x, 0,  (int)getX(15) , this.getHeight());
				
			}
			for(int x =(int) this.getX(0), i = 0 ; x < (int)this.getX(80)  ; x += (int)getX(15) ) {
					if(x>getX(25) && x<getX(34)) continue;
					if(Klavir.flg) {
						if(gl == i) {
							g.setColor(Color.RED);
							gl = -1 ;
						}
						else g.setColor(Color.black);
					} else g.setColor(Color.black);
				
				// System.out.println(x);
					g.fillRect(x+(int)getX(10)  , 0,  (int)getX(10) , this.getHeight()/2);
					//System.out.println(getWidth());
				//	g.drawLine(x,0 ,x,this.getHeight()) ;
				i++ ;
			}
		} else {
			for(int x =(int) this.getX(0) , i = 0  ; x < (int)this.getX(105)  ; x += (int)getX(15) ) {
				if(i ==7) break ;
				if( Klavir.xx[i][Klavir.x] == 1) {
					g.setColor(Color.RED);
				}else g.setColor(Color.WHITE);
				//System.out.println(i);
				g.fillRect(x, 0,  (int)getX(15) , this.getHeight());
				//System.out.println(getWidth());
				//g.drawLine(x,0 ,x,this.getHeight()) ;
				g.setColor(Color.BLACK); 
				g.drawRect(x, 0,  (int)getX(15) , this.getHeight());
				i++;
			}
			for(int x =(int) this.getX(0) ; x < (int)this.getX(80)  ; x += (int)getX(15) ) {
				if(x>getX(25) && x<getX(34)) continue;
				g.setColor(Color.black);				
				g.fillRect(x+(int)getX(10)  , 0,  (int)getX(10) , this.getHeight()/2);
			}	
		}
	}
	
	
	public double getX(double x) {
		return (  (x - xmin)/ (xmax - xmin) * (this.getWidth() - 1)  ) ;
	}
	
	public double getY(double y) {
		return ( (y-ymin) / (ymax - ymin) * (this.getHeight() - 1) ) ;
	}
	
	
	
	
	
}
