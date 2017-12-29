package vn.svptit.learning.model

import android.util.Log
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NVQuang on 19/12/2017.
 */
class Subject: Serializable {
    var id = ""
    var name = ""
    @SerializedName("url_image")
    var urlImage = ""
    var chapters: MutableList<Chapter> = arrayListOf()
}