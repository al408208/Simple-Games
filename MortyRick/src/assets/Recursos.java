package assets;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;

public class Recursos {
	
	public static Image fondo;
	public static Image icono;
	public static Image connect;
	public static Image portada;
	public static ImageIcon iisprite1;
	public static Image sprite1;
	public static ImageIcon iisprite2;
	public static Image sprite2;
	public static ImageIcon iisprite3;
	public static Image sprite3;
	public static ImageIcon iisprite4;
	public static Image sprite4;
	public static ImageIcon iisprite5;
	public static Image sprite5;
	public static ImageIcon iisprite6;
	public static Image sprite6;
	public static Image sprite2escudo;
	public static Image sprite5vidaextra;
	public static Image powerup;
	public static Image p1;
	public static Image p2;
	public static Image p3;
	public static Image p4;
	public static Image p5;
	public static Image p6;
	public static Image podio;
	
	public static AudioClip minicio;
	public static AudioClip mjuego;
	public static AudioClip rickpilla;
	public static AudioClip sonidopower;
	public static AudioClip fin;

	public static void loadAssets() {
		ImageIcon ii=new ImageIcon(("img/ingame.png"));
		fondo=ii.getImage();
		ii=new ImageIcon(("img/icono.png"));
		icono=ii.getImage();
		ii=new ImageIcon(("img/connect.png"));
		connect=ii.getImage();
		ii=new ImageIcon(("img/caratula.png"));
		portada=ii.getImage();
		iisprite1=new ImageIcon(("img/sprite1.png"));
		sprite1=iisprite1.getImage();
		iisprite2=new ImageIcon(("img/sprite2.png"));
		sprite2=iisprite2.getImage();
		iisprite3=new ImageIcon(("img/sprite3.png"));
		sprite3=iisprite3.getImage();
		iisprite4=new ImageIcon(("img/sprite4.png"));
		sprite4=iisprite4.getImage();
		iisprite5=new ImageIcon(("img/sprite5.png"));
		sprite5=iisprite5.getImage();
		iisprite6=new ImageIcon(("img/sprite6.png"));
		sprite6=iisprite6.getImage();
		ii=new ImageIcon(("img/powerup.png"));
		powerup=ii.getImage();
		ii=new ImageIcon(("img/sprite2escudo.png"));
		sprite2escudo=ii.getImage();
		ii=new ImageIcon(("img/sprite5vidaextra.png"));
		sprite5vidaextra=ii.getImage();
		ii=new ImageIcon(("img/p1.png"));
		p1=ii.getImage();
		ii=new ImageIcon(("img/p2.png"));
		p2=ii.getImage();
		ii=new ImageIcon(("img/p3.png"));
		p3=ii.getImage();
		ii=new ImageIcon(("img/p4.png"));
		p4=ii.getImage();
		ii=new ImageIcon(("img/p5.png"));
		p5=ii.getImage();
		ii=new ImageIcon(("img/p6.png"));
		p6=ii.getImage();
		ii=new ImageIcon(("img/podio.png"));
		podio=ii.getImage();
		
		try {
			minicio= Applet.newAudioClip(new File("sounds/mi.wav").toURI().toURL());
			mjuego= Applet.newAudioClip(new File("sounds/mjuego.wav").toURI().toURL());
			rickpilla= Applet.newAudioClip(new File("sounds/rickpilla.wav").toURI().toURL());
			sonidopower= Applet.newAudioClip(new File("sounds/sonidopowerup.wav").toURI().toURL());
			fin= Applet.newAudioClip(new File("sounds/fin.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
