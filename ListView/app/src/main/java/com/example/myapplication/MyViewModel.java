package com.example.myapplication;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> mData;
    private List<Integer> currentList;

    public MyViewModel() {
        mData = new MutableLiveData<>();
        currentList = new ArrayList<Integer>();
    }

    public LiveData<Integer> getNumbers() {
        return mData;
    }

    public void increaseNumber() {
        int number = currentList.size();
        currentList.add(number);
        System.out.println(mData.getValue());
        mData.setValue(number);
    }
    public void updateData(int number) {
        mData.setValue(number);
    }
}

