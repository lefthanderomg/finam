package andrey.murzin.finam.presentation.feature.quotes.screen.list.di.module

import andrey.murzin.finam.di.annotation.ViewModelKey
import andrey.murzin.finam.di.scopes.ScreenScope
import andrey.murzin.finam.presentation.factory.ViewModelFactory
import andrey.murzin.finam.presentation.feature.quotes.screen.list.QuotesListViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class QuotesListModule {

    @Binds
    @IntoMap
    @ScreenScope
    @ViewModelKey(QuotesListViewModel::class)
    internal abstract fun quotesListViewModel(viewModel: QuotesListViewModel): ViewModel

    @Binds
    @ScreenScope
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}