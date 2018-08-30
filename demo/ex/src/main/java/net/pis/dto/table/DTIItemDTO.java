/**
 * (c)BOC
 */
package net.pis.dto.table;

import java.io.Serializable;
import java.util.Date;

/**
 * TABLE DTO (XXSB_DTI_ITEM)
 * <p>
 * Created by achiz on 14. 7. 10.
 *
 * XXSB_DTI_ITEM
 * </p>
 */
public class DTIItemDTO implements Serializable {

    private static final long serialVersionUID = -6020411108946800108L;

    /**
     * 관리번호
     */
    private String conversationId;

    /**
     * 매입,매출 구분
     */
    private String supbuyType;

    /**
     * 정발행, 역발행 구분
     */
    private String direction;

    /**
     * DTI_LINE_NUM
     */
    private Integer dtiLineNum;

    /**
     * 품목코드
     */
    private String itemCode;

    /**
     * 품목명
     */
    private String itemName;

    /**
     * 규격
     */
    private String itemSize;

    /**
     * 구입일자
     */
    private Date itemMd;

    /**
     * 단가
     */
    private Double unitPrice;

    /**
     * 수량
     */
    private Double itemQty;

    /**
     * 공급가액
     */
    private Double supAmount;

    /**
     * 세액
     */
    private Double taxAmount;

    /**
     * 외화금액
     */
    private Double foreignAmount;

    /**
     * 통화코드
     */
    private String currencyCode;

    /**
     * ITEM 구분값
     */
    private String itemGubun;

    /**
     * 비고
     */
    private String itemRemark;

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
     * 최종 수정일시
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

    public Integer getDtiLineNum() {
        return dtiLineNum;
    }

    public void setDtiLineNum(Integer dtiLineNum) {
        this.dtiLineNum = dtiLineNum;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public Date getItemMd() {
        return itemMd;
    }

    public void setItemMd(Date itemMd) {
        this.itemMd = itemMd;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getItemQty() {
        return itemQty;
    }

    public void setItemQty(Double itemQty) {
        this.itemQty = itemQty;
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

    public Double getForeignAmount() {
        return foreignAmount;
    }

    public void setForeignAmount(Double foreignAmount) {
        this.foreignAmount = foreignAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getItemGubun() {
        return itemGubun;
    }

    public void setItemGubun(String itemGubun) {
        this.itemGubun = itemGubun;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
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
    /**
     * 최종수정일시 *
     */

}
