package uk.co.acleaninstall.essentialtools;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import at.amartinz.hardware.device.Device;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.nillith.android.common.MiscUtils;

public class MyApp extends Application {

    private static MyApp sInstance;


    public static Context getContext() {
        return sInstance;
    }


    public MyApp() {
        sInstance = this;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(IconicsContextWrapper.wrap(base));
    }


    @Override
    public void onCreate() {
        super.onCreate();
        MiscUtils.init(getApplicationContext());
    }


    /**
     * @return - This Devices props
     */
    public static Device getDevice() {
        return Device.get(getContext());
    }


    /**
     * @return - The TelephonyManager Class
     */
    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getContext().getSystemService(TELEPHONY_SERVICE);
    }


    /**
     * @return - The PowerManager Class
     */
    public static PowerManager getPowerManager() {
        return (PowerManager) getContext().getSystemService(POWER_SERVICE);
    }


    /**
     * @return - The ActivityManager Class
     */
    public static ActivityManager getActivityManager() {
        return (ActivityManager) getContext().getSystemService(ACTIVITY_SERVICE);
    }


    /**
     * @return - The InputManager Class
     */
    public static InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
    }
}
