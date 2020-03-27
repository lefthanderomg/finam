package andrey.murzin.finam.data.network

import andrey.murzin.finam.data.model.QuoteInfoModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TradernetApi {

    @GET("securities/export")
    fun getQuotes(
        @Query("tickers", encoded = true) tickers: String,
        @Query("params", encoded = true) params: String
    ): Single<List<QuoteInfoModel>>
}