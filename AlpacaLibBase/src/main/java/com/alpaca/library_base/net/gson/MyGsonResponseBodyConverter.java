package com.alpaca.library_base.net.gson;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        T result = null;
        String response = null;
        try {
            response = value.string();
            if (!TextUtils.isEmpty(response)) {
                response = response.replace("\"data\":\"\"","\"data\":null");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            result = new GsonBuilder().create().fromJson(response, type);
        } finally {
            value.close();
        }
        return result;
    }
}
