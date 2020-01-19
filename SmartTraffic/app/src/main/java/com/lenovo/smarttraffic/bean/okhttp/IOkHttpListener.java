package com.lenovo.smarttraffic.bean.okhttp;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/21
 */
public abstract class IOkHttpListener<T>  {
    Type type;
    protected IOkHttpListener(){
        type=getType(getClass());
    }

    public static Type getType(Class<?> c){
        Type type=c.getGenericSuperclass();
        if(type instanceof Class){
            throw  new RuntimeException("miss");
        }

        ParameterizedType parameterizedType= (ParameterizedType) type;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    public abstract void onJson(T t);
    public abstract void onErr(String err);
}
