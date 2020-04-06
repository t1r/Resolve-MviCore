package nn.nn.resolve_mvicore.commonfeature

sealed class CommonUiEvent {
    object NextClick : CommonUiEvent()
}

class CommonUiEventToWish : (CommonUiEvent) -> CommonFeature.Wish? {
    override fun invoke(event: CommonUiEvent): CommonFeature.Wish? = when (event) {
        is CommonUiEvent.NextClick -> CommonFeature.Wish.GoNext
    }
}