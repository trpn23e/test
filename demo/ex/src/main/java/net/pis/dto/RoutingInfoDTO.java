/**
 * (c)BOC
 */
package net.pis.dto;

import java.io.Serializable;

/**
 * TABLE DTO (SBMS_ROUTING_INFO)
 *
 * <p>
 * Created by achiz on 14. 7. 10.
 * </p>
 *
 */
public class RoutingInfoDTO implements Serializable {

    private static final long serialVersionUID = -5703732913324151289L;
    /**
     * 시스템 ID *
     */
    private String systemId;

    /**
     * 시스템 명 *
     */
    private String systemName;

    /**
     * 시스템 키 *
     */
    private String targetKey;

    /**
     * 어댑터 종류
     * <ul>
     * <li>SAP</li>
     * <li>NON_SAP</li>
     * </ul>
     */
    private String adapter;

    /**
     * SAP CLIENT
     * <p>
     * SAP 전용</p>
     */
    private String client;

    /**
     * SAP BIZ.ID
     * <p>
     * SAP 전용</p>
     */
    private String company;

    /**
     * 등록자 *
     */
    private String regUserId;

    /**
     * 등록시간 *
     */
    private String regTimestamp;

    /**
     * 수정자 *
     */
    private String modUserId;

    /**
     * 수정시간 *
     */
    private String modTimestamp;

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(String regUserId) {
        this.regUserId = regUserId;
    }

    public String getRegTimestamp() {
        return regTimestamp;
    }

    public void setRegTimestamp(String regTimestamp) {
        this.regTimestamp = regTimestamp;
    }

    public String getModUserId() {
        return modUserId;
    }

    public void setModUserId(String modUserId) {
        this.modUserId = modUserId;
    }

    public String getModTimestamp() {
        return modTimestamp;
    }

    public void setModTimestamp(String modTimestamp) {
        this.modTimestamp = modTimestamp;
    }

}
