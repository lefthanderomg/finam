package andrey.murzin.finam.di.modules

import andrey.murzin.finam.data.delegate.SocketDelegate
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SocketModule {

    companion object {
        private const val BASE_URL = "https://ws2.tradernet.ru/"
    }

    @Singleton
    @Provides
    fun provideSocketIO(): Socket = IO.socket(BASE_URL)

    @Singleton
    @Provides
    fun provideOptions() = IO.Options().apply {
        forceNew = true
    }

    @Singleton
    @Provides
    fun provideSocketDelegate(
        connectivityManager: ConnectivityManager,
        socket: Socket,
        networkRequest: NetworkRequest
    ) =
        SocketDelegate(
            socket = socket,
            connectivityManager = connectivityManager,
            networkRequest = networkRequest
        )
}