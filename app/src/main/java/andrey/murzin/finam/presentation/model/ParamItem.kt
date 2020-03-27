package andrey.murzin.finam.presentation.model

data class ParamItem(
    val data: Double?,
    var state: SateParamType = SateParamType.INIT
) {

}

fun ParamItem.getNewState(oldParamItem: ParamItem?): ParamItem =
    copy(
        state = if (data != null && oldParamItem?.data != null) {
            if (data > oldParamItem.data) {
                SateParamType.RISE
            } else {
                SateParamType.FALL
            }
        } else {
            SateParamType.INIT
        }
    )




