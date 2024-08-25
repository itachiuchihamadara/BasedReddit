package com.based.genzedong.BasedReddit.daos;

import com.based.genzedong.BasedReddit.entity.Posts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.based.genzedong.BasedReddit.Constants.GET_POST_BY_USER_ID;

@Repository
@Slf4j
public class PostDAOs {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PostDAOs(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Posts> getPostsByUserId(Integer userID){

        HashMap<String, Object> params = new HashMap<>(1);
        params.put("userid", userID);

        List<Posts> posts =
                namedParameterJdbcTemplate.query(GET_POST_BY_USER_ID, params,  new RowMapper<Posts>() {
                    @Override
                    public Posts mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Posts post = new Posts();
                        post.setPostID(rs.getInt("postid"));
                        post.setUserID(rs.getInt("userid"));
                        post.setPostBody(rs.getString("post_body"));
                        post.setPostHeader(rs.getString("post_header"));
                        post.setSubPosted(rs.getInt("sub_posted"));
                        post.setApproved(rs.getBoolean("is_approved"));
                        post.setNumberOfUpvotes(rs.getInt("upvotes"));
                        return post;
                    }
                });

        log.info("The posts are retrieved successfully from db");
        return posts;
    }
}
