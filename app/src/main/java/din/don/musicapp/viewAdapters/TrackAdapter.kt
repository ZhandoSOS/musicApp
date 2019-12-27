package din.don.musicapp.viewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import din.don.musicapp.R
import din.don.musicapp.audioDbApi.Track
import kotlinx.android.synthetic.main.track.view.*

class TrackAdapter(
    private val tracks: ArrayList<Track>,
    private val onClick: (Track) -> Unit)
    : RecyclerView.Adapter<TrackAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.track, parent, false))

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(tracks[position], onClick)

    class EventsHolder(private val view: View)
        : RecyclerView.ViewHolder(view){
        fun binding(track1: Track, onClick: (Track) -> Unit){
            view.nameTxt.text = track1.trackName
            view.albumTxt.text = track1.albumName
            view.genreTxt.text = track1.genre
            view.durationTxt.text = track1.duration.toString()
            view.moodTxt.text = track1.mood
            if (!track1.thumbnailPath.isNullOrBlank()){
                Picasso.get().load(track1.thumbnailPath).into(view.thumbTrack)
            }
            view.addToFavs.setOnClickListener{
                onClick(track1)
            }


        }
    }
}