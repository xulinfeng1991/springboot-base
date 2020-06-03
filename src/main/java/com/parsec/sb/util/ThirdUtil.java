package com.parsec.sb.util;

import com.parsec.universal.exception.BadRequestException;
import com.parsec.universal.utils.HttpClientUtil;
import org.apache.http.entity.ContentType;

/**
 * Third平台，短信验证和图片验证服务
 */
public class ThirdUtil {
    /**
     * 图片验证（调用骥叔封装的图片验证微服务）
     * https://third.parsec.com.cn/piccapt/page/check
     *
     * @param clientCode
     * @param captchaCode
     * @return
     */
    public static void check(String clientCode, String captchaCode) {
        String url = "https://third.parsec.com.cn/piccapt/check";
        String content = "captcha_code=" + captchaCode + "&client_code=" + clientCode;

        String ret = "";
        try {
            ret = HttpClientUtil.postData(url, content, ContentType.APPLICATION_FORM_URLENCODED);
        } catch (Exception e) {
            throw new BadRequestException("校验图片验证码时发生异常");
        }

        if (!"{\"code\": 200, \"message\": \"success\"}".equals(ret)) {
            throw new BadRequestException("验证码不匹配或已过期");
        }
    }

    /**
     * 获取图片验证码
     *
     * @return
     */
    public static String fetch() {
        String url = "https://third.parsec.com.cn/piccapt/fetch";

        String ret = "";
        try {
            ret = HttpClientUtil.postData(url, "", ContentType.APPLICATION_FORM_URLENCODED);
        } catch (Exception e) {
            throw new BadRequestException("获取图片验证码时发生异常");
        }
        return ret;
    }

    /**
     * 校验短信验证码
     *
     * @param phone
     * @param token
     * @param captcha
     */
    public static void checkSMS(String phone, String token, String captcha) {
        String url = "http://third.parsec.com.cn/sms/check";
        String content = "phone=" + phone + "&token=" + token + "&captcha=" + captcha;

        String ret = "";
        try {
            ret = HttpClientUtil.postData(url, content, ContentType.APPLICATION_FORM_URLENCODED);
        } catch (Exception e) {
            throw new BadRequestException("校验短信验证码时发生异常");
        }

        if (!"{\"code\": 200, \"message\": \"success\"}".equals(ret)) {
            throw new BadRequestException("验证码不匹配或已过期");
        }
    }

    /**
     * 获取短信验证码
     *
     * @return
     */
    public static String fetchSMS(String phone, String token) {
        String url = "http://third.parsec.com.cn/sms/send";
        String content = "phone=" + phone + "&token=" + token;

        String ret = "";
        try {
            ret = HttpClientUtil.postData(url, content, ContentType.APPLICATION_FORM_URLENCODED);
        } catch (Exception e) {
            throw new BadRequestException("获取短信验证码时发生异常");
        }
        return ret;
    }
}
