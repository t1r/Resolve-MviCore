package nn.nn.resolve_mvicore

import android.app.Application
import nn.nn.resolve_mvicore.di.AppModule
import nn.nn.resolve_mvicore.di.DI
import timber.log.Timber
import toothpick.Scope
import toothpick.configuration.Configuration
import toothpick.ktp.KTP

class App : Application() {

    lateinit var scope: Scope

    override fun onCreate() {
        super.onCreate()
        initToothpick()
        initAppScope()
        setupDebugTools()
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            KTP.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            KTP.setConfiguration(Configuration.forProduction())
        }
    }

    private fun initAppScope() {
        scope = KTP.openScope(DI.APP_SCOPE)
            .installModules(AppModule(this@App))
    }

    private fun setupDebugTools() {
        Timber.plant(Timber.DebugTree())
    }
}