package top.cocoawork.util;


public class BeanUtil {

    public static void convert(Object sourceObj, Object destObj){
        if (null != sourceObj) {
            cn.hutool.core.bean.BeanUtil.copyProperties(sourceObj, destObj);
        }
    }

}
