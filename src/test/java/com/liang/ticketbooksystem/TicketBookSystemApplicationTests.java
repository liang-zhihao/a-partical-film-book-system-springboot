package com.liang.ticketbooksystem;

import com.liang.ticketbooksystem.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketBookSystemApplicationTests {
    public static void main(String[] args) {
        User user = new User();
        method(user);
        System.out.println(user.getEmail());
    }

    public static void method(User user) {
        user.setEmail("123123");
    }

    //    @Test
//    void contextLoads() {
//        System.out.println();
//        int[] a = linearFib(5);
//        System.out.println(a);
//        for (int i : a
//        ) {
//            System.out.println(i);
//
//        }
//    }
//
//    public static int[] linearFib(int n) {
//        if (n <= 1) {
//            return (new int[]{n, 0});
//        } else {
//            int[] F = linearFib(n - 1);
//            return (new int[]{F[0] + F[1], F[0]});
//        }
//    }
    @Test
    public void test() {

    }
}
