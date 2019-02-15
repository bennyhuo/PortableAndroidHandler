package android.os;

public final class Looper {

    private static final ThreadLocal<Looper> THREAD_LOCAL = new ThreadLocal<Looper>();

    private MessageQueue messageQueue;

    public static void prepare() {
        if (THREAD_LOCAL.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        THREAD_LOCAL.set(new Looper());
    }

    public static Looper myLooper() {
        return THREAD_LOCAL.get();
    }

    private Looper() {
        messageQueue = new MessageQueue();
    }

    public MessageQueue getQueue() {
        return messageQueue;
    }

    public static void loop() {
        Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No looper");
        }
        MessageQueue queue = me.messageQueue;
        while(true) {
            Message msg = queue.next();
            if (msg == null) {
                return;
            }
            msg.target.dispatchMessage(msg);
            msg.recycleUnchecked();
        }
    }

    public void quit(){
        messageQueue.quit(false);
    }

    public void quitSafely() {
        messageQueue.quit(true);
    }
}