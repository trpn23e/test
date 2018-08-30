/**
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * KEC표준전자(세금)계산서
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlRootElement(name = "TaxInvoice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"exchangedDocument", "taxInvoiceDocument",
    "taxInvoiceSettlement", "taxInvoiceTradeLineItem",
    "etcNo1", "etcNo2", "etcNo3", "etcNo4"})
public class TaxInvoice implements Serializable {

    /**
     * 관리정보
     */
    @XmlElement(name = "ExchangedDocument")
    private ExchangedDocument exchangedDocument;

    /**
     * 기본정보
     */
    @XmlElement(name = "TaxInvoiceDocument")
    private TaxInvoiceDocument taxInvoiceDocument;

    /**
     * 계산서 정보
     */
    @XmlElement(name = "TaxInvoiceTradeSettlement")
    private TaxInvoiceTradeSettlement taxInvoiceSettlement;

    /**
     * 상품정보
     */
    @XmlElement(name = "TaxInvoiceTradeLineItem")
    private List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItem;

    /**
     * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
     */
    @XmlElement(name = "EtcNo1")
    private String etcNo1;
    /**
     * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
     */
    @XmlElement(name = "EtcNo2")
    private String etcNo2;
    /**
     * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
     */
    @XmlElement(name = "EtcNo3")
    private String etcNo3;
    /**
     * 거래명세서 전용 (세금계산서에서는 사용해서는 안됨)
     */
    @XmlElement(name = "EtcNo4")
    private String etcNo4;

    public ExchangedDocument getExchangedDocument() {
        return exchangedDocument;
    }

    public void setExchangedDocument(ExchangedDocument exchangedDocument) {
        this.exchangedDocument = exchangedDocument;
    }

    public TaxInvoiceDocument getTaxInvoiceDocument() {
        return taxInvoiceDocument;
    }

    public void setTaxInvoiceDocument(TaxInvoiceDocument taxInvoiceDocument) {
        this.taxInvoiceDocument = taxInvoiceDocument;
    }

    public TaxInvoiceTradeSettlement getTaxInvoiceSettlement() {
        return taxInvoiceSettlement;
    }

    public void setTaxInvoiceSettlement(TaxInvoiceTradeSettlement taxInvoiceSettlement) {
        this.taxInvoiceSettlement = taxInvoiceSettlement;
    }

    public List<TaxInvoiceTradeLineItem> getTaxInvoiceTradeLineItem() {
        return taxInvoiceTradeLineItem;
    }

    public void setTaxInvoiceTradeLineItem(List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItem) {
        this.taxInvoiceTradeLineItem = taxInvoiceTradeLineItem;
    }

    public String getEtcNo1() {
        return etcNo1;
    }

    public void setEtcNo1(String etcNo1) {
        this.etcNo1 = etcNo1;
    }

    public String getEtcNo2() {
        return etcNo2;
    }

    public void setEtcNo2(String etcNo2) {
        this.etcNo2 = etcNo2;
    }

    public String getEtcNo3() {
        return etcNo3;
    }

    public void setEtcNo3(String etcNo3) {
        this.etcNo3 = etcNo3;
    }

    public String getEtcNo4() {
        return etcNo4;
    }

    public void setEtcNo4(String etcNo4) {
        this.etcNo4 = etcNo4;
    }

}
