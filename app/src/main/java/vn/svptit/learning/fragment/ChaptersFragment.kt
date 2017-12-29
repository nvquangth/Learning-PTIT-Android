package vn.svptit.learning.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.quangnv.baseproject.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_chapters.*
import vn.svptit.learning.R
import vn.svptit.learning.adapter.ChaptersRecyclerViewAdapter
import vn.svptit.learning.interfaces.OnItemClickListener
import vn.svptit.learning.model.Subject
import java.io.InputStream

/**
 * Created by NVQuang on 23/12/2017.
 */
class ChaptersFragment: BaseFragment(), OnItemClickListener {
    private val TAG_SUBJECT = "SUBJECT"
    private var subject: Subject = Subject()
    private var adapter: ChaptersRecyclerViewAdapter? = null

    fun newInstance(subject: Subject): ChaptersFragment {
        var fm = ChaptersFragment()
        var bundle = Bundle()
        bundle.putSerializable(TAG_SUBJECT, subject)
        fm.arguments = bundle
        return fm
    }
    override fun initViews(view: View?) {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_chapters
    }

    override fun initDataDefault() {
        getSubject()
        setView()
    }

    private fun getSubject() {
        var bundle: Bundle = arguments
        subject = bundle.getSerializable(TAG_SUBJECT) as Subject
    }

    private fun setView() {
        setToolbar()
        setCollapsingLayout()
        setRecyclerView()
    }

    private fun setToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_action_back)
        toolbar.setNavigationOnClickListener { mainActivity.onBackPressed() }
    }

    private fun setCollapsingLayout() {
        var inputStream: InputStream = context.assets.open(subject?.urlImage)
        var drawable: Drawable = Drawable.createFromStream(inputStream, null)
        img_subject.setImageDrawable(drawable)
        collapsing_toolbar.title = subject?.name
    }

    private fun setRecyclerView() {
        adapter = ChaptersRecyclerViewAdapter(subject.chapters, this)
        rcv_chapters.adapter = adapter

    }

    override fun onClick(position: Int) {
        mainActivity.onOpenFragment(ChapterDetailFragment2().newInstance(subject.id, subject.chapters[position]), true)
    }

}