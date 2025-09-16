/*
 Client ---send a request---> | Handle | (handler have different handlers like 1,2,3)
      two cases can be done 
      a> ny one of them can either send response or send to next handler
      b> every one can participate like slve req partially
*/

// Logger Question
// this way will application call Object.log("type","message")
// Abstract Logger class
public abstract class Logger {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;

    protected Logger nextLogger;

    public Logger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int logLevel, String msg) {
        if (nextLogger != null) {
            nextLogger.log(logLevel, msg);
        }
    }
}

// Info Logger
class InfoLog extends Logger {
    public InfoLog(Logger nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(int logLevel, String msg) {
        if (logLevel == INFO) {
            System.out.println("INFO: " + msg);
        } else {
            super.log(logLevel, msg);
        }
    }
}

// Debug Logger
class DebugLog extends Logger {
    public DebugLog(Logger nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(int logLevel, String msg) {
        if (logLevel == DEBUG) {
            System.out.println("DEBUG: " + msg);
        } else {
            super.log(logLevel, msg);
        }
    }
}

// Error Logger
class ErrorLog extends Logger {
    public ErrorLog(Logger nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(int logLevel, String msg) {
        if (logLevel == ERROR) {
            System.out.println("ERROR: " + msg);
        } else {
            super.log(logLevel, msg);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Logger loggerChain = new InfoLog(new DebugLog(new ErrorLog(null)));

        loggerChain.log(Logger.ERROR, "This is an error message.");
        loggerChain.log(Logger.INFO, "This is an info message.");
        loggerChain.log(Logger.DEBUG, "This is a debug message.");
    }
}

//lets understand workflow for Error
// call the info first not matched so called super(parents log) -> Parent will call debug log as its next and the circle will go this way
