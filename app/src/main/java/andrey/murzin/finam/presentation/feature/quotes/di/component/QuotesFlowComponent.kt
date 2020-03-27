package andrey.murzin.finam.presentation.feature.quotes.di.component

import andrey.murzin.finam.di.providers.MainProvider
import andrey.murzin.finam.di.providers.QuotesFlowProvider
import andrey.murzin.finam.di.scopes.FlowScope
import andrey.murzin.finam.presentation.feature.quotes.QuotesFlowFragment
import andrey.murzin.finam.presentation.feature.quotes.di.module.QuotesFlowModule
import dagger.Component

@Component(
    dependencies = [MainProvider::class],
    modules = [QuotesFlowModule::class]
)
@FlowScope
interface QuotesFlowComponent : QuotesFlowProvider {

    @Component.Factory
    interface Factory {
        fun create(provider: MainProvider): QuotesFlowComponent
    }

    fun inject(fragment: QuotesFlowFragment)

    class Initializer private constructor() {
        companion object {

            fun init(provider: MainProvider): QuotesFlowComponent {

                return DaggerQuotesFlowComponent.factory()
                    .create(provider)
            }
        }
    }
}