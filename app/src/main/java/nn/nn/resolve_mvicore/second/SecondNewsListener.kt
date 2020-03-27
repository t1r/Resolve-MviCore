package nn.nn.resolve_mvicore.second

import io.reactivex.functions.Consumer
import nn.nn.resolve_mvicore.di.FragmentNavigation
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SecondNewsListener @Inject constructor(
    private val view: SecondFragment,
    @FragmentNavigation private val router: Router
) : Consumer<SecondFeature.News> {
    override fun accept(news: SecondFeature.News?) {
        when (news) {
            is SecondFeature.News.Back -> router.exit()
        }
    }
}