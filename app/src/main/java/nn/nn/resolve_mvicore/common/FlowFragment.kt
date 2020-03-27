package nn.nn.resolve_mvicore.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import nn.nn.resolve_mvicore.R
import nn.nn.resolve_mvicore.di.FragmentNavigation
import nn.nn.resolve_mvicore.ext.setLaunchScreen
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import toothpick.ktp.delegate.inject

abstract class FlowFragment : BaseDiFragment(), BackPressHandler {

    override val layoutRes: Int = R.layout.fragment_flow
    private val navigatorHolder: NavigatorHolder by inject(FragmentNavigation::class)

    protected val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseDiFragment

    protected open val navigator: Navigator? by lazy {
        object : SupportAppNavigator(
            activity!!,
            childFragmentManager,
            R.id.container
        ) {
            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    abstract fun getLaunchScreen(): SupportAppScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator?.setLaunchScreen(getLaunchScreen())
        }
    }

    override fun onResume() {
        super.onResume()
        navigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}