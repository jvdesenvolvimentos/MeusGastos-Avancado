package com.jvdesenvolvimentos.meusgastos.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class CurrencyResponse(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)

data class BackupData(
    val total: Double
)

data class BackupResponse(
    val id: Int,
    val total: Double
)

interface CurrencyApiService {
    @GET("latest?base=BRL")
    suspend fun getExchangeRates(): CurrencyResponse

    @POST("https://jsonplaceholder.typicode.com/posts")
    suspend fun sendBackup(@Body backup: BackupData): BackupResponse
}

object CurrencyApi {
    private const val BASE_URL = "https://api.frankfurter.app/"

    val retrofitService: CurrencyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApiService::class.java)
    }
}
