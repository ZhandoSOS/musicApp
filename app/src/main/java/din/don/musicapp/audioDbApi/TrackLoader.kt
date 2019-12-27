package din.don.musicapp.audioDbApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TrackLoader (
    private val onSuccess:(TrackResponse) -> Unit,
    private val onError:(Throwable) -> Unit
){

    fun getTopTenTracks(name: String){
        ApiFactory.getApiCient()
            .getTopTen(name).enqueue(object : Callback<TrackResponse> {
                override fun onFailure(call: Call<TrackResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<TrackResponse>, response: Response<TrackResponse>) {
                    onSuccess(response.body()!!)
                }


            }
            )
    }


}