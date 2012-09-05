package com.seminarski.komunikacija;

import util.Util;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class BluetoothServerThread extends Thread {

	private final BluetoothServerSocket mmServerSocket;
	private BluetoothSocket  socket;

	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	public BluetoothServerThread() {
		socket = null;
		BluetoothServerSocket tmp = null;
		try {
			tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(
					Util.APP_NAME, Util.APP_UUID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mmServerSocket = tmp;
	}

	@Override
	public void run() {

	//	BluetoothSocket socket = null;
		while (socket == null) {
			try {
				socket = mmServerSocket.accept();
				if(socket!=null) {
					Log.e("SOOOOKET", "soket vise nije null");
				}
				else{
					Log.e("SOOOOKET", "soket je nulllllll!!!!");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		BluetoothCommunication komunikacija = BluetoothCommunication
				.getInstanceOfBluetoothKomunikacijaNit(socket);
	}
}
