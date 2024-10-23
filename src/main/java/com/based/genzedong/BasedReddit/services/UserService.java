package com.based.genzedong.BasedReddit.services;

import com.based.genzedong.BasedReddit.daos.UserDAO;
import com.based.genzedong.BasedReddit.entity.User;
import com.based.genzedong.BasedReddit.entity.UserProfileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.based.genzedong.BasedReddit.utils.Constants.BANNER_IMAGE_PATH;
import static com.based.genzedong.BasedReddit.utils.Constants.PROFILE_IMAGE_PATH;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final File bannerImage = new File(BANNER_IMAGE_PATH);
    private final File profileImage = new File(PROFILE_IMAGE_PATH);


    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void addNewUser(User user) throws IOException {
        Long userId = this.userDAO.addUser(user);
        this.addDefaultUserProfile(userId);
    }
    private void addDefaultUserProfile(Long userId) throws IOException {
        this.userDAO.addDefaultUserProfile(userId, Files.readAllBytes(profileImage.toPath()),  Files.readAllBytes(bannerImage.toPath()));
    }

    public UserProfileConfig getProfileConfig(Long userId) throws IOException {
        return this.userDAO.getProfileConfig(userId);
    }


}
