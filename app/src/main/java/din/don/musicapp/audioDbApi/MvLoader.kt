package din.don.musicapp.audioDbApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MvLoader(
    private val onSuccess:(MvResponse) -> Unit,
    private val onError:(Throwable) -> Unit
) {


    fun getArtistInfo(id: Int) {
        ApiFactory.getApiCient()
            .getMv(id).enqueue(object : Callback<MvResponse> {
                override fun onFailure(call: Call<MvResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<MvResponse>, response: Response<MvResponse>) {
                    onSuccess(response.body()!!)
                }
            }
            )
    }
}