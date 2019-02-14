package android.os;

public class HandlerThread extends Thread {
    Looper mLooper;
    private Handler mHandler;

    public HandlerThread(String name) { super(name); }
    protected void onLooperPrepared() { }

    @Override
    public void run() {
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
            notifyAll();
        }
        onLooperPrepared();
        Looper.loop();
    }

    public Looper getLooper() {
        if (!isAlive()) return null;
        synchronized (this) {
            while (isAlive() && mLooper == null)
                try { wait(); } catch (InterruptedException e) { }
        }
        return mLooper;
    }

    public Handler getThreadHandler() {
        if (mHandler == null) mHandler = new Handler(getLooper());
        return mHandler;
    }

    public boolean quit() {
        Looper looper = getLooper();
        if (looper != null) {
            looper.quit();
            return true;
        }
        return false;
    }
}
