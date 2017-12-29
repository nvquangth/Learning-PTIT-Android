package vn.svptit.learning.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_rcv_chapters.view.*
import vn.svptit.learning.R
import vn.svptit.learning.interfaces.OnItemClickListener
import vn.svptit.learning.model.Chapter
import vn.svptit.learning.util.ResourceUtils

/**
 * Created by NVQuang on 23/12/2017.
 */
class ChaptersRecyclerViewAdapter: RecyclerView.Adapter<ChapterViewHolder> {

    private var chapters: MutableList<Chapter> = arrayListOf()
    private var callBack: OnItemClickListener? = null

    constructor(chapters: MutableList<Chapter>, callBack: OnItemClickListener) {
        this.chapters = chapters
        this.callBack = callBack
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bindItems(chapters[position], position)
        holder.itemView.setOnClickListener(View.OnClickListener { callBack?.onClick(position) })
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChapterViewHolder {
        var view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_rcv_chapters, parent, false)
        return ChapterViewHolder(view)
    }

}

class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItems(chapter: Chapter, position: Int) {
        itemView.no_chapter.text = ((position + 1).toString())
        itemView.txt_chapter.text = chapter.name
        itemView.bg_view.setBackgroundResource(ResourceUtils().getResourceId(itemView.context, "bg_square_" + (((position + 1) % 4) + 1), "drawable"))
    }
}