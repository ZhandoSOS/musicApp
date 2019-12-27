package din.don.musicapp.audioDbApi

import com.google.gson.annotations.SerializedName
import java.util.*

data class Artist (
    @SerializedName("idArtist")
    val id : Int,
    @SerializedName("strArtist")
    val artistName : String,
    @SerializedName("intBornYear")
    val bornYear: Int,
    @SerializedName("strGenre")
    val genre : String,
    @SerializedName("strWebsite")
    val website: String,
    @SerializedName("strBiographyEN")
    val biography: String,
    @SerializedName("strArtistLogo")
    val logoPath : String,
    @SerializedName("strArtistThumb")
    val thumbnailPath: String
)
data class ArtistResponse (
    @SerializedName("artists")
    val artists : List<Artist>
)