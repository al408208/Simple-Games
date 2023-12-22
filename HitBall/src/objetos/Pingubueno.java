package objetos;

import java.awt.Graphics;

import assets.Recursos;

public class Pingubueno {
	private int x,y;
	private int ALTO;
	private int ANCHO;
	private int contadorx,contadory;
	private boolean bandera=false;
	int v;
	public Pingubueno() {
		ANCHO=Recursos.iip1.getIconWidth()/11;
		ALTO=Recursos.iip1.getIconHeight()/1;
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
		g.drawImage(Recursos.p1, x, y, x+ANCHO/2, y+ALTO/2, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
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

