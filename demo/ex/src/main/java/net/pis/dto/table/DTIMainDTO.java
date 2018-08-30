/**
 * (c)BOC
 */
package net.pis.dto.table;

import java.io.Serializable;
import java.util.Date;

/**
 * TABLE DTO (XXSB_DTI_MAIN)
 * <p>
 * Created by achiz on 14. 7. 10.
 *
 * XXSB_DTI_MAIN DTO
 * </p>
 *
 */
public class DTIMainDTO implements Serializable {

    private static final long serialVersionUID = 7193040944219731581L;

    /**
     * 참조번호
     */
    private String conversationId;
    /**
     * 매입/매출 구분
     */
    private String supbuyType;
    /**
     * 정발행, 역발행 구분
     */
    private String direction;
    /**
     * 인터페이스배치번호
     */
    private String interfaceBatchId;

    /**
     * 세금계산서 일자
     */
    private Date dtiWdate;

    /**
     * 발행일자
     */
    private Date dtiIdate;

    /**
     * 전송일자
     */
    private Date dtiSdate;

    /**
     * 세금계산서 종류
     * <p>
     * 계산서 원본의 TYPE_CODE
     * </p>
     */
    private String dtiType;
    /**
     * 영수,청구 구분
     */
    private String taxDemand;

    /**
     * 관리번호
     */
    private String seqId;

    /**
     * 공급자 스마트빌 아이디
     */
    private String supComId;
    /**
     * 공급자 사업자 등록번호
     */
    private String supComRegno;
    /**
     * 공급자 대표자 성명
     */
    private String supRepName;
    /**
     * 공급자 상호
     */
    private String supComName;
    /**
     * 공급자 업태
     */
    private String supComType;
    /**
     * 공급자 업종
     */
    private String supComClassify;
    /**
     * 공급자 주소
     */
    private String supComAddr;
    /**
     * 공급자 담당부서명
     */
    private String supDeptName;
    /**
     * 공급자 담당자명
     */
    private String supEmpName;
    /**
     * 공급자 담당자 전화번호
     */
    private String supTelNum;
    /**
     * 공급자 담당자 이메일
     */
    private String supEmail;
    /**
     * 공급받는자 스마트빌 ID
     */
    private String byrComId;
    /**
     * 공급받는자 사업자 등록번호
     */
    private String byrComRegno;
    /**
     * 공급받는자 대표자 성명
     */
    private String byrRepName;
    /**
     * 공급받는자 상호
     */
    private String byrComName;
    /**
     * 공급받는자 업태
     */
    private String byrComType;
    /**
     * 공급받는자 업종
     */
    private String byrComClassify;
    /**
     * 공급받는자 주소
     */
    private String byrComAddr;
    /**
     * 공급받는자 담당부서명
     */
    private String byrDeptName;
    /**
     * 공급받는자 담당자명
     */
    private String byrEmpName;
    /**
     * 공급받는자 담당자 전화번호
     */
    private String byrTelNum;
    /**
     * 공급받는자 담당자 이메일
     */
    private String byrEmail;
    /**
     * 수탁자 스마트빌 ID
     */
    private String brokerComId;
    /**
     * 수탁자 사업자 등록번호
     */
    private String brokerComRegno;
    /**
     * 수탁자 대표자 성명
     */
    private String brokerRepName;
    /**
     * 수탁자 상호
     */
    private String brokerComName;
    /**
     * 수탁자 업태
     */
    private String brokerComType;
    /**
     * 수탁자 업종
     */
    private String brokerComClassify;
    /**
     * 수탁자 주소
     */
    private String brokerComAddr;
    /**
     * 수탁자 담당부서명
     */
    private String brokerDeptName;
    /**
     * 수탁자 담당자명
     */
    private String brokerEmpName;
    /**
     * 수탁자 담당자 전화번호
     */
    private String brokerTelNum;
    /**
     * 수탁자 담당자 이메일
     */
    private String brokerEmail;
    /**
     * 현금코드
     */
    private String cashCode;
    /**
     * 현금금액
     */
    private Double cashAmount;
    /**
     * 수표코드
     */
    private String checkCode;
    /**
     * 수표금액
     */
    private Double checkAmount;
    /**
     * 어음코드
     */
    private String noteCode;
    /**
     * 어음금액
     */
    private Double noteAmount;
    /**
     * 외상미수금코드
     */
    private String receivableCode;
    /**
     * 외상미수금금액
     */
    private Double receivableAmount;
    /**
     * 공급가액합계
     */
    private Double supAmount;
    /**
     * 세액합계
     */
    private Double taxAmount;
    /**
     * 외화공급가액합계
     */
    private Double totForeignAmount;
    /**
     * 총금액
     */
    private Double totalAmount;
    /**
     * 총수량
     */
    private Double totalQuantity;
    /**
     * 거래명세서 구분
     */
    private String dttYn;
    /**
     * 비고
     */
    private String remark;
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
    /**
     * 기타번호1
     */
    private String etcnum1;
    /**
     * 기타번호2
     */
    private String etcnum2;
    /**
     * 기타번호3
     */
    private String etcnum3;
    /**
     * 기타번호4
     */
    private String etcnum4;
    /**
     * 세금계산서 원본
     */
    private String dtiMsg;
    /**
     * 수정코드
     */
    private String amendCode;
    /**
     * 비고2
     */
    private String remark2;
    /**
     * 비고3
     */
    private String remark3;
    /**
     * 서비스관리번호
     */
    private String exchangedDocId;
    /**
     * 승인번호
     */
    private String approveId;
    /**
     * 사업장코드
     */
    private String supBizplaceCode;
    /**
     * 사업장코드
     */
    private String byrBizplaceCode;
    /**
     * 사업장코드
     */
    private String brkBizplaceCode;
    /**
     * 담당부서명 2
     */
    private String byrDeptName2;
    /**
     * 담당자명 2
     */
    private String byrEmpName2;
    /**
     * 담당자 전화번호 2
     */
    private String byrTelNum2;
    /**
     * 담당자 이메일 2
     */
    private String byrEmail2;
    /**
     * 첨부파일 여부
     */
    private String attachfileYn;
    /**
     * SMTP 유통을 위한 메일 주소
     */
    private String aspSmtpMail;

    /**
     * 당초 승인번호
     */
    private String oriIssueId;

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

    public String getInterfaceBatchId() {
        return interfaceBatchId;
    }

    public void setInterfaceBatchId(String interfaceBatchId) {
        this.interfaceBatchId = interfaceBatchId;
    }

    public Date getDtiWdate() {
        return dtiWdate;
    }

    public void setDtiWdate(Date dtiWdate) {
        this.dtiWdate = dtiWdate;
    }

    public Date getDtiIdate() {
        return dtiIdate;
    }

    public void setDtiIdate(Date dtiIdate) {
        this.dtiIdate = dtiIdate;
    }

    public Date getDtiSdate() {
        return dtiSdate;
    }

    public void setDtiSdate(Date dtiSdate) {
        this.dtiSdate = dtiSdate;
    }

    public String getDtiType() {
        return dtiType;
    }

    public void setDtiType(String dtiType) {
        this.dtiType = dtiType;
    }

    public String getTaxDemand() {
        return taxDemand;
    }

    public void setTaxDemand(String taxDemand) {
        this.taxDemand = taxDemand;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getSupComId() {
        return supComId;
    }

    public void setSupComId(String supComId) {
        this.supComId = supComId;
    }

    public String getSupComRegno() {
        return supComRegno;
    }

    public void setSupComRegno(String supComRegno) {
        this.supComRegno = supComRegno;
    }

    public String getSupRepName() {
        return supRepName;
    }

    public void setSupRepName(String supRepName) {
        this.supRepName = supRepName;
    }

    public String getSupComName() {
        return supComName;
    }

    public void setSupComName(String supComName) {
        this.supComName = supComName;
    }

    public String getSupComType() {
        return supComType;
    }

    public void setSupComType(String supComType) {
        this.supComType = supComType;
    }

    public String getSupComClassify() {
        return supComClassify;
    }

    public void setSupComClassify(String supComClassify) {
        this.supComClassify = supComClassify;
    }

    public String getSupComAddr() {
        return supComAddr;
    }

    public void setSupComAddr(String supComAddr) {
        this.supComAddr = supComAddr;
    }

    public String getSupDeptName() {
        return supDeptName;
    }

    public void setSupDeptName(String supDeptName) {
        this.supDeptName = supDeptName;
    }

    public String getSupEmpName() {
        return supEmpName;
    }

    public void setSupEmpName(String supEmpName) {
        this.supEmpName = supEmpName;
    }

    public String getSupTelNum() {
        return supTelNum;
    }

    public void setSupTelNum(String supTelNum) {
        this.supTelNum = supTelNum;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }

    public String getByrComId() {
        return byrComId;
    }

    public void setByrComId(String byrComId) {
        this.byrComId = byrComId;
    }

    public String getByrComRegno() {
        return byrComRegno;
    }

    public void setByrComRegno(String byrComRegno) {
        this.byrComRegno = byrComRegno;
    }

    public String getByrRepName() {
        return byrRepName;
    }

    public void setByrRepName(String byrRepName) {
        this.byrRepName = byrRepName;
    }

    public String getByrComName() {
        return byrComName;
    }

    public void setByrComName(String byrComName) {
        this.byrComName = byrComName;
    }

    public String getByrComType() {
        return byrComType;
    }

    public void setByrComType(String byrComType) {
        this.byrComType = byrComType;
    }

    public String getByrComClassify() {
        return byrComClassify;
    }

    public void setByrComClassify(String byrComClassify) {
        this.byrComClassify = byrComClassify;
    }

    public String getByrComAddr() {
        return byrComAddr;
    }

    public void setByrComAddr(String byrComAddr) {
        this.byrComAddr = byrComAddr;
    }

    public String getByrDeptName() {
        return byrDeptName;
    }

    public void setByrDeptName(String byrDeptName) {
        this.byrDeptName = byrDeptName;
    }

    public String getByrEmpName() {
        return byrEmpName;
    }

    public void setByrEmpName(String byrEmpName) {
        this.byrEmpName = byrEmpName;
    }

    public String getByrTelNum() {
        return byrTelNum;
    }

    public void setByrTelNum(String byrTelNum) {
        this.byrTelNum = byrTelNum;
    }

    public String getByrEmail() {
        return byrEmail;
    }

    public void setByrEmail(String byrEmail) {
        this.byrEmail = byrEmail;
    }

    public String getBrokerComId() {
        return brokerComId;
    }

    public void setBrokerComId(String brokerComId) {
        this.brokerComId = brokerComId;
    }

    public String getBrokerComRegno() {
        return brokerComRegno;
    }

    public void setBrokerComRegno(String brokerComRegno) {
        this.brokerComRegno = brokerComRegno;
    }

    public String getBrokerRepName() {
        return brokerRepName;
    }

    public void setBrokerRepName(String brokerRepName) {
        this.brokerRepName = brokerRepName;
    }

    public String getBrokerComName() {
        return brokerComName;
    }

    public void setBrokerComName(String brokerComName) {
        this.brokerComName = brokerComName;
    }

    public String getBrokerComType() {
        return brokerComType;
    }

    public void setBrokerComType(String brokerComType) {
        this.brokerComType = brokerComType;
    }

    public String getBrokerComClassify() {
        return brokerComClassify;
    }

    public void setBrokerComClassify(String brokerComClassify) {
        this.brokerComClassify = brokerComClassify;
    }

    public String getBrokerComAddr() {
        return brokerComAddr;
    }

    public void setBrokerComAddr(String brokerComAddr) {
        this.brokerComAddr = brokerComAddr;
    }

    public String getBrokerDeptName() {
        return brokerDeptName;
    }

    public void setBrokerDeptName(String brokerDeptName) {
        this.brokerDeptName = brokerDeptName;
    }

    public String getBrokerEmpName() {
        return brokerEmpName;
    }

    public void setBrokerEmpName(String brokerEmpName) {
        this.brokerEmpName = brokerEmpName;
    }

    public String getBrokerTelNum() {
        return brokerTelNum;
    }

    public void setBrokerTelNum(String brokerTelNum) {
        this.brokerTelNum = brokerTelNum;
    }

    public String getBrokerEmail() {
        return brokerEmail;
    }

    public void setBrokerEmail(String brokerEmail) {
        this.brokerEmail = brokerEmail;
    }

    public String getCashCode() {
        return cashCode;
    }

    public void setCashCode(String cashCode) {
        this.cashCode = cashCode;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public Double getNoteAmount() {
        return noteAmount;
    }

    public void setNoteAmount(Double noteAmount) {
        this.noteAmount = noteAmount;
    }

    public String getReceivableCode() {
        return receivableCode;
    }

    public void setReceivableCode(String receivableCode) {
        this.receivableCode = receivableCode;
    }

    public Double getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(Double receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public Double getSupAmount() {
        return supAmount;
    }

    public void setSupAmount(Double supAmount) {
        this.supAmount = supAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotForeignAmount() {
        return totForeignAmount;
    }

    public void setTotForeignAmount(Double totForeignAmount) {
        this.totForeignAmount = totForeignAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDttYn() {
        return dttYn;
    }

    public void setDttYn(String dttYn) {
        this.dttYn = dttYn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getEtcnum1() {
        return etcnum1;
    }

    public void setEtcnum1(String etcnum1) {
        this.etcnum1 = etcnum1;
    }

    public String getEtcnum2() {
        return etcnum2;
    }

    public void setEtcnum2(String etcnum2) {
        this.etcnum2 = etcnum2;
    }

    public String getEtcnum3() {
        return etcnum3;
    }

    public void setEtcnum3(String etcnum3) {
        this.etcnum3 = etcnum3;
    }

    public String getEtcnum4() {
        return etcnum4;
    }

    public void setEtcnum4(String etcnum4) {
        this.etcnum4 = etcnum4;
    }

    public String getDtiMsg() {
        return dtiMsg;
    }

    public void setDtiMsg(String dtiMsg) {
        this.dtiMsg = dtiMsg;
    }

    public String getAmendCode() {
        return amendCode;
    }

    public void setAmendCode(String amendCode) {
        this.amendCode = amendCode;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getExchangedDocId() {
        return exchangedDocId;
    }

    public void setExchangedDocId(String exchangedDocId) {
        this.exchangedDocId = exchangedDocId;
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public String getSupBizplaceCode() {
        return supBizplaceCode;
    }

    public void setSupBizplaceCode(String supBizplaceCode) {
        this.supBizplaceCode = supBizplaceCode;
    }

    public String getByrBizplaceCode() {
        return byrBizplaceCode;
    }

    public void setByrBizplaceCode(String byrBizplaceCode) {
        this.byrBizplaceCode = byrBizplaceCode;
    }

    public String getBrkBizplaceCode() {
        return brkBizplaceCode;
    }

    public void setBrkBizplaceCode(String brkBizplaceCode) {
        this.brkBizplaceCode = brkBizplaceCode;
    }

    public String getByrDeptName2() {
        return byrDeptName2;
    }

    public void setByrDeptName2(String byrDeptName2) {
        this.byrDeptName2 = byrDeptName2;
    }

    public String getByrEmpName2() {
        return byrEmpName2;
    }

    public void setByrEmpName2(String byrEmpName2) {
        this.byrEmpName2 = byrEmpName2;
    }

    public String getByrTelNum2() {
        return byrTelNum2;
    }

    public void setByrTelNum2(String byrTelNum2) {
        this.byrTelNum2 = byrTelNum2;
    }

    public String getByrEmail2() {
        return byrEmail2;
    }

    public void setByrEmail2(String byrEmail2) {
        this.byrEmail2 = byrEmail2;
    }

    public String getAttachfileYn() {
        return attachfileYn;
    }

    public void setAttachfileYn(String attachfileYn) {
        this.attachfileYn = attachfileYn;
    }

    public String getAspSmtpMail() {
        return aspSmtpMail;
    }

    public void setAspSmtpMail(String aspSmtpMail) {
        this.aspSmtpMail = aspSmtpMail;
    }

    public String getOriIssueId() {
        return oriIssueId;
    }

    public void setOriIssueId(String oriIssueId) {
        this.oriIssueId = oriIssueId;
    }

}
