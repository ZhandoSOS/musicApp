package din.don.musicapp.audioDbApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArtistLoader(
    private val onSuccess:(ArtistResponse) -> Unit,
    private val onError:(Throwable) -> Unit
) {


    fun getArtistInfo(name: String) {
        ApiFactory.getApiCient()
            .getArtistInfo(name).enqueue(object : Callback<ArtistResponse> {
                override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                    onSuccess(response.body()!!)
                }
            }
            )
    }
}