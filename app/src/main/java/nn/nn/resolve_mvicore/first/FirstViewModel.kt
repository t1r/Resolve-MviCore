package nn.nn.resolve_mvicore.first

import nn.nn.resolve_mvicore.first.model.FirstResponseModel


data class FirstViewModel(
    val responseModel: FirstResponseModel?
)

class FirstViewModelTransformer : (FirstFeature.State) -> FirstViewModel {
    override fun invoke(state: FirstFeature.State): FirstViewModel = FirstViewModel(
        responseModel = state.responseModel
    )
}