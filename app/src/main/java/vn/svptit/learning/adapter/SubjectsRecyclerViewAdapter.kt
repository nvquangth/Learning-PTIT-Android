package vn.svptit.learning.adapter

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_rcv_subjects.view.*
import vn.svptit.learning.R
import vn.svptit.learning.interfaces.OnItemClickListener
import vn.svptit.learning.model.Subject
import java.io.InputStream

/**
 * Created by NVQuang on 19/12/2017.
 */
class SubjectsRecyclerViewAdapter : RecyclerView.Adapter<SubjectViewHolder> {

    private var subjects: MutableList<Subject> = arrayListOf()
    private var callBack: OnItemClickListener? = null

    constructor(subjects: MutableList<Subject>, callBack: OnItemClickListener) {
        this.subjects = subjects
        this.callBack = callBack
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bindItems(subjects[position])
        holder.itemView.setOnClickListener(View.OnClickListener { callBack?.onClick(position) })
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SubjectViewHolder {
        var view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_rcv_subjects, parent, false)
        return SubjectViewHolder(view)
    }

}

class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItems(subject: Subject) {
        var inputStream: InputStream = itemView.context.assets.open(subject.urlImage)
        var drawable: Drawable = Drawable.createFromStream(inputStream, null)
        itemView.img_subject.setImageDrawable(drawable)
        itemView.txt_subject.text = subject.name
    }
}
