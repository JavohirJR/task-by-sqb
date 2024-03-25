package com.example.taskbysqb.utils;

import lombok.experimental.UtilityClass;
import wsdl.GenericParam;

import java.util.List;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
@UtilityClass
public class Utils {
    public static String getValueByKey(List<GenericParam> list, String key) {
        return list
                .stream()
                .filter(data -> data.getKey().equals(key))
                .findFirst()
                .map(GenericParam::getValue)
                .orElse(null);
    }
}
