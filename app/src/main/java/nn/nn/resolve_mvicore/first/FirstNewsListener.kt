package nn.nn.resolve_mvicore.first

import io.reactivex.functions.Consumer
import nn.nn.resolve_mvicore.di.FragmentNavigation
import nn.nn.resolve_mvicore.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FirstNewsListener @Inject constructor(
    private val view: FirstFragment,
    @FragmentNavigation private val router: Router
) : Consumer<FirstFeature.News> {
    override fun accept(news: FirstFeature.News?) {
        when (news) {
            is FirstFeature.News.Back -> router.exit()
            is FirstFeature.News.GoNext -> router.navigateTo(Screens.Second)
        }
    }
}