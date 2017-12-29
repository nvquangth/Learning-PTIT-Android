package vn.svptit.learning.adapter

import android.database.DataSetObserver
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import vn.svptit.learning.fragment.QuestionFragment
import vn.svptit.learning.fragment.ResultTestFragment
import vn.svptit.learning.model.Question

/**
 * Created by NVQuang on 27/12/2017.
 */
class QuestionViewPagerAdapter(fm: FragmentManager?, questionList: ArrayList<Question>, chapterId: Int, subjectId: String) : FragmentStatePagerAdapter(fm) {
    private var questionList: ArrayList<Question> = questionList
    private var stack = SparseArray<Fragment>()
    private var chapterId = chapterId
    private var subjectId = subjectId

    override fun getItem(position: Int): Fragment {
        if (position == questionList.size) {
            return ResultTestFragment().newInstance(questionList, chapterId, subjectId)
        }
        var question = questionList[position]
        return QuestionFragment().newInstance(question)
    }

    override fun getCount(): Int {
        return questionList.size + 1
    }

    fun getFragment(key: Int): Fragment {
        return stack.get(key)
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        super.registerDataSetObserver(observer)
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        stack.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var obj = super.instantiateItem(container, position)
        stack.put(position, obj as Fragment?)
        return obj
    }
}