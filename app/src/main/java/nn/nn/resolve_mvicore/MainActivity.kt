package nn.nn.resolve_mvicore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nn.nn.resolve_mvicore.di.DI
import nn.nn.resolve_mvicore.first.FirstFragment
import toothpick.ktp.KTP

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        openScope()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FirstFragment(), "FirstTag")
            .commit()
    }

    fun openScope() {
        KTP.openScope(DI.APP_SCOPE)
            .inject(this)
    }
}
