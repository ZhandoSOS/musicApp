package din.don.musicapp.viewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import din.don.musicapp.R
import din.don.musicapp.audioDbApi.Mv
import kotlinx.android.synthetic.main.mv.view.*


class MvAdapter(
    private val mvs: ArrayList<Mv>,
    private val onClick: (Mv) -> Unit)
    : RecyclerView.Adapter<MvAdapter.EventsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R
                    .layout.mv, parent, false))

    override fun getItemCount() = mvs.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.binding(mvs[position], onClick)

    class EventsHolder(private val view: View)
        : RecyclerView.ViewHolder(view){
        fun binding(mv: Mv, onClick: (Mv) -> Unit){
            view.nameTxt.text = mv.trackName
            if (!mv.thumbnailPath.isNullOrBlank()){
                Picasso.get().load(mv.thumbnailPath).into(view.thumbTrack)
            }
            view.nameTxt.setOnClickListener{
                onClick(mv)
            }


        }
    }
}