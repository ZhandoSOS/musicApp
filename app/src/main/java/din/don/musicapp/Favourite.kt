package din.don.musicapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import din.don.musicapp.audioDbApi.Track
import din.don.musicapp.audioDbApi.TrackResponse
import din.don.musicapp.database.AppDatabase
import din.don.musicapp.viewAdapters.TrackAdapter
import kotlinx.android.synthetic.main.activity_favourite.*

class Favourite : AppCompatActivity() {

    private lateinit var adapter: TrackAdapter
    private var auth = FirebaseAuth.getInstance()
    private var user = auth.currentUser!!.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        loadFavs()

        logoutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun loadFavs() {

        AsyncTask.execute {
            val favss =
                AppDatabase.getInstance(applicationContext)!!.getFavTracks().getTracksByUserId(user)
            val favs = ArrayList<Track>()
            favss.forEach {
                val track = Track(
                    trackName = it!!.trackName,
                    albumName = it.albumName,
                    duration = it?.duration,
                    mood = it?.mood,
                    genre = it?.genre,
                    thumbnailPath = it?.thumbnailPath
                )
                favs.add(track)
            }
            runOnUiThread {
                adapter = TrackAdapter(ArrayList(favs), onClick = { track1 ->
                    deleteFromFavs(track1.trackName)
                })
                tracksRecyclerView.layoutManager = LinearLayoutManager(this)
                tracksRecyclerView.adapter = adapter
            }
        }
    }

    private fun deleteFromFavs(trackName: String) {
        AsyncTask.execute {
            AppDatabase.getInstance(applicationContext)!!.getFavTracks().deleteTrack(trackName)
        }
        startActivity(Intent(this, Favourite::class.java))
    }
}