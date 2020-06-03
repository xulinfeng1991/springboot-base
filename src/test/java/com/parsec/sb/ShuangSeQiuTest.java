package com.parsec.sb;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShuangSeQiuTest {

    /*
    幸运数字：
    1,10,19,20,29,32
    8
     */

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            getNumber();
        }
    }

    //红球一共6组，每组从1-33中抽取一个，六个互相不重复。然后蓝球是从1-16中抽取一个数字，这整个组成的双色球
    public void getNumber() {
        Set<Integer> redBall = new TreeSet<>();
        while (redBall.size() < 6) {
            redBall.add(RandomUtils.nextInt(1, 34));
        }
        System.out.print("开奖：");
        for (Integer number : redBall) {
            if (number < 10) {
                System.out.print("0" + number + " ");
            } else {
                System.out.print(number + " ");
            }
        }
        Integer blue = RandomUtils.nextInt(1, 17);
        if (blue < 10) {
            System.out.print("_0" + blue + "\n");
        } else {
            System.out.print("_" + blue + "\n");
        }

    }
}
