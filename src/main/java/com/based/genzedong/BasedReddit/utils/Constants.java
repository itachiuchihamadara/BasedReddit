package com.based.genzedong.BasedReddit.utils;

public interface Constants {

    String POSTGRES_DRIVER_CLASS = "org.postgresql.Driver";

    String INSERT_USER = """
            
            INSERT INTO users(
            	username, joindate, is_active, karma_number, user_password)
            	VALUES (?, ?, ?, ?, ?)
            RETURNING user_id
            """;

    String INSERT_USER_PROFILE = """
            INSERT INTO user_profile_configs(user_id, profile_image, banner_image)
            VALUES (?, ?, ?)
            """;

    String BANNER_IMAGE_PATH = "src/main/resources/static/free-photo-of-surface-of-the-sea.jpeg";

    String PROFILE_IMAGE_PATH = "src/main/resources/static/profile.jpeg";

    String GET_PROFILE_CONFIG =  """
                SELECT user_id, user_bio, user_location, user_dob, profile_image, banner_image
                FROM public.user_profile_configs WHERE USER_ID = :userId
        """;


}
