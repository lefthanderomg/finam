package andrey.murzin.finam

import andrey.murzin.finam.di.AppComponentProvider
import andrey.murzin.finam.di.component.AppComponent
import andrey.murzin.finam.di.providers.AppProvider
import android.app.Application

class App : Application(), AppComponentProvider {

    private val appComponent: AppComponent by lazy {
        AppComponent.Initializer.init(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }


    override fun getAppComponent(): AppProvider = appComponent
}