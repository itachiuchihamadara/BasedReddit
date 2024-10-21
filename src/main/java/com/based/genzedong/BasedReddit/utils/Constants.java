package com.based.genzedong.BasedReddit.utils;

public interface Constants {

    String POSTGRES_DRIVER_CLASS = "org.postgresql.Driver";

    String INSERT_USER = """
            
            INSERT INTO users(
            	username, joindate, is_active, karma_number, password)
            	VALUES (:userName, :joinDate, :isActive, :karmaNumber, :password)
            
            """;

}
