package android.os;

import android.os.utils.Logger;

public class Handler {
    private MessageQueue mQueue;

    public Handler() {
        this(Looper.myLooper());
    }

    public Handler(Looper looper) {
        this.mQueue = looper.getQueue();
    }

    public final void sendMessage(Message msg){
        sendMessageDelayed(msg, 0L);
    }

    public final void sendMessageDelayed(Message msg, long delay){
        msg.target = this;
        delay = Math.max(delay, 0L);
        msg.when = System.currentTimeMillis() + delay;
        Logger.debug("sendMessage: %s", msg);
        mQueue.enqueueMessage(msg);
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

    /**
     * Remove any pending posts of messages with code 'what' that are in the
     * message queue.
     */
    public final void removeMessages(int what) {
        mQueue.removeMessages(this, what, null);
    }

    /**
     * Remove any pending posts of messages with code 'what' and whose obj is
     * 'object' that are in the message queue.  If <var>object</var> is null,
     * all messages will be removed.
     */
    public final void removeMessages(int what, Object object) {
        mQueue.removeMessages(this, what, object);
    }

    /**
     * Remove any pending posts of callbacks and sent messages whose
     * <var>obj</var> is <var>token</var>.  If <var>token</var> is null,
     * all callbacks and messages will be removed.
     */
    public final void removeCallbacksAndMessages(Object token) {
        mQueue.removeCallbacksAndMessages(this, token);
    }
}