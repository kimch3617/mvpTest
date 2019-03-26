package com.example.mvptest.ui.rx

import android.content.Intent
import android.os.Bundle

internal sealed class ActivityLifecycle {
    class OnCreate(val intent: Intent, val saveInstanceState: Bundle?) : ActivityLifecycle()

    class OnStart : ActivityLifecycle()

    class OnResume : ActivityLifecycle()

    class OnPause : ActivityLifecycle()

    class OnStop : ActivityLifecycle()

    class OnDestroy : ActivityLifecycle()

    class OnActivityResult(val requestCode: Int, val resultCode: Int, val data: Intent?) : ActivityLifecycle()
}

internal fun ActivityLifecycle.OnCreate.isFirstCreate(): Boolean {
    return this.saveInstanceState == null
}
