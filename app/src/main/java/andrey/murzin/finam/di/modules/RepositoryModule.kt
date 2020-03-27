package andrey.murzin.finam.di.modules

import andrey.murzin.finam.data.repository.QuotesRepositoryImpl
import andrey.murzin.finam.domain.repository.QuotesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideQuotesRepository(impl: QuotesRepositoryImpl): QuotesRepository
}