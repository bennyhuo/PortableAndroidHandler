package android.os

import android.os.utils.*

class BaseTestHandler(looper: Looper = Looper.myLooper()) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        Logger.debug(msg)
    }
}
