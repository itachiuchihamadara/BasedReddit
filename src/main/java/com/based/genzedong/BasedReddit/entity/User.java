package com.based.genzedong.BasedReddit.entity;


import lombok.Data;

import java.sql.Date;

@Data
public class User {
    Long userId;
    String userName;
    Date joinDate;
    Boolean isActive;
    Integer karma;
    char[] password;
}
