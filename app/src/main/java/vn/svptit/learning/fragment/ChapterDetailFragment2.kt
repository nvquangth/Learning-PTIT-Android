package vn.svptit.learning.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.quangnv.baseproject.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_chapter_detail.*
import kotlinx.android.synthetic.main.fragment_chapter_detail2.*
import kotlinx.android.synthetic.main.fragment_chapters.*
import vn.svptit.learning.R
import vn.svptit.learning.adapter.QuestionViewPagerAdapter
import vn.svptit.learning.adapter.QuestionViewPagerAdapter2
import vn.svptit.learning.model.Chapter
import vn.svptit.learning.model.Question
import vn.svptit.learning.model.Subject
import vn.svptit.learning.util.AssetUtils

/**
 * Created by NVQuang on 27/12/2017.
 */
class ChapterDetailFragment2 : BaseFragment(), View.OnClickListener {

    var questionList = arrayListOf<Question>()
    private var chapter = Chapter()
    private var subjectId = ""

    var adapter: QuestionViewPagerAdapter2? = null
    var indexCur = 0
    var isFinishTest = false
    var arrAnswer = IntArray(questionList.size)

    private val CHAPTER_TAG = "CHAPTER"
    private val SUBJECT_TAG = "SUBJECT_ID"

    fun newInstance(subjectId: String, chapter: Chapter): ChapterDetailFragment2 {
        var fm = ChapterDetailFragment2()
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
        setToolbar()
        initViewPager()
        init()
        setViewPager()
        setEvents()
    }

    private fun init() {
        arrAnswer = IntArray(questionList.size)
        for (i in 0 until arrAnswer.size) {
            arrAnswer[i] = -1
        }
        setTitleQuestion(indexCur)
        btn_next.visibility = View.INVISIBLE
    }

    private fun getContent() {
        var bundle = arguments
        chapter = bundle.getSerializable(CHAPTER_TAG) as Chapter
        subjectId = bundle.getString(SUBJECT_TAG)
        getQuestion()
    }

    private fun setToolbar() {
        toolbar2.title = chapter.name
        toolbar2.setNavigationIcon(R.drawable.ic_action_back)
        toolbar2.setNavigationOnClickListener { mainActivity.onBackPressed() }
    }

    private fun getQuestion() {
        var json = AssetUtils().read(context, "data/json/$subjectId/ch${chapter.id}.json")
//        Log.d("question_json: ", json)
        questionList = Gson().fromJson(json, object : TypeToken<ArrayList<Question>>(){}.type)
    }

    fun onSelect(position: Int) {
        arrAnswer[view_pager.currentItem] = position
        btn_next.visibility = View.VISIBLE
    }

    private fun setViewPager() {
        adapter = QuestionViewPagerAdapter2(childFragmentManager, questionList, chapter.id, subjectId)
        view_pager.adapter = adapter
        view_pager.setPagingEnable(false)
    }

    private fun initViewPager() {
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                indexCur = position
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

        })
    }

    private fun setTitleQuestion(position: Int) {
        txt_no_task.text = "${position + 1}/${questionList.size}"
    }

    private fun setEvents() {
        btn_next.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        btn_next.visibility = View.INVISIBLE
        view_pager.currentItem = view_pager.currentItem + 1
        if (view_pager.currentItem == questionList.size) {
            view_pager.setPagingEnable(true)
            isFinishTest = true
        }
    }

}