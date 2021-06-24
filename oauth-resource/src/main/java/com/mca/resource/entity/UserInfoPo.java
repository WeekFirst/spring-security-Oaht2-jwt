package com.mca.resource.entity;

import lombok.Data;

/**
 * @Author an Stark
 * @ClassName UserInfoPo
 * @Description TODO
 * @date 2021/6/24 下午4:13
 * @Version 1.0
 */
@Data
public class UserInfoPo {

    private Long id;

    private String userName;

    private String userAuthorities;

    private String userNick;

    private String userImg;


}
