package util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.gesture.GestureOverlayView;

public class Util {

	
	public static final String APP_NAME = "XOX";
	
	public static final UUID APP_UUID = UUID.fromString("2f2e6f10-e20d-11e1-9b23-0800200c9a66");
	
	public static final String EXTRA_DEVICE_ADDRESS = "device_address";
	
	public static List<GestureOverlayView> listaGesturePolja = new ArrayList<GestureOverlayView>();
	
	public static final String NEW_GAME_TWO_PHONES = "New game on two phones";
	
	public static final String NEW_GAME_ONE_PHONE = "New game on one phone";
	
	public static final String JOIN_GAME ="Join the game";
	
	public static final String TYPE_OF_START = "Type of start"; 
	
	public static final String ERROR_READING_TO = "error reading TransferObject object";
	
	public static final String ERROR_SENDING_TO = "error sending TransferObject object";
	
	public static BluetoothAdapter getBluetoothAdapter(){
		
	  return	BluetoothAdapter.getDefaultAdapter();
	}
}
