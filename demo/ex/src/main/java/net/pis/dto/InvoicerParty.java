/*
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * 공급자 정보(위탁 사업자 정보)
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "typeCode", "nameText", "classfigicationCode",
    "specifiedOrganization",
    "specifiedPerson",
    "definedContact",
    "specifiedAddress"
})
public class InvoicerParty {

    @XmlElement(name = "ID")
    private String id;

    @XmlElement(name = "SpecifiedOrganization", required = true)
    private SpecifiedOrganization specifiedOrganization;

    @XmlElement(name = "NameText")
    private String nameText;

    @XmlElement(name = "SpecifiedPerson")
    private SpecifiedPerson specifiedPerson;

    @XmlElement(name = "SpecifiedAddress")
    private SpecifiedAddress specifiedAddress;

    @XmlElement(name = "TypeCode")
    private String typeCode;

    @XmlElement(name = "ClassificationCode")
    private String classfigicationCode;

    @XmlElement(name = "DefinedContact")

    private DefinedContact definedContact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SpecifiedOrganization getSpecifiedOrganization() {
        return specifiedOrganization;
    }

    public void setSpecifiedOrganization(SpecifiedOrganization specifiedOrganization) {
        this.specifiedOrganization = specifiedOrganization;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public SpecifiedPerson getSpecifiedPerson() {
        return specifiedPerson;
    }

    public void setSpecifiedPerson(SpecifiedPerson specifiedPerson) {
        this.specifiedPerson = specifiedPerson;
    }

    public SpecifiedAddress getSpecifiedAddress() {
        return specifiedAddress;
    }

    public void setSpecifiedAddress(SpecifiedAddress specifiedAddress) {
        this.specifiedAddress = specifiedAddress;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getClassfigicationCode() {
        return classfigicationCode;
    }

    public void setClassfigicationCode(String classfigicationCode) {
        this.classfigicationCode = classfigicationCode;
    }

    public DefinedContact getDefinedContact() {
        return definedContact;
    }

    public void setDefinedContact(DefinedContact definedContact) {
        this.definedContact = definedContact;
    }

}
