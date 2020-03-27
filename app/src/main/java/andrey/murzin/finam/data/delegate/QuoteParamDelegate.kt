package andrey.murzin.finam.data.delegate

import org.json.JSONArray
import javax.inject.Inject

class QuoteParamDelegate @Inject constructor() {

    private val defaultTickers = arrayListOf(
        "RSTI",
        "GAZP",
        "MRKZ",
        "RUAL",
        "HYDR",
        "MRKS",
        "SBER",
        "FEES",
        "TGKA",
        "VTBR",
        "ANH.US",
        "VICL.US",
        "BURG.US",
        "NBL.US",
        "YETI.US",
        "WSFS.US",
        "NIO.US",
        "DXC.US",
        "MIC.US",
        "HSBC.US",
        "EXPN.EU",
        "GSK.EU",
        "SHP.EU",
        "MAN.EU",
        "DB1.EU",
        "MUV2.EU",
        "TATE.EU",
        "KGF.EU",
        "MGGT.EU",
        "SGGD.EU"
    )

    val dataJson by lazy {
        val json = JSONArray()
        defaultTickers.forEach {
            json.put(it)
        }
        json
    }

    val tickersParams by lazy {
        defaultTickers.joinToString("+") { it }
    }

    val dataParam = "c+pcp+ltr+name+ltp"
}