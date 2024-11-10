package sazhin.onlinebookstoreapp.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sazhin.onlinebookstoreapp.data.api.BookApi

val networkModule = module {
    factory { provideRetrofit() }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://localhost:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()

}

fun provideNetworkApi(retrofit: Retrofit): BookApi =
    retrofit.create(BookApi::class.java)