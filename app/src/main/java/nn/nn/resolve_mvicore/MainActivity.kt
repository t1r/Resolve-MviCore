package nn.nn.resolve_mvicore

import android.os.Bundle
import nn.nn.resolve_mvicore.common.BaseActivity
import nn.nn.resolve_mvicore.di.DI
import nn.nn.resolve_mvicore.navigation.AppLauncher
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class MainActivity : BaseActivity() {

    private val appLauncher: AppLauncher by inject()
    override val navigator: Navigator = SupportAppNavigator(this, R.id.main_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            appLauncher.coldStart()
        }
    }

    override fun openScope() {
        KTP.openScope(DI.APP_SCOPE)
            .inject(this)
    }
}