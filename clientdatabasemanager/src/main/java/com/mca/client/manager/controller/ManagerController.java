package com.mca.client.manager.controller;

import com.mca.client.manager.entity.ClientDetails;
import com.mca.client.manager.entity.ClientSelectPage;
import com.mca.client.manager.server.ClientService;
import com.mca.client.manager.util.RCode;
import com.mca.client.manager.util.Result;
import com.mca.client.manager.util.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Author an Stark
 * @ClassName ManagerController
 * @Description TODO
 * @date 2021/6/24 下午7:40
 * @Version 1.0
 */
@RestController
@Slf4j
@Api(value = "ManagerController", description = "client manager")
public class ManagerController {


    @Autowired
    private ClientService clientService;

    @ApiOperation(notes = "获取列表" , value = "page")
    @GetMapping(value = "/getClients")
    public Result getClients(@ModelAttribute("page") ClientSelectPage page) {
        try {
            ServiceResult<Map<String, Object>> result = clientService.getClients(page);
            if (result.isSuccess()) {
                return Result.ok(result.getValue());
            } else {
                return Result.fail(RCode.NO_DATA);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }
    }

    @ApiOperation(notes = "获取详情", value = "clientID")
    @GetMapping(value = "/client")
    public Result getClient(@RequestParam("clientID") String clientID) {
        try {
            ServiceResult<ClientDetails> result = clientService.getClient(clientID);
            if (result.isSuccess()) {
                return Result.ok(result.getValue());
            } else {
                return Result.fail(RCode.NO_DATA);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }
    }

    @ApiOperation(notes = "添加" , value = "client")
    @PostMapping(value = "/client")
    public Result addClient(@Valid @ModelAttribute("client") ClientDetails client) {
        try {
            ServiceResult<Boolean> result = clientService.addClient(client);
            if (result.isSuccess()) {
                return Result.ok();
            } else {
                return Result.build(result.getCode(), result.getMsg(), result.getValue());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }
    }

    @ApiOperation(notes = "修改" , value = "client")
    @PutMapping(value = "/client")
    public Result updateClient(@ModelAttribute("client") ClientDetails client) {
        try {
            ServiceResult<Boolean> result = clientService.updateClient(client);
            System.out.println(result.isSuccess());
            if (result.isSuccess()) {
                return Result.ok();
            } else {
                return Result.fail(RCode.FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Result.error();
        }
    }

    @ApiOperation(notes = "删除" , value = "clientID")
    @DeleteMapping(value = "/client")
    public Result deleteClient(@RequestParam("clientID") String clientID) {
        try {
            ServiceResult<Boolean> result = clientService.deleteClient(clientID);
            if (result.isSuccess()) {
                return Result.ok();
            } else {
                return Result.fail(RCode.FAIL);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }

    }
}
