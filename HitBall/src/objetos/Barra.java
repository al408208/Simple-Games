package objetos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import assets.Constantes;
import assets.Recursos;

public class Barra {
	private int xBarra=170;

	public int getxBarra() {
		return xBarra;
	}

	public void setxBarra(int xBarra) {
		this.xBarra = xBarra;
	}
	
	public void left(){
		xBarra-=15;
	}
	
	public void right(){
		xBarra+=15;
	}
	
	public void draw(Graphics g) {
//		g.setColor(Color.RED);
//		g.fillRect(xBarra, yBarra, ancho, alto);
		g.drawImage(Recursos.barra, xBarra, Constantes.yBarra, Constantes.anchoBarra, Constantes.altoBarra, null);
		
	}
	
	public void siguiente() {
		if (xBarra<=0) {
			xBarra=0;
		}
		
		if(xBarra >= (800-Constantes.anchoBarra)) {
			xBarra=800-Constantes.anchoBarra;
		}
	}
}
