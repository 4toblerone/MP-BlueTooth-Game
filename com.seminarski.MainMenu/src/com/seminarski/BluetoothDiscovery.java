package com.seminarski;

import util.Util;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ParserError")
public class BluetoothDiscovery extends Activity {

	
	//public static String EXTRA_DEVICE_ADDRESS = "device_address";
	
	private ArrayAdapter<String> listOfDevices;

	private BluetoothAdapter bluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_list);
		Button scanButton = (Button) findViewById(R.id.button_scan);

		ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
		
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		//BluetoothServerNit bserver = new BluetoothServerNit();
		//bserver.run();
		
		
		listOfDevices = new ArrayAdapter<String>(this, R.layout.device_name);
		newDevicesListView.setAdapter(listOfDevices);
		newDevicesListView.setOnItemClickListener(onNewDeviceClickListener);
		
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		this.registerReceiver(mReceiver, filter);
		scanButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// kreni sa otkrivanjem
				System.err.println("Fsdfsd");
				//nekiNizReci.add("[probajfdjlka");//radi
				doDiscovery();
			}
		});
	}
	
	private void doDiscovery(){
		bluetoothAdapter.startDiscovery();
	}
	
	private OnItemClickListener onNewDeviceClickListener = new OnItemClickListener() {

		//@Override
		public void onItemClick(AdapterView<?> arg0, View v, int arg2,
				long arg3) {
			bluetoothAdapter.cancelDiscovery();
			
			String info = ((TextView) v).getText().toString();
			String address = info.substring(info.length()-17);
			bluetoothAdapter.cancelDiscovery();
			Intent intent = new Intent(BluetoothDiscovery.this, PlayScreen.class);
			Bundle bundle = new Bundle();
			bundle.putString(Util.TYPE_OF_START, Util.JOIN_GAME );
			bundle.putString(Util.EXTRA_DEVICE_ADDRESS, address);
			intent.putExtras(bundle);
			BluetoothDiscovery.this.startActivity(intent);			
			
			//intent.putExtra(Util.EXTRA_DEVICE_ADDRESS, address);
			//setResult(Activity.RESULT_OK, intent);
			//finish();
		}
	};
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			
			String action = intent.getAction();
			if(BluetoothDevice.ACTION_FOUND.equals(action)){
				
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				
				/*if(device.getBondState() == BluetoothDevice.BOND_BONDED){
					listOfDevices.add(device.getName()+"\n"+device.getAddress());
				}*/
				listOfDevices.add(device.getName()+"\n"+device.getAddress());
			}
			
			else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
			{
				
			}
			
		}
	};
	
	
	
}