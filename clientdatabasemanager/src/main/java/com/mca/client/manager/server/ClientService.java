package com.mca.client.manager.server;

import com.mca.client.manager.entity.ClientDetails;
import com.mca.client.manager.entity.ClientSelectPage;
import com.mca.client.manager.util.ServiceResult;

import java.util.Map;

/**
 * @Author an Stark
 * @ClassName ClientService
 * @Description 客户端管理接口
 * @date 2021/6/24 下午7:34
 * @Version 1.0
 */
public interface ClientService {

    public ServiceResult<Map<String, Object>> getClients(ClientSelectPage page);

    public ServiceResult<ClientDetails> getClient(String clientID);

    public ServiceResult<Boolean> addClient(ClientDetails client);

    public ServiceResult<Boolean> updateClient(ClientDetails client);

    public ServiceResult<Boolean> deleteClient(String clientID);
}
