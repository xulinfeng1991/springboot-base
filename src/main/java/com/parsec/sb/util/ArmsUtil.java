package com.parsec.sb.util;

import com.parsec.universal.exception.BadRequestException;

import java.util.regex.Pattern;

public class ArmsUtil {

    /**
     * 正则验证ID串，防止SQL注入
     *
     * @param ids ID列表
     */
    public static void verifyIds(String ids) {
        boolean flag = Pattern.matches("^\\d+(,\\d+)*$", ids);
        if (!flag) {
            throw new BadRequestException("ID串，参数不合法");
        }
    }

}
