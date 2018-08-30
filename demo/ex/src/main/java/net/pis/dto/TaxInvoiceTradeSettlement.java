/*
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * 계산서, 거래처, 걸제 정보
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"invoicerParty", "invoiceeParty", "brokerParty",
    "specifiedPaymentMeans", "specifiedMonetarySummation"})
public class TaxInvoiceTradeSettlement {

    /**
     * 공급자
     */
    @XmlElement(name = "InvoicerParty")
    private InvoicerParty invoicerParty;

    /**
     * 공급받는자
     */
    @XmlElement(name = "InvoiceeParty")
    private InvoiceeParty invoiceeParty;

    /**
     * 수탁 사업자
     */
    @XmlElement(name = "BrokerParty")
    private BrokerParty brokerParty;

    /**
     * 결제 정보
     */
    @XmlElement(name = "SpecifiedPaymentMeans")
    private List<SpecifiedPaymentMeans> specifiedPaymentMeans = null;

    /**
     * Summary
     */
    @XmlElement(name = "SpecifiedMonetarySummation")
    private SpecifiedMonetarySummation specifiedMonetarySummation;

    public InvoicerParty getInvoicerParty() {
        return invoicerParty;
    }

    public void setInvoicerParty(InvoicerParty invoicerParty) {
        this.invoicerParty = invoicerParty;
    }

    public InvoiceeParty getInvoiceeParty() {
        return invoiceeParty;
    }

    public void setInvoiceeParty(InvoiceeParty invoiceeParty) {
        this.invoiceeParty = invoiceeParty;
    }

    public BrokerParty getBrokerParty() {
        return brokerParty;
    }

    public void setBrokerParty(BrokerParty brokerParty) {
        this.brokerParty = brokerParty;
    }

    public List<SpecifiedPaymentMeans> getSpecifiedPaymentMeans() {
        return specifiedPaymentMeans;
    }

    public void setSpecifiedPaymentMeans(List<SpecifiedPaymentMeans> specifiedPaymentMeans) {
        this.specifiedPaymentMeans = specifiedPaymentMeans;
    }

    public SpecifiedMonetarySummation getSpecifiedMonetarySummation() {
        return specifiedMonetarySummation;
    }

    public void setSpecifiedMonetarySummation(SpecifiedMonetarySummation specifiedMonetarySummation) {
        this.specifiedMonetarySummation = specifiedMonetarySummation;
    }
}
