package nn.nn.resolve_mvicore.commonfeature

import io.reactivex.functions.Consumer
import nn.nn.resolve_mvicore.di.ActivityNavigation
import nn.nn.resolve_mvicore.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CommonNewsListener @Inject constructor(
    @ActivityNavigation private val mainRouter: Router
) : Consumer<CommonFeature.News> {
    override fun accept(news: CommonFeature.News?) {
        when (news) {
            is CommonFeature.News.GoNext -> mainRouter.navigateTo(Screens.Third)
        }
    }
}