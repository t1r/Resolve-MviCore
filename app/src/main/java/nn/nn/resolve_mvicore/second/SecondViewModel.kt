package nn.nn.resolve_mvicore.second


data class SecondViewModel(
    val resendRemainingSeconds: Long
)

class SecondViewModelTransformer : (SecondFeature.State) -> SecondViewModel {
    override fun invoke(state: SecondFeature.State): SecondViewModel = SecondViewModel(
        resendRemainingSeconds = state.resendRemainingSeconds
    )
}