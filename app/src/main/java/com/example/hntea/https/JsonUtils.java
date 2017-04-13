package com.example.hnTea.https;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by 太能 on 2016/12/21.
 */
public class JsonUtils {
    private static Gson mGson = new Gson();

    /**把一个Object类型的数据转换为 Json格式的字符串
     * @param object ：
     * @return ：
     */
    public static String toJsonString(Object object) {
        return mGson.toJson(object);
    }

    /**
     * @param jsonString : json字符串
     * @param c ：Class类型
     * @param <T> ：泛型
     * @return ：T所代表的类的对象
     */
    public static <T> T parseArray(String jsonString, Class<T> c) {
        return mGson.fromJson(jsonString, c);
    }

    /**
     * 解析json字符串成为一个 集合
     *
     * @param jsonArrayString ：
     * @param <T>             ：泛型，集合中的元素类型
     * @return ： list
     */
    public static <T> List<T> parseArray(String jsonArrayString, TypeToken<List<T>> type) {
        return mGson.fromJson(jsonArrayString,type.getType());
    }
}
