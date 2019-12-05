package cn.gaozheng.sales.service;

public interface NetworkService {
     String sendGet(String url);
     String sendPost(String urlString, String param);
}
