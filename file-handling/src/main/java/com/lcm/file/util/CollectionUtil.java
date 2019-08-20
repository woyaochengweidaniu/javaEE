package com.lcm.file.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lcm
 */
public class CollectionUtil {


    public static List<Object[]> collectToArray(List<Map<String,Object>> resours){

        List<Object[]> result = new ArrayList<>();
        if (cn.hutool.core.collection.CollectionUtil.isEmpty(resours)){
            return new ArrayList<>();
        }

        for (int i = 0; i <resours.size(); i ++){
            Object[] objects = new Object[8];
            Map<String, Object> map = resours.get(i);
            objects[0]  = validata(map.get("user_name"));
            objects[1] = validata(map.get("age"));
            objects[2] = validata(map.get("sex"));
            objects[3] = validata(map.get("address"));
            objects[4] = validata(map.get("phone"));
            objects[5] = validata(map.get("hobby"));
            objects[6] = validata(map.get("birthday"));
            objects[7] = validata(map.get("create_time"));
            result.add(objects);
        }
        return result;

    }


    public static Object validata(Object o){
        if (ObjectUtil.isNull(o)){
            return null;
        }
        return o;
    }
}
