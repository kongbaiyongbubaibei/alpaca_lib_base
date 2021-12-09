package com.alpaca.library_base.net;

import com.alpaca.library_base.module.BaseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a on 2017/5/16.
 */

public interface BaseService {
    /**
     * 事件统计
     */
    @GET("api/user/saveUserOperation")
    Observable<BaseData> onEvent(@Query("operation") String operation, @Query("extendString") String extendString);


}
