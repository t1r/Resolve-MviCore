package nn.nn.resolve_mvicore.first

import nn.nn.resolve_mvicore.commonfeature.CommonFeature

class FirstNewsToCommonWish : (FirstFeature.News) -> CommonFeature.Wish? {
    override fun invoke(event: FirstFeature.News): CommonFeature.Wish? = when (event) {
        is FirstFeature.News.GoNextByCommon -> CommonFeature.Wish.GoNext
        else -> null
    }
}