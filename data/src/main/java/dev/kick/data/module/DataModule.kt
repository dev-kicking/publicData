package dev.kick.data.module

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.kick.data.datasource.AppDatabase
import dev.kick.data.service.OpenDataService
import dev.kick.data.util.RetryInterceptor
import dev.kick.domain.repo.AppApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    private const val PREFERENCE_APP_KEY: String = "nuvilab"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        PREFERENCE_APP_KEY
    ).build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().disableHtmlEscaping().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(
        appApplication: AppApplication
    ): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        if (!message.startsWith("{") && !message.startsWith("[")) {
            Timber.tag("OkHttp").d(message)
        } else {
            try {
                Timber
                    .tag("OkHttp")
                    .d(
                        GsonBuilder()
                            .setPrettyPrinting().create()
                            .toJson(JsonParser.parseString(message))
                    )
            } catch (m: JsonSyntaxException) {
                Timber.tag("OkHttp").d(message)
            }
        }
    }.apply {
        level = if (appApplication.getDebug()) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideRetryInterceptor(): RetryInterceptor {
        return RetryInterceptor(maxRetry = 3, backoffMultiplier = 2L)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor,
        retryInterceptor: RetryInterceptor
    ): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(logger)
        .addInterceptor(retryInterceptor)
        .build()

    @Provides
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.odcloud.kr/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenDataService(
        retrofit: Retrofit
    ): OpenDataService {
        return retrofit.create(OpenDataService::class.java)
    }
}