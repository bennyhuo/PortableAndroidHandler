package android.os;

public class Handler {
    private MessageQueue messageQueue;

    public Handler() {
        this(Looper.myLooper());
    }

    public Handler(Looper looper) {
        this.messageQueue = looper.getQueue();
    }

    public final void sendMessage(Message msg){
        sendMessageDelayed(msg, 0L);
    }

    public final void sendMessageDelayed(Message msg, long delay){
        msg.target = this;
        delay = Math.max(delay, 0L);
        msg.when = System.currentTimeMillis() + delay;
        messageQueue.enqueueMessage(msg);
    }

    public final void post(Runnable r){
        postDelayed(r, 0L);
    }

    public final void postDelayed(Runnable r, long delay){
        Message msg = new Message();
        msg.callback = r;
        sendMessageDelayed(msg, delay);
    }

    public void handleMessage(Message msg){

    }

    public final void dispatchMessage(Message msg){
        handleMessage(msg);
    }
}