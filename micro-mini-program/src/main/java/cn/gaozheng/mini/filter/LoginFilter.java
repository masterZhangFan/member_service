package cn.gaozheng.mini.filter;

import cn.gaozheng.mini.util.SessionUtil;
import cn.gaozheng.sales.model.vo.base.RespCode;
import cn.gaozheng.sales.model.vo.base.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * LoginFilter
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Slf4j
@Component
@Order(1)
public class LoginFilter implements Filter {

    private String[] loginExcludes;
    private String[] applyExcludes;

    private ObjectMapper objectMapper;

    @Value("${system.default.file.upload-dir}")
    private String uploadDir;
    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public void init(FilterConfig filterConfig) {
        String mobileFile = String.format("/%s/", uploadDir);
        loginExcludes = new String[]{
                "/login/sendSms",
                "/login/signIn",
                "/login/wxToken",
                "/file/upload",
                "/swagger-ui.html",
                mobileFile
        };
        applyExcludes = new String[]{"/company"};
        objectMapper = new ObjectMapper();
        log.info("init LoginFilter!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String originHeader = httpServletRequest.getHeader("Origin");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", originHeader);
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "0");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("XDomainRequestAllowed","1");
        httpServletResponse.setHeader("XDomainRequestAllowed","1");
        String uri = httpServletRequest.getRequestURI();
        log.info("Request URI: {}", uri);
        boolean isMatch = isMatch(uri, this.loginExcludes);
        if (isMatch) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = httpServletRequest.getSession(true);
        Object user = SessionUtil.getSignInClient(session);
        if (user == null) {
            write(httpServletResponse, RespCode.NOT_SIGNED_IN,"未登录不允许该操作");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        loginExcludes = null;
        applyExcludes = null;
    }

    private boolean isMatch(String uri, String[] prefixes) {
        for (String match : prefixes) {
            if (uri.startsWith(match)) {
                return true;
            }
        }
        return false;
    }

    private void write(HttpServletResponse httpServletResponse, int code, String msg) throws IOException {
        RestResponse<String> restResponse = RestResponse.getInstance(code, msg);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        String result = objectMapper.writeValueAsString(restResponse);
        httpServletResponse.getWriter().write(result);
    }
}
