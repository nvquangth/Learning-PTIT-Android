package vn.svptit.learning.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by NVQuang on 28/12/2017.
 */
class SharePrefUtils {

    fun setInt(context: Context, key: String, value: Int) {
        var editor = context.getSharedPreferences("APPSHAREPREFS", Context.MODE_PRIVATE).edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(context: Context, key: String): Int {
        var sharedPreferences = context.getSharedPreferences("APPSHAREPREFS", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, 0)
    }
}