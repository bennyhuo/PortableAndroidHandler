package android.os;

import java.util.concurrent.DelayQueue;

public class MessageQueue {

    private DelayQueue<Message> queue = new DelayQueue<>();

    public void enqueueMessage(Message msg) {
        queue.add(msg);
    }

    public Message next() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void quit() {
        queue.clear();
    }
}