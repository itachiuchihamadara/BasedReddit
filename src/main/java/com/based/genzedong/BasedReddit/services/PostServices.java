package com.based.genzedong.BasedReddit.services;


import com.based.genzedong.BasedReddit.daos.PostDAOs;
import com.based.genzedong.BasedReddit.entity.Posts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PostServices {

    private final PostDAOs postDAOs;


    public PostServices(PostDAOs postDAOs) {
        this.postDAOs = postDAOs;
    }

    public List<Posts> getPostsByUserId(int userid){
        log.info("Retrieving posts for the user with id {}", userid);
        return postDAOs.getPostsByUserId(userid);
    }
}
