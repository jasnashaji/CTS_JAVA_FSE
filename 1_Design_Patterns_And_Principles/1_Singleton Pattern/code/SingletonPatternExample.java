// File: SingletonPatternExample.java

// Logger class - Singleton implementation
class Logger {
    // Step 1: Private static instance of Logger
    private static Logger instance;

    // Step 2: Private constructor to prevent instantiation
    private Logger() {
        System.out.println("Logger instance created.");
    }

    // Step 3: Public static method to get the single instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger(); // Only created once
        }
        return instance;
    }

    // A sample method for logging
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

// Test class
public class SingletonPatternExample {
    public static void main(String[] args) {
        // Step 4: Test the singleton pattern

        Logger logger1 = Logger.getInstance();
        logger1.log("First log message");

        Logger logger2 = Logger.getInstance();
        logger2.log("Second log message");

        // Check if both logger1 and logger2 point to the same instance
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 are the same instance.");
        } else {
            System.out.println("Different instances exist! Singleton pattern failed.");
        }
    }
}
