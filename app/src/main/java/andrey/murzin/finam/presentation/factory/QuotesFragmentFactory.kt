package andrey.murzin.finam.presentation.factory

import andrey.murzin.finam.di.providers.QuotesFlowProvider
import andrey.murzin.finam.presentation.feature.quotes.screen.list.QuotesListFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class QuotesFragmentFactory(
    private val provider: QuotesFlowProvider
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            QuotesListFragment::class.java.name -> QuotesListFragment(
                provider
            )
            else -> super.instantiate(classLoader, className)
        }
    }
}