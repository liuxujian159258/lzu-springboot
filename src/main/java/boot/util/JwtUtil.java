package boot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    // 定义加密的密钥
    private static final String SING="LZU";

    // 生成token header.payload.sing
    public static String getToken(Map<String,String> map) {
        Calendar instance = Calendar.getInstance();
        // 设置过期时间
        instance.add(Calendar.HOUR,1);
        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        // 创建payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        // 指定过期时间和签名令牌
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    // 验证token合法性
    /*public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }*/
    // 返回DecodeJWT
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

}
