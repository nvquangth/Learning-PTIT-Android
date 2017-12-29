package vn.svptit.learning.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by NVQuang on 19/12/2017.
 */
class AssetUtils {
    fun read(context: Context, path: String): String {
        return context.assets.open(path).bufferedReader().use { it.readText() }
    }
}