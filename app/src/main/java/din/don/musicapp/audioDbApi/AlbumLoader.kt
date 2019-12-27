package din.don.musicapp.audioDbApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumLoader (
    private val onSuccess:(AlbumResponse) -> Unit,
    private val onError:(Throwable) -> Unit
){
    fun getAlbumsByActorName(name: String){
        ApiFactory.getApiCient()
            .getAlbum(name).enqueue(object : Callback<AlbumResponse> {
                override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {
                    onSuccess(response.body()!!)
                }
            }

            )
    }




}