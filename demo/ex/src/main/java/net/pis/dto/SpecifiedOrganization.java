/*
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 사업장
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"taxRegistrationId", "businessTypeCode"})
public class SpecifiedOrganization {

    /**
     * 종사업장 번호
     */
    @XmlElement(name = "TaxRegistrationID")
    private String taxRegistrationId;

    /**
     * 공급받는자 사업자등록번호 구분코드
     * <p>
     * <ul>
     * <li>01 : 사업자등록번호</li>
     * <li>02 : 주민등록번호</li>
     * <li>03 : 외국인</li>
     * </ul>
     * </p>
     */
    @XmlElement(name = "BusinessTypeCode")
    private String businessTypeCode;

    public String getTaxRegistrationId() {
        return taxRegistrationId;
    }

    public void setTaxRegistrationId(String taxRegistrationId) {
        this.taxRegistrationId = taxRegistrationId;
    }

    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    public void setBusinessTypeCode(String businessTypeCode) {
        this.businessTypeCode = businessTypeCode;
    }

}
