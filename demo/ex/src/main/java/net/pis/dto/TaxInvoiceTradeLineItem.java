/*
 * (c)BOC
 */
package net.pis.dto;

import net.pis.common.adapter.BasicDateFormatAdapter;
import net.pis.common.adapter.DoubleAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
/**
 * 상품정보 (99회까지 반복)
 *
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "sequenceNumeric", "itemCode", "descriptionText", "invoiceAmount", "chargeableUnitQuantity",
    "informationText", "nameText", "purchaseExpiryDateTime", "totalTax",
    "unitPrice"
})
public class TaxInvoiceTradeLineItem {

    /**
     * 일련번호
     */
    @XmlElement(name = "SequenceNumeric")
    private Integer sequenceNumeric;

    /**
     * 아이템 코드 (거래명세서 용도 이외에 사용하지 말것)
     */
    @XmlElement(name = "ItemCode")
    private String itemCode;

    /**
     * 공급년월일 YYYYMMDD
     */
    @XmlElement(name = "PurchaseExpiryDateTime")
    @XmlJavaTypeAdapter(BasicDateFormatAdapter.class)
    private Date purchaseExpiryDateTime;

    /**
     * 물품명
     */
    @XmlElement(name = "NameText")
    private String nameText;

    /**
     * 규격
     */
    @XmlElement(name = "InformationText")
    private String informationText;

    /**
     * 비고
     */
    @XmlElement(name = "DescriptionText")
    private String descriptionText;

    /**
     * 수량
     */
    @XmlElement(name = "ChargeableUnitQuantity")
    private Double chargeableUnitQuantity;

    /**
     * 단가
     */
    @XmlElement(name = "UnitPrice")
    private UnitPrice unitPrice;

    /**
     * 공급가액
     */
    @XmlElement(name = "InvoiceAmount")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double invoiceAmount;

    /**
     * 세액
     */
    @XmlElement(name = "TotalTax")
    private TotalTax totalTax;

    public Integer getSequenceNumeric() {
        return sequenceNumeric;
    }

    public void setSequenceNumeric(Integer sequenceNumeric) {
        this.sequenceNumeric = sequenceNumeric;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Date getPurchaseExpiryDateTime() {
        return purchaseExpiryDateTime;
    }

    public void setPurchaseExpiryDateTime(Date purchaseExpiryDateTime) {
        this.purchaseExpiryDateTime = purchaseExpiryDateTime;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getInformationText() {
        return informationText;
    }

    public void setInformationText(String informationText) {
        this.informationText = informationText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Double getChargeableUnitQuantity() {
        return chargeableUnitQuantity;
    }

    public void setChargeableUnitQuantity(Double chargeableUnitQuantity) {
        this.chargeableUnitQuantity = chargeableUnitQuantity;
    }

    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(UnitPrice unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public TotalTax getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(TotalTax totalTax) {
        this.totalTax = totalTax;
    }

}
