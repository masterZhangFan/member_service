package cn.gaozheng.sales.convert;

import cn.gaozheng.sales.DurationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * DurationTypeConverterFactory
 *
 * @author Cheng Bo
 * @version 1.0
 */
public class StringToDurationType implements Converter<String, DurationType> {
    private Class<DurationType> targerType;

    public StringToDurationType(Class<DurationType> targerType) {
        this.targerType = targerType;
    }

    @Override
    public DurationType convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        return StringToDurationType.getIEnum(this.targerType, source);
    }

    private static DurationType getIEnum(Class<DurationType> targerType, String source) {
        for (DurationType enumObj : targerType.getEnumConstants()) {
            if (source.equals(String.valueOf(enumObj.getValue()))) {
                return enumObj;
            }
        }
        throw new IllegalArgumentException(String.format("周期值: '%s' 非法.", source));
    }
}
