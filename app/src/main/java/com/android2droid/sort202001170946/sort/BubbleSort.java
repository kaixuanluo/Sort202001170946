package com.android2droid.sort202001170946.sort;

/**
 * @author created by luokaixuan
 * @date 2020/1/17
 * 这个类是用来干嘛的
 */
public class BubbleSort extends BaseSort{

    @Override
    public void start() {
        super.start();
        bubbleSort();
    }

    public void bubbleSort() {
        for (int i = 1; i < mArray.length; i++) {
            setIPosition(i);
            for (int j = 0; j < mArray.length - i; j++) {
                int k = j + 1;
                if (mArray[j] > mArray[k]) {
                    int temp = mArray[j];
                    setJPosition(j);
                    mArray[j] = mArray[k];
                    mArray[k] = temp;
                    setJNextPosition(k);
                    update();
                }
            }
        }
    }
}
