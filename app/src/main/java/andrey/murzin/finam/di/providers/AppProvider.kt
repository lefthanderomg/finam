package andrey.murzin.finam.di.providers

import andrey.murzin.finam.domain.repository.QuotesRepository
import android.content.Context
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson

interface AppProvider {
    fun provideSocket(): Socket
    fun provideQuotesRepository(): QuotesRepository
    fun provideGson(): Gson
    fun provideContext(): Context
}