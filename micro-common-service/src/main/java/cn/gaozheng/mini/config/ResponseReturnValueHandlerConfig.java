package cn.gaozheng.mini.config;

import cn.gaozheng.mini.handler.ResponseMethodReturnValueHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cheng Bo
 * @version 1.0
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class ResponseReturnValueHandlerConfig implements InitializingBean {

    private RequestMappingHandlerAdapter handlerAdapter;

    @Bean
    public ResponseMethodReturnValueHandler getFilterMethodReturnValueHandler() {
        return new ResponseMethodReturnValueHandler();
    }

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> list = handlerAdapter.getReturnValueHandlers();
        if (null != list) {
            List<HandlerMethodReturnValueHandler> newList = new ArrayList<>(list.size() + 1);
            for (HandlerMethodReturnValueHandler valueHandler : list) {
                if (valueHandler instanceof RequestResponseBodyMethodProcessor) {
                    log.info("Registering FilterMethodReturnValueHandler before RequestResponseBodyMethodProcessor to returnValueHandlers!");
                    newList.add(getFilterMethodReturnValueHandler());
                }
                newList.add(valueHandler);
            }
            handlerAdapter.setReturnValueHandlers(newList);
        }


    }
}