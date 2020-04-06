package nn.nn.resolve_mvicore.first

sealed class FirstUiEvent {
    object BackClick : FirstUiEvent()
    object NextClick : FirstUiEvent()
    object Init : FirstUiEvent()
    object MainNextClick : FirstUiEvent()
}

class FirstUiEventToWish : (FirstUiEvent) -> FirstFeature.Wish? {
    override fun invoke(event: FirstUiEvent): FirstFeature.Wish? = when (event) {
        is FirstUiEvent.BackClick -> FirstFeature.Wish.Back
        is FirstUiEvent.NextClick -> FirstFeature.Wish.GoNext
        is FirstUiEvent.Init -> FirstFeature.Wish.Init
        is FirstUiEvent.MainNextClick -> FirstFeature.Wish.GoNextByCommon
    }
}