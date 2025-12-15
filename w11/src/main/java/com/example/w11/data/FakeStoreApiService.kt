package com.example.w11.data

import com.example.w11.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 1. API 명세 (서버에 요청할 주소)
interface FakeStoreApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}

// 2. Retrofit 인스턴스 (실제 통신을 담당하는 기계) - 이 부분이 빠져서 에러가 난 겁니다!
object RetrofitInstance {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val api: FakeStoreApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeStoreApiService::class.java)
    }
}