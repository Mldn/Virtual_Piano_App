package Package_POOP_PROJ2;

import java.awt.Frame.*;
import java.awt.*;
import java.awt.event.* ;
import java.util.*;

import javax.sound.midi.MidiUnavailableException;



public class Klavir extends Frame {
		
	public LinkedList<PianoOktava> klavir = new LinkedList<PianoOktava>() ; 
	private Menu meni1 = new Menu("Formatiraj");
	private Menu meni2 = new Menu("Ucitaj kompoziciju") ;
	private Menu pm = new Menu("Ispis") ;
	private Menu meni3 = new Menu("Pokretanje");
	private Menu meni4 = new Menu("Mod Snimanja");
	private String[] op1 = { "TXT" , "Midi" } ;
	private String[] op2 = {"fur_elise.txt" , "jingle_bells.txt" , "ode_to_joy.txt" , "spring.txt", "we_wich_you_a_merry_christmas.txt"};
	private String[] op3 = {"Znak", "Opis"} ;
	private String[] op4 = {"Start", "Pauza" , "Stop"} ;
	private String[] op5 = { "On" , "Off" } ;
	private Kompozicija komp = new Kompozicija() ;
	private KompPleyer play  ;
	//private Canvas ispis ; 
	private Ispis i;
	private boolean opis = true ;
	
	public static int r ;
	public static int g ; 
	public static int x ; 
	public static boolean flg;
	public static boolean snimanje = false ;
	public static LinkedList<MuzickiSimbol> snimak = new LinkedList<MuzickiSimbol>() ; 
	
	public static int[] gg = new int[5];
	public static int[][] xx  = new int[7][5];
	public static int ii;
	public static int jj; 
	public static boolean akord ;
	public static boolean[] flg1 = new boolean[5];
	
	public static Nota global;
	public static Kompozicija globalK;
	public static int globali;
	
	public Klavir() throws MidiUnavailableException {
		super("Virtual Piano") ;
		setSize(1050 , 600) ; 
		r = 0 ;
		g = 100;
		for(int u = 0 ; u < 5; u ++) {
			for(int j = 0 ; j < 5 ; j ++) {
				xx[u][j] = 0 ;
			}
		}
		for(int i = 0; i <  5 ; i ++) {
			Klavir.gg[i] = 0 ;
		}
		
		x = 100 ;
		ii = 0;
		jj = 0 ;
		akord =false;
		setResizable(false) ;
		setLayout(new BorderLayout());
		i = new Ispis(komp,opis) ;
		play = new KompPleyer(komp,i, this);
		
		dodajSever() ;
		dodajDirke() ;
		dodajMeni() ;
		dodajIspis() ;
		
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent dog) {
				dispose() ;
				play.prekini();
			}
		}) ;	
		setVisible(true);
	}
	
	public void dodajSever() {
		Panel p = new Panel() ;
		p.setLayout(new BorderLayout());
		
		Button b = new Button("SVIRAJ") ;
		
			
		p.add(b,BorderLayout.CENTER) ;
		
	}
	
	
	public void dodajDirke() throws MidiUnavailableException {
		Panel p = new Panel() ;	
		
		p.setLayout(new GridLayout(1,5));
		for(int i = 0 ; i < 5 ; i++) {
			PianoOktava o = new PianoOktava(this, this.i) ;
			klavir.add(o) ; 
		}
		
		for(int i = 0 ; i < 5 ; i ++ ) {
			PianoOktava oktava = klavir.get(i);
			oktava.repaint();
			p.add(oktava);
		}	
		add(p, BorderLayout.CENTER) ;	
	}

	
	public void dodajMeni() {

		ActionListener osl1 = new ActionListener() {
			public void actionPerformed(ActionEvent dog) {
				MenuItem stv = (MenuItem)dog.getSource();
				String s = stv.getLabel();
				if( s == "TXT") {
					TXT.formatiraj(komp);
				}else if(s == "Midi") {
					MIDI.formatiraj(komp);
				}
			}
		};
		
		ActionListener osl2 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				MenuItem stv = (MenuItem)ae.getSource();
				String s = stv.getLabel();
				komp.ucitaj(s);
				Klavir.r = 0 ;
				play.prekini(); 
				try {
					play = new KompPleyer(komp,i,Klavir.this);
					play.start();
				} catch (MidiUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i.promenaKomp(komp);
				i.repaint() ;
				globalK = komp;
				globali = 0 ;
				global =(Nota) globalK.simboli.get(0);
			}
		};
		
		ActionListener osl3 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				MenuItem stv = (MenuItem)ae.getSource();
				String s = stv.getLabel();
				if(s == "Opis") i.promena();
				else i.promenaF() ;
				i.promenaKomp(komp);
				i.repaint();
			}
		};
		
		ActionListener osl4 = new ActionListener() {
			public void actionPerformed (ActionEvent ae ) {
				MenuItem stv = (MenuItem)ae.getSource();
				String s = stv.getLabel();
				if(s == "Start") {
					play.kreni();
				}
				if(s == "Pauza") {
					play.stani();
				}
				if(s == "Stop") {
					play.zaustavi(); 
				}
				
			}
		};
		
		ActionListener osl5 = new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				MenuItem stv = (MenuItem) ae.getSource() ; 
				String s = stv.getLabel() ;
				if(s == "On") {
					snimanje = true;
					snimak.clear();
				}
				if(s == "Off") {
					snimanje = false;
					Kompozicija kompo = new Kompozicija(snimak) ;
					TXT.formatiraj(kompo);
				}
			}
		};
		
		
		
		
		MenuBar traka = new MenuBar();
		setMenuBar(traka) ;
		
		
		
		
		traka.add(meni2) ;
		for(String v : op2) {
			MenuItem st = new MenuItem(v) ;
			meni2.add(st) ;
			st.addActionListener(osl2);
		}
		
		traka.add(meni1) ;
		for(String v : op1) {
			MenuItem st = new MenuItem(v);
			meni1.add(st) ;
			st.addActionListener(osl1);
		}
		
		traka.add(pm) ;
		for(int i = 0 ; i < op3.length ; i++) {
			MenuItem stv = new MenuItem(op3[i]) ;
			pm.add(stv) ;
			stv.addActionListener(osl3);
		}
		
		traka.add(meni3);
		
		for(String v : op4) {
			MenuItem st = new MenuItem(v);
			meni3.add(st);
			st.addActionListener(osl4);
		}
		traka.add(meni4);
		for(String v: op5) {
			MenuItem st = new MenuItem(v);
			meni4.add(st);
			st.addActionListener(osl5);
		}
		
		
	}
	
	
	
	public void dodajIspis() {
		i.repaint() ;
		add(i,BorderLayout.SOUTH);
	}
	
	public PianoOktava getOkt(int i) {
		return klavir.get(i); 
	}
	

	
	
	public static void main(String argv[]) throws MidiUnavailableException {
		Kompozicija.ucitaj_mapu("C:\\Users\\Mladen\\source\\repos\\POOP_PROJ1\\POOP_PROJ1\\map.csv");
		Klavir k = new Klavir();
	}
	
	
}
