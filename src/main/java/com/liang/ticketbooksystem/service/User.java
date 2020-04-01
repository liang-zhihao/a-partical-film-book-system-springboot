package com.liang.ticketbooksystem.service;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String email;
    private String username;
    private String password;
    private String tel;
    private String portrait;
    private int isStudent;

}
