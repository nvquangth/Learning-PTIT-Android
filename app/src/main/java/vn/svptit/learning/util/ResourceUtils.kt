package vn.svptit.learning.util

import android.content.Context

/**
 * Created by NVQuang on 23/12/2017.
 */
class ResourceUtils {
    fun getResourceId(context: Context, variableName: String, resourceName: String): Int {
        return context.resources.getIdentifier(variableName, resourceName, context.packageName)
    }
}