package midterm_exam;

public class Q06_CommandValidator {
    public static void main(String[] args) {
        String[] commands = {
            "START",
            new String("stop"),
            "  Pause  ",
            "RUN",
            "   ",
            null
        };

        for (String command : commands) {
            System.out.println(command + " -> " + isValidCommand(command));
        }
    }

    public static boolean isValidCommand(String command) {
        if (command == null) {
            return false;
        }

        String normalized = command.trim();

        return normalized.equalsIgnoreCase("START")
            || normalized.equalsIgnoreCase("STOP")
            || normalized.equalsIgnoreCase("PAUSE");
    }
}
