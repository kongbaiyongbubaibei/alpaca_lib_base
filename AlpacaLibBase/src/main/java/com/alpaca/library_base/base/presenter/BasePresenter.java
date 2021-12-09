package com.alpaca.library_base.base.presenter;


import com.alpaca.library_base.base.view.IBaseView;

public class BasePresenter<T extends IBaseView> extends MvpPresenter<T> {
    public BasePresenter(T mView) {
        super(mView);
    }
}
