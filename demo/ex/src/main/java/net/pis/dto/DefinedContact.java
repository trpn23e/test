/*
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 담당자 정보
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "departmentNameText", "personNameText", "telephoneCommuncation", "uriCommunication"
})
public class DefinedContact {

    /**
     * 담당 부서명
     */
    @XmlElement(name = "DepartmentNameText")
    private String departmentNameText;

    /**
     * 담당자 명
     */
    @XmlElement(name = "PersonNameText")
    private String personNameText;

    /**
     * 담당자 전화번호
     */
    @XmlElement(name = "TelephoneCommunication")
    private String telephoneCommuncation;

    /**
     * 담당자 이메일
     */
    @XmlElement(name = "URICommunication")
    private String uriCommunication;

    public String getDepartmentNameText() {
        return departmentNameText;
    }

    public void setDepartmentNameText(String departmentNameText) {
        this.departmentNameText = departmentNameText;
    }

    public String getPersonNameText() {
        return personNameText;
    }

    public void setPersonNameText(String personNameText) {
        this.personNameText = personNameText;
    }

    public String getTelephoneCommuncation() {
        return telephoneCommuncation;
    }

    public void setTelephoneCommuncation(String telephoneCommuncation) {
        this.telephoneCommuncation = telephoneCommuncation;
    }

    public String getUriCommunication() {
        return uriCommunication;
    }

    public void setUriCommunication(String uriCommunication) {
        this.uriCommunication = uriCommunication;
    }

}
