package uk.co.acleaninstall.essentialtools.listener;

import android.Manifest;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CustomCallStateListener extends PhoneStateListener {

    public static final String TAG = "CallListener";


    /**
     * Create a PhoneStateListener for the Phone with the default subscription.
     * This class requires Looper.myLooper() not return null.
     */
    public CustomCallStateListener() {
        super();
    }


    /**
     * Callback invoked when device call state changes.
     *
     * @param state call state
     * @param incomingNumber incoming call phone number. If application does not have
     * {@link Manifest.permission#READ_PHONE_STATE READ_PHONE_STATE} permission, an empty
     * string will be passed as an argument.
     * @see TelephonyManager#CALL_STATE_IDLE
     * @see TelephonyManager#CALL_STATE_RINGING
     * @see TelephonyManager#CALL_STATE_OFFHOOK
     */
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG, "onCallStateChanged: CALL_STATE_IDLE");
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(TAG, "onCallStateChanged: CALL_STATE_RINGING");
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(TAG, "onCallStateChanged: CALL_STATE_OFFHOOK");
                break;
            default:
                Log.i(TAG, "UNKNOWN_STATE: " + state);
                break;
        }

    }


    /**
     * Callback invoked when connection state changes.
     *
     * @see TelephonyManager#DATA_DISCONNECTED
     * @see TelephonyManager#DATA_CONNECTING
     * @see TelephonyManager#DATA_CONNECTED
     * @see TelephonyManager#DATA_SUSPENDED
     */

    @Override
    public void onDataConnectionStateChanged(int state) {
        super.onDataConnectionStateChanged(state);

        switch (state) {
            case TelephonyManager.DATA_DISCONNECTED:
                Log.i(TAG, "onDataStateChanged: MOBILE_DATA_DISCONNECTED");
                break;
            case TelephonyManager.DATA_CONNECTING:
                Log.i(TAG, "onDataStateChanged: MOBILE_DATA_CONNECTING");
                break;
            case TelephonyManager.DATA_CONNECTED:
                Log.i(TAG, "onDataStateChanged: MOBILE_DATA_CONNECTED");
                break;
            case TelephonyManager.DATA_SUSPENDED:
                Log.i(TAG, "onDataStateChanged: MOBILE_DATA_SUSPENDED");
                break;
            default:
                Log.i(TAG, "UNKNOWN_STATE: " + state);
                break;
        }
    }

}
