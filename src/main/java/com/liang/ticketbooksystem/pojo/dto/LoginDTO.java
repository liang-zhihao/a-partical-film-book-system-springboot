package com.liang.ticketbooksystem.pojo.dto;

import com.liang.ticketbooksystem.pojo.User;
import lombok.Data;

@Data
public class LoginDTO {
    private String accessToken;
    private User user;

}
