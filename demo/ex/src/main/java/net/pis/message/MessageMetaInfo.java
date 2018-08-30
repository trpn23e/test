/*
 * (c)BOC
 */
package net.pis.message;


import net.pis.common.Direction;
import net.pis.common.Listener;

import java.io.Serializable;

/**
 * 메세지에 대한 메타 정보
 * <p>
 * 구간간의 통신은 이 클래스를 통하여 이루어진다. 이 클래스는 메메세지의 방향에 대한 간단한 정보만을 취급한다. 따라서 메세지에 대한 상세
 * 정보를 확인 하려면 DBMS에 의존하여 저장된 정보를 직접 추출 해야 한다.
 * </p>
 *
 * @author jh,Seo
 */
public class MessageMetaInfo implements Serializable {

    public static final long serialVersionUID = 4124122L;

    /**
     * 메세지 PK
     */
    private String messageTagId;

    /**
     * 메세지 식별자
     */
    private String messageId;

    /**
     * 목적지 리스너
     */
    private Listener destination;

    /**
     * 메세지의 방향
     *
     */
    private Direction direction;

    /**
     * 응답메세지 여부
     */
    private boolean isAck;

    /**
     * 오류 여부
     */
    private boolean isError;

    /**
     * 라우팅 목적지 키값
     */
    private String targetSystemId;

    private String erpSystem;

    private String clientCode;
    private String companyCode;
    /**
     * 어댑터 ID
     */
    private String targetKey;

    /**
     * 인증 티켓 (or email address)
     */
    private String ticket;

    public String getErpSystem() {
        return erpSystem;
    }

    public void setErpSystem(String erpSystem) {
        this.erpSystem = erpSystem;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }

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

    public Listener getDestination() {
        return destination;
    }

    public void setDestination(Listener destination) {
        this.destination = destination;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAck() {
        return isAck;
    }

    public void setAck(boolean isAck) {
        this.isAck = isAck;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public String getTargetSystemId() {
        return targetSystemId;
    }

    public void setTargetSystemId(String targetSystemId) {
        this.targetSystemId = targetSystemId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        switch (this.getDirection()) {
            case Inbound: {
                builder.append("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<").append(System.lineSeparator());
                break;
            }
            case Outbound: {
                builder.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").append(System.lineSeparator());
                break;
            }
        }
        builder.append("* HASH CODE : ").append(this.hashCode()).append(System.lineSeparator());
        builder.append("messageId : ").append(this.getMessageId()).append(System.lineSeparator());
        builder.append("messageTagId : ").append(this.getMessageTagId()).append(System.lineSeparator());
        builder.append("direction : ").append(this.getDirection()).append(System.lineSeparator());
        builder.append("destination : ").append(this.getDestination()).append(System.lineSeparator());
        builder.append("ack : ").append(this.isAck()).append(System.lineSeparator());
        builder.append("error : ").append(this.isError()).append(System.lineSeparator());
        builder.append("targetSystemId : ").append(this.getTargetSystemId()).append(System.lineSeparator());

        if (null != this.getErpSystem()) {
            builder.append("erpSystem : ").append(this.getErpSystem()).append(System.lineSeparator());
            builder.append("  destination : ").append(this.getTargetKey()).append(System.lineSeparator());

            if (null != this.getClientCode()) {
                builder.append("    client : ").append(
                    this.getClientCode()).append(System.lineSeparator());

                builder.append("    company : ").append(this.getCompanyCode()).append(System.lineSeparator());
            }

        } else {
            builder.append("erpSystem : unkown").append(System.lineSeparator());
        }
        switch (this.getDirection()) {
            case Inbound: {
                builder.append("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<").append(System.lineSeparator());
                break;
            }
            case Outbound: {
                builder.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").append(System.lineSeparator());
                break;
            }
        }

        return builder.toString();

    }
}
