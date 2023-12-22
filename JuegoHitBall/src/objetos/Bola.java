package objetos;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import assets.Constantes;
import assets.Recursos;

public class Bola{
	private int xBola=50, yBola=400;
	private int speedX=10, speedY=10;
	private Barra barra;
	int alfa;
	
	public Bola(Barra barra) {
		super();
		this.barra = barra;
		alfa=50;
	}
	public int getyBola() {
		return yBola;
	}
	public int getxBola() {
		return xBola;
	}
	public void setxBola(int xBola) {
		this.xBola = xBola;
	}
	public void setyBola(int yBola) {
		this.yBola = yBola;
	}
	public void setspeedX(int speedX) {
		this.speedX = speedX;
	}
	public void setspeedY(int speedY) {
		this.speedY = speedY;
	}
	public int getspeedX() {
		return speedX;
	}
	public int getspeedY() {
		return speedY;
	}
	public void draw(Graphics g) {
		g.drawImage(Recursos.bola, xBola, yBola, Constantes.anchoBola, Constantes.altoBola, null);
	}
	
	public void update() {
		yBola+=(int)(Math.sin(Math.toRadians(alfa))*speedY);
		xBola+=(int)(Math.cos(Math.toRadians(alfa))*speedX);
		control();
	}
	private void control() {//10 es el punto medio de la bola en la x
		if(yBola+Constantes.altoBola>Constantes.yBarra && yBola+Constantes.altoBola<Constantes.yBarra+Constantes.altoBarra && xBola+10>this.barra.getxBarra() && xBola+10<this.barra.getxBarra()+Constantes.anchoBarra){
			if(xBola+10>=this.barra.getxBarra() && xBola+10<this.barra.getxBarra()+20) {
				alfa=150;
				if(speedX<0) {//sin sentido
					speedX=-speedX;
				}
			}else if(xBola+10>=this.barra.getxBarra()+20 && xBola+10<this.barra.getxBarra()+40) {
				alfa=120;
				if(speedX<0) {//sin sentido
					speedX=-speedX;
				}
			}else if(xBola+10>=this.barra.getxBarra()+40 && xBola+10<this.barra.getxBarra()+60) {
				alfa=90;
			}else if(xBola+10>=this.barra.getxBarra()+60 && xBola+10<this.barra.getxBarra()+80) {
				alfa=60;
				if(speedX<0) {
					speedX=-speedX;
				}
			}else if(xBola+10>=this.barra.getxBarra()+80 && xBola+10<=this.barra.getxBarra()+100) {
				alfa=30;
				if(speedX<0) {
					speedX=-speedX;
				}
			}
			Recursos.bote.play();
			if(speedY>0) {
				speedY=-speedY;
			}
		}
			
		if (xBola<0 || xBola > 800-Constantes.anchoBola) {
			speedX=-speedX;
			Recursos.bote.play();
		} 
		if (yBola<0) {
			speedY=-speedY;
			Recursos.bote.play();
		}
	}
}