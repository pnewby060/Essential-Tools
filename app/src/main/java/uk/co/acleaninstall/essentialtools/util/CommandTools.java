package uk.co.acleaninstall.essentialtools.util;

final class CommandTools {

    private CommandTools() {throw new Error("Do not need instantiate!");}


    public static class Command {

        private Command() {throw new Error("Do not need instantiate!");}


        public static String cat() {
            return "cat ";
        }


        public static String echo() {
            return "echo > ";
        }

    }
}
