package vn.svptit.learning.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.quangnv.baseproject.fragment.BaseFragment
import vn.svptit.learning.R
import vn.svptit.learning.model.Question

/**
 * Created by NVQuang on 27/12/2017.
 */
class QuestionFragment2 : BaseFragment() {

    private var question = Question()
    private val QUESTION_TAG = "QUESTION"

    fun newInstance(question: Question): QuestionFragment2 {
        var fm = QuestionFragment2()
        var bundle = Bundle()
        bundle.putSerializable(QUESTION_TAG, question)
        fm.arguments = bundle
        return fm
    }

    override fun initViews(view: View?) {

    }

    override fun getLayout(): Int {
        return R.layout.item_vp_question
    }

}