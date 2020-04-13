package com.liang.ticketbooksystem;

import org.apache.ibatis.io.Resources;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@MapperScan("com.liang.ticketbooksystem.mapper")
public class TicketBookSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketBookSystemApplication.class, args);
    }

}
