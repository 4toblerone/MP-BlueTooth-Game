package com.seminarski.komunikacija;

import util.Util;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class BluetoothServerThread extends Thread {

	private final BluetoothServerSocket mmServerSocket;

	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	public BluetoothServerThread() {

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

		BluetoothSocket socket = null;
		while (socket == null) {
			try {
				socket = mmServerSocket.accept();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		BluetoothCommunication komunikacija = BluetoothCommunication
				.getInstanceOfBluetoothKomunikacijaNit(socket);
	}
}
