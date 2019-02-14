package android.os;

import android.os.internal.DelayQueue;
import android.os.utils.Logger;

import java.util.function.Predicate;

public class MessageQueue {
    private static final Message POISON = new Message();

    private volatile boolean isQuited = false;

    private DelayQueue<Message> queue = new DelayQueue<>();

    public void enqueueMessage(Message msg) {
        queue.add(msg);
    }

    public Message next() {
        try {
            Message next = queue.take();
            if (next == POISON) {
                return null;
            }
            return next;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    void removeMessages(Handler h, int what, Object object) {
        if (h == null) {
            return;
        }
        queue.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return message.target == h && message.what == what && (object == null || message.obj == object);
            }
        });
    }

    void removeMessages(Handler h, Runnable r, Object object) {
        if (h == null || r == null) {
            return;
        }
        queue.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return message.target == h && message.callback == r && (object == null || message.obj == object);
            }
        });
    }

    void removeCallbacksAndMessages(Handler h, Object object) {
        if (h == null) {
            return;
        }

        queue.removeIf(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return message.target == h && (object == null || message.obj == object);
            }
        });
    }

    void quit(boolean safe) {
        if (isQuited) return;
        isQuited = true;
        Logger.debug("Quit, messages in queue: " + queue.size());
        if (!safe) {
            queue.clear();
        }
        // Tell looper to quit.
        POISON.when = System.currentTimeMillis();
        POISON.who = "KILLER";
        Logger.debug("Feed poison: %s", POISON);
        queue.offer(POISON);
    }
}