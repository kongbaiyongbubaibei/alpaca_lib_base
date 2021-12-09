package com.alpaca.library_base.utils

import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * class desc :
 *
 * 上拉加载更多-帮助类
 *
 * @author : zzh
 * @date : 2018/3/31
 */
class LoadMoreHelp {
    var pageIndex = 1
    var pageSize = 10

    private var mAdapter: BaseQuickAdapter<*, *>? = null

    fun init(recyclerView: RecyclerView?, mAdapter: BaseQuickAdapter<*, *>?, mOnRequest: (() -> Unit)?) {
        this.mAdapter = mAdapter
        if (mOnRequest != null) {
            mAdapter?.loadMoreModule?.setOnLoadMoreListener {
                mOnRequest()
            }
        }
    }

    /**
     * 网络请求成功时调用,适用于获取数据成功之后再设置adapter的情况
     * @param dataSize 数据结果大小
     * @param mOnReplaceDataListener 第一次请求或者下拉刷新的时候，替换所有数据的回调
     * @param mOnAddDataListener 上拉加载成功后添加数据的回调
     */
    fun onRequestComplete(dataSize: Int, mOnReplaceDataListener: (() -> Unit)?, mOnAddDataListener: (() -> Unit)?): LoadMoreHelp {
        if (mAdapter == null) throw IllegalStateException("Adapter must not be null")

        if (pageIndex == 1) {
            if (mOnReplaceDataListener != null) {
                mOnReplaceDataListener()
            }
        } else {
            if (mOnAddDataListener != null) {
                mOnAddDataListener()
            }
        }
        onRequestSucc(dataSize)
        return this
    }

    private var dataSize: Int = 0

    private fun onRequestSucc(dataSize: Int) {
        this.dataSize = dataSize
        mAdapter?.loadMoreModule?.loadMoreEnd(true)

        if (dataSize < pageSize) {
            if (pageIndex == 1) {
                mAdapter?.loadMoreModule?.loadMoreEnd(true)
            } else {
                mAdapter?.loadMoreModule?.loadMoreEnd(false)
            }
        } else {
            mAdapter?.loadMoreModule?.loadMoreComplete()
        }
        pageIndex += 1
    }

    fun onRequestFaild() {
        mAdapter?.loadMoreModule?.loadMoreEnd(true)

        mAdapter?.loadMoreModule?.loadMoreFail()
    }

    fun onRefresh(mOnRequest: (() -> Unit)?) {
        pageIndex = 1
        mAdapter?.loadMoreModule?.loadMoreEnd(false)
        if (mOnRequest != null) {
            mOnRequest()
        }
    }

    fun getAdapter(): BaseQuickAdapter<*, *>? {
        return this.mAdapter
    }
}