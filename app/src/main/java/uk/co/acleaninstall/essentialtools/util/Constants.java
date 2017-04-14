package uk.co.acleaninstall.essentialtools.util;

import android.os.Environment;

public class Constants {

    public static class FileConstants {

        static final String WHATSAPP_DIR = Environment.getDataDirectory().getPath() +
            "/data/com.whatsapp/shared_prefs";
        public static final String WHATSAPP_SHARED_PREFS_FILE = WHATSAPP_DIR + "/" +
            "com.whatsapp_preferences.xml";
    }


    public static class ShellConstants {

        public static class ACPI {

            public static final String TITLE = "ACPI";
            public static final String CONTENT
                = "Show status of power sources and thermal devices.";
            public static final String COMMAND = "acpi ";
            public static final String ARG_A = "-a ";
            public static final String ARG_B = "-b ";
            public static final String ARG_C = "-t ";
            public static final String ARG_D = "-c ";
            public static final String ARG_E = "-V ";

        }


        public static class Free {

            public static final String TITLE = "free";
            public static final String CONTENT
                = "Display amount of free and used memory in the system.";
            public static final String COMMAND = "free ";
            public static final String ARG_A = "-b ";
            public static final String ARG_B = "-k ";
            public static final String ARG_C = "-m ";
            public static final String ARG_D = "-g ";
            public static final String ARG_E = "-h ";

        }


        public static class GetProp {

            public static final String TITLE = "getprop";
            public static final String CONTENT
                = "Gets an Android system property (value) or lists them all.";
            public static final String COMMAND = "getprop ";

        }


        public static class Which {

            public static final String TITLE = "Which";
            public static final String CONTENT
                = "Here we will run the which command and return the value, you can choose the options also for the command.";
            public static final String COMMAND = "which ";
            public static final String ARG_A = "-a ";

        }

    }


    public static class RootConstants {

        public static final String[] listAllCommandsAsRoot = { "su", "-c", "ls /system/bin" };

        public static final String[] knownRootAppsPackages = {
            "com.noshufou.android.su",
            "com.noshufou.android.su.elite",
            "eu.chainfire.supersu",
            "com.koushikdutta.superuser",
            "com.thirdparty.superuser",
            "com.yellowes.su",

        };

        public static final String[] knownDangerousAppsPackages = {
            "com.koushikdutta.rommanager",
            "com.dimonvideo.luckypatcher",
            "com.chelpus.lackypatch",
            "com.ramdroid.appquarantine"
        };

        public static final String[] knownRootCloakingPackages = {
            "com.devadvance.rootcloak",
            "de.robv.android.xposed.installer",
            "com.saurik.substrate",
            "com.devadvance.rootcloakplus",
            "com.zachspong.temprootremovejb",
            "com.amphoras.hidemyroot",
            "com.formyhm.hideroot"
        };

        public static final String[] suPaths = {
            "/data/local/",
            "/data/local/bin/",
            "/data/local/xbin/",
            "/sbin/",
            "/system/bin/",
            "/system/bin/.ext/",
            "/system/bin/failsafe/",
            "/system/sd/xbin/",
            "/system/usr/we-need-root/",
            "/system/xbin/"
        };

        public static final String[] pathsThatShouldNotBeWrtiable = {
            "/system",
            "/system/bin",
            "/system/sbin",
            "/system/xbin",
            "/vendor/bin",
            //"/sys",
            "/sbin",
            "/etc",
            //"/proc",
            //"/dev"
        };

    }
}
