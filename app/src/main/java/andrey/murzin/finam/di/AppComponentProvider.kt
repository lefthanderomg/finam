package andrey.murzin.finam.di

import andrey.murzin.finam.di.providers.AppProvider
import android.content.Context

interface AppComponentProvider {
    fun getAppComponent(): AppProvider
    fun getApplicationContext(): Context
}