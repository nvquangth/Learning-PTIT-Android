package com.quangnv.baseproject.fragment

import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_subjects.*
import kotlinx.android.synthetic.main.toolbar.*
import vn.svptit.learning.R
import vn.svptit.learning.adapter.SubjectsRecyclerViewAdapter
import vn.svptit.learning.fragment.ChaptersFragment
import vn.svptit.learning.interfaces.OnItemClickListener
import vn.svptit.learning.model.Subject
import vn.svptit.learning.util.AssetUtils

/**
 * Created by NVQuang on 11/12/2017.
 */
class SubjectsFragment : BaseFragment(), OnItemClickListener {
    private var subjects: MutableList<Subject> = arrayListOf()
    private var gson: Gson = Gson()
    private var adapter: SubjectsRecyclerViewAdapter? = null

    override fun initViews(view: View?) {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_subjects
    }

    override fun initDataDefault() {
        setToolbar()
        loadData()
    }

    private fun setToolbar() {
        toolbar.title = "Môn học"
        mainActivity.setUpWithToolbar(toolbar)
    }

    private fun loadData() {
        var json = AssetUtils().read(context, "data/json/subjects.json")
        subjects = gson.fromJson(json, object : TypeToken<ArrayList<Subject>>(){}.type)
        Log.d("subject_json: ", json)
        setContent()
    }

    private fun setContent() {
        adapter = SubjectsRecyclerViewAdapter(subjects, this)
        rcv_subjects.adapter = adapter
    }


    override fun onClick(position: Int) {
        mainActivity.onOpenFragment(ChaptersFragment().newInstance(subjects.get(position)), true)
    }

}