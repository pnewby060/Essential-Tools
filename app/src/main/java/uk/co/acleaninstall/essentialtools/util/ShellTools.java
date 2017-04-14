package uk.co.acleaninstall.essentialtools.util;

import com.jrummyapps.android.shell.CommandResult;
import com.jrummyapps.android.shell.Shell;
import java.util.ArrayList;
import java.util.List;

import static com.jrummyapps.android.shell.Shell.SH;

public class ShellTools {

    private ShellTools() {
    }


    public static List<String> getResultAsList(String... commands) {
        List<String> resultsList = new ArrayList<>();

        CommandResult result = Shell.SU.run(commands);

        if (result.isSuccessful()) {
            resultsList = result.stdout;
        }

        if (!result.isSuccessful()) {
            resultsList = result.stderr;
        }

        return resultsList;
    }


    /**
     * runCommand(String command)
     *
     * @param command - The command line string to run
     * @return - Either the stdout or the stderr
     */
    public static String runCommand(Boolean asRoot, String... command) {

        // return value
        String resultToPrint;

        // the result will be run with SU or normal shell
        CommandResult result = null;

        // set the result to the type of shell we are using
        if (asRoot) {
            result = Shell.SU.run(command);
        } else {
            result = SH.run(command);
        }

        if (result.isSuccessful()) {
            // set as commands result
            resultToPrint = result.getStdout();
        } else
        // set as commands error
        {
            resultToPrint = result.getStderr();
        }

        // close the console on root
        if (asRoot) {
            Shell.SU.closeConsole();
        }

        // close the console on standard shell
        if (!asRoot) {
            Shell.SH.closeConsole();
        }

        return resultToPrint;
    }
}
