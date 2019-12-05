package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.service.NetworkService;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;

@Service
public class NetworkServiceImpl implements NetworkService {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    @Override
    public String sendGet(String url) {
        String str = "";
        try{
            str = Request.Get(url)
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .addHeader(HTTP.CONTENT_ENCODING,"UTF-8")
                    .execute().returnContent().asString();
        }
        catch (Exception ex)
        {

        }
        return str;
    }
    @Override
    public String sendPost(String urlString, String param) {
        String str = "";
        try{
            str =  Request.Post(urlString)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .addHeader(HTTP.CONTENT_ENCODING,"UTF-8")
                    .bodyString(param, ContentType.APPLICATION_JSON).execute().returnContent().asString();
        }
        catch (Exception ex)
        {

        }
        return str;
    }
}
