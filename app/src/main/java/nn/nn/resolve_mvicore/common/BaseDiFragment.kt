package nn.nn.resolve_mvicore.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import nn.nn.resolve_mvicore.di.DI
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.smoothie.lifecycle.closeOnDestroy

abstract class BaseDiFragment : Fragment() {

    @get:LayoutRes
    abstract val layoutRes: Int
    protected lateinit var scope: Scope
    protected open var scopeIdentifier: String? = null
    abstract val modules: Array<Module>

    override fun onAttach(context: Context) {
        (context as? AppCompatActivity)?.let {
            scope = KTP.openScopes(DI.APP_SCOPE)
                .openSubScope(it)
                .openSubScope(scopeIdentifier ?: this)
                .installModules(*modules)
                .closeOnDestroy(this)
            scope.inject(this)
        }
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }
}