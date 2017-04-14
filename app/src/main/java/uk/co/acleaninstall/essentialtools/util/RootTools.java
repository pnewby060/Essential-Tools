package uk.co.acleaninstall.essentialtools.util;

import at.amartinz.execution.RootCheck;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RootTools {

    // Taken from https://github.com/scottyab/rootbeer/blob/master/rootbeerlib/src/main/java/com/scottyab/rootbeer/RootBeer.java
    public static boolean checkSuExists() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return in.readLine() != null;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }


    public static boolean isRooted() {
        boolean rooted;
        rooted = RootCheck.isRootGranted(true);
        return rooted;
    }
}
