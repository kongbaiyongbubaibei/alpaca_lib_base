package com.alpaca.library_base.module;

/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/10/23
 */
public class BaseDataT<T> extends BaseData {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
