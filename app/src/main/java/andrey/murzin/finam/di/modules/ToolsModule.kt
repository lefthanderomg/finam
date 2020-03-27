package andrey.murzin.finam.di.modules

import andrey.murzin.finam.data.delegate.ParseDelegate
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class ToolsModule {

    @Provides
    fun provideParseDelegate(gson: Gson) = ParseDelegate(gson)
}