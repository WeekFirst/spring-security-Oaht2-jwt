package com.mca.server.entity;

import lombok.Data;

/**
 * @Author an Stark
 * @ClassName UserInfo
 * @Description TODO
 * @date 2021/6/24 下午12:29
 * @Version 1.0
 */
@Data
public class UserInfoDTO {

    private Long id;

    private String userName;

    private String userPassword;

    private String userAuthorities;

    private String userNick;

    private String userImg;

}
