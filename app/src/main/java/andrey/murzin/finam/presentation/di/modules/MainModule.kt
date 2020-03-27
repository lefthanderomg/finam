package andrey.murzin.finam.presentation.di.modules

import andrey.murzin.finam.di.scopes.ActivityScope
import andrey.murzin.finam.presentation.di.component.MainActivityComponent
import andrey.murzin.finam.presentation.factory.MainFragmentFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideMainFactory(component: MainActivityComponent): MainFragmentFactory =
        MainFragmentFactory(component)
}