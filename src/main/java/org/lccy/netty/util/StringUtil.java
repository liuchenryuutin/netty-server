package org.lccy.netty.util;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2023/04/21 17:15 <br>
 * @author: liuchen11
 */
public final class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        } else {
            return "".equals(str.trim());
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String conver2String(Object obj) {
        if(obj == null) {
            return null;
        }
        if(obj instanceof String) {
            return (String) obj;
        } else {
            return obj.toString();
        }
    }
}
