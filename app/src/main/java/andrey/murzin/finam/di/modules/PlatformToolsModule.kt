package andrey.murzin.finam.di.modules

import andrey.murzin.finam.di.AppComponentProvider
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlatformToolsModule {

    @Provides
    @Singleton
    fun provideContext(app: AppComponentProvider): Context =
        app.getApplicationContext()

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources =
        context.resources

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context) =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}