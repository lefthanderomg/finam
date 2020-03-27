package andrey.murzin.finam.utils

import andrey.murzin.finam.presentation.model.QuoteInfoUiModel
import andrey.murzin.finam.presentation.model.SateParamType
import andrey.murzin.finam.presentation.model.getNewState
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlin.collections.List
import kotlin.collections.MutableMap
import kotlin.collections.forEach
import kotlin.collections.set

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val viewModel = ViewModelProvider(this, factory)[T::class.java]
    viewModel.body()
    return viewModel
}

fun <T, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun MutableMap<String, QuoteInfoUiModel>.addQuotesList(data: List<QuoteInfoUiModel>) {
    data.forEach {
        val currentItem = this[it.c]
        if (currentItem == null) {
            this[it.c] = it
        } else {
            this[it.c] = currentItem.copy(
                c = it.c,
                pcp = if (it.pcp?.data == null) currentItem.pcp?.copy(
                    state = SateParamType.INIT
                ) else {
                    it.pcp.getNewState(currentItem.pcp)
                },
                ltr = it.ltr ?: currentItem.ltr,
                name = it.name ?: currentItem.name,
                ltp = it.ltp ?: currentItem.ltp,
                chg = it.chg ?: currentItem.chg
            )
        }
    }
}

fun Double.toNumberWithSymbol(): String = "${if (this > 0) "+" else ""}$this"