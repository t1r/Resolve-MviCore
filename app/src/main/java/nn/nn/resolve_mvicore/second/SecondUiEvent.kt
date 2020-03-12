package nn.nn.resolve_mvicore.second

sealed class SecondUiEvent {
    object BackClick : SecondUiEvent()
}

class SecondUiEventToWish : (SecondUiEvent) -> SecondFeature.Wish? {
    override fun invoke(event: SecondUiEvent): SecondFeature.Wish? = when (event) {
        is SecondUiEvent.BackClick -> SecondFeature.Wish.Back
    }
}