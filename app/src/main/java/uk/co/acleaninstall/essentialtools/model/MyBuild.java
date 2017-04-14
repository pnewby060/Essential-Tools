package uk.co.acleaninstall.essentialtools.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("NewApi")
public class MyBuild {

    public static final String DISPLAY_NAME =
        Build.DISPLAY;
    public static final String FINGERPRINT =
        Build.FINGERPRINT;
    public static final String HARDWARE =
        Build.HARDWARE;
    public static final String HOST =
        Build.HOST;
    public static final String FIRMWARE =
        Build.ID;
    public static final String PRODUCT_NAME =
        Build.PRODUCT;
    public static final String RADIO_VERSION =
        Build.getRadioVersion();
    public static final String SERIAL =
        Build.SERIAL;
    public static final String BUILD_TAGS =
        Build.TAGS;
    public static final String TIME =
        new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
            .format(new Date(Build.TIME));
    public static final String BUILD_TYPE =
        Build.TYPE;
    public static final String USER =
        Build.USER;


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
