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
 * SUMMARY
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "chargeTotalAmount", "taxTotalAmount", "grandTotalAmount"
})
public class SpecifiedMonetarySummation {

    /**
     * 공급가액 합계
     */
    @XmlElement(name = "ChargeTotalAmount")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double chargeTotalAmount;
    /**
     * 세액 합계
     */
    @XmlElement(name = "TaxTotalAmount")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double taxTotalAmount;
    /**
     * 총 금액
     */
    @XmlElement(name = "GrandTotalAmount")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double grandTotalAmount;

    public Double getChargeTotalAmount() {
        return chargeTotalAmount;
    }

    public void setChargeTotalAmount(Double chargeTotalAmount) {
        this.chargeTotalAmount = chargeTotalAmount;
    }

    public Double getTaxTotalAmount() {
        return taxTotalAmount;
    }

    public void setTaxTotalAmount(Double taxTotalAmount) {
        this.taxTotalAmount = taxTotalAmount;
    }

    public Double getGrandTotalAmount() {
        return grandTotalAmount;
    }

    public void setGrandTotalAmount(Double grandTotalAmount) {
        this.grandTotalAmount = grandTotalAmount;
    }

}
