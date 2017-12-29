package vn.svptit.learning.model

import android.util.Log
import java.io.Serializable

/**
 * Created by NVQuang on 19/12/2017.
 */
class Chapter: Serializable {
    var id = 0
    var name = ""

    fun print() {
        Log.d("FUCK_chapter: ", "$id--$name")
    }
}