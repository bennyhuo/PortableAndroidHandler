package android.os;

import android.os.internal.Delayed;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class Message implements Delayed {

    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    Handler target;
    public int what;
    public int arg1;
    public int arg2;
    public Object obj;

    long when;

    /**
     * for TEST
     */
    String who;

    Runnable callback;

    @Override
    public String toString() {
        return "Message[" + who + "]{" +
                "what=" + what +
                ", obj=" + obj +
                ", when=" + dateFormat.format(new Date(when)) +
                '}';
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(when - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof Message) {
            return (int) (when - ((Message) o).when);
        } else {
            return (int) (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}