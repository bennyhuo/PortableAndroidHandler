package android.os

import android.os.utils.*
import org.junit.*

class HandlerTest {
    private lateinit var looper: Looper

    @Test
    fun test() {
        Looper.prepare()
        looper = Looper.myLooper()

        val workingThread = Thread(Runnable {
            testRemove()
            looper.quitSafely()
        }, "Working-Thread")
        workingThread.start()

        Looper.loop()
    }

    fun testRemove() {
        val token = Any()
        val r = Runnable {
            Logger.debug("Runnable called!")
        }
        val handler = BaseTestHandler(looper)

        val msg = Message().also {
            it.obj = token
            it.who = "msg"
        }

        val msg1= Message().also {
            it.what = 1
            it.who = "msg1"
        }

        val msg2 = Message().also {
            it.callback = r
            it.who = "msg2"
        }

        val msg3 = Message().also {
            it.callback = r
            it.obj = token
            it.who = "msg3"
        }

        val msg4= Message().also {
            it.what = 1
            it.obj = token
            it.who = "msg4"
        }

        handler.sendMessage(msg)
        handler.sendMessage(msg1)
        handler.sendMessage(msg2)
        handler.sendMessageDelayed(msg3, 10)
        handler.sendMessageDelayed(msg4, 100)
        //handler.removeMessages(1)
        //handler.removeCallbacksAndMessages(token)
    }
}
