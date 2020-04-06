package nn.nn.resolve_mvicore.common

import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class BaseObservableActivity<T>
    : BaseActivity(), ObservableSource<T> {

    private val source = PublishSubject.create<T>()
    var viewDisposables: CompositeDisposable = CompositeDisposable()

    protected fun onNext(t: T) {
        source.onNext(t)
    }

    override fun subscribe(observer: Observer<in T>) {
        source.subscribe(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!viewDisposables.isDisposed) {
            viewDisposables.dispose()
            viewDisposables = CompositeDisposable()
        }
    }
}