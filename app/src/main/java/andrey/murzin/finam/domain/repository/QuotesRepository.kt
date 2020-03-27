package andrey.murzin.finam.domain.repository

import andrey.murzin.finam.domain.entity.QuoteInfoEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface QuotesRepository {
    fun startListenQuoteUpdate(): Observable<List<QuoteInfoEntity>>
    fun getQuote(): Single<List<QuoteInfoEntity>>
}