package nn.nn.resolve_mvicore.second

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SecondFeature : BaseFeature<
        SecondFeature.Wish,
        SecondFeature.Action,
        SecondFeature.Effect,
        SecondFeature.State,
        SecondFeature.News
        >(
    initialState = State(),
    actor = ActorImpl(),
    wishToAction = { Action.Execute(it) },
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl(),
    bootstrapper = BootstrapperImpl()
) {

    data class State(
        val resendRemainingSeconds: Long = 100L
    )

    sealed class Action {
        data class Execute(val wish: Wish) : Action()
        object Tick : Action()
    }

    sealed class Wish {
        object Back : Wish()
    }

    sealed class Effect {
        object Back : Effect()
        data class Tick(val resendRemainingSeconds: Long) : Effect()
    }

    sealed class News {
        object Back : News()
    }

    class ActorImpl : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<out Effect> = when (action) {
            is Action.Execute -> when (action.wish) {
                is Wish.Back -> Observable.just(Effect.Back)
            }
            is Action.Tick -> resolveCountdown(state)
        }

        private fun resolveCountdown(state: State): Observable<out Effect> {
            val seconds = state.resendRemainingSeconds
            //Timber.d("resolveCountdown: $seconds $this ")
            return when {
                seconds > 0L -> {
                    Observable.just(
                        Effect.Tick(
                            resendRemainingSeconds = seconds - 1L
                        )
                    )
                }
                seconds == 0L -> {
                    Observable.just(
                        Effect.Tick(
                            resendRemainingSeconds = seconds - 1L
                        )
                    )
                }
                else -> Observable.empty()
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.Back -> state
            is Effect.Tick -> state.copy(resendRemainingSeconds = effect.resendRemainingSeconds)
        }
    }

    class NewsPublisherImpl
        : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News? = when (effect) {
            is Effect.Back -> News.Back
            else -> null
        }
    }

    class BootstrapperImpl : Bootstrapper<Action> {

        private val tickEmitter: Observable<Long> =
            Observable.timer(1000, TimeUnit.MILLISECONDS).repeat()

        override fun invoke(): Observable<Action> {
            return tickEmitter.map<Action> { Action.Tick }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}