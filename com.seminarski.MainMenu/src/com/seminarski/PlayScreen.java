package com.seminarski;

import java.util.ArrayList;
import java.util.List;

import util.Util;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import com.seminarski.domen.Znak;
import com.seminarski.komunikacija.BluetoothClient;
import com.seminarski.komunikacija.BluetoothCommunication;
import com.seminarski.komunikacija.BluetoothServerThread;
import com.seminarski.logika.Igra;
import com.seminarski.transfer.TransferObject;
import com.seminarski.transfer.TransferObjectWrapper;

public class PlayScreen extends Activity implements OnGesturePerformedListener {
	/** Called when the activity is first created. */

	private GestureLibrary gestureLib;
	private GestureOverlayView gesture;
	private static List<GestureOverlayView> listaGesturePolja;
	private Igra igra;
	// private ProgressBar progressbar;
	// private BluetoothServerNit bservernit;
	private ProgressDialog progressdialog;
	private boolean vezaUspostavljena;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		igra = new Igra(this);

		gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);

		if (!gestureLib.load()) {
			Toast.makeText(this,
					"Doslo je do problema pri ucitavanju gestures-a",
					Toast.LENGTH_SHORT).show();
			finish();
		} else {
			Toast.makeText(this, "Lista gestura se uspesno ucitala",
					Toast.LENGTH_SHORT).show();
		}

		try {

			gesture = (GestureOverlayView) findViewById(R.id.gestures11);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures12);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures13);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures21);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures22);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures23);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures31);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures32);
			Util.listaGesturePolja.add(gesture);
			gesture = (GestureOverlayView) findViewById(R.id.gestures33);
			Util.listaGesturePolja.add(gesture);
			for (GestureOverlayView gesture1 : Util.listaGesturePolja) {
				gesture1.addOnGesturePerformedListener(this);

			}

		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}

		progressdialog = ProgressDialog.show(PlayScreen.this,
				"Ceka se drugi igrac", "Molimo sacekajte");

		povezivanje();

	}

	private void povezivanje() {

		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				progressdialog.dismiss();
			}

		};
		// due to inner class Thread i had to put final modifier on tow. Why?
		final TransferObjectWrapper tow = new TransferObjectWrapper();
		Bundle bundle = this.getIntent().getExtras();
		// if new start is on two phones...
		if (bundle.getString(Util.TYPE_OF_START).equalsIgnoreCase(
				Util.NEW_GAME_TWO_PHONES)) {
			final BluetoothServerThread btserverthread = new BluetoothServerThread();
			new Thread() {
				public void run() {
					btserverthread.start();

					while (btserverthread.isAlive()) {
						continue;
					}
					handler.sendEmptyMessage(0);
					while (true) {
						tow.setTransferObject(BluetoothCommunication
								.getInstanceOfBluetoothKomunikacijaNit(null)
								.getTransferObjekat());
						// play/draw a move on board
						drawAndMarkMove(tow);
					}
				};

			}.start();

		}
		if (bundle.getString(Util.TYPE_OF_START).equalsIgnoreCase(
				Util.NEW_GAME_ONE_PHONE)) {

			handler.sendEmptyMessage(0);
		}

		else {
			BluetoothAdapter bluetoothAdapter = BluetoothAdapter
					.getDefaultAdapter();
			String address = bundle.getString(Util.EXTRA_DEVICE_ADDRESS);
			BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
			final BluetoothClient btClient = new BluetoothClient(device);
			new Thread() {
				public void run() {
					btClient.connect();
					handler.sendEmptyMessage(0);
					// here has to go
				};
			}.start();
		}
	}

	private Prediction vratiNajboljePredvidjanje(List<Prediction> predictions) {
		Prediction prediction1 = predictions.get(0);
		for (int i = 0; i < predictions.size(); i++) {

			if (prediction1.score < predictions.get(i++).score) {

				prediction1 = predictions.get(i++);
			}
		}

		return prediction1;
	}

	private void drawAndMarkMove(TransferObjectWrapper tow) {

		int coorY = tow.getTransferObject().getKoordinataY();
		int coorX = tow.getTransferObject().getKoordinataX();
		int imageViewId = getResources()
				.getIdentifier(
						"imageview" + Integer.toString(coorX)
								+ Integer.toString(coorY), "id",
						getPackageName());
		ImageView imageView = (ImageView) findViewById(imageViewId);
		igra.getMatrica_table()[coorX - 1][coorY - 1].Obelezi(tow
				.getTransferObject().getZnak());
		igra.iscrtajIksIliOks(this, imageView, tow.getTransferObject()
				.getZnak());
		igra.proveriPobedu();
	}

	// @Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

		Znak znak = Znak.DEFAULT;
		ImageView imageView;
		ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
		Prediction prediction = vratiNajboljePredvidjanje(predictions);
		znak = odrediZnak(prediction);

		// is it more optimal then hardcoded imageView initialization with
		// switch?

		String fieldName = getResources().getResourceEntryName(overlay.getId());
		char y = fieldName.toCharArray()[fieldName.toCharArray().length - 1];
		char x = fieldName.toCharArray()[fieldName.toCharArray().length - 2];
		int coorY = Integer.parseInt(Character.toString(y));
		int coorX = Integer.parseInt(Character.toString(x));
		TransferObject to = new TransferObject();
		to.setKoordinataX(coorX);
		to.setKoordinataY(coorY);
		to.setZnak(znak);
		to.setPotezOdigran(true);
		TransferObjectWrapper tow = new TransferObjectWrapper();
		tow.setTransferObject(to);
		drawAndMarkMove(tow);
	}

	private Znak odrediZnak(Prediction prediction) {

		if (prediction.name.trim().equalsIgnoreCase("OKS"))

			return Znak.OKS;

		if (prediction.name.trim().equalsIgnoreCase("IKS"))
			return Znak.IKS;
		return null;
	}
}