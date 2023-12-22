package objetos;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import assets.Constantes;
import assets.Recursos;

public class Jelly{
	private int x,y;
	private int ALTO;
	private int ANCHO;
	private int contadorx,contadory;
	private boolean bandera=false,controlfin=false;
	int v;
	private int aux=0;
	private int signo;
	
	public Jelly() {
		ANCHO=Recursos.iijelly.getIconWidth()/5;
		ALTO=Recursos.iijelly.getIconHeight()/4;
		x=(int)(Math.random()*680)+20;
		y=(int)(Math.random()*400)+1;
		contadorx=0;
		contadory=0;
		v=40;
		signo=(int)(Math.random()*2);
		if(signo==0) {
			v=-v;
			contadory=1;
		}
	}
	
	public int gety() {
		return y;
	}
	public int getx() {
		return x;
	}
	public int getancho() {
		return ANCHO;
	}
	public int getalto() {
		return ALTO;
	}
	public boolean getControlfin() {
		return controlfin;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Recursos.jelly, x, y, x+ANCHO/2, y+ALTO/2, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
	}
	
	public void update() {
		x+=v;
		if(x<=0) {
			v=-v;
			contadory=0;
		}
		if(x>=800-(ANCHO/2)) {
			v=-v;
			contadory=1;
		}	
		if(bandera && contadory==1) {
			contadory=2;			
		}
		if(bandera && contadory==0) {
			contadory=3;
		}	
		if(bandera) {
			cambiocmuerte();
		}else {
			cambioc();
		}
		controlVelocidad();
	}
	
	public void cambioc() {
		if(contadorx==4) {
			contadorx=0;
		}else {
			contadorx++;
		}
	}
	
	public void cambiocmuerte() {
		if(aux==0) {
			contadorx=0;
		}else {
			contadorx++;
			if(contadorx==2) {
				controlfin=true;
			}
		}
		aux++;
	}	
	private void controlVelocidad(){
		if(contadorx>2 ||bandera) {
			if(v<0) {
				v=-1;
			}else {
				v=1;
			}
		}
		if(contadorx>=0 && contadorx<3 && !bandera) {
			if(v<0) {
				v=-40;
			}else {
				v=40;
			}
		}
	}
}
