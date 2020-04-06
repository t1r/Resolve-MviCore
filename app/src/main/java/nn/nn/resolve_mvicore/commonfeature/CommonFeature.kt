package nn.nn.resolve_mvicore.commonfeature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class CommonFeature @Inject constructor() : BaseFeature<
        CommonFeature.Wish,
        CommonFeature.Action,
        CommonFeature.Effect,
        CommonFeature.State,
        CommonFeature.News
        >(
    initialState = State(),
    actor = ActorImpl(),
    wishToAction = { Action.Execute(it) },
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    init {
        Timber.d("Init First Feature")
    }

    data class State(
        val isLoading: Boolean = false
    )

    sealed class Action {
        data class Execute(val wish: Wish) : Action()
    }

    sealed class Wish {
        object GoNext : Wish()
    }

    sealed class Effect {
        object GoNext : Effect()
    }

    sealed class News {
        object GoNext : News()
    }

    class ActorImpl
        : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<out Effect> {
            Timber.d("Actor: $action")
            return when (action) {
                is Action.Execute -> when (action.wish) {
                    is Wish.GoNext -> Observable.just(Effect.GoNext)
                }
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.GoNext -> state
        }
    }

    class NewsPublisherImpl
        : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News? = when (effect) {
            is Effect.GoNext -> News.GoNext
            else -> null
        }
    }
}