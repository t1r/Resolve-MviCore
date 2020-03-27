package nn.nn.resolve_mvicore.common

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.lifecycle.StartStopBinderLifecycle
import com.badoo.mvicore.binder.Binder

abstract class StartStopBindings<T : Any>(
    lifecycleOwner: LifecycleOwner
) {
    val binder = Binder(
        lifecycle = StartStopBinderLifecycle(
            androidLifecycle = lifecycleOwner.lifecycle
        )
    )

    abstract fun setup(view: T)
}