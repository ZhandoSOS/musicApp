package din.don.musicapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tracks")
data class Favourites(
    @PrimaryKey
    @ColumnInfo(name = "trackId")
    val trackId: Int? = null,
    @ColumnInfo(name = "userId")
    val userId: String,
    @ColumnInfo(name = "trackName")
    @SerializedName("strTrack")
    val trackName: String,
    @ColumnInfo(name = "albumName")
    @SerializedName("strAlbum")
    val albumName: String,
    @ColumnInfo(name = "duration")
    @SerializedName("intDuration")
    val duration: Int?,
    @ColumnInfo(name = "genre")
    @SerializedName("strGenre")
    val genre: String?,
    @ColumnInfo(name = "mood")
    @SerializedName("strMood")
    val mood: String?,
    @ColumnInfo(name = "thumbnailPath")
    @SerializedName("strTrackThumb")
    val thumbnailPath: String?

)