package org.example.myblog.util;

import org.apache.commons.lang3.StringUtils;
import org.example.myblog.entity.User;

import cn.hutool.jwt.JWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;

public class JWTUtil {
    private static final byte[] KEY = "daotian".getBytes();
    /**
     * 过期时间（秒）
     */
    public static final long EXPIRE = 12 * 60 * 60;

    private JWTUtil() {
    }

    /**
     * 根据 userDto 生成 token
     *
     * @param user    用户信息
     * @return token
     */
    public static String generateTokenForUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        return generateToken(map);
    }



    public static int getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            return Integer.parseInt(claims.get("id", String.class));
        } catch (Exception e) {
            // JWT 解析失败，返回一个默认值或者抛出异常
            e.printStackTrace();
            return -1; // 或者抛出自定义异常
        }
    }



    /**
     * 根据 map 生成 token 默认：HS265(HmacSHA256)算法
     *
     * @param map    携带数据
     * @return token
     */
    public static String generateToken(Map<String, Object> map) {
        JWT jwt = JWT.create();
        // 设置携带数据
        map.forEach(jwt::setPayload);
        // 设置密钥
        jwt.setKey(KEY);
        // 设置过期时间
        jwt.setExpiresAt(new Date(System.currentTimeMillis() + EXPIRE * 1000));
        return jwt.sign();
    }

    /**
     * token 校验
     * @param token token
     * @return 是否通过校验
     */
    public static boolean verify(String token) {
        if (StringUtils.isBlank(token))
            return false;
        return JWT.of(token).setKey(KEY).verify();
    }
}
