package andrey.murzin.finam.data.model


import com.google.gson.annotations.SerializedName

data class QuoteInfoModel(
    @SerializedName("c")
    val c: String,
    @SerializedName("pcp")
    val pcp: Double?,
    @SerializedName("ltr")
    val ltr: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ltp")
    val ltp: Double?,
    @SerializedName("chg")
    val chg: Double?
)