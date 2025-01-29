package enefit_backend_assignment.helpers;

import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggingEventBuilder;

public abstract class Logger {
    public static LoggingEventBuilder infoLogger() {
        return LoggerFactory
                .getLogger(Thread.currentThread().getStackTrace()[2].getClassName())
                .atInfo();
    }

    public static LoggingEventBuilder errorLogger() {
        return LoggerFactory
                .getLogger(Thread.currentThread().getStackTrace()[2].getClassName())
                .atInfo();
    }
}
