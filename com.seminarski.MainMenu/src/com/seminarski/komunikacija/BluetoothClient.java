package com.seminarski.komunikacija;

import java.io.IOException;

import util.Util;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class BluetoothClient {

	private BluetoothSocket socket;
	private BluetoothDevice device;
	
	
	public BluetoothClient(BluetoothDevice device){
		
		this.device = device;
		
		try{
			socket = this.device.createRfcommSocketToServiceRecord(Util.APP_UUID);
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
	public void connect() {
		
		try{
			socket.connect();
			BluetoothCommunication komunikacija = BluetoothCommunication.getInstanceOfBluetoothKomunikacijaNit(socket);
		}
		catch(IOException e){
			e.printStackTrace();
			try{
				socket.close();
			}
			catch(IOException closeException){
				closeException.printStackTrace();
			}
			//return;
		}

		
		//bluetoothKomunikacijaNit = new BluetoothKomunikacijaNit(socket);
		//bluetoothKomunikacijaNit.run();
		
		
	}
	
}
