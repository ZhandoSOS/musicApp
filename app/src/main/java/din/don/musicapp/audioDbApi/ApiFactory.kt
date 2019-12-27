package din.don.musicapp.audioDbApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val baseUrl = "https://theaudiodb.com/api/v1/json/1/"

    fun getRerofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiCient() =
        getRerofit().create(ApiClient::class.java)
}