package nn.nn.resolve_mvicore.second

import io.reactivex.functions.Consumer

class SecondNewsListener constructor(
    private val view: SecondFragment
) : Consumer<SecondFeature.News> {
    override fun accept(news: SecondFeature.News?) {
        when (news) {
            is SecondFeature.News.Back -> {
                view.fragmentManager?.popBackStack()
            }
        }
    }
}