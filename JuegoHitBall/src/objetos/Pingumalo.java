package objetos;

import java.awt.Graphics;

import assets.Constantes;
import assets.Recursos;
import ventanas.GameEngine;

public class Pingumalo {
	private int x,y;
	private int ALTO;
	private int ANCHO;
	private int contadorx,contadory;
	private boolean bandera=false;
	int v;
	
	public Pingumalo() {
		ANCHO=Recursos.iip2.getIconWidth()/11;
		ALTO=Recursos.iip2.getIconHeight()/1;
		x=0;
		y=(int)(Math.random()*400)+1;
		contadorx=0;
		contadory=0;
		v=10;
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
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public void draw(Graphics g) {
		g.drawImage(Recursos.p2, x, y, x+ANCHO/2, y+ALTO/2, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
	}
	
	public void update() {
		x+=v;
		if(x>=800-(ANCHO/2)) {
			x=0;
		}
		cambioc();
	}
	
	public void cambioc() {
		if(contadorx==10) {
			contadorx=0;
		}else {
			contadorx++;
		}
	}
	
}
