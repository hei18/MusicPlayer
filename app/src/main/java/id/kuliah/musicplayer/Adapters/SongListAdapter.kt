package id.kuliah.musicplayer.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.kuliah.musicplayer.Model.SongModel
import id.kuliah.musicplayer.R

class SongListAdapter(SongModel:ArrayList<SongModel>):RecyclerView.Adapter<SongListAdapter.SongListViewHolder>() {

    var mSongModel = SongModel

    override fun getItemCount(): Int {
        return mSongModel.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.music_row,parent,false)
        return  SongListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        var model = mSongModel[position]
        var songName = model.mSongModel
        var songDuration = model.mSongDuration
        holder!!.songTv.text = songName
        holder.durationTv.text = songDuration

    }


    class  SongListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var songTv:TextView
        var durationTv:TextView
        var albumArt:ImageView
        init {
            songTv = itemView.findViewById(R.id.song_name_tv)
            durationTv = itemView.findViewById(R.id.song_duration_tv)
            albumArt = itemView.findViewById(R.id.al_img_view)

        }

    }



//EndOfCode
}