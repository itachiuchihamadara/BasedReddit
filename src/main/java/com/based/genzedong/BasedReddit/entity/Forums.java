package com.based.genzedong.BasedReddit.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Forums {

    Long forumId;
    String forumString;
    Long adminId;
    String forumBio;
    MultipartFile profileImage;
    MultipartFile bannerImage;

}
