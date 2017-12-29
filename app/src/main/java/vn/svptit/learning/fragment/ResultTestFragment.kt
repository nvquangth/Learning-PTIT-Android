package vn.svptit.learning.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.quangnv.baseproject.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_test_result.*
import vn.svptit.learning.R
import vn.svptit.learning.model.Question
import vn.svptit.learning.util.SharePrefUtils

/**
 * Created by NVQuang on 28/12/2017.
 */
class ResultTestFragment: BaseFragment() {
    private val QUESTION_TAG = "QUESTION"
    private val CHAPTER_TAG = "CHAPTER_ID"
    private val SUBJECT_TAG = "SUBJECT_ID"

    private var chapterId = 0
    private var subjectId = ""
    private var questionList = arrayListOf<Question>()
    private var arrAnswer = IntArray(questionList.size)
    private var handler = Handler()
    private var nCorrect = 0
    private var nMistake = 0
    private var highScore = 0

    fun newInstance(questionList: ArrayList<Question>, chapterId: Int, subjectId: String):ResultTestFragment {
        var fm = ResultTestFragment()
        var bundle = Bundle()
        bundle.putSerializable(QUESTION_TAG, questionList)
        bundle.putInt(CHAPTER_TAG, chapterId)
        bundle.putString(SUBJECT_TAG, subjectId)
        fm.arguments = bundle
        return fm
    }

    override fun initViews(view: View?) {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_test_result
    }

    fun showResult() {
        getArrAnswer()
        setResult()
        setHighScore()
    }

    private fun getArrAnswer() {
        var bundle = arguments
        questionList = bundle.getSerializable(QUESTION_TAG) as ArrayList<Question>
        chapterId = bundle.getInt(CHAPTER_TAG)
        subjectId = bundle.getString(SUBJECT_TAG)
        arrAnswer = IntArray(questionList.size)
        arrAnswer = (parentFragment as ChapterDetailFragment2).arrAnswer
    }

    private fun setResult() {
        nCorrect = checkCorrect()
        nMistake = arrAnswer.size - nCorrect

        setCircleProgressBar(nCorrect)
        txt_correct.text = "CORRECT: $nCorrect"
        txt_mistake.text = "MISTAKE: $nMistake"
    }

    private fun setHighScore() {
        highScore = SharePrefUtils().getInt(context, "$subjectId _HIGHSCORE_$chapterId")
        if (nCorrect > highScore) {
            highScore = nCorrect
            SharePrefUtils().setInt(context, "$subjectId _HIGHSCORE_$chapterId", highScore)
        }
        if (nCorrect >= (questionList.size * 2 / 3)) {
            txt_noti.text = resources.getString(R.string.noti_pass)
            txt_noti.setTextColor(Color.parseColor("#1DE9B6"))
        } else {
            txt_noti.text = resources.getString(R.string.noti_nopass_test)
            txt_noti.setTextColor(Color.parseColor("#FF0000"))
        }
        txt_high_score.text = "$highScore"
    }

    private fun checkCorrect(): Int {
        var dem = 0
        for (i in 0 until arrAnswer.size) {
            if (arrAnswer[i] == questionList[i].answer) {
                dem++
            }
        }
        return dem
    }

    private fun setCircleProgressBar(n: Int) {
        progress_bar.progress = 0
        Thread(Runnable {
            var progressState = 0
            var time = 500
            var percent = (100 * n / arrAnswer.size)
            while (progressState < percent) {
                progressState++
                handler.post {
                    progress_bar.progress = progressState
                    txt_score.text = "$progressState%"
                }
                Thread.sleep((time/percent).toLong())
            }
        }).start()
    }

}