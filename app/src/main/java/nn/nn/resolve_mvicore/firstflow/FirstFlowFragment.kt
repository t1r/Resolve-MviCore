package nn.nn.resolve_mvicore.firstflow

import nn.nn.resolve_mvicore.common.FlowFragment
import nn.nn.resolve_mvicore.navigation.FlowNavResolver
import nn.nn.resolve_mvicore.navigation.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen
import toothpick.config.Module
import toothpick.ktp.delegate.inject

class FirstFlowFragment : FlowFragment() {

    private val flowNavResolver: FlowNavResolver by inject()
    override val modules: Array<Module> = emptyArray()

    override fun getLaunchScreen(): SupportAppScreen = Screens.First

    override fun onBackPressed(): Boolean {
        flowNavResolver.onBackPressed(childFragmentManager.backStackEntryCount == 0)
        return true
    }
}