package vn.svptit.learning.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.quangnv.baseproject.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_chapter_detail.*
import kotlinx.android.synthetic.main.fragment_chapters.*
import vn.svptit.learning.R
import vn.svptit.learning.adapter.QuestionViewPagerAdapter
import vn.svptit.learning.model.Chapter
import vn.svptit.learning.model.Question

/**
 * Created by NVQuang on 27/12/2017.
 */
class ChapterDetailFragment: BaseFragment(), View.OnClickListener {

    var questionList = arrayListOf<Question>()
    var chapter = Chapter()
    var subjectId = ""
    var arrAnswer = IntArray(questionList.size)
    var indexCur = 0
    var isFinishTest = false
    var adapter: QuestionViewPagerAdapter? = null

    private val CHAPTER_TAG = "CHAPTER"
    private val SUBJECT_TAG = "SUBJECT_ID"

    fun newInstance(subjectId: String, chapter: Chapter):ChapterDetailFragment {
        var fm = ChapterDetailFragment()
        var bundle = Bundle()
        bundle.putString(SUBJECT_TAG, subjectId)
        bundle.putSerializable(CHAPTER_TAG, chapter)
        fm.arguments = bundle
        return fm
    }

    override fun initViews(view: View?) {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_chapter_detail;
    }

    override fun initDataDefault() {
        getContent()
        initViewPager()
        init()
        setToolbar()
        setViewPager()
//        setButtonNext()
//        setEvents()
    }

    private fun getContent() {
        var bundle = arguments
        chapter = bundle.getSerializable(CHAPTER_TAG) as Chapter
        subjectId = bundle.getString(SUBJECT_TAG)
        getQuestion()
    }

    private fun getQuestion() {

    }

    private fun init() {
        arrAnswer = IntArray(questionList.size)
        for (i in 1..arrAnswer.size) {
            arrAnswer[i] = -1
        }
    }

    private fun setToolbar() {
        toolbar.title = chapter.name
    }

    private fun setViewPager() {
        adapter = QuestionViewPagerAdapter(childFragmentManager, questionList, chapter.id, subjectId)
        view_pager.adapter = adapter
        view_pager.setPagingEnable(false)
        setTitleQuestion(view_pager.currentItem)
    }

    private fun setButtonNext() {
        btn_next.visibility = View.INVISIBLE
    }

    fun onSelect(position: Int) {
        arrAnswer[view_pager.currentItem] = position
    }

    private fun initViewPager() {
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageSelected(position: Int) {
                indexCur = position
                if (position == questionList.size) {
                    txt_no_task.text = "Result"
                    if (position == questionList.size) {
                        txt_no_task.text = "Result"
                        var fm = adapter?.getFragment(position) as ResultTestFragment
                        fm.showResult()
                    } else {
                        setTitleQuestion(position)
                        if (isFinishTest) {
                            var fm = adapter?.getFragment(position) as QuestionFragment
                            fm.viewResult()
                        }
                    }
                }
            }

        })
    }

    private fun setTitleQuestion(position: Int) {
        txt_no_task.text = "${position + 1}/${questionList.size}"
    }

    private fun setEvents() {
        btn_next.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view_pager.currentItem = view_pager.currentItem + 1
        if (view_pager.currentItem == questionList.size) {
            view_pager.setPagingEnable(true)
            isFinishTest = true
        }
    }
}