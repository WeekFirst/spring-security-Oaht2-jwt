package com.mca.server.dao;

import com.mca.server.entity.UserInfoDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author an Stark
 * @ClassName UserLoginDao
 * @Description TODO
 * @date 2021/6/24 下午12:27
 * @Version 1.0
 */
@Repository
public interface UserLoginDao {

    public UserInfoDTO getUserByName(@Param("username") String username);
}
