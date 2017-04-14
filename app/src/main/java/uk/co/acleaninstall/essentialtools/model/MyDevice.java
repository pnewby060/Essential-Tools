package uk.co.acleaninstall.essentialtools.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import java.lang.reflect.Method;

@SuppressLint("NewApi")
public class MyDevice {

    @SuppressLint("HardwareIds")
    public static String ANDROID_ID(@NonNull Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static final String BOARD_NAME =
        Build.BOARD;
    public static final String BOOTLOADER_VERSION =
        Build.BOOTLOADER;
    public static final String BRAND_NAME =
        Build.BRAND;
    public static final String DESIGN_NAME =
        Build.DEVICE;
    public static final String MODEL_NAME =
        Build.MODEL;
    public static final String MANUFACTURER_NAME =
        Build.MANUFACTURER;


    /**
     * allows user to read build.prop files for properties and access the hidden
     * android.os.SystemProperties
     * class using reflection.
     */
    public static class SystemProperties {

        public static String get(Context context, String key) throws IllegalArgumentException {

            String ret = "";

            try {

                ClassLoader cl = context.getClassLoader();
                @SuppressWarnings("rawtypes")
                Class SystemProperties = cl.loadClass("android.os.SystemProperties");

                //Parameters Types
                @SuppressWarnings("rawtypes")
                Class[] paramTypes = new Class[1];
                paramTypes[0] = String.class;

                Method get = SystemProperties.getMethod("get", paramTypes);

                //Parameters
                Object[] params = new Object[1];
                params[0] = new String(key);

                ret = (String) get.invoke(SystemProperties, params);

            } catch (IllegalArgumentException iAE) {
                throw iAE;
            } catch (Exception e) {
                ret = "";
                //TODO
            }

            return ret;

        }

    }

}
