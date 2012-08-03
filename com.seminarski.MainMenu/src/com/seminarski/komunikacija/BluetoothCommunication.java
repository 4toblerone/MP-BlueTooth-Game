package com.seminarski.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import util.Util;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.seminarski.transfer.TransferObject;

public class BluetoothCommunication {

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private BluetoothSocket socket;

	private static BluetoothCommunication instance;

	public static BluetoothCommunication getInstanceOfBluetoothKomunikacijaNit(
			BluetoothSocket socket) {

		if (instance == null) {
			instance = new BluetoothCommunication(socket);
		}
		return instance;
	}

	private BluetoothCommunication(BluetoothSocket socket) {

		this.socket = socket;

		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public TransferObject getTransferObjekat() {
		TransferObject to = null;
		try {
			to = (TransferObject) ois.readObject();
		} catch (Exception e) {
			Log.e(Util.ERROR_READING_TO, e.getMessage());
		}
		return to;
	}

	public void sendTO(TransferObject to) {
		try {
			oos.writeObject(to);
			oos.flush();
		} catch (Exception e) {
			Log.e(Util.ERROR_SENDING_TO, e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
