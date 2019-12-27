package din.don.musicapp.audioDbApi

import com.google.gson.annotations.SerializedName

data class Mv (
    @SerializedName("idArtist")
    val id : Int,
    @SerializedName("strTrack")
    val trackName : String,
    @SerializedName("strMusicVid")
    val mvPath : String,
    @SerializedName("strTrackThumb")
    val thumbnailPath: String
)
data class MvResponse (
    @SerializedName("mvids")
    val mvids : List<Mv>
)