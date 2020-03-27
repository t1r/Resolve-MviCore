package nn.nn.resolve_mvicore.first

import android.content.Context
import android.os.Bundle
import android.view.View
import com.badoo.mvicore.modelWatcher
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_first.*
import nn.nn.resolve_mvicore.R
import nn.nn.resolve_mvicore.common.BaseObservableDiFragment
import timber.log.Timber
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class FirstFragment
    : BaseObservableDiFragment<FirstUiEvent>(),
    Consumer<FirstViewModel> {

    override val layoutRes: Int = R.layout.fragment_first
    override val modules: Array<Module> = arrayOf(module {
        bind<FirstFragment>().toInstance { this@FirstFragment }
    })
    private val bindings: FirstBindings by inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bindings.setup(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next.setOnClickListener { onNext(FirstUiEvent.NextClick) }
    }

    override fun accept(viewModel: FirstViewModel) {
        watcher.invoke(viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("FirstFragment: onDestroy $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("FirstFragment: onDestroyView $this")
    }

    private val watcher = modelWatcher<FirstViewModel> {
        watch(FirstViewModel::responseModel) { model ->

        }
    }

    init {
        Timber.d("FirstFragment: $this")
    }
}