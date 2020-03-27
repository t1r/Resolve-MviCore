package nn.nn.resolve_mvicore.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nn.nn.resolve_mvicore.di.ActivityNavigation
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import toothpick.ktp.delegate.inject

abstract class BaseActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by inject(ActivityNavigation::class)
    abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        openScope()
        super.onCreate(savedInstanceState)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    abstract fun openScope()
}