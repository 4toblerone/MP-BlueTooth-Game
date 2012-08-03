package com.seminarski;

import javax.xml.validation.TypeInfoProvider;

import util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_menu);
		
		Button btnNovaIgra = (Button) findViewById(R.id.nova_igra);
		btnNovaIgra.setOnClickListener(onNovaIgraListener);
		
		Button btnPrikljuciSe = (Button) findViewById(R.id.prikljuci_se);
		btnPrikljuciSe.setOnClickListener(onPrikljuciSeListener);
		
		Button btnIzadji = (Button) findViewById(R.id.izadji);
		btnIzadji.setOnClickListener(onIzadjiListener);
		//AlertDialog.Builder builder = new AlertDialog.Builder(this);	
		
	}
	
	
	OnClickListener onNovaIgraListener = new OnClickListener() {
			
		//@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//it should open dialog box with two choices 
			//first  : start game which will be played on two phones
			//second : start game which will be played on one phone
			final CharSequence [] listOfChoices = {"one phone" , "two phones"};
			AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);	
			builder.setTitle("Play game on:");
			builder.setItems(listOfChoices, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int choice) {
					
					Toast.makeText(getApplicationContext(), listOfChoices[choice], Toast.LENGTH_SHORT).show();
					//here based on users choise it will be send signal to
					//PlayScreen activity Util.NEW_GAME_TWO_PHONES or Util.NEW_GAME_ONE_PHONE
					
					String start = choice==0 ? Util.NEW_GAME_ONE_PHONE : Util.NEW_GAME_TWO_PHONES;
					Bundle bundle = new Bundle();
					bundle.putString(Util.TYPE_OF_START, start );
					Intent novaIgra = new Intent(MainMenu.this, PlayScreen.class);
					novaIgra.putExtras(bundle);
					MainMenu.this.startActivity(novaIgra);
				}
			});
			AlertDialog choose = builder.create();
			choose.show();
			
			/*Bundle bundle = new Bundle();
			bundle.putString(Util.TIP_POCETKA, Util.NOVA_IGRA);
			Intent novaIgra = new Intent(MainMenu.this, PlayScreen.class);
			novaIgra.putExtras(bundle);
			MainMenu.this.startActivity(novaIgra);*/
		}
	};
	
	OnClickListener onPrikljuciSeListener = new OnClickListener() {
		
		//@Override
		public void onClick(View v) {
			Intent prikljuciSeIgri = new Intent(MainMenu.this, BluetoothDiscovery.class);
			MainMenu.this.startActivity(prikljuciSeIgri);
			
		}
	};
	
	OnClickListener onIzadjiListener = new OnClickListener() {
		
		//@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
}
