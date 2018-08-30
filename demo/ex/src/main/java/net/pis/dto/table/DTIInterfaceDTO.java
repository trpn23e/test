/**
 * (c)BOC
 */
package net.pis.dto.table;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * TABLE DTO (XXSB_DTI_INTEFACE)
 * <p>
 * Created by achiz on 14. 7. 14.
 *
 * XXSB_DTI_INTERFACE
 * </p>
 */
public class DTIInterfaceDTO implements Serializable {

    private static final long serialVersionUID = 8184608683592402640L;

    private String messageId;
    private String conversationId;
    private String supbuyType;
    private String direction;
    private String signal;
    private String statusSignal;
    private String messageStatusFlag;
    private String targetSystemId;
    private String metaString;
    private String authTicket;
    private Date lastUpdateDate;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getSupbuyType() {
        return supbuyType;
    }

    public void setSupbuyType(String supbuyType) {
        this.supbuyType = supbuyType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getStatusSignal() {
        return statusSignal;
    }

    public void setStatusSignal(String statusSignal) {
        this.statusSignal = statusSignal;
    }

    public String getMessageStatusFlag() {
        return messageStatusFlag;
    }

    public void setMessageStatusFlag(String messageStatusFlag) {
        this.messageStatusFlag = messageStatusFlag;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getTargetSystemId() {
        return targetSystemId;
    }

    public void setTargetSystemId(String targetSystemId) {
        this.targetSystemId = targetSystemId;
    }

    public String getMetaString() {
        return metaString;
    }

    public void setMetaString(String metaString) {
        this.metaString = metaString;
    }

    public String getAuthTicket() {
        return authTicket;
    }

    public void setAuthTicket(String authTicket) {
        this.authTicket = authTicket;
    }
}
