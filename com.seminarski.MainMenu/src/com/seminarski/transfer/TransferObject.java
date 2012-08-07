package com.seminarski.transfer;

import java.io.Serializable;

import com.seminarski.domen.Znak;

public class TransferObject implements Serializable {

	//Da li ce mi trebati i znak?
	private int koordinataX;
	private int koordinataY;
	private boolean pobeda; //ukoliko je true ne dozvoliti igracu koji cita TransferObjekat da igra dalje
	private boolean potezOdigran;
	private Znak znak;//this may not be necessary due the fact there is only two signs player 
					  //can play with , but in future game may change so
					  //more then 2 signs would be an 
	
	public void setKoordinataX(int koordinataX) {
		this.koordinataX = koordinataX;
	}
	public int getKoordinataX() {
		return koordinataX;
	}
	public void setKoordinataY(int koordinataY) {
		this.koordinataY = koordinataY;
	}
	public int getKoordinataY() {
		return koordinataY;
	}
	public void setPobeda(boolean pobeda) {
		this.pobeda = pobeda;
	}
	public boolean isPobeda() {
		return pobeda;
	}
	
	public void setPotezOdigran(boolean potezOdigran){
		this.potezOdigran = potezOdigran;
	}
	
	public boolean isPotezOdigran(){
		return potezOdigran;
	}
	public Znak getZnak() {
		return znak;
	}
	public void setZnak(Znak znak) {
		this.znak = znak;
	}
	
	
	
	
	
	
	
	
}
