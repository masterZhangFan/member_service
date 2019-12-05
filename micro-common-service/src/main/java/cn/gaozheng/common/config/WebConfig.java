package cn.gaozheng.common.config;

import cn.gaozheng.util.DateUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

/**
 * Web Config
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Configuration
@Slf4j
public class WebConfig implements WebApplicationInitializer {

    @Bean(name = "mapperObject")
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND);
        objectMapper.setDateFormat(dateFormat);
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND)));
        javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND)));
        javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND)));

        javaTimeModule.addSerializer(Date.class,new DateSerializer(false, dateFormat));
        javaTimeModule.addSerializer(Calendar.class,new CalendarSerializer(false, dateFormat));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND)));
        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND)));
        javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT_PATTERN_SECOND)));
        javaTimeModule.addDeserializer(Date.class,new DateDeserializers.DateDeserializer());
        javaTimeModule.addDeserializer(Calendar.class,new DateDeserializers.CalendarDeserializer());
        objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());
        return objectMapper;
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        HashSet<SessionTrackingMode> set = new HashSet<>();
        set.add(SessionTrackingMode.COOKIE);
        set.add(SessionTrackingMode.URL);
        set.add(SessionTrackingMode.SSL);
        servletContext.setSessionTrackingModes(set);
    }
}
