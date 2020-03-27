package andrey.murzin.finam.presentation.feature.quotes.di.module

import andrey.murzin.finam.di.scopes.FlowScope
import andrey.murzin.finam.presentation.factory.QuotesFragmentFactory
import andrey.murzin.finam.presentation.feature.quotes.di.component.QuotesFlowComponent
import dagger.Module
import dagger.Provides

@Module
class QuotesFlowModule {

    @FlowScope
    @Provides
    fun providerQuotesFlowFactory(component: QuotesFlowComponent) = QuotesFragmentFactory(component)
}