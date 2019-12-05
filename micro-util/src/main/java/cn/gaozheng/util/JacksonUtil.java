package cn.gaozheng.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.gaozheng.util.DateUtil.DEFAULT_FORMAT_PATTERN_SECOND;

/**
 * Jackson Util
 *
 * @author Cheng Bo
 * @version 1.0
 */
public final class JacksonUtil {

    private static final ObjectMapper mapper;

    private JacksonFilter jacksonFilter = new JacksonFilter();

    private JacksonUtil() {

    }

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND);
        mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);

        // 允许对象忽略json中不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许出现特殊字符和转义符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);

        mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
        mapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());


        SimpleModule serializerModule = new SimpleModule("DateSerializer", PackageVersion.VERSION);
        serializerModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        serializerModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(serializerModule);

    }

    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
            arg1.writeString(DateUtil.getInstance().format(arg0, DateUtil.DEFAULT_FORMAT_PATTERN_SECOND));
        }
    }

    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {
            return LocalDateTime.parse(arg0.getText(), DateTimeFormatter.ofPattern(DEFAULT_FORMAT_PATTERN_SECOND));
        }
    }

    public static JacksonUtil getInstance() {
        return new JacksonUtil();
    }

    public void filter(Class<?> clazz, String[] includes, String[] filters) {
        if (clazz == null)
            return;
        if (ArrayUtils.isNotEmpty(includes)) {
            jacksonFilter.include(clazz, includes);
        }
        if (ArrayUtils.isNotEmpty(filters)) {
            jacksonFilter.filter(clazz, filters);
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object obj) {
        try {
            mapper.setFilterProvider(jacksonFilter);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Convert to Json is failed!", e);
        }
    }

    public <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Convert to Object is failed!", e);
        }
    }

    public <T> T toObject(String json, TypeReference<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Convert to Object is failed!", e);
        }
    }


    @JsonFilter("jacksonFilter")
    static class JacksonFilter extends FilterProvider {

        private Map<Class<?>, Set<String>> includeMap = new HashMap<>(12);
        private Map<Class<?>, Set<String>> filterMap = new HashMap<>(12);

        void include(Class<?> type, String[] fields) {
            addToMap(includeMap, type, fields);
        }

        void filter(Class<?> type, String[] fields) {
            addToMap(filterMap, type, fields);
        }

        private void addToMap(Map<Class<?>, Set<String>> map, Class<?> type, String[] fields) {
            if (fields != null && fields.length > 0) {
                Set<String> filedSet = new HashSet<>(fields.length);
                filedSet.addAll(Arrays.asList(fields));
                map.put(type, filedSet);
            } else {
                map.put(type, Collections.emptySet());
            }
        }

        @Override
        @Deprecated
        public BeanPropertyFilter findFilter(Object filterId) {
            throw new UnsupportedOperationException("Access to deprecated filters not supported");
        }

        @Override
        public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {

            return new SimpleBeanPropertyFilter() {

                @Override
                public void serializeAsField(Object pojo, JsonGenerator jsonGenerator, SerializerProvider prov, PropertyWriter writer)
                        throws Exception {
                    if (apply(pojo.getClass(), writer.getName())) {
                        writer.serializeAsField(pojo, jsonGenerator, prov);
                    } else if (!jsonGenerator.canOmitFields()) {
                        writer.serializeAsOmittedField(pojo, jsonGenerator, prov);
                    }
                }
            };
        }

        private boolean apply(Class<?> type, String name) {
            Set<String> includeFields = includeMap.get(type);
            Set<String> filterFields = filterMap.get(type);
            if (includeFields != null && includeFields.contains(name)) {
                return true;
            }
            if (filterFields != null && !filterFields.contains(name)) {
                return true;
            } else {
                return (includeFields == null && filterFields == null);
            }
        }
    }
}

