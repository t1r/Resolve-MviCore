package nn.nn.resolve_mvicore

import android.os.Bundle
import nn.nn.resolve_mvicore.common.BaseObservableActivity
import nn.nn.resolve_mvicore.commonfeature.CommonBindings
import nn.nn.resolve_mvicore.commonfeature.CommonUiEvent
import nn.nn.resolve_mvicore.di.DI
import nn.nn.resolve_mvicore.navigation.AppLauncher
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class MainActivity : BaseObservableActivity<CommonUiEvent>() {

    private val appLauncher: AppLauncher by inject()
    override val navigator: Navigator = SupportAppNavigator(this, R.id.main_container)
    private val bindings: CommonBindings by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindings.setup(this)


        if (savedInstanceState == null) {
            appLauncher.coldStart()
        }
    }

    override fun openScope() {
        KTP.openScope(DI.APP_SCOPE)
            .installModules(module {
                bind<MainActivity>().toInstance { this@MainActivity }
            })
            .inject(this)
    }

    fun goToNext() {
        onNext(CommonUiEvent.NextClick)
    }
}