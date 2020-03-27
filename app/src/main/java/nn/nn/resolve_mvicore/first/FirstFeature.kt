package nn.nn.resolve_mvicore.first

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nn.nn.resolve_mvicore.ext.toddMMyyyyHHmmss
import nn.nn.resolve_mvicore.first.model.FirstResponseModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirstFeature @Inject constructor() : BaseFeature<
        FirstFeature.Wish,
        FirstFeature.Action,
        FirstFeature.Effect,
        FirstFeature.State,
        FirstFeature.News
        >(
    initialState = State(),
    actor = ActorImpl(),
    wishToAction = { Action.Execute(it) },
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val isLoading: Boolean = false,
        val responseModel: FirstResponseModel? = null
    )

    sealed class Action {
        data class Execute(val wish: Wish) : Action()
    }

    sealed class Wish {
        object Back : Wish()
        object GoNext : Wish()
        object Init : Wish()
    }

    sealed class Effect {
        object Back : Effect()
        object GoNext : Effect()
        object InitInProgress : Effect()
        data class InitComplete(val response: FirstResponseModel) : Effect()
        data class InitFailure(val error: Throwable) : Effect()
    }

    sealed class News {
        object Back : News()
        object GoNext : News()
    }

    class ActorImpl
        : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<out Effect> = when (action) {
            is Action.Execute -> when (action.wish) {
                is Wish.Back -> Observable.just(Effect.Back)
                is Wish.GoNext -> Observable.just(Effect.GoNext)
                is Wish.Init -> resolveInit(state)
            }
        }

        private fun resolveInit(
            state: State
        ): Observable<out Effect> {
            if (state.responseModel != null) return Observable.empty()
            if (state.isLoading) return Observable.empty()
            return Observable.timer(200, TimeUnit.MILLISECONDS)
                .map {
                    val timeStamp = System.currentTimeMillis().toddMMyyyyHHmmss()
                    return@map Effect.InitComplete(
                        FirstResponseModel(
                            "Text1 $timeStamp",
                            "Text2 $timeStamp"
                        )
                    )
                }
                .cast(Effect::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(Observable.just(Effect.InitInProgress))
                .onErrorReturn { Effect.InitFailure(it) }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.Back -> state
            is Effect.GoNext -> state
            is Effect.InitInProgress -> state.copy(isLoading = true)
            is Effect.InitComplete -> state.copy(isLoading = false, responseModel = effect.response)
            is Effect.InitFailure -> state.copy(isLoading = false)
        }
    }

    class NewsPublisherImpl
        : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News? = when (effect) {
            is Effect.Back -> News.Back
            is Effect.GoNext -> News.GoNext
            else -> null
        }
    }
}