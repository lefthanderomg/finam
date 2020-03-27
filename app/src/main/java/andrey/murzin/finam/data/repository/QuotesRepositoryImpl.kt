package andrey.murzin.finam.data.repository

import andrey.murzin.finam.data.delegate.ParseDelegate
import andrey.murzin.finam.data.delegate.QuoteParamDelegate
import andrey.murzin.finam.data.delegate.SocketDelegate
import andrey.murzin.finam.data.model.QuoteInfoResultModel
import andrey.murzin.finam.data.network.TradernetApi
import andrey.murzin.finam.domain.entity.QuoteInfoEntity
import andrey.murzin.finam.domain.repository.QuotesRepository
import andrey.murzin.finam.mapper.toEntity
import com.github.nkzawa.socketio.client.Socket
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class QuotesRepositoryImpl @Inject constructor(
    private val socket: Socket,
    private val parseDelegate: ParseDelegate,
    private val quoteParamDelegate: QuoteParamDelegate,
    private val tradernetApi: TradernetApi,
    private val socketDelegate: SocketDelegate
) : QuotesRepository {

    companion object {
        private const val QUOTES_PARAM = "q"
        private const val QUOTES_UPDATE_PARAM = "sup_updateSecurities2"
    }

    override fun startListenQuoteUpdate(): Observable<List<QuoteInfoEntity>> =
        socketDelegate.listenConnection()
            .flatMap {
                startListenQuoteUpdateSocket()
            }.map { it.result }
            .map { it.toEntity() }
            .doOnDispose {
                socketDelegate.disconnect()
                socket.off(QUOTES_PARAM)
            }.retry()

    override fun getQuote(): Single<List<QuoteInfoEntity>> =
        tradernetApi.getQuotes(
            tickers = quoteParamDelegate.tickersParams,
            params = quoteParamDelegate.dataParam
        ).map {
            it.toEntity()
        }

    private fun startListenQuoteUpdateSocket(): Observable<QuoteInfoResultModel> =
        Observable.create<QuoteInfoResultModel> { subscriber ->
            socket.on(QUOTES_PARAM) {
                if (subscriber.isDisposed.not()) {
                    subscriber.onNext(parseDelegate.execute(it))
                }
            }
            if (socket.connected()) {
                socket.emit(QUOTES_UPDATE_PARAM, quoteParamDelegate.dataJson)
            } else {
               subscriber.onError(Throwable())
            }
        }
}