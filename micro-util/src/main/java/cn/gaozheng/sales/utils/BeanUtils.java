package cn.gaozheng.sales.utils;

import cn.gaozheng.sales.exception.SalesException;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtils extends org.springframework.beans.BeanUtils {
    public static void copyPropertiesIgnoreNullValue(Object source, Object target, String... ignoreProperties)
            throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null) {
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties '" + targetPd.getName() +
                                "'  from source to target", ex);
                    }
                }
            }
        }
    }

    public static void copyPropertiesIgnoreNullValue(Object source, Object target) throws BeansException {
        copyPropertiesIgnoreNullValue(source, target, (String[]) null);
    }

    public static <T> T createSingleAndCopyPropertiesIgnoreNullValue(Object source, Class<T> targetClass)
            throws BeansException {
        T targetInstance = createNewInstance(targetClass);
        copyPropertiesIgnoreNullValue(source, targetInstance);
        return targetInstance;
    }

    public static <T> T createSingleAndCopyPropertiesIgnoreNullValue(Object source, Class<T>
            targetClass, String... ignoreProperties)
            throws BeansException {
        T targetInstance = createNewInstance(targetClass);
        copyPropertiesIgnoreNullValue(source, targetInstance, ignoreProperties);
        return targetInstance;
    }

    public static <K, T> List<T> createListAndCopyPropertiesIgnoreNullValue(List<K> sourceList, Class<T> targetClass)
            throws BeansException {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return sourceList.stream().map(source -> {
            T targetInstance = createNewInstance(targetClass);
            copyPropertiesIgnoreNullValue(source, targetInstance);
            return targetInstance;
        }).collect(Collectors.toList());
    }

    public static <K, T> List<T> createListAndCopyPropertiesIgnoreNullValue(List<K> sourceList
            , Class<T> targetClass, String... ignoreProperties)
            throws BeansException {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return sourceList.stream().map(source -> {
            T targetInstance = createNewInstance(targetClass);
            copyPropertiesIgnoreNullValue(source, targetInstance, ignoreProperties);
            return targetInstance;
        }).collect(Collectors.toList());
    }

    private static <T> T createNewInstance(Class<T> targetClass) {
        try {
            return targetClass.newInstance();
        } catch (Throwable ex) {
            throw new FatalBeanException("Could not copy properties '" + targetClass.getName() +
                    "'  from source to target", ex);
        }
    }

    public static class DateFormatUtil {

        public static final SimpleDateFormat SDF_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        public static final SimpleDateFormat SDF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyy-MM-dd");
        public static final SimpleDateFormat SDF_YM = new SimpleDateFormat("yyyy-MM-");
        public static final SimpleDateFormat SDF_D_OF_MONTH = new SimpleDateFormat("dd");
        public static final SimpleDateFormat SDF_Y = new SimpleDateFormat("yyyy");

    }

    public static class ParamCheckUtil {
        public static void checkPage(Integer pageIndex,Integer pageSize){
            if (!EmptyUtil.isNotEmpty(pageIndex)){
                pageIndex = 1;
                throw new SalesException("缺少pageIndex");
            }
            if (!EmptyUtil.isNotEmpty(pageSize)){
                pageSize = 10;
                throw new SalesException("缺少pageSize");
            }
        }

        public static void paramNotNull(Object o,String msg){
            if (o==null){
                throw new SalesException(msg + "为空");
            }
        }

        public static void checkTime(Date fromTime, Date toTime) {
            if (fromTime==null){
                throw new SalesException("fromTime为空 或者格式不匹配yyyy-MM-dd HH:mm:ss");
            }
            if (toTime==null){
                throw new SalesException("toTime为空 或者格式不匹配yyyy-MM-dd HH:mm:ss");
            }
        }
    }
}
