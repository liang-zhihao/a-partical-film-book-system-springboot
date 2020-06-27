package com.liang.ticketbooksystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User {
    private String username;
    private String email;
    private String password;
    private int tel;
    private int isStudent;
    private String portrait;
    @TableId(value = "user_id", type = IdType.AUTO)
    private int userId;
    @TableField(exist = false)
    private List<String> roles;
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tel=" + tel +
                ", isStudent=" + isStudent +
                ", portrait='" + portrait + '\'' +
                ", userId=" + userId +
                '}';
    }

    public List<String> getRoles() {
        return roles;
    }
}
