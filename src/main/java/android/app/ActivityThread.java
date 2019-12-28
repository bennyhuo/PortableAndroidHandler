package android.app;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ActivityThread {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg);
        }
    };

    private void attach(){
        Thread otherThread = new Thread() {
            @Override
            public void run() {
                super.run();

                for (int i = 0; i < 5; i++) {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 5; j++) {
                        Message msg = new Message();
                        msg.what = i * 5 + j;
                        msg.obj = "[" + i + ", " + j + "]";
                        handler.sendMessageDelayed(msg, (long) (Math.random() * 10000));
                    }
                }
            }
        };
        otherThread.start();
    }

    public static void main(String... args) {
        Looper.prepareMainLooper();
        ActivityThread activityThread = new ActivityThread();
        activityThread.attach();
        Looper.loop();
    }
}
