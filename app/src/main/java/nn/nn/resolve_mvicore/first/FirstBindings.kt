package nn.nn.resolve_mvicore.first

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import javax.inject.Inject

class FirstBindings @Inject constructor(
    lifecycleOwner: FirstFragment,
    private val feature: FirstFeature,
    private val newsListener: FirstNewsListener
) : AndroidBindings<FirstFragment>(lifecycleOwner) {

    override fun setup(view: FirstFragment) {
        binder.bind(view to feature using FirstUiEventToWish())
        binder.bind(feature to view using FirstViewModelTransformer())
        binder.bind(feature.news to newsListener)
    }
}