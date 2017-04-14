package uk.co.acleaninstall.essentialtools.util;

final class CommandTools {

    private CommandTools() {throw new Error("Do not need instantiate!");}


    private static class Command {

        private Command() {throw new Error("Do not need instantiate!");}


        private static String cat() {
            return "cat ";
        }


        private static String echo() {
            return "echo > ";
        }

    }
}
