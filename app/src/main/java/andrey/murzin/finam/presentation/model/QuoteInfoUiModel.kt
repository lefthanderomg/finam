package andrey.murzin.finam.presentation.model

import andrey.murzin.finam.utils.toNumberWithSymbol


data class QuoteInfoUiModel(
    val c: String,
    val pcp: ParamItem?,
    val ltr: String?,
    val name: String?,
    val ltp: Double?,
    val chg: Double?
) : ComparableItem {
    override fun id(): Any = c

    override fun content(): Any = this

}

fun QuoteInfoUiModel.getPriceInfo(): String {
    val result = StringBuilder()

    ltp?.let(result::append)


    chg?.let {
        result.append("(${it.toNumberWithSymbol()})")
    }

    return result.toString()
}

fun QuoteInfoUiModel.getInfo(): String {
    val result = StringBuilder()

    ltr?.let(result::append)

    if (result.isNotEmpty() && name?.isNotEmpty() == true) {
        result.append(" | ")
    }

    name?.let(result::append)

    return result.toString()
}