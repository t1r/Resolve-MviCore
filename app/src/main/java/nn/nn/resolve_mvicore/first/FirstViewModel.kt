package nn.nn.resolve_mvicore.first

import nn.nn.resolve_mvicore.first.model.FirstResponseModel


data class FirstViewModel(
    val isLoading: Boolean,
    val responseModel: FirstResponseModel?
)

class FirstViewModelTransformer : (FirstFeature.State) -> FirstViewModel {
    override fun invoke(state: FirstFeature.State): FirstViewModel = FirstViewModel(
        isLoading = state.isLoading,
        responseModel = state.responseModel
    )
}