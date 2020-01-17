package com.android2droid.sort202001170946.sort;

/**
 * @author created by luokaixuan
 * @date 2020/1/17
 * 这个类是用来干嘛的
 */
public class SelectionSort extends BaseSort{

    @Override
    public void start() {
        super.start();
        bubbleSort();
    }

    public void bubbleSort() {
//        for (int i = 1; i < mmArrayay.length; i++) {
//            setIPosition(i);
//            for (int j = 0; j < mmArrayay.length - i; j++) {
//                int k = j + 1;
//                if (mmArrayay[j] > mmArrayay[k]) {
//                    int temp = mmArrayay[j];
//                    setJPosition(j);
//                    mmArrayay[j] = mmArrayay[k];
//                    mmArrayay[k] = temp;
//                    setJNextPosition(k);
//                    update();
//                }
//            }
//        }
    }

    public void selectionSort(){
        for (int i = 0; i < mArray.length - 1; i++) {
            int  min = i;
            for (int j = i + 1; j < mArray.length; j++) {
                if (mArray[min] > mArray[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = mArray[min];
                mArray[min] = mArray[i];
                mArray[i] = tmp;
            }
        }

    }
}
