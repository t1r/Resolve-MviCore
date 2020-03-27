package nn.nn.resolve_mvicore.navigation

import nn.nn.resolve_mvicore.di.ActivityNavigation
import nn.nn.resolve_mvicore.di.FragmentNavigation
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FlowNavResolver @Inject constructor(
    @FragmentNavigation private val router: Router,
    @ActivityNavigation private val mainRouter: Router
) {

    fun onBackPressed(isLastChild: Boolean) {
        if (isLastChild) mainRouter.exit()
        else router.exit()
    }

    fun onBackPressed() = router.exit()
}