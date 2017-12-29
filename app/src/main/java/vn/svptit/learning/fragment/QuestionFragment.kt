package vn.svptit.learning.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.quangnv.baseproject.fragment.BaseFragment
import kotlinx.android.synthetic.main.item_vp_question.*
import vn.svptit.learning.R
import vn.svptit.learning.model.Question

/**
 * Created by NVQuang on 27/12/2017.
 */
class QuestionFragment: BaseFragment(), View.OnClickListener {

    private var question = Question()
    private val QUESTION_TAG = "QUESTION"

    fun newInstance(question: Question): QuestionFragment {
        var fm = QuestionFragment()
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

    override fun initDataDefault() {
        getQuestion()
        setView()
        setEvents()
    }

    private fun getQuestion() {
        var bundle = arguments
        question = bundle.getSerializable(QUESTION_TAG) as Question
    }

    private fun setView() {
        txt_content.text = question.question
        txt_ans1.text = question.choies[0]
        txt_ans2.text = question.choies[1]
        txt_ans3.text = question.choies[2]
        txt_ans4.text = question.choies[3]
    }

    private fun setEvents() {
        layout_ans1.setOnClickListener(this)
        layout_ans2.setOnClickListener(this)
        layout_ans3.setOnClickListener(this)
        layout_ans4.setOnClickListener(this)
        layout_ans4.setOnClickListener(View.OnClickListener {  })
    }

    override fun onClick(view: View?) {
        setAnswer()
        disableSelectAnswer()
        when(view?.id) {
            R.id.layout_ans1 -> {
                (parentFragment as ChapterDetailFragment).onSelect(1)
                if (question.answer != 1) {
                    setMistake(btn_ans1, txt_ans1)
                }
            }
            R.id.layout_ans2 -> {
                (parentFragment as ChapterDetailFragment).onSelect(2)
                if (question.answer != 2) {
                    setMistake(btn_ans2, txt_ans2)
                }
            }
            R.id.layout_ans3 -> {
                (parentFragment as ChapterDetailFragment).onSelect(3)
                if (question.answer != 3) {
                    setMistake(btn_ans3, txt_ans3)
                }
            }
            R.id.layout_ans4 -> {
                (parentFragment as ChapterDetailFragment).onSelect(4)
                if (question.answer != 4) {
                    setMistake(btn_ans4, txt_ans4)
                }
            }
        }
    }

    private fun setAnswer() {
        when(question.answer) {
            1 -> setCorrect(btn_ans1, txt_ans1)
            2 -> setCorrect(btn_ans2, txt_ans2)
            3 -> setCorrect(btn_ans3, txt_ans3)
            4 -> setCorrect(btn_ans4, txt_ans4)
        }
    }

    private fun setCorrect(btn_ans: ImageButton?, txt_ans: TextView?) {
        btn_ans?.setImageResource(R.drawable.ic_correct)
        txt_ans?.setTextColor(resources.getColor(R.color.colorCorrect))
    }

    private fun setMistake(btn_ans: ImageButton?, txt_ans: TextView?) {
        btn_ans?.setImageResource(R.drawable.ic_mistake)
        txt_ans?.setTextColor(resources.getColor(R.color.colorMistake))
    }

    private fun disableSelectAnswer() {
        layout_ans1.isEnabled = false
        layout_ans2.isEnabled = false
        layout_ans3.isEnabled = false
        layout_ans4.isEnabled = false
    }

    fun viewResult() {
        disableSelectAnswer()

        var index = (parentFragment as ChapterDetailFragment).indexCur
        var answer = (parentFragment as ChapterDetailFragment).arrAnswer[index]
        var answerCor = (parentFragment as ChapterDetailFragment).questionList[index].answer

        setResultCorrect(answerCor)

        if (answer != answerCor) {
            setResultMistake(answer)
        }
    }

    private fun setResultCorrect(answer: Int) {
        when(answer) {
            1 -> {
                btn_ans1.setImageResource(R.drawable.ic_correct)
                txt_ans1.setTextColor(resources.getColor(R.color.colorCorrect))
            }
            2 -> {
                btn_ans2.setImageResource(R.drawable.ic_correct)
                txt_ans2.setTextColor(resources.getColor(R.color.colorCorrect))
            }
            3 -> {
                btn_ans3.setImageResource(R.drawable.ic_correct)
                txt_ans3.setTextColor(resources.getColor(R.color.colorCorrect))
            }
            4 -> {
                btn_ans4.setImageResource(R.drawable.ic_correct)
                txt_ans4.setTextColor(resources.getColor(R.color.colorCorrect))
            }
        }
    }

    private fun setResultMistake(answer: Int) {
        when(answer) {
            1 -> {
                btn_ans1.setImageResource(R.drawable.ic_mistake)
                txt_ans1.setTextColor(resources.getColor(R.color.colorMistake))
            }
            2 -> {
                btn_ans2.setImageResource(R.drawable.ic_mistake)
                txt_ans2.setTextColor(resources.getColor(R.color.colorMistake))
            }
            3 -> {
                btn_ans3.setImageResource(R.drawable.ic_mistake)
                txt_ans3.setTextColor(resources.getColor(R.color.colorMistake))
            }
            4 -> {
                btn_ans4.setImageResource(R.drawable.ic_mistake)
                txt_ans4.setTextColor(resources.getColor(R.color.colorMistake))
            }
        }
    }

}