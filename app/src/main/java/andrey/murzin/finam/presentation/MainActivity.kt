package andrey.murzin.finam.presentation

import andrey.murzin.finam.R
import andrey.murzin.finam.di.AppComponentProvider
import andrey.murzin.finam.di.providers.MainProvider
import andrey.murzin.finam.presentation.di.component.MainActivityComponent
import andrey.murzin.finam.presentation.factory.MainFragmentFactory
import andrey.murzin.finam.presentation.feature.quotes.QuotesFlowFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), MainComponentProvider {

    @Inject
    lateinit var mainFragmentFactory: MainFragmentFactory

    private val mainComponent by lazy {
        MainActivityComponent
            .Initializer
            .init((application as AppComponentProvider).getAppComponent())

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent.inject(this)
        supportFragmentManager.fragmentFactory = mainFragmentFactory
        super.onCreate(savedInstanceState)
        savedInstanceState ?: coldStart()
    }


    private fun coldStart() {
        with(supportFragmentManager) {
            beginTransaction()
                .replace(
                    R.id.container,
                    fragmentFactory.instantiate(classLoader, QuotesFlowFragment::class.java.name)
                )
                .commitNow()
        }
    }

    override fun getMainComponent(): MainProvider = mainComponent
}
