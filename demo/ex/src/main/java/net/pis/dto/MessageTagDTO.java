/*
 * (c)BOC
 */
package net.pis.dto;


import java.io.Serializable;
import java.util.Date;
/**
 * TABLE DTO (SBMS_MESSAGE_TAG)
 *
 * @author jh,Seo
 */
public class MessageTagDTO implements Serializable {

    private static final long serialVersionUID = -2854508516071362401L;

    /**
     * 메시지태그 아이디
     */
    private String messageTagId;

    /**
     * 메시지 아이디
     */
    private String messageId;

    /**
     * 서비스 아이디
     */
    private String serviceId;

    /**
     * 참조번호
     */
    private String conversationId;

    /**
     * 송신자 사업자번호
     */
    private String sendRegNo;

    /**
     * 수신자 사업자 번호
     */
    private String receiveRegNo;

    /**
     * 전자계약상태
     */
    @Deprecated
    private String status;

    /**
     * Business SIGNAL
     */
    private String signal;

    /**
     * 송신자 시스템 식별자
     */
    private String fromSystem;

    /**
     * 수신자 시스템 식별자
     */
    private String toSystem;

    /**
     * 수신자 시스템 클라이언트 코드 (SAP 전용)
     */
    @Deprecated
    private String toSystemClientCode;
    /**
     * 수신자 시스템 회사 코드 (SAP 전용)
     */
    @Deprecated
    private String toSystemCompanycode;

    /**
     * 메시지 종류
     */
    private String messageType;

    /**
     * 메시지 전송 상태
     */
    private String messageStatus;

    /**
     * 전송방향
     */
    private String direction;

    /**
     * 파일 개수
     */
    private double fileCount;

    /**
     * 총 용량
     */
    private double totalSize;

    /**
     * 수정시간
     */
    private Date currTimestamp;

    /**
     * 수신시간
     */
    private Date recvTimestamp;

    /**
     * 송신시간
     */
    private Date sendTimestamp;

    /**
     * 인증티켓
     */
    private String authTicket;

    /**
     * TO_SYSTEM_SYSTEM_ID
     */
    @Deprecated
    private String toSystemSystemId;

    /**
     * ENV_STSTUS
     */
    @Deprecated
    private String envStstus;

    /**
     * ENV_KEY
     */
    @Deprecated
    private String envKey;

    /**
     * 수신자 시스템 라우팅 키
     */
    private String targetSystemId;

    /**
     * 송신자 시스템 라우팅 키
     */
    private String senderSystemId;

    public String getMessageTagId() {
        return messageTagId;
    }

    public void setMessageTagId(String messageTagId) {
        this.messageTagId = messageTagId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getSendRegNo() {
        return sendRegNo;
    }

    public void setSendRegNo(String sendRegNo) {
        this.sendRegNo = sendRegNo;
    }

    public String getReceiveRegNo() {
        return receiveRegNo;
    }

    public void setReceiveRegNo(String receiveRegNo) {
        this.receiveRegNo = receiveRegNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getFromSystem() {
        return fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    public String getToSystem() {
        return toSystem;
    }

    public void setToSystem(String toSystem) {
        this.toSystem = toSystem;
    }

    public String getToSystemClientCode() {
        return toSystemClientCode;
    }

    public void setToSystemClientCode(String toSystemClientCode) {
        this.toSystemClientCode = toSystemClientCode;
    }

    public String getToSystemCompanycode() {
        return toSystemCompanycode;
    }

    public void setToSystemCompanycode(String toSystemCompanycode) {
        this.toSystemCompanycode = toSystemCompanycode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getFileCount() {
        return fileCount;
    }

    public void setFileCount(double fileCount) {
        this.fileCount = fileCount;
    }

    public double getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(double totalSize) {
        this.totalSize = totalSize;
    }

    public Date getCurrTimestamp() {
        return currTimestamp;
    }

    public void setCurrTimestamp(Date currTimestamp) {
        this.currTimestamp = currTimestamp;
    }

    public Date getRecvTimestamp() {
        return recvTimestamp;
    }

    public void setRecvTimestamp(Date recvTimestamp) {
        this.recvTimestamp = recvTimestamp;
    }

    public Date getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(Date sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public String getToSystemSystemId() {
        return toSystemSystemId;
    }

    public void setToSystemSystemId(String toSystemSystemId) {
        this.toSystemSystemId = toSystemSystemId;
    }

    public String getEnvStstus() {
        return envStstus;
    }

    public void setEnvStstus(String envStstus) {
        this.envStstus = envStstus;
    }

    public String getEnvKey() {
        return envKey;
    }

    public void setEnvKey(String envKey) {
        this.envKey = envKey;
    }

    public String getTargetSystemId() {
        return targetSystemId;
    }

    public void setTargetSystemId(String targetSystemId) {
        this.targetSystemId = targetSystemId;
    }

    public String getAuthTicket() {
        return authTicket;
    }

    public void setAuthTicket(String authTicket) {
        this.authTicket = authTicket;
    }

    public String getSenderSystemId() {
        return senderSystemId;
    }

    public void setSenderSystemId(String senderSystemId) {
        this.senderSystemId = senderSystemId;
    }

}
