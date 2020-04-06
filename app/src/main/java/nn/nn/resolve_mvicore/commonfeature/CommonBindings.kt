package nn.nn.resolve_mvicore.commonfeature

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import nn.nn.resolve_mvicore.MainActivity
import javax.inject.Inject

class CommonBindings @Inject constructor(
    lifecycleOwner: MainActivity,
    private val feature: CommonFeature,
    private val newsListener: CommonNewsListener
) : AndroidBindings<MainActivity>(lifecycleOwner) {

    override fun setup(view: MainActivity) {
        binder.bind(feature.news to newsListener)
        binder.bind(view to feature using CommonUiEventToWish())
    }
}