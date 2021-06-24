package com.mca.client.manager.entity;

/**
 * @Author an Stark
 * @ClassName ClientSelectPage
 * @Description TODO
 * @date 2021/6/24 下午7:43
 * @Version 1.0
 */
public class ClientSelectPage {

    private Integer pageNo;

    private Integer pageSize;

    private String information;

    public Integer getPageNo() {
        if (null == pageNo) {
            return 0;
        }
        return (pageNo - 1) * getPageSize();
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if(null == pageSize){
            return 5;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
