package cn.gaozheng.mini.handler;

import cn.gaozheng.common.annotation.JsonInclude;
import cn.gaozheng.common.annotation.JsonIncludes;
import cn.gaozheng.sales.model.vo.base.RespCode;
import cn.gaozheng.sales.model.vo.base.RestResponse;
import cn.gaozheng.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FilterMethodReturnValueHandler
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Slf4j
public class ResponseMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    @SuppressWarnings("unchecked")
    public boolean supportsReturnType(MethodParameter methodParameter) {
        boolean jsonInclude = methodParameter.hasMethodAnnotation(JsonInclude.class);
        boolean jsonIncludes = methodParameter.hasMethodAnnotation(JsonIncludes.class);

        boolean responseBody = (AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ResponseBody.class) ||
                methodParameter.hasMethodAnnotation(ResponseBody.class));
        return responseBody || jsonInclude || jsonIncludes;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        if (request != null)
            log.info("Response URI: {}", request.getRequestURI());

        JacksonUtil jacksonUtil = JacksonUtil.getInstance();
        //Ignore swagger
        if (request != null) {
            String uri = request.getRequestURI();
            if (StringUtils.isNotEmpty(uri)) {
                if (uri.startsWith("/swagger") || uri.startsWith("/v2")) {
                    if (httpServletResponse != null) {
                        httpServletResponse.getWriter().write(jacksonUtil.toJson(o));
                    }
                }
            }
        }

        RestResponse<Object> restResponse;
        if (o instanceof RestResponse) {
            restResponse = (RestResponse) o;
        } else {
            restResponse = RestResponse.getInstance(RespCode.SUCCESS);
            restResponse.setData(o);
        }


        // Json filter
        if (o != null) {
            if (methodParameter.hasMethodAnnotation(JsonInclude.class)) {
                JsonInclude jsonInclude = methodParameter.getMethodAnnotation(JsonInclude.class);
                if (jsonInclude != null)
                    filter(jsonInclude, jacksonUtil);
            }

            if (methodParameter.hasMethodAnnotation(JsonIncludes.class)) {
                JsonIncludes jsonIncludes = methodParameter.getMethodAnnotation(JsonIncludes.class);
                if (jsonIncludes != null) {
                    for (JsonInclude jsonInclude : jsonIncludes.value()) {
                        if (jsonInclude != null)
                            filter(jsonInclude, jacksonUtil);
                    }
                }
            }
        }


        if (httpServletResponse != null) {
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().write(jacksonUtil.toJson(restResponse));
        }
    }

    private void filter(JsonInclude jsonInclude, JacksonUtil jacksonUtil) {
        Class clazz = jsonInclude.filter();
        String[] includes = jsonInclude.includes();
        String[] excludes = jsonInclude.excludes();
        jacksonUtil.filter(clazz, includes, excludes);
    }
}
