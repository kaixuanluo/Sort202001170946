package com.android2droid.sort202001170946;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.android2droid.sort202001170946.sort.BubbleSort;
import com.android2droid.sort202001170946.sort.SortCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author created by luokaixuan
 * @date 2020/1/17
 * 这个类是用来干嘛的
 */
public class ChartView extends View {

    // 音频矩形的数量
    private int mRectCount = 30;
    // 音频矩形的画笔
    private Paint mRectPaint, mRectPaint2, mRectPaint3;
    // 渐变颜色的两种
    private int topColor, downColor;
    // 音频矩形的宽和高
    private int mRectWidth, mRectHeight;
    // 偏移量
    private int mSpace;
    // 频率速度
    private int mInterval;

    int[] mShuffleArray;

    private String TAG = ChartView.class.getSimpleName();

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mShuffleArray = initShuffleArray();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ChartView);
        setPaint(context, ta);
        setOther(context, ta);
        // 回收TypeArray
        ta.recycle();
    }

    public void setPaint(Context context, TypedArray ta) {
        // 将属性存储到TypedArray中
        mRectPaint = new Paint();
        // 添加矩形画笔的基础颜色
        mRectPaint.setColor(ta.getColor(R.styleable.ChartView_chartTopColor,
                ContextCompat.getColor(context, R.color.colorAccent)));
    }

    private void setOther(Context context, TypedArray ta) {
        // 添加矩形渐变色的上面部分
        topColor = ta.getColor(R.styleable.ChartView_chartTopColor,
                ContextCompat.getColor(context, R.color.colorAccent));
        // 添加矩形渐变色的下面部分
        downColor = ta.getColor(R.styleable.ChartView_chartBottomColor,
                ContextCompat.getColor(context, R.color.colorPrimary));
        // 设置重绘的时间间隔，也就是变化速度
        mInterval = ta.getInt(R.styleable.ChartView_chartInterval, 300);
        // 每个矩形的间隔
        mSpace = ta.getDimensionPixelSize(R.styleable.ChartView_chartSpace, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                12, getResources().getDisplayMetrics()));
//        getInt(R.styleable.ChartView_chartOffset, 5);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        // 渐变效果
        LinearGradient mLinearGradient;
        // 画布的宽
        int mWidth;
        // 获取画布的宽
        mWidth = getWidth();
        // 获取矩形的最大高度
        mRectHeight = getHeight();
        // 获取单个矩形的宽度(减去的部分为到右边界的间距)
        mRectWidth = (mWidth - mSpace) / mRectCount;
        // 实例化一个线性渐变
//        mLinearGradient = new LinearGradient(
//                0,
//                0,
//                mRectWidth,
//                mRectHeight,
//                topColor,
//                topColor,
//                Shader.TileMode.CLAMP
//        );
//        // 添加进画笔的着色器
//        mRectPaint.setShader(mLinearGradient);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        double mRandom;
//        float currentHeight;
//        for (int i = 0; i < mRectCount; i++) {
//            // 由于只是简单的案例就不监听音频输入，随机模拟一些数字即可
//            mRandom = Math.random();
//            currentHeight = (float) (mRectHeight * mRandom);
//
//            // 矩形的绘制是从左边开始到上、右、下边（左右边距离左边画布边界的距离，上下边距离上边画布边界的距离）
//            canvas.drawRect(
//                    (float) (mRectWidth * i + mSpace),
//                    currentHeight,
//                    (float) ( mRectWidth * (i + 1)),
//                    mRectHeight,
//                    mRectPaint
//            );
//        }
//        // 使得view延迟重绘
//        postInvalidateDelayed(mInterval);
//    }
//

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float currentHeight;
        for (int i = 0; i < mShuffleArray.length; i++) {
            // 由于只是简单的案例就不监听音频输入，随机模拟一些数字即可
            double part = new BigDecimal((float) mShuffleArray[i] / mRectCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            currentHeight = (float) (mRectHeight * part);

            if (i == mIPosition) {
                mRectPaint.setColor(getResources().getColor(R.color.color5));
            } else if (i == mJPosition) {
                mRectPaint.setColor(getResources().getColor(R.color.color4));
            } else if (i == mJNextPosition) {
                mRectPaint.setColor(getResources().getColor(R.color.colorPrimary));
            } else {
                mRectPaint.setColor(getResources().getColor(R.color.color2));
            }

            // 矩形的绘制是从左边开始到上、右、下边（左右边距离左边画布边界的距离，上下边距离上边画布边界的距离）
            canvas.drawRect(
                    (float) (mRectWidth * i + mSpace),
                    currentHeight,
                    (float) (mRectWidth * (i + 1)),
                    mRectHeight,
                    mRectPaint
            );
        }
    }

    public int[] initShuffleArray() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < mRectCount; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
//        Integer[] a = new Integer[]{};

        int[] b = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            Integer integer = integers.get(i);
            int j = integer.intValue();
            b[i] = j;
        }
        return b;
    }

    int mIPosition, mJPosition, mJNextPosition;

    public void startSort() {
        HandlerThread sort = new HandlerThread("sort");
        sort.start();
        Handler handler = new Handler(sort.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                BubbleSort bubbleSort = new BubbleSort();
                bubbleSort.setArray(mShuffleArray);
                bubbleSort.setSortCallback(new SortCallback() {
                    @Override
                    public void callbackIPosition(int iPosition) {
                        mIPosition = iPosition;
                    }

                    @Override
                    public void callbackJPosition(int jPosition) {
                        mJPosition = jPosition;
                    }

                    @Override
                    public void callbackJNextPosition(int jNextPosition) {
                        mJNextPosition = jNextPosition;
                    }

                    @Override
                    public void update() {
                        postInvalidate();
                    }
                });
                bubbleSort.start();
            }
        });
    }
}
