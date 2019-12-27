package din.don.musicapp

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import din.don.musicapp.audioDbApi.*
import din.don.musicapp.database.AppDatabase
import din.don.musicapp.database.Favourites
import din.don.musicapp.viewAdapters.AlbumAdapter
import din.don.musicapp.viewAdapters.MvAdapter
import din.don.musicapp.viewAdapters.TrackAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var mvAdapter: MvAdapter


    private var auth = FirebaseAuth.getInstance()
    private var artistId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    private fun setView(){
        search.setOnClickListener{
            if(artistName.text.isNullOrBlank()){
                Toast.makeText(this, "Incorrect Input", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            getArtistInfo(artistName.text.toString())
            getAlbums(artistName.text.toString())
            getTracks(artistName.text.toString())
            getMv(artistId)
        }
        goToFavBtn.setOnClickListener{
            if(auth.currentUser == null) {
                startActivity(Intent(this, Login::class.java))
            }else{
                startActivity(Intent(this, Favourite::class.java))
            }
        }

    }

    private fun getArtistInfo(name: String){
        ArtistLoader(onSuccess = {
                val artists = it.artists
                if(!artists.isNullOrEmpty()){

                    val artist = artists.get(0)
                    artistId = artist.id
                    arName.text = artist.artistName
                    arBio.text = artist.biography
                    arYear.text = artist.bornYear.toString()
                    arGenre.text = artist.genre
                    arWebsite.text = artist.website


                    if (!artist.thumbnailPath.isNullOrBlank()) {
                        Picasso.get().load(artist.thumbnailPath).into(arthumb)
                    }
                    if (!artist.logoPath.isNullOrBlank()) {
                        Picasso.get().load(artist.logoPath).into(arLogo)
                    }
                }
            },
            onError = {
                Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
            }

        ).getArtistInfo(name)


    }

    private fun getTracks(name: String){
        TrackLoader(
            onSuccess = {
                val tracks = it.track
                trackAdapter = TrackAdapter(ArrayList(tracks), onClick = {track1 ->
                    if(auth.currentUser == null) {
                        startActivity(Intent(this, Login::class.java))
                    }else{
                        val user: String = auth.currentUser!!.uid
                        AsyncTask.execute{
                            AppDatabase.getInstance(applicationContext)!!.getFavTracks()
                                .insertFavTrack(
                                    Favourites(
                                        userId = user,
                                        trackName = track1.trackName,
                                        albumName = track1.albumName,
                                        duration = track1.duration,
                                        mood = track1.mood,
                                        genre = track1.genre,
                                        thumbnailPath = track1.thumbnailPath

                                    )
                                )
                        }
                    }
                })
                tracksRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL ,false)
                tracksRecyclerView.adapter = trackAdapter
            },
            onError = {
                Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
            }

        ).getTopTenTracks(name)
    }
    private fun getAlbums(name: String){
        AlbumLoader(
            onSuccess = {
                val albums = it.album
                albumAdapter = AlbumAdapter(ArrayList(albums), onClick = {
                })
                albumsRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL ,false)
                albumsRecyclerView.adapter = albumAdapter
            },
            onError = {
                Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
            }
        ).getAlbumsByActorName(name)
    }

    private fun getMv(artistId: Int) = MvLoader(
        onSuccess = {
            val mvs = it.mvids
            mvAdapter = MvAdapter(ArrayList(mvs), onClick = {mv ->
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mv.mvPath))
                startActivity(webIntent)
            })
            mvRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL ,false)
            mvRecyclerView.adapter = mvAdapter

        },
        onError = {
            Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
        }
    ).getArtistInfo(artistId)
}
