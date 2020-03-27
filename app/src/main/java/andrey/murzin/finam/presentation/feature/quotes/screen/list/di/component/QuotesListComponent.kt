package andrey.murzin.finam.presentation.feature.quotes.screen.list.di.component

import andrey.murzin.finam.di.providers.QuotesFlowProvider
import andrey.murzin.finam.di.scopes.ScreenScope
import andrey.murzin.finam.presentation.feature.quotes.screen.list.QuotesListFragment
import andrey.murzin.finam.presentation.feature.quotes.screen.list.di.module.QuotesListModule
import dagger.Component

@Component(
    dependencies = [QuotesFlowProvider::class],
    modules = [QuotesListModule::class]
)
@ScreenScope
interface QuotesListComponent {

    @Component.Factory
    interface Factory {
        fun create(provider: QuotesFlowProvider): QuotesListComponent
    }

    fun inject(fragment: QuotesListFragment)

    class Initializer private constructor() {
        companion object {

            fun init(provider: QuotesFlowProvider): QuotesListComponent {

                return DaggerQuotesListComponent.factory()
                    .create(provider)
            }
        }
    }
}