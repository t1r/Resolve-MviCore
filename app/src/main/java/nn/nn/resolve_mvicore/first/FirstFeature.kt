package nn.nn.resolve_mvicore.first

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import nn.nn.resolve_mvicore.first.model.FirstResponseModel
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
    }

    sealed class Effect {
        object Back : Effect()
        object GoNext : Effect()
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
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.Back -> state
            is Effect.GoNext -> state
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