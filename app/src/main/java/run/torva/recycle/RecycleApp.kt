package run.torva.recycle

import android.app.Application
import timber.log.Timber

class RecycleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO
        }
    }
}