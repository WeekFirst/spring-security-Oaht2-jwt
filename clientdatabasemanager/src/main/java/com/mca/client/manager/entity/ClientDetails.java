package com.mca.client.manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author an Stark
 * @ClassName ClientDetails
 * @Description TODO
 * @date 2021/6/24 下午7:25
 * @Version 1.0
 */
@Data
@TableName("oauth_client_details")
public class ClientDetails {

    @NotEmpty(message = "can not be empty")
    @TableField(value = "client_id")
    private String clientID;

    @TableField(value = "resource_ids")
    private String resourceIDs;

    @TableField(value = "client_secret")
    private String clientSecret;

    @TableField(value = "scope")
    private String scope;

    @TableField(value = "authorized_grant_types")
    private String authorizedGrantTypes;

    @TableField(value = "web_server_redirect_uri")
    private String webServerRedirectUri;

    @TableField(value = "authorities")
    private String authorities;

    @TableField(value = "additional_information")
    private String additionalInformation;

    @TableField(value = "autoapprove")
    private String autoapprove;

    @TableField(value = "access_token_validity")
    private Integer accessTokenValidity;

    @TableField(value = "refresh_token_validity")
    private Integer refreshTokenValidity;


}
