package cn.gaozheng.sales.service.impl;


import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.model.vo.base.TokenModel;
import cn.gaozheng.sales.service.TokenUtilsServer;
import cn.gaozheng.sales.utils.EmptyUtil;
import cn.gaozheng.sales.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenUtilsServerImpl implements TokenUtilsServer {

    public static String splitStr = "&";
    @Override
    public String generateTokenString(long idValue,String formatDate)
    {
        String value = idValue+splitStr+formatDate ;
        value = Md5Utils.MD5Encode(value);
        return value;
    }
    @Override
    public TokenModel getTokenModel( String tokenString){
        String msg = "二维码错误,请重新扫码";
        if (tokenString == null)
        {
            throw new SaleException(msg);
        }
        String baseStr = Md5Utils.MD5Decode(tokenString);
        String[] arraryStrings = baseStr.split(splitStr);
        if (arraryStrings == null || arraryStrings.length != 2)
        {
            throw new SaleException(msg);
        }
        TokenModel tokenModel = new TokenModel();
        tokenModel.setDateFormat(arraryStrings[1]);
        tokenModel.setIdValue(Long.valueOf(arraryStrings[0]));
        return tokenModel;
    }
    @Override
    public Long uid( HttpServletRequest request){
        String token = request.getHeader("token");
        if (!EmptyUtil.isNotEmpty(token)){
            throw new SaleException("请传入token");
        }
        TokenModel tokenModel = getTokenModel(token);
        if (tokenModel == null){
            throw new SaleException("token错误");
        }
        return tokenModel.getIdValue();
    }
}
