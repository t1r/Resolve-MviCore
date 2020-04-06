package nn.nn.resolve_mvicore.third

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_third.*
import nn.nn.resolve_mvicore.R
import nn.nn.resolve_mvicore.common.BaseDiFragment
import nn.nn.resolve_mvicore.di.ActivityNavigation
import ru.terrakok.cicerone.Router
import toothpick.config.Module
import toothpick.ktp.delegate.inject

class ThirdFragment : BaseDiFragment() {
    override val layoutRes: Int = R.layout.fragment_third
    override val modules: Array<Module> = arrayOf()
    private val mainRouter: Router by inject(ActivityNavigation::class)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        back.setOnClickListener { mainRouter.exit() }
    }
}