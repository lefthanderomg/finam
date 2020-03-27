package andrey.murzin.finam.di.modules

import andrey.murzin.finam.data.network.TradernetApi
import android.net.NetworkRequest
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://tradernet.ru/"
        private const val TIME_OUT = 30L
        private const val REQUEST_TAG = "Request"
        private const val RESPONSE_TAG = "Response"
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().serializeNulls().create()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): LoggingInterceptor =
        LoggingInterceptor
            .Builder()
            .loggable(true)
            .setLevel(Level.BODY)
            .log(Log.INFO)
            .request(REQUEST_TAG)
            .response(RESPONSE_TAG)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        logging: LoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            addInterceptor(logging)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    fun provideCmsGateApi(httpClient: OkHttpClient): TradernetApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(httpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(TradernetApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkRequest(): NetworkRequest = NetworkRequest.Builder().build()
}