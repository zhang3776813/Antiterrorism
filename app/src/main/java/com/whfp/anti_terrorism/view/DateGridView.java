package com.whfp.anti_terrorism.view;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * 因为android没有提供直接禁止Gridview滑动的API，
 * 也没有提供相应的属性来在XML布局文件中直接禁止滑动，
 * 当我们做菜单时要禁止Gridview上下滑动怎么办呢？ 
 * 1、自定义一个Gridview 
 * 2、通过重新dispatchTouchEvent方法来禁止滑动
 * 
 * @author lake
 */
public class DateGridView extends GridView {


	/** 触摸时按下的点 **/
	PointF downP = new PointF();
	/** 触摸时当前的点 **/
	PointF curP = new PointF();
	/** 点击事件 */

	public DateGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DateGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DateGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 设置不滚动
	 * 其中onMeasure函数决定了组件显示的高度与宽度；
	 * makeMeasureSpec函数中第一个函数决定布局空间的大小，第二个参数是布局模式
	 * MeasureSpec.AT_MOST的意思就是子控件需要多大的控件就扩展到多大的空间
	 * 之后在ScrollView中添加这个组件就OK了，同样的道理，ListView也适用。
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

	// 通过重新dispatchTouchEvent方法来禁止滑动
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 每次进行onTouch事件都记录当前的按下的坐标
		curP.x = event.getX();
		curP.y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// 记录按下时候的坐标
			// 切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
			downP.x = event.getX();
			downP.y = event.getY();
			// 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
			getParent().requestDisallowInterceptTouchEvent(true);
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰

			if (Math.abs(curP.y - downP.y) > Math.abs(curP.x - downP.x)) {

				getParent().requestDisallowInterceptTouchEvent(false);

			} else {
				getParent().requestDisallowInterceptTouchEvent(true);
			}
		}
		return super.onTouchEvent(event);
	}
}
