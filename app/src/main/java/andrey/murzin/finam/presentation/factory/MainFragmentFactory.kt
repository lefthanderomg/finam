package andrey.murzin.finam.presentation.factory

import andrey.murzin.finam.di.providers.MainProvider
import andrey.murzin.finam.presentation.feature.quotes.QuotesFlowFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class MainFragmentFactory(
    private val mainProvider: MainProvider
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            QuotesFlowFragment::class.java.name -> QuotesFlowFragment(mainProvider)
            else -> super.instantiate(classLoader, className)
        }
    }
}