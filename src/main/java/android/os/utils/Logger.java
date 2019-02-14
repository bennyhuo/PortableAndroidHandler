package android.os.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final boolean DEBUG;

    static {
        boolean isDebug = false;
        try {
            isDebug = Logger.class.getClassLoader().getResource("logger/debug") != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        DEBUG = isDebug;
    }

    public static void debug(Object msg, Object ... args){
        if(DEBUG){
            StackTraceElement ste = new Throwable().getStackTrace()[1];
            StringBuilder sb = new StringBuilder();
            sb.append(SIMPLE_DATE_FORMAT.format(new Date())).append(" [").append(Thread.currentThread().getName()).append("] ")
                    .append(build(String.format(String.valueOf(msg), args),ste));
            System.out.println(sb);
        }
    }

    private static String build(String log, StackTraceElement ste) {
        StringBuilder buf = new StringBuilder();
        if (ste.isNativeMethod()) {
            buf.append("(Native Method)");
        } else {
            String fName = ste.getFileName();

            if (fName == null) {
                buf.append("(Unknown Source)");
            } else {
                int lineNum = ste.getLineNumber();
                buf.append('(');
                buf.append(fName);
                if (lineNum >= 0) {
                    buf.append(':');
                    buf.append(lineNum);
                }
                buf.append("):");
            }
        }
        buf.append(log);
        return buf.toString();
    }

    public static void main(String... args) {
        debug("Output nothing.");
    }
}
