package andrey.murzin.finam.data.delegate

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import com.github.nkzawa.socketio.client.Socket
import io.reactivex.rxjava3.core.Observable
import javax.inject.Singleton

@Singleton
class SocketDelegate(
    private val socket: Socket,
    private val connectivityManager: ConnectivityManager,
    private val networkRequest: NetworkRequest
) {

    init {
        startListenNetwork()
    }

    fun listenConnection(): Observable<Boolean> = Observable.create<Boolean> { subscriber ->
        if (socket.connected().not()) {
            socket.connect()
            socket.on(Socket.EVENT_CONNECT) {
                if (subscriber.isDisposed.not()) {
                    subscriber.onNext(true)
                }
            }
            socket.on(Socket.EVENT_ERROR) {
                if (subscriber.isDisposed.not()) {
                    subscriber.onNext(false)
                }
            }
        } else {
            subscriber.onNext(true)
        }
    }

    fun disconnect() {
        socket.disconnect()
    }

    private fun startListenNetwork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    if (socket.connected().not()) {
                        socket.connect()
                    }
                }

                override fun onLost(network: Network?) {
                    socket.disconnect()
                }
            })
        } else {
            connectivityManager.registerNetworkCallback(
                networkRequest,
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        if (socket.connected().not()) {
                            socket.connect()
                        }
                    }

                    override fun onLost(network: Network?) {
                        socket.disconnect()
                    }
                }
            )
        }
    }
}