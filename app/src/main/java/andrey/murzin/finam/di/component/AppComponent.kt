package andrey.murzin.finam.di.component

import andrey.murzin.finam.App
import andrey.murzin.finam.di.AppComponentProvider
import andrey.murzin.finam.di.modules.*
import andrey.murzin.finam.di.providers.AppProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        SocketModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        ToolsModule::class,
        PlatformToolsModule::class
    ]
)
@Singleton
interface AppComponent : AppProvider {

    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: AppComponentProvider): AppComponent
    }

    class Initializer private constructor() {
        companion object {
            fun init(app: AppComponentProvider): AppComponent {

                return DaggerAppComponent
                    .factory()
                    .create(app)
            }
        }
    }
}