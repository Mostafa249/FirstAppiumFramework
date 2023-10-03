package Utiliti;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;

import java.io.File;

public class Logs {

    // Initialize Log4j logs
    private static Logger log = LogManager.getLogger(Logs.class);


    public static void info(String message) {
        LoggerContext context = LogManager.getContext(false);
        File file = new File(System.getProperty("user.dir")+"/src/resources/log4j2.xml");
        ((org.apache.logging.log4j.core.LoggerContext) context).setConfigLocation(file.toURI());

        log.info(message);

    }

    public static void warn(String message) {

        log.warn(message);

    }

    public static void error(String message) {

        Logs.error(message);

    }

    public static void fatal(String message) {

        log.fatal(message);

    }

    public static void debug(String message) {

        log.debug(message);

    }
}
