package andrey.murzin.finam.presentation.feature.quotes.screen.list

import andrey.murzin.finam.domain.entity.QuoteInfoEntity
import andrey.murzin.finam.domain.repository.QuotesRepository
import andrey.murzin.finam.mapper.toUiModel
import andrey.murzin.finam.presentation.model.QuoteInfoUiModel
import andrey.murzin.finam.utils.addQuotesList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class QuotesListViewModel @Inject constructor(
    private val repository: QuotesRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
        getQuotes()
    }

    private val cacheQuotes = mutableMapOf<String, QuoteInfoUiModel>()

    val quotesLiveData = MutableLiveData<List<QuoteInfoUiModel>>()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    fun startListenUpdate() {
        if (cacheQuotes.isNotEmpty()) {
            repository.startListenQuoteUpdate()
                .subscribeOn(Schedulers.io())
                .compose(getListenQuotesTransformer())
                .subscribe({

                }, {
                }).let(compositeDisposable::add)
        }
    }

    fun stopListenUpdate() {
        compositeDisposable.clear()
    }

    private fun getQuotes() {
        repository.getQuote()
            .map { it.toUiModel() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                cacheQuotes.addQuotesList(it)
                quotesLiveData.postValue(cacheQuotes.values.toList())
            }.observeOn(Schedulers.io())
            .toObservable().flatMap {
                repository.startListenQuoteUpdate()
                    .compose(getListenQuotesTransformer())
            }
            .subscribe({}, {}).let(compositeDisposable::add)
    }

    private fun getListenQuotesTransformer() =
        ObservableTransformer<List<QuoteInfoEntity>, List<QuoteInfoUiModel>> {
            it.map { items -> items.toUiModel() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { items -> cacheQuotes.addQuotesList(items) }
                .doOnNext {
                    quotesLiveData.postValue(cacheQuotes.values.toList())
                }
        }
}

