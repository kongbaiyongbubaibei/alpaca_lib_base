package com.alpaca.library_base.net;

import com.alpaca.library_base.event.UserCustomEvent;
import com.alpaca.library_base.module.BaseData;
import com.alpaca.library_base.utils.RxSchedulers;

import io.reactivex.Observable;

/**
 * Created by a on 2017/5/16.
 */

public class BaseApiFactory {

    public static Observable<BaseData> onEvent(UserCustomEvent event) {
        return ApiClient.get(HttpUrl.BaseURL.BASE_URL)
                .create(BaseService.class)
                .onEvent(event.getOperation(), event.getExtendString())
                .compose(RxSchedulers.ioMain());
    }
}
