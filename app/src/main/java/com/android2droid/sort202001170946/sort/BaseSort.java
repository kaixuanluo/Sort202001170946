package com.android2droid.sort202001170946.sort;

/**
 * @author created by luokaixuan
 * @date 2020/1/17
 * 这个类是用来干嘛的
 */
public abstract class BaseSort implements Sort {

    private int mIPosition;
    private int mJPosition;
    private int mKPosition;

    public int getIPositioin() {
        return mIPosition;
    }

    public int getJPosition() {
        return mJPosition;
    }

    public int getKPosition() {
        return mKPosition;
    }

    protected void setIPosition(int iPosition) {
        this.mIPosition = iPosition;
        if (mSortCallback != null) {
            mSortCallback.callbackIPosition(iPosition);
        }
    }

    protected void setJPosition(int jPosition) {
        this.mJPosition = jPosition;
        if (mSortCallback != null) {
            mSortCallback.callbackJPosition(jPosition);
        }
    }

    protected void setJNextPosition(int jNextPosition) {
        this.mKPosition = jNextPosition;
        if (mSortCallback != null) {
            mSortCallback.callbackJNextPosition(jNextPosition);
        }
    }

    protected int[] mArray;

    @Override
    public void setArray(int[] array) {
        mArray = array;
    }

    @Override
    public void start() {

    }

    protected void update() {

        if (mSortCallback != null) {
            mSortCallback.update();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    SortCallback mSortCallback;

    public void setSortCallback(SortCallback mSortCallback) {
        this.mSortCallback = mSortCallback;
    }
}
