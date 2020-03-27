package andrey.murzin.finam.presentation.di.component

import andrey.murzin.finam.di.providers.AppProvider
import andrey.murzin.finam.di.providers.MainProvider
import andrey.murzin.finam.di.scopes.ActivityScope
import andrey.murzin.finam.presentation.MainActivity
import andrey.murzin.finam.presentation.di.modules.MainModule
import dagger.Component

@Component(
    dependencies = [AppProvider::class],
    modules = [MainModule::class]
)
@ActivityScope
interface MainActivityComponent : MainProvider {

    @Component.Factory
    interface Factory {
        fun create(appProvider: AppProvider): MainActivityComponent
    }

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): MainActivityComponent {

                return DaggerMainActivityComponent
                    .factory()
                    .create(appProvider)
            }
        }
    }
}