package com.parsec.sb.practice;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

public class CommonsLang3Practice {

    /**
     * commons-lang3包里，比较有用的工具方法整理
     * <p>
     * StringUtils.isAnyEmpty("","aa","bb");
     * StringUtils.capitalize("cat");
     * </p>
     *
     * @param args
     */
    public static void main(String[] args) throws ParseException {

        //验证多个字符串，是否有一个是空的
        StringUtils.isAnyEmpty("", "aa", "bb");

        //首字母大小写转换
        StringUtils.capitalize("cat");//---"Cat"
        StringUtils.uncapitalize("Cat");//---"cat"

        //判断字符串大写、小写
        StringUtils.isAllUpperCase("ABC");//---true
        StringUtils.isAllLowerCase("abC");//---false

        //检查字符串结尾后缀是否匹配
        StringUtils.endsWith("abcdef", "def");//---true
        StringUtils.endsWithIgnoreCase("ABCDEF", "def");//---true
        StringUtils.endsWithAny("abcxyz", new String[]{null, "xyz", "abc"});//---true

        //检查起始字符串是否匹配
        StringUtils.startsWith("abcdef", "abc");//---true
        StringUtils.startsWithIgnoreCase("ABCDEF", "abc");//---true
        StringUtils.startsWithAny("abcxyz", new String[]{null, "xyz", "abc"});//---true

        //判断两字符串是否相同
        StringUtils.equals("abc", "abc");//---true
        StringUtils.equalsIgnoreCase("abc", "ABC");//---true

        //重复字符
        StringUtils.repeat("abc", 3);//---abcabcabc

        //反转字符串
        StringUtils.reverse("bat");//---"tab"

        //随机字符、字符串相关的工具类
        System.out.println(RandomStringUtils.randomNumeric(8));
        System.out.println(RandomUtils.nextInt(0, 9));
        System.out.println(RandomStringUtils.random(6, "abcdefghijk"));

        //日期加n天
        DateUtils.addDays(new Date(), 1);
        //判断是否同一天
        DateUtils.isSameDay(new Date(), new Date());
        //字符串时间转换为Date
        DateUtils.parseDate("2020-06-02", "yyyy-MM-dd");


    }
}
