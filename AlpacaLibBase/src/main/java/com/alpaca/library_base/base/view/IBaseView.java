package com.alpaca.library_base.base.view;

public interface IBaseView extends IMvpBaseView {

    void showNormal();

    void showError(String err);

    void showLoading();

    void showEmpty();

    void reload();

    void showToast(String msg);
}
