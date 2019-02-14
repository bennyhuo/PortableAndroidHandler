package android.os;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

	Handler target;
	public int what;
	public int arg1;
	public int arg2;
	public Object obj;

	long when;

	Runnable callback;

	@Override
	public String toString() {
		return "Message{" +
				"what=" + what +
				", obj=" + obj +
				", when=" + dateFormat.format(new Date(when)) +
				'}';
	}

	@Override
	public long getDelay(@NotNull TimeUnit unit) {
		return unit.convert(when - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(@NotNull Delayed o) {
		if(o instanceof Message){
			return (int) (when - ((Message) o).when);
		} else {
			return (int) (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
		}
	}
}