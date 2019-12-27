package din.don.musicapp.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query



@Dao
interface FavTracksDao {

    @Insert
    fun insertFavTrack(track: Favourites)

    @Query("SELECT * FROM tracks where userId =:userId")
    fun getTracksByUserId(userId: String) : List<Favourites?>

    @Query("DELETE FROM tracks where trackName =:trackName")
    fun deleteTrack(trackName: String)
}