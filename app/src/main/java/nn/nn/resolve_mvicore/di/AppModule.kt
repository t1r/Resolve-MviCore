package nn.nn.resolve_mvicore.di

import android.content.Context
import nn.nn.resolve_mvicore.commonfeature.CommonFeature
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module
import toothpick.ktp.binding.bind

class AppModule(context: Context) : Module() {
    init {
        /* Global */
        bind<Context>().toInstance { context }

        /* Navigation */
        val activityNavCicerone = Cicerone.create()
        bind<Router>().withName(ActivityNavigation::class).toInstance(activityNavCicerone.router)
        bind<NavigatorHolder>().withName(ActivityNavigation::class)
            .toInstance(activityNavCicerone.navigatorHolder)

        val fragmentNavCicerone = Cicerone.create()
        bind<Router>().withName(FragmentNavigation::class).toInstance(fragmentNavCicerone.router)
        bind<NavigatorHolder>().withName(FragmentNavigation::class)
            .toInstance(fragmentNavCicerone.navigatorHolder)

        bind<CommonFeature>().singleton()
    }
}