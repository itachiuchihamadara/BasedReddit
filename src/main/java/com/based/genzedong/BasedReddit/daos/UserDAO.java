package com.based.genzedong.BasedReddit.daos;

import com.based.genzedong.BasedReddit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.based.genzedong.BasedReddit.utils.Constants.INSERT_USER;

@Repository
public class UserDAO {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public void addUser(User user){

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userName", user.getUserName());
        mapSqlParameterSource.addValue("joinDate", user.getJoinDate());
        mapSqlParameterSource.addValue("isActive", user.getIsActive());
        mapSqlParameterSource.addValue("karmaNumber", user.getKarma());
        mapSqlParameterSource.addValue("password", user.getPassword());

        namedParameterJdbcTemplate.update(INSERT_USER, mapSqlParameterSource);
    }
}
