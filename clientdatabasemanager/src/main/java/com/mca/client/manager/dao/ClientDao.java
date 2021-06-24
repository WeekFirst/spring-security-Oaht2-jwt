package com.mca.client.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mca.client.manager.entity.ClientDetails;
import com.mca.client.manager.entity.ClientSelectPage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author an Stark
 * @ClassName ClientDao
 * @Description TODO
 * @date 2021/6/24 下午8:04
 * @Version 1.0
 */
@Repository
public interface ClientDao extends BaseMapper<ClientDetails> {

    public int getTotal();


    public List<ClientDetails> getClients(ClientSelectPage page);

    public ClientDetails selectOne(String clientID);

    public int updateClient(ClientDetails client);

    public int deleteByClientID(String clientID);
}
