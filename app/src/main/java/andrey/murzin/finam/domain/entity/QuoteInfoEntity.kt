package andrey.murzin.finam.domain.entity


data class QuoteInfoEntity(
    val c: String,
    val pcp: Double?,
    val ltr: String?,
    val name: String?,
    val ltp: Double?,
    val chg: Double?
)