package uk.co.acleaninstall.essentialtools.util;

import android.widget.Toast;
import uk.co.acleaninstall.essentialtools.MyApp;

import static uk.co.acleaninstall.essentialtools.util.CommandTools.Command.cat;
import static uk.co.acleaninstall.essentialtools.util.CommandTools.Command.echo;

public final class SuperTools {

    private static final String TAG = "SuperTools";


    private SuperTools() {
        throw new Error("Do not need instantiate!");
    }


    public static class RootDirectory {

        private RootDirectory() {
            throw new Error("Do not need instantiate!");
        }


        /**
         * Command to list 'everything' including symbolic links inside the root directory.
         *
         * @return - the number of dirs/files/links inside the root directory.
         */
        public static int getAllInRootDir() {
            return Integer.parseInt(ShellTools.runCommand(true, "ls -l | wc -l "));
        }


        public static boolean isEmpty() {
            return getAllInRootDir() == 0;
        }


        /**
         * Command to list 'directories only' inside the root directory.
         *
         * @return - the number of dirs inside the root directory.
         */
        public static int getDirsInRootDir() {
            return Integer.parseInt(ShellTools.runCommand(true, "ls -l | grep ^d | wc -l"));
        }


        public static boolean hasDirectories() {
            return getDirsInRootDir() > 0;
        }


        /**
         * Command to list 'files only' inside the root directory.
         *
         * @return - the number of files inside the root directory.
         */
        public static int getFilesInRootDir() {
            return Integer.parseInt(ShellTools.runCommand(true, "ls -l | grep ^- | wc -l"));
        }


        public static boolean hasFiles() {
            return getFilesInRootDir() > 0;
        }


        /**
         * Command to list 'symbolic links only' inside the root directory.
         *
         * @return - the number of links inside the root directory.
         */
        public static int getSymbolicsInRootDir() {
            return Integer.parseInt(ShellTools.runCommand(true, "ls -l | grep ^l | wc -l"));
        }


        public static boolean hasSymbolics() {
            return getSymbolicsInRootDir() > 0;
        }

    }


    public static class BacklightSysClass {

        private BacklightSysClass() {throw new Error("Do not need instantiate!");}


        private static String BACKLIGHT_CLASS_LOCATION = "/sys/class/backlight/lm3530/";
        private static String BRIGHTNESS_ATTR = "brightness";
        private static String MAX_BRIGHTNESS_ATTR = "max_brightness";


        public static CharSequence getBacklightBrightnessLevel() {
            return ShellTools.runCommand(true,
                cat() + BACKLIGHT_CLASS_LOCATION + BRIGHTNESS_ATTR);
        }


        public static int getMaxBacklightBrightnessLevel() {
            return Integer.valueOf(ShellTools.runCommand(true,
                cat() + BACKLIGHT_CLASS_LOCATION + MAX_BRIGHTNESS_ATTR));
        }


        public static void setBacklightBrightnessLevel(int levelToSet) {
            if (levelToSet > getMaxBacklightBrightnessLevel()) {
                Toast.makeText(MyApp.getContext(),
                    "Cant set level higher than " + getMaxBacklightBrightnessLevel(),
                    Toast.LENGTH_LONG)
                    .show();
            } else if (levelToSet < 0) {
                Toast.makeText(MyApp.getContext(), "Cant set level to a negative value",
                    Toast.LENGTH_SHORT)
                    .show();
            } else {
                ShellTools.runCommand(true,
                    echo() + levelToSet + " " + BACKLIGHT_CLASS_LOCATION + BRIGHTNESS_ATTR);
                Toast.makeText(MyApp.getContext(), "Brightness level set to " + levelToSet,
                    Toast.LENGTH_SHORT)
                    .show();
            }

        }

    }
}
