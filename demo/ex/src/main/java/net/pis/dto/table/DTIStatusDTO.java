/**
 * (c)BOC
 */
package net.pis.dto.table;

import java.io.Serializable;
import java.util.Date;

/**
 * TABLE DTO (XXSB_DTI_STATUS)
 * <p>
 * Created by achiz on 14. 7. 10.
 *
 * XXSB_DTI_STATUS
 * </p>
 */
public class DTIStatusDTO implements Serializable {

    private static final long serialVersionUID = -6637264646639908086L;

    /**
     * 관리번호
     */
    private String conversationId;

    /**
     * 매입, 매출 구분
     */
    private String supbuyType;

    /**
     * 정발행, 역발행 구분
     */
    private String direction;

    /**
     * 최종상태
     */
    private String dtiStatus;

    /**
     * 전송상태
     */
    private String tranStatus;

    /**
     * 전자세금계산서 최종 비즈니스 상태
     */
    private String finalStatus;

    /**
     * 공급자 출력횟수
     */
    private int supPrintCnt;

    /**
     * 받는자 출력횟수
     */
    private int byrPrintCnt;

    /**
     * RETURN CODE
     */
    private String returnCode;

    /**
     * RETURN DESCRIPTION
     */
    private String returnDescription;

    /**
     * 거부,취소사유
     */
    private String sbdescription;

    /**
     * 부서명
     */
    private String deptName;

    /**
     * 국세청 전송 상태
     */
    private String sendRequest;

    /**
     * 국세청 전송 상태 메세지
     */
    private String sendRequestDesc;

    /**
     * 국세청 전송 결과 코드
     */
    private String resultRequest;

    /**
     * 국세청 오류 메세지
     */
    private String errorMsg;

    /**
     * 작성자
     */
    private String createdBy;

    /**
     * 작성일시
     */
    private Date creationDate;

    /**
     * 최종수정자
     */
    private String lastUpdatedBy;

    /**
     * 최종수정일시
     */
    private Date lastUpdateDate;

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

    public String getDtiStatus() {
        return dtiStatus;
    }

    public void setDtiStatus(String dtiStatus) {
        this.dtiStatus = dtiStatus;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public int getSupPrintCnt() {
        return supPrintCnt;
    }

    public void setSupPrintCnt(int supPrintCnt) {
        this.supPrintCnt = supPrintCnt;
    }

    public int getByrPrintCnt() {
        return byrPrintCnt;
    }

    public void setByrPrintCnt(int byrPrintCnt) {
        this.byrPrintCnt = byrPrintCnt;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    public String getSbdescription() {
        return sbdescription;
    }

    public void setSbdescription(String sbdescription) {
        this.sbdescription = sbdescription;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSendRequest() {
        return sendRequest;
    }

    public void setSendRequest(String sendRequest) {
        this.sendRequest = sendRequest;
    }

    public String getSendRequestDesc() {
        return sendRequestDesc;
    }

    public void setSendRequestDesc(String sendRequestDesc) {
        this.sendRequestDesc = sendRequestDesc;
    }

    public String getResultRequest() {
        return resultRequest;
    }

    public void setResultRequest(String resultRequest) {
        this.resultRequest = resultRequest;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

}
