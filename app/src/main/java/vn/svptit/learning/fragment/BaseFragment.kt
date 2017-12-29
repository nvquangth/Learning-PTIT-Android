package com.quangnv.baseproject.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.svptit.learning.activity.MainActivity

/**
 * Created by NVQuang on 10/12/2017.
 */
abstract class BaseFragment: Fragment() {
    private var savedWhenInStackFlag: Boolean = false
    private var bundleSavedWhenInStack: Bundle = Bundle()
    private var restoreFromBackStackFlag: Boolean = false
    protected var mainActivity: MainActivity = MainActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            bundleSavedWhenInStack = savedInstanceState
            savedWhenInStackFlag = true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        /* Disable onBackStack */
        savedWhenInStackFlag = false
        super.onActivityCreated(savedInstanceState)
        if (restoreFromBackStackFlag) {
            initDataWhenRestoreFromStack()
        } else if (savedInstanceState == null) {
            initDataWhenCreateNew()
        } else {
            initDataWhenSavedState(savedInstanceState)
        }
        /**
         * Fragment is shown -> mark back stack flag. When fragment is obtained from the stack
         * Local variables of fragment is kept
         */
        restoreFromBackStackFlag = false
        mainActivity = activity as MainActivity

        initDataDefault()
    }

    /**
     *
     * Always call
     *
     */
    open fun initDataDefault() {

    }

    /**
     *
     * Called after screen rotation. Should retrieve saved data
     * Ex: mId = savedInstanceState.getIn("id")
     *
     * @param   savedInstanceState
     *
     */
    open fun initDataWhenSavedState(savedInstanceState: Bundle) {

    }

    /**
     *
     * Called when create new instant. Data should be pass via get and set Argument
     * Ex: mId = getArgument.getInt("id")
     *
     */
    open fun initDataWhenCreateNew() {

    }

    /**
     *
     * Called when fragments is bring to front from stack. Field variables still exist
     * Ex: mId keep value before
     *
     */
    open fun initDataWhenRestoreFromStack() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(getLayout(), null)
        initViews(view)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (savedWhenInStackFlag && bundleSavedWhenInStack != null) {
            outState?.putAll(bundleSavedWhenInStack)
            return
        }
        onSaveFragmentState(outState)
    }

    /**
     *
     * Should save variables here
     * Ex: outState.putIn("id", mId)
     *
     * @param outState
     *
     */
    private fun onSaveFragmentState(outState: Bundle?) {

    }

    abstract fun initViews(view: View?)

    abstract fun getLayout(): Int


}