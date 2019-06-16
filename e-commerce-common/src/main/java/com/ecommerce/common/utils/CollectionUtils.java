package com.ecommerce.common.utils;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-03-26 22:09
 */
public class CollectionUtils {

    public static <T> T getOne(List<T> list) {
        return isNotEmpty(list) ? list.get(0) : null;
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return isNotNull(coll) && coll.size() > 0;
    }

    /**
     * @param coll
     *            要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

}
