package id.kuliah.musicplayer

import android.content.Intent
import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import id.kuliah.musicplayer.Adapters.SongListAdapter
import id.kuliah.musicplayer.Model.SongModel
import java.io.File
import java.util.ArrayList
//import java.util.jar.Manifest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var songModelData:ArrayList<SongModel> = ArrayList()
    var songListAdapter:SongListAdapter?=null
    companion object{
        val PERMISSION_REQUEST_CODE = 12
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        }else{
            LodaData()
        }


    }

    fun LodaData(){
        var songCursor: Cursor? = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null,null,null,null)
        while (songCursor!=null && songCursor.moveToNext()){
            var songName = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
            var songDuration = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
            songModelData.add(SongModel(songName,songDuration))
        }
        songListAdapter = SongListAdapter(songModelData)
        var layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = songListAdapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext,"Just Permissiob Granted",Toast.LENGTH_LONG).show()
                LodaData()
            }
        }
    }



// EndOfCode
}
