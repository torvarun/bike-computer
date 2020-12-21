package run.torva.recycle

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer

/**
 * Chronometer that can be paused and resumed.
 *
 * TODO test code
 */
class Stopwatch : Chronometer {
    constructor(context : Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs : AttributeSet, defStyleAttr : Int)
            : super(context, attrs, defStyleAttr)

    var stoppedTime : Long = 0
        set(value) {
            if (value >= 0) {
                field = value
                base = SystemClock.elapsedRealtime() - value
            }
        }

    override fun start() {
        base = SystemClock.elapsedRealtime() - stoppedTime
        super.start()
    }

    override fun stop() {
        super.stop()
        stoppedTime = SystemClock.elapsedRealtime() - base
    }

    fun reset() {
        stop()
        base = SystemClock.elapsedRealtime()
        stoppedTime = 0
    }
}