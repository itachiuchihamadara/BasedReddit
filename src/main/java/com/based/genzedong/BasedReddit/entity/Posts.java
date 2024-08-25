package com.based.genzedong.BasedReddit.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    private Integer postID;
    private Integer userID;
    private String postHeader;
    private String postBody;
    private Integer numberOfUpvotes;
    private Integer subPosted;
    private boolean isApproved;

}
