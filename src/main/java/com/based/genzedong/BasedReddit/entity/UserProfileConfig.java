package com.based.genzedong.BasedReddit.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserProfileConfig implements Serializable {

    Long userId;
    String userBio;
    String userLocation;
    Date dateOfBirth;
    MultipartFile profileImage;
    MultipartFile bannerImage;
}
