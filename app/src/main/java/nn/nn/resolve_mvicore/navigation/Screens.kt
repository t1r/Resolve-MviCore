package nn.nn.resolve_mvicore.navigation

import nn.nn.resolve_mvicore.first.FirstFragment
import nn.nn.resolve_mvicore.second.SecondFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object First : SupportAppScreen() {
        override fun getFragment() = FirstFragment()
    }

    object Second : SupportAppScreen() {
        override fun getFragment() = SecondFragment()
    }

//    object Third : SupportAppScreen() {
//        override fun getFragment() = ThirdFragment()
//    }
}