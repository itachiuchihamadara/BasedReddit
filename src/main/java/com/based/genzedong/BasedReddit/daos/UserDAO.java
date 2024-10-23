package com.based.genzedong.BasedReddit.daos;

import com.based.genzedong.BasedReddit.entity.User;
import com.based.genzedong.BasedReddit.entity.UserProfileConfig;
import com.based.genzedong.BasedReddit.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.based.genzedong.BasedReddit.utils.Constants.*;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public Long addUser(User user){

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {

            PreparedStatement statement =
                    connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setDate(2, user.getJoinDate());
            statement.setBoolean(3, user.getIsActive());
            statement.setInt(4, user.getKarma());
            statement.setString(5, Arrays.toString(user.getPassword()));

            return statement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void addDefaultUserProfile(Long userId, byte[] profile, byte[] banner){

        jdbcTemplate.update(connection -> {

            PreparedStatement statement =
                    connection.prepareStatement(INSERT_USER_PROFILE);

            statement.setLong(1, userId);
            statement.setBytes(2, profile);
            statement.setBytes(3, banner);

            return statement;
        });
    }

    public UserProfileConfig getProfileConfig(Long userId){

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);

        List<UserProfileConfig> userProfileConfigs = namedParameterJdbcTemplate.query(GET_PROFILE_CONFIG, mapSqlParameterSource, new RowMapper<UserProfileConfig>() {

            @Override
            public UserProfileConfig mapRow(ResultSet rs, int rowNum) throws SQLException {

                UserProfileConfig userProfileConfig = new UserProfileConfig();

                userProfileConfig.setUserId(rs.getLong("user_id"));
                userProfileConfig.setUserBio(rs.getString("user_bio"));
                userProfileConfig.setUserLocation(rs.getString("user_location"));
                userProfileConfig.setDateOfBirth(rs.getDate("user_dob"));

                BASE64DecodedMultipartFile profile = new BASE64DecodedMultipartFile(rs.getBytes("profile_image"), "profile");
                userProfileConfig.setProfileImage(profile);

                BASE64DecodedMultipartFile banner = new BASE64DecodedMultipartFile(rs.getBytes("banner_image"), "banner");
                userProfileConfig.setBannerImage(banner);

                return userProfileConfig;
            }
        });

        return userProfileConfigs.get(0);
    }



}
