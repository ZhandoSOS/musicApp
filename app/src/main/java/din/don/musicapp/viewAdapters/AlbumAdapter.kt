package din.don.musicapp.viewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import din.don.musicapp.R
import din.don.musicapp.audioDbApi.Album
import kotlinx.android.synthetic.main.album.view.*


class AlbumAdapter(
    private val albums: ArrayList<Album>,
    private val onClick: (Album) -> Unit)
    : RecyclerView.Adapter<AlbumAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album, parent, false))

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(albums[position], onClick)

    class EventsHolder(private val view: View)
        : RecyclerView.ViewHolder(view){
        fun binding(album1: Album, onClick: (Album) -> Unit){
            view.nameTxt.text = album1.albumName
            view.yearTxt.text = album1.releasedYear.toString()
            view.genreTxt.text = album1.genre
            if (!album1.thumbnailPath.isNullOrBlank()){
                Picasso.get().load(album1.thumbnailPath).into(view.thumbTrack)
            }

        }
    }
}