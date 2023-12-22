package model;

import java.awt.Graphics;

import assets.Recursos;

public class Power {
	private int x,y;
	private int ALTO=37;
	private int ANCHO=30;
	public Power() {
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getALTO() {
		return ALTO;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getANCHO() {
		return ANCHO;
	}
	public void draw(Graphics g) {
		g.drawImage(Recursos.powerup, x, y, ANCHO, ALTO, null);
	}
	
}
