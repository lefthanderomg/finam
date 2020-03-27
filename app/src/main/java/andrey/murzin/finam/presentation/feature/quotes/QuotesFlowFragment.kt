package andrey.murzin.finam.presentation.feature.quotes

import andrey.murzin.finam.R
import andrey.murzin.finam.di.providers.MainProvider
import andrey.murzin.finam.presentation.factory.QuotesFragmentFactory
import andrey.murzin.finam.presentation.feature.quotes.di.component.QuotesFlowComponent
import andrey.murzin.finam.presentation.feature.quotes.screen.list.QuotesListFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import javax.inject.Inject

class QuotesFlowFragment(mainProvider: MainProvider) : Fragment(R.layout.fragment_flow_quotes) {

    @Inject
    lateinit var fragmentQuotesFactory: QuotesFragmentFactory

    private val quotesFlowComponent by lazy {
        QuotesFlowComponent.Initializer.init(mainProvider)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        quotesFlowComponent.inject(this)
        childFragmentManager.fragmentFactory = fragmentQuotesFactory
        super.onCreate(savedInstanceState)
        savedInstanceState ?: startFlow()
    }

    private fun startFlow() {
        with(childFragmentManager) {
            beginTransaction()
                .replace(
                    R.id.container,
                    fragmentFactory.instantiate(
                        activity!!.classLoader,
                        QuotesListFragment::class.java.name
                    )
                )
                .commitNow()
        }
    }
}