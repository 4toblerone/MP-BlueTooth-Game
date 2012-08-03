package com.seminarski;

import com.seminarski.R;
import com.seminarski.komunikacija.BluetoothCommunication;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends Activity {

	private Button upaliBT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main3);
		upaliBT = (Button) findViewById(R.id.UpaliBT);
		
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void turnOnBlueTooth(View view){
		
			//BluetoothCommunication.getInstance().enableBlueTooth(this);
			
		
	}
}
