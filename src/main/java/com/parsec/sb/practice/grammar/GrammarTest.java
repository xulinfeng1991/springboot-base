package com.parsec.sb.practice.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试结论
 * （1）foreach循环null列表会出错，mybatis正常查询出的列表，至少是empty列表，所以可以直接foreach循环
 */
public class GrammarTest {

    public static void testNullListForEach() {
        List<String> list = null;
        for (String str : list) {
            System.out.println(str);
        }
    }

    public static void testEmptyListForEach() {
        List<String> list = new ArrayList<>();
        for (String str : list) {
            System.out.println(str);
        }
    }

    /**
     * Exception in thread "main" java.lang.NumberFormatException: For input string: "我不是一个Double值"
     * Exception in thread "main" java.lang.NullPointerException
     */
    public static void testParseDouble(){
        System.out.println(Double.parseDouble("我不是一个Double值"));
        System.out.println(Double.parseDouble(null));
    }

    public static void floatString(Integer discount){
        Float f = discount/10f;
        String str = f.toString();
        str = str.replaceAll(".0", "") + "折";
        System.out.println(str);
    }


    public static void main(String[] args) {
//        //Exception in thread "main" java.lang.NullPointerException
//        testNullListForEach();
//        //OK
//        testEmptyListForEach();


    }
}
