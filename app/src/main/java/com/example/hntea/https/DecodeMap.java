package com.example.hnTea.https;

import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 太能 on 2016/12/21.
 */
public class DecodeMap {

    public static Map<String, String> getParam(TreeMap<String, String> treeMap) throws NoSuchAlgorithmException {
        Map<String, String> map = new HashMap<String, String>();
        String sign = DecodeMap.getSign(treeMap);
        map.putAll(treeMap);
        map.put("sign",sign);
        String json = JsonUtils.toJsonString(map);
        Map<String, String> endMap = new HashMap<String, String>();
        endMap.put("param",json);
        return endMap;
    }


    public static String getSign(TreeMap<String, String> treeMap) throws NoSuchAlgorithmException {
        //得到一个排序过后的map
        Map<String, String> resultMap = sortMapByKey(treeMap);
        //得到string
        String result = transMapToString(resultMap);
        //拼接app_key
        String endString = result + "&henantaineng";
//        Log.i("volleyError",endString);
//        Log.i("volleyError",MD5.getMD5(endString));
        return MD5.getMD5(endString);
    }
//       使用 Map按key进行排序
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    private static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str2.compareTo(str1);
        }
    }

    //将map转化成string
    public static String transMapToString(Map map) {
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (java.util.Map.Entry) iterator.next();
            sb.append(entry.getKey().toString()).append("=").append(null == entry.getValue() ? "" :
                    entry.getValue().toString()).append(iterator.hasNext() ? "&" : "");
        }
        return sb.toString();
    }

}
