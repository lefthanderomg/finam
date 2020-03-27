package andrey.murzin.finam.presentation.feature.quotes.screen.list

import andrey.murzin.finam.R
import andrey.murzin.finam.di.providers.QuotesFlowProvider
import andrey.murzin.finam.presentation.feature.quotes.screen.list.di.component.QuotesListComponent
import andrey.murzin.finam.presentation.model.QuoteInfoUiModel
import andrey.murzin.finam.utils.injectViewModel
import andrey.murzin.finam.utils.observe
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_quotes.*
import javax.inject.Inject

class QuotesListFragment(
    private val provider: QuotesFlowProvider
) : Fragment(R.layout.fragment_quotes) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: QuotesListViewModel

    private val adapterQuotes = QuotesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        createComponent()
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory) {
            observe(quotesLiveData, ::showQuotes)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(rvQuotes) {
            val currentLayoutManager = LinearLayoutManager(context)
            layoutManager = currentLayoutManager
            adapter = adapterQuotes
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    currentLayoutManager.orientation
                )
            )
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.startListenUpdate()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopListenUpdate()
    }

    private fun showQuotes(data: List<QuoteInfoUiModel>?) {
        data?.let {
            adapterQuotes.addData(it)
        }
    }

    private fun createComponent() {
        QuotesListComponent.Initializer
            .init(provider)
            .inject(this)
    }
}