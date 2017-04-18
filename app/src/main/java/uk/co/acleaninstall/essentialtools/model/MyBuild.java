package uk.co.acleaninstall.essentialtools.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import uk.co.acleaninstall.essentialtools.MyApp;

@SuppressLint("NewApi")
public class MyBuild {

    /**
     * some build info from my device to refer to:
     *
     * [ro.build.characteristics]: [nosdcard]
     * [ro.build.date]: [Wed Feb 15 17:51:31 IST 2017]
     * [ro.build.date.utc]: [1487161291]
     * [ro.build.description]: [xenonhd_mako-userdebug 7.1.1 NMF26V d559d5a815 test-keys]
     * [ro.build.display.id]: [xenonhd_mako-userdebug 7.1.1 NMF26V d559d5a815 test-keys]
     * [ro.build.fingerprint]: [google/xenonhd_mako/mako:7.1.1/NMF26V/d559d5a815:userdebug/test-keys]
     * [ro.build.flavor]: [xenonhd_mako-userdebug]
     * [ro.build.host]: [Supernova]
     * [ro.build.id]: [NMF26V]
     * [ro.build.product]: [mako]
     * [ro.build.selinux]: [1]
     * [ro.build.tags]: [test-keys]
     * [ro.build.type]: [userdebug]
     * [ro.build.user]: [jenkins]
     * [ro.build.version.all_codenames]: [REL]
     * [ro.build.version.base_os]: []
     * [ro.build.version.codename]: [REL]
     * [ro.build.version.incremental]: [d559d5a815]
     * [ro.build.version.preview_sdk]: [0]
     * [ro.build.version.release]: [7.1.1]
     * [ro.build.version.sdk]: [25]
     * [ro.build.version.security_patch]: [2017-01-05]
     */

    public static final String BUILD_CHARACTERISTICS =
        SystemProperties.get(MyApp.getContext(), "ro.build.characteristics");
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
    public static final String RADIO_VERSION =
        Build.getRadioVersion();
    public static final String SERIAL =
        Build.SERIAL;
    public static final String BUILD_TAGS =
        Build.TAGS;
    public static final String TIME =
        new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"", Locale.ENGLISH)
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
