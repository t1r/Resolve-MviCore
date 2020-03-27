package nn.nn.resolve_mvicore.second

import android.content.Context
import android.os.Bundle
import android.view.View
import com.badoo.mvicore.modelWatcher
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_second.*
import nn.nn.resolve_mvicore.R
import nn.nn.resolve_mvicore.common.BaseObservableDiFragment
import timber.log.Timber
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class SecondFragment
    : BaseObservableDiFragment<SecondUiEvent>(),
    Consumer<SecondViewModel> {

    override val layoutRes: Int = R.layout.fragment_second
    override val modules: Array<Module> = arrayOf(module {
        bind<SecondFragment>().toInstance { this@SecondFragment }
    })
    private val bindings: SecondBindings by inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bindings.setup(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back.setOnClickListener { onNext(SecondUiEvent.BackClick) }
    }

    override fun accept(viewModel: SecondViewModel) {
        watcher.invoke(viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        bindings.dispose()
        Timber.d("SecondFragment: onDestroy $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("SecondFragment: onDestroyView $this")
    }

    private val watcher = modelWatcher<SecondViewModel> {
        watch(SecondViewModel::resendRemainingSeconds) { seconds ->
            timer?.text = seconds.toString()
        }
    }

    init {
        Timber.d("SecondFragment: $this")
    }
}