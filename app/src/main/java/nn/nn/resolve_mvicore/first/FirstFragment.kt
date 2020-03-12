package nn.nn.resolve_mvicore.first

import android.content.Context
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_first.*
import nn.nn.resolve_mvicore.R
import nn.nn.resolve_mvicore.common.BaseDiFragment
import nn.nn.resolve_mvicore.second.SecondFragment
import timber.log.Timber
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class FirstFragment : BaseDiFragment() {

    override val layoutRes: Int = R.layout.fragment_first
    override val modules: Array<Module> = arrayOf(module {
        bind<FirstFragment>().toInstance(this@FirstFragment)
    })
    private val firstFeature: FirstFeature by inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        firstFeature.doSomething(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next.setOnClickListener {
            goToSecond()
        }
    }

    private fun goToSecond() {
        val frag = SecondFragment()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, frag)
            ?.addToBackStack("SECOND")
            ?.commit()
    }

    init {
        Timber.d("FirstFragment: $this")
    }
}