package com.based.genzedong.BasedReddit;

public interface Constants {

    String POSTGRES_DRIVER_CLASS = "org.postgresql.Driver";

    String GET_POST_BY_USER_ID = "SELECT * FROM POSTS WHERE USERID = :userid";
}
