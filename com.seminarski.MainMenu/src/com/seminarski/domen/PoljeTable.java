package com.seminarski.domen;

import android.gesture.GestureOverlayView;

public class PoljeTable {

	private boolean obelezeno = false;

	/*public enum Znak {

		IKS, OKS
	}
*/
	
	private Znak znak;
	private GestureOverlayView gestureView;

	public PoljeTable(){
		this.znak = Znak.DEFAULT;
		
	}
	
	public void Obelezi(Znak znak) {

		if (obelezeno == false) {
			obelezeno = true;
			this.setZnak(znak);

		}

	}

	public void setZnak(Znak znak) {
		this.znak = znak;
	}

	public Znak getZnak() {
		return znak;
	}
}
