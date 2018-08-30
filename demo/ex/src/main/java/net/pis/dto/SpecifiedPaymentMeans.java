/*
 * (c)BOC
 */
package net.pis.dto;

import net.pis.common.adapter.DoubleAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 * 겨료제방법별 금액 (4회까지)
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "typeCode", "paidAmount"
})
public class SpecifiedPaymentMeans {

    /**
     * 결제방법 코드
     * <ul>
     * <li>10 : 현금</li>
     * <li>20 : 수표</li>
     * <li>30 : 어음</li>
     * <li>40 : 외상(매출금/미수금)</li>
     *
     * </ul>
     */
    @XmlElement(name = "TypeCode")
    private String typeCode;

    /**
     * 금액
     */
    @XmlElement(name = "PaidAmount")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double paidAmount;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

}
