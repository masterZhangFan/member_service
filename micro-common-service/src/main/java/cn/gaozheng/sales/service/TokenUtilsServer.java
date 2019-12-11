package cn.gaozheng.sales.service;


import cn.gaozheng.sales.model.vo.base.TokenModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface TokenUtilsServer {

    /**
     * 生成Token
     * @param idValue
     * @param formatDate
     * @return
     */
     String generateTokenString( long idValue, String formatDate );
    /**
     * 获取Token对象,如果错误，抛出异常
     * @param tokenString
     * @return
     */
     TokenModel getTokenModel( String tokenString );

     Long uid( HttpServletRequest request);

}
