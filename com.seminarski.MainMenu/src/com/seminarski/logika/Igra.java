package com.seminarski.logika;

import android.app.Activity;
import android.gesture.Prediction;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.seminarski.PlayScreen;
import com.seminarski.R;
import com.seminarski.domen.Igrac;
import com.seminarski.domen.PoljeTable;
import com.seminarski.domen.Znak;

public class Igra {

	private PoljeTable[][] matrica_table;

	private Igrac igrac1;
	private Igrac igrac2;

	private PlayScreen pocetni_meni;

	private void inicijalizujSvaPolja(PoljeTable[][] matrica_tabele) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrica_tabele[i][j] = new PoljeTable();

			}

		}

	}

	public Igra(PlayScreen pocetni_meni) {

		this.pocetni_meni = pocetni_meni;
		matrica_table = new PoljeTable[3][3];
		inicijalizujSvaPolja(matrica_table);
		igrac1 = new Igrac(Znak.IKS);
		igrac2 = new Igrac(Znak.OKS);

	}

	private void setMatrica_table(PoljeTable[][] matrica_table) {
		this.matrica_table = matrica_table;
	}

	public PoljeTable[][] getMatrica_table() {
		return matrica_table;
	}

	// uvesti proveru da li je pravi igrac odigrao svoj potez, ako je polje vec
	// popunjeno ne moze se ponovo popunjavati

	public void proveriPobedu() {

		for (int i = 0; i < 3; i++) {

			if (matrica_table[i][0].getZnak().equals(
					matrica_table[i][1].getZnak())
					&& matrica_table[i][0].getZnak().equals(
							matrica_table[i][2].getZnak())&& matrica_table[i][0].getZnak()!=Znak.DEFAULT) {

				proglasiPobednika(matrica_table[i][0].getZnak());
				return;

			}
			if (matrica_table[0][i].getZnak().equals(
					matrica_table[1][i].getZnak())
					&& matrica_table[0][i].getZnak().equals(
							matrica_table[2][i].getZnak())&& matrica_table[0][i].getZnak()!=Znak.DEFAULT) {

				proglasiPobednika(matrica_table[0][i].getZnak());
				return;

			}
		}

		if (matrica_table[0][0].getZnak().equals(matrica_table[1][1].getZnak())
				&& matrica_table[0][0].getZnak().equals(
						matrica_table[2][2].getZnak())&& matrica_table[0][0].getZnak()!=Znak.DEFAULT) {
			proglasiPobednika(matrica_table[1][1].getZnak());
			return;

		}
		if (matrica_table[0][2].getZnak().equals(matrica_table[1][1].getZnak())
				&& matrica_table[0][2].getZnak().equals(
						matrica_table[2][0].getZnak()) && matrica_table[0][2].getZnak()!=Znak.DEFAULT) {
			proglasiPobednika(matrica_table[0][2].getZnak());
			return;

		}

		Toast.makeText(pocetni_meni, "Nema pobednika", Toast.LENGTH_SHORT)
				.show();

	}

	private void proglasiPobednika(Znak znak) {

		Toast.makeText(pocetni_meni, znak.toString() + "je pobednik!",
				Toast.LENGTH_SHORT).show();
		// return znak.toString() + "je pobednik!";

	}

	private int vratiIDIksIliOkd(Znak znak) {
		if (znak.equals(Znak.IKS))
			return R.drawable.iks;
		else
			return R.drawable.oks;
	}

	public void iscrtajIksIliOks(Activity activity, ImageView imageView,
			Znak znak) {
		Animation myFadeInAnimation = AnimationUtils.loadAnimation(activity,
				R.anim.iksoksfadein);

		imageView.setImageResource(vratiIDIksIliOkd(znak));

		imageView.startAnimation(myFadeInAnimation);

	}

}
