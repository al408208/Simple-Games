package assets;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;


public class Recursos {
	public static Image barra;
	public static Image bola;
	public static Image caratula;
	public static Image fondojuego;
	public static Image icono;
	public static Image redjelly;
	public static Image yjelly;
	public static Image pjelly;
	public static Image vida;
	public static Image jelly;
	public static ImageIcon iijelly;
	public static Image p1;
	public static ImageIcon iip1;
	public static Image p2;
	public static ImageIcon iip2;
	public static Image victory;
	public static Image rip;
	public static ImageIcon iirip;
	public static ImageIcon iivictory;
	
	public static AudioClip musica;
	public static AudioClip bote;
	public static AudioClip muerte;
	public static AudioClip win;
	public static AudioClip lose;
	public static AudioClip pausa;
	public static AudioClip vidamenos;
	public static AudioClip pingu1;
	public static AudioClip pingu2;
	
	public static void loadAssets() {
		ImageIcon ii=new ImageIcon(("img/barra.png"));
		barra=ii.getImage();
		iijelly=new ImageIcon(("img/hojasinfondo.png"));
		jelly=iijelly.getImage();
		iip1=new ImageIcon(("img/p1.png"));
		p1=iip1.getImage();
		iip2=new ImageIcon(("img/p2.png"));
		p2=iip2.getImage();
		ii=new ImageIcon(("img/iconobola.png"));
		bola=ii.getImage();
		ii=new ImageIcon(("img/caratula.jpg"));
		caratula=ii.getImage();
		ii=new ImageIcon(("img/ingame.jpg"));
		fondojuego=ii.getImage();
		ii=new ImageIcon(("img/morph.png"));
		icono=ii.getImage();
		ii=new ImageIcon(("img/redjelly.png"));
		redjelly=ii.getImage();
		ii=new ImageIcon(("img/yjelly.png"));
		yjelly=ii.getImage();
		ii=new ImageIcon(("img/pjelly.png"));
		pjelly=ii.getImage();
		ii=new ImageIcon(("img/vida.png"));
		vida=ii.getImage();
		iivictory=new ImageIcon(("img/victory.jpg"));
		victory=ii.getImage();
		iirip=new ImageIcon(("img/rip.png"));
		rip=ii.getImage();
		
		try {
			musica= Applet.newAudioClip(new File("sounds/Cancion.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bote= Applet.newAudioClip(new File("sounds/bote.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			muerte= Applet.newAudioClip(new File("sounds/muerte.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			win= Applet.newAudioClip(new File("sounds/win.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lose= Applet.newAudioClip(new File("sounds/lose.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pausa= Applet.newAudioClip(new File("sounds/pausa.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			vidamenos= Applet.newAudioClip(new File("sounds/vidamenos.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pingu1= Applet.newAudioClip(new File("sounds/pingu1.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pingu2= Applet.newAudioClip(new File("sounds/pingu2.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
