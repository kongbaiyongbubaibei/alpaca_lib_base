package com.alpaca.library_base.widgets

import android.content.Context
import android.content.res.TypedArray
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.OverScroller
import androidx.core.view.NestedScrollingParent
import com.alpaca.library_base.R
import java.util.*


/**
 * class desc :
 *
 * 粘性头部的自定义view(通过滑动嵌套机制实现)
 * 头部id  R.id.sticky_top_view_id
 * 悬浮置顶id R.id.sticky_nav_view_id
 * 底部滑动控件id R.id.sticky_scroll_view_id
 *
 * id需要匹配，否则不支持
 *
 * @author : zzh
 * @date : 2018/3/28
 */
class StickyNestedScrollLayout : LinearLayout, NestedScrollingParent {

    companion object {
        private const val TAG = "StickyNestedScroll"
        private var DEBUG = false
    }

    private var navEnable = true//控制悬停的开关,关掉就跟普通的LinerLayout一样

    var navOffset = 0//置顶的偏移量

    val isTopHidden: Boolean
        get() = scrollY >= mTopViewHeight - navOffset

    private var mTopView: View? = null
    private var mNavView: View? = null
    private var mScrollView: View? = null

    private var mTopViewHeight: Int = 0

    private val mScroller: OverScroller
    private val mTouchSlop: Int
    private val mMaximumVelocity: Int
    private val mMinimumVelocity: Int

    private var mTimer: Timer? = null//定时器,监听fling是否结束

    private val mHandler = Handler()

    private var dy = 0//记录滑动时dy的值

    private var topViewId: Int
    private var navViewId: Int
    private var scrollViewId: Int

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        //获取自定义属性
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StickyNestedScrollLayout)

        topViewId = typedArray.getResourceId(R.styleable.StickyNestedScrollLayout_topViewId, R.id.sticky_top_view_id)
        navViewId = typedArray.getResourceId(R.styleable.StickyNestedScrollLayout_navViewId, R.id.sticky_nav_view_id)
        scrollViewId = typedArray.getResourceId(R.styleable.StickyNestedScrollLayout_scrollViewId, R.id.sticky_scroll_view_id)

        mTopView = findViewById(topViewId)
        mNavView = findViewById(navViewId)
        mScrollView = findViewById(scrollViewId)

        typedArray.recycle()
    }

    /**
     * 开始嵌套滑动
     */
    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        if (DEBUG) Log.e(TAG, "onStartNestedScroll")
        //先停止掉之前的滑动效果,否则快速滑动时会出现不动或者闪动一下
        mScroller.abortAnimation()
        //初始化dy
        dy = 0
        //如果关闭,则不进行嵌套滑动,跟普通layout一样
        if (!navEnable) {
            return false
        }
        return true
    }

    /**
     * 父类接收到嵌套滑动的请求
     */
    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int) {
        if (DEBUG) Log.e(TAG, "onNestedScrollAccepted")
    }

    /**
     * 停止嵌套滑动(正常是在手指离开后调用,而不是fling结束)
     */
    override fun onStopNestedScroll(target: View) {
        if (DEBUG) Log.e(TAG, "onStopNestedScroll")
    }

    /**
     * 嵌套滑动
     */
    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        if (DEBUG) Log.e(TAG, "onNestedScroll")
    }

    /**
     * 嵌套滑动之前,父类接收到子类的请求,在这里判断是否父类消耗掉滑动
     */
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        //dy <0  向下滑动    dy>0 向上滑动
        if (DEBUG) Log.e(TAG, "onNestedPreScroll")
        this.dy = dy
//        Log.e(TAG, dy.toString() + "")
        val hiddenTop = dy > 0 && scrollY < mTopViewHeight - navOffset
        val showTop = dy < 0 && scrollY <= mTopViewHeight - navOffset && !target.canScrollVertically(-1)

        if (hiddenTop || showTop) {
            scrollBy(0, dy)
            consumed[1] = dy//consumed消费,数组传1代表父类view消费掉了dy
            invalidate()
        }
        if (isTopHidden) {
            if (this::layoutIsSticky.isInitialized) {
                layoutIsSticky(true)
            }
        } else {
            if (this::layoutIsSticky.isInitialized) {
                layoutIsSticky(false)
            }
        }
        if (this::nestedPreScroll.isInitialized) {
            nestedPreScroll(target, dx, dy, consumed)
        }
    }

    /**
     * 当fling时调用
     */
    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return true
    }

    /**
     * 子类请求fling前,父类是否要消耗掉fling操作
     */
    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        //不做拦截 可以传递给子View
        if (DEBUG) Log.e(TAG, velocityY.toString() + "")
        if (!isTopHidden) {
            fling(velocityY.toInt())
            return true
        }
        return false
    }

    /**
     * 嵌套滑动方向
     */
    override fun getNestedScrollAxes(): Int {
        if (DEBUG) Log.e(TAG, "getNestedScrollAxes")
        return 0
    }

    /**
     * 初始化操作
     */
    init {
        orientation = LinearLayout.VERTICAL
        mScroller = OverScroller(context)
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        mMaximumVelocity = ViewConfiguration.get(context)
                .scaledMaximumFlingVelocity
        mMinimumVelocity = ViewConfiguration.get(context)
                .scaledMinimumFlingVelocity
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mTopView = findViewById(topViewId)
        mNavView = findViewById(navViewId)
        mScrollView = findViewById(scrollViewId)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        if (DEBUG) Log.e(TAG, "onMeasure")

        val params = mScrollView!!.layoutParams
        //当关闭悬浮开关时要将高度设置为包裹内容
        if (navEnable) {
            //viewpager的高度 = 总高度-悬浮置顶的高度
            val mNavViewHeight = if (mNavView!!.visibility == View.VISIBLE) mNavView!!.measuredHeight else 0
            params.height = measuredHeight - mNavViewHeight - navOffset
        } else {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        mScrollView!!.layoutParams = params
        //总高度
        val mTopViewHeight = if (mTopView!!.visibility == View.VISIBLE) mTopView!!.measuredHeight else 0
        val mNavViewHeight = if (mNavView!!.visibility == View.VISIBLE) mNavView!!.measuredHeight else 0
        val mScrollViewHeight = if (mScrollView!!.visibility == View.VISIBLE) mScrollView!!.measuredHeight else 0

        if (DEBUG) Log.e(TAG, "onMeasure:" + mTopView!!.measuredHeight + "__" + mNavView!!.measuredHeight + "__" + mScrollView!!.measuredHeight + "__" + navOffset)

        setMeasuredDimension(measuredWidth, mTopViewHeight + mNavViewHeight + mScrollViewHeight - navOffset)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mTopViewHeight = mTopView!!.measuredHeight
    }

    private fun fling(velocityY: Int) {
        var mVelocityY = velocityY
        //修复dy的值跟velocityY方向不匹配的问题,保证dy方向和velocityY的方向一致
        if (dy > 0) {
            mVelocityY = Math.abs(mVelocityY)
        } else if (dy < 0) {
            mVelocityY = -Math.abs(mVelocityY)
        }

        //控制velocityY的值在最大最小范围区间内
        if (mVelocityY < 0) {
            if (mVelocityY > -mMinimumVelocity) {
                mVelocityY = -mMinimumVelocity
            }
            if (velocityY < -mMaximumVelocity) {
                mVelocityY = -mMaximumVelocity
            }
        }
        if (mVelocityY > 0) {
            if (mVelocityY > mMaximumVelocity) {
                mVelocityY = mMaximumVelocity
            }
            if (mVelocityY < mMinimumVelocity) {
                mVelocityY = mMinimumVelocity
            }
        }
        startScrollListener()
        mScroller.fling(0, scrollY, 0, (mVelocityY * 0.75).toInt(), 0, 0, 0, mTopViewHeight)
        invalidate()
    }

    override fun scrollTo(x: Int, y: Int) {
        var mY = y
        if (mY < 0) {
            mY = 0
        }
        //控制滑动高度不超过悬浮置顶的高度
        if (mY > mTopViewHeight - navOffset) {
            mY = mTopViewHeight - navOffset
        }
        if (mY != scrollY) {
            super.scrollTo(x, mY)
        }
    }

    /**
     * 当fling方法调用的时候会回调此方法
     */
    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.currY)
            invalidate()
        }
    }

    /**
     * 滑动到顶部
     */
    fun flingToTop() {
        mScroller.fling(0, scrollY, 0, -3500, 0, 0, 0, Int.MAX_VALUE)
        invalidate()
        if (this::layoutIsSticky.isInitialized) {
            layoutIsSticky(false)
        }
    }

    /**
     * 判断是否滑动到顶部
     */
    fun isScrollTop(): Boolean {
        return scrollY == 0
    }

    /**
     * 当OnNestedPreScroll调用的监听
     */
    lateinit var nestedPreScroll: (target: View, dx: Int, dy: Int, consumed: IntArray) -> Unit

    fun setOnNestedPreScrollListener(onNestedPreScroll: (target: View, dx: Int, dy: Int, consumed: IntArray) -> Unit) {
        this.nestedPreScroll = onNestedPreScroll
    }

    lateinit var layoutIsSticky: (Boolean) -> Unit

    /**
     * 当悬浮置顶或非置顶的监听
     */
    fun setOnStickyChangeListener(boolean: (Boolean) -> Unit) {
        this.layoutIsSticky = boolean
    }

    /**
     * 控制悬浮控制的开关
     */
    fun setNavEnable(b: Boolean) {
        this.navEnable = b
    }

    /**
     * 开启滚动监听
     */
    private fun startScrollListener() {
        //先停止
        stopScrollListener()
        if (mTimer == null) {
            mTimer = Timer()
        }
        mTimer?.schedule(ScrollTask(), 0, 5)
    }

    private inner class ScrollTask : TimerTask() {
        override fun run() {
            mHandler.post(runnable)
        }
    }

    private val runnable = Runnable {
        if (isTopHidden) {
            if (this::layoutIsSticky.isInitialized) {
                layoutIsSticky(true)
            }
        } else {
            if (this::layoutIsSticky.isInitialized) {
                layoutIsSticky(false)
            }
        }
    }

    /**
     * 停止滚动监听
     */
    private fun stopScrollListener() {
        //取消定时器
        if (mTimer != null) {
            mTimer?.cancel()
            mTimer = null
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopScrollListener()
    }
}
