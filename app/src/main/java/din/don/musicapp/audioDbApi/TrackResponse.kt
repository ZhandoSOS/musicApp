package din.don.musicapp.audioDbApi

import com.google.gson.annotations.SerializedName

data class Track (
    @SerializedName("strTrack")
    val trackName: String,
    @SerializedName("strAlbum")
    val albumName: String,
    @SerializedName("intDuration")
    val duration: Int?,
    @SerializedName("strGenre")
    val genre: String?,
    @SerializedName("strMood")
    val mood: String?,
    @SerializedName("strTrackThumb")
    val thumbnailPath: String?

)

data class TrackResponse (
    @SerializedName("track")
    val track : List<Track>
)