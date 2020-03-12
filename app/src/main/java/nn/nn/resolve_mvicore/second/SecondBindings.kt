package nn.nn.resolve_mvicore.second

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using

class SecondBindings constructor(
    lifecycleOwner: SecondFragment,
    private val feature: SecondFeature,
    private val newsListener: SecondNewsListener
) : AndroidBindings<SecondFragment>(lifecycleOwner) {

    override fun setup(view: SecondFragment) {
        binder.bind(view to feature using SecondUiEventToWish())
        binder.bind(feature to view using SecondViewModelTransformer())
        binder.bind(feature.news to newsListener)
    }

    fun dispose() {
        feature.dispose()
    }
}