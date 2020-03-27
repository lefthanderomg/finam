package andrey.murzin.finam.data.model


import com.google.gson.annotations.SerializedName

data class QuoteInfoResultModel(
    @SerializedName("q")
    val result: List<QuoteInfoModel>
)