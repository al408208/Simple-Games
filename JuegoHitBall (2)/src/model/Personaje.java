package model;
import java.awt.Graphics;

import assets.Recursos;

public class Personaje{
	private int x,y;
	private int ALTO,ANCHO;
	private int contadorx,contadory;
	private int personaje;
	private int avanza=10;
	private int div=1;
	private boolean habilidad=false,escudo=false,ve=false;
	private int refresh=0;

	public Personaje(int personaje) {//busco que personaje se ha creado
		this.personaje = personaje;
		switch(personaje){
	    case 1: 
	    	ANCHO=Recursos.iisprite1.getIconWidth()/4;
			ALTO=Recursos.iisprite1.getIconHeight()/4;
			avanza=11;
			break;
		case 2:
			ANCHO=Recursos.iisprite2.getIconWidth()/4;
			ALTO=Recursos.iisprite2.getIconHeight()/4;
			break;
		case 3:
			ANCHO=Recursos.iisprite3.getIconWidth()/4;
			ALTO=Recursos.iisprite3.getIconHeight()/4;
			break;
		case 4: 
			ANCHO=Recursos.iisprite4.getIconWidth()/4;
			ALTO=Recursos.iisprite4.getIconHeight()/4;
			break;
		case 5:
			ANCHO=Recursos.iisprite5.getIconWidth()/4;
			ALTO=Recursos.iisprite5.getIconHeight()/4;
			break;
		case 6:
			ANCHO=Recursos.iisprite6.getIconWidth()/4;
			ALTO=Recursos.iisprite6.getIconHeight()/4;
			break;
		default:
			System.out.println("numero no valido");			
		}
		
		contadorx=0;
		contadory=0;
		
	}
	public void reinicio(){//reinicio lascondiciones normales de los personajes
		avanza=10;
		div=1;
		escudo=false;
		ve=false;
	}
	public void rickgolpeado() {//solo para el enemigo
		if(contadory==0) {
			x=x-200;
		}else if(contadory==1) {
			x=x+200;
		}else if(contadory==2) {
			y=y+200;
		}else if(contadory==3) {
			y=y-200;
		}
	}

	public void setHabilidad(boolean habilidad) {
		this.habilidad = habilidad;
	}
	public boolean isHabilidad() {
		return habilidad;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getALTO() {
		return ALTO;
	}

	public int getANCHO() {
		return ANCHO;
	}

	public void left(){
		x-=avanza;
		contadory=1;
		cambioc();
	}
	
	public void right(){
		x+=avanza;
		contadory=0;
		cambioc();
	}
	public void up(){
		y-=avanza;
		contadory=2;
		cambioc();
	}
	public void down(){
		y+=avanza;
		contadory=3;
		cambioc();
	}
	public void espacio(){//habilidades
		if(habilidad) {

			if(personaje==2) {
				escudo=true;
			}
			if(personaje==3) {
				avanza=14;
			}
			if(personaje==4) {
				div=15;
			}
			if(personaje==5) {
				ve=true;
			}
			if(personaje==6) {
				if(contadory==0) {
					x=x+50;
				}else if(contadory==1) {
					x=x-50;
				}else if(contadory==2) {
					y=y-50;
				}else if(contadory==3) {
					y=y+50;
				}
			}
		}
		
	}
	
	public int getPersonaje() {
		return personaje;
	}

	public void draw(Graphics g) {
		switch(personaje){
	    case 1: 
			g.drawImage(Recursos.sprite1, x, y, x+ANCHO, y+ALTO, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			break;
		case 2:
			if(escudo) {
				g.drawImage(Recursos.sprite2escudo, x, y, x+ANCHO, y+ALTO, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			}else {
				g.drawImage(Recursos.sprite2, x, y, x+ANCHO, y+ALTO, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			}
			break;
		case 3:
			g.drawImage(Recursos.sprite3, x, y, x+ANCHO, y+ALTO, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			break;
		case 4: 
			g.drawImage(Recursos.sprite4, x, y, x+ANCHO/div, y+ALTO/div, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			break;
		case 5:
			if(ve) {				
				g.drawImage(Recursos.sprite5vidaextra, x, y, x+ANCHO, y+ALTO, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			}else {
				g.drawImage(Recursos.sprite5, x, y, x+ANCHO, y+ALTO, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			}
			break;
		case 6:
			g.drawImage(Recursos.sprite6, x, y, x+ANCHO, y+ALTO, contadorx*ANCHO, contadory*ALTO, (contadorx+1)*ANCHO, (contadory+1)*ALTO, null);
			break;		
		}
	}
	
	public void siguiente() {
		if (x<=0) {//control lados
			x=0;
		}
		
		if(x >= (1334-ANCHO/div)) {//La imagen no llega a 1340 no se porque
			x=1334-ANCHO/div;
		}
		
		if ( y > 650-ALTO/div) {//control alto
			y=650-ALTO/div;
		} 
		if (y<0) {
			y=0;
		}
	}
	
	public void cambioc() {//ralentizo el sprite
		refresh=++refresh %3;
		if(refresh==0) {
			if(contadorx==3) {
				contadorx=0;
			}else {
				contadorx++;
			}
		}
	}

	
}
