package andrey.murzin.finam.data.delegate

import andrey.murzin.finam.data.model.QuoteInfoResultModel
import com.google.gson.Gson
import org.json.JSONObject

class ParseDelegate constructor(
    private val gson: Gson
) {

    fun execute(data: Array<Any>): QuoteInfoResultModel? =
        (data.firstOrNull() as? JSONObject)?.let {
            gson.fromJson(it.toString(), QuoteInfoResultModel::class.java)
        }
}