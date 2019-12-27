package din.don.musicapp.audioDbApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient{

    @GET("search.php")
    fun getArtistInfo(
        @Query("s") s: String
    ): Call<ArtistResponse>

    @GET("track-top10.php")
    fun getTopTen(
        @Query("s") s: String
    ): Call<TrackResponse>

    @GET("searchalbum.php")
    fun getAlbum(
        @Query("s") s: String
    ): Call<AlbumResponse>


    @GET("mvid.php")
    fun getMv(
        @Query("i") i: Int
    ): Call<MvResponse>
}