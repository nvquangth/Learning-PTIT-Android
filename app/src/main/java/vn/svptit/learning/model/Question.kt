package vn.svptit.learning.model

import java.io.Serializable

/**
 * Created by NVQuang on 27/12/2017.
 */
class Question: Serializable {
    var question = ""
    var choies = arrayListOf<String>()
    var answer = 0
}