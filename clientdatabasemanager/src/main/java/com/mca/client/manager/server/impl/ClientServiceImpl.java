package com.mca.client.manager.server.impl;

import com.mca.client.manager.dao.ClientDao;
import com.mca.client.manager.entity.ClientDetails;
import com.mca.client.manager.entity.ClientSelectPage;
import com.mca.client.manager.server.ClientService;
import com.mca.client.manager.util.RCode;
import com.mca.client.manager.util.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author an Stark
 * @ClassName ClientServiceImpl
 * @Description TODO
 * @date 2021/6/24 下午8:03
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public ServiceResult<Map<String, Object>> getClients(ClientSelectPage page) {
        try {
            int total = clientDao.getTotal();
            List<ClientDetails> list = clientDao.getClients(page);
            if (list.isEmpty()) {
                return ServiceResult.createFail(RCode.NO_DATA);
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("total", total);
                map.put("clients", list);
                return ServiceResult.createSuccess(map, RCode.SUCCESS);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ServiceResult.createError();
        }
    }

    @Override
    public ServiceResult<ClientDetails> getClient(String clientID) {
        try {
            ClientDetails clientDetails = clientDao.selectOne(clientID);
            if (ObjectUtils.isEmpty(clientDetails)) {
                return ServiceResult.createFail();
            }else{
                return ServiceResult.createSuccess(clientDetails, RCode.SUCCESS);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ServiceResult.createError();
        }
    }

    @Override
    public ServiceResult<Boolean> addClient(ClientDetails client) {
        try {
            ClientDetails clientDetails = clientDao.selectOne(client.getClientID());
            if(ObjectUtils.isEmpty(clientDetails)){
                if ((clientDao.insert(client)) != 0) {
                    return ServiceResult.createSuccess(true, RCode.SUCCESS);
                }else{
                    return ServiceResult.createFail(RCode.FAIL);
                }
            }else{
                return ServiceResult.createFail(RCode.DATA_EXIT);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return ServiceResult.createError();
        }

    }

    @Override
    public ServiceResult<Boolean> updateClient(ClientDetails client) {
        try {
            if ((clientDao.updateClient(client)) != 0) {
                return ServiceResult.createSuccess(true, RCode.SUCCESS);
            }else{
                return ServiceResult.createFail();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceResult.createError();
        }
    }

    @Override
    public ServiceResult<Boolean> deleteClient(String clientID) {
        try {
            if((clientDao.deleteByClientID(clientID)) != 0){
                return ServiceResult.createSuccess(true, RCode.SUCCESS);
            }else{
                return ServiceResult.createFail();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceResult.createError();
        }
    }
}
