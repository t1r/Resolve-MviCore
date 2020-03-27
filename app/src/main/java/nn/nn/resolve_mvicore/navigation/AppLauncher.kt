package nn.nn.resolve_mvicore.navigation

import nn.nn.resolve_mvicore.di.ActivityNavigation
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppLauncher @Inject constructor(
    @ActivityNavigation private val mainRouter: Router
) {

    fun coldStart() {
        mainRouter.newRootScreen(Screens.FirstFlow)
    }
}