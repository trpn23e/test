/*
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 공급받는자 정보
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "typeCode", "nameText", "classfigicationCode",
    "specifiedOrganization",
    "specifiedPerson",
    "primaryDefinedContract", "secondaryDefinedContract",
    "specifiedAddress"
})
public class InvoiceeParty {

    @XmlElement(name = "SpecifiedOrganization")
    private SpecifiedOrganization specifiedOrganization;

    @XmlElement(name = "ID")
    private String id;
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

    /**
     * 연락처 1 공급받는자 전용
     */
    @XmlElement(name = "PrimaryDefinedContact")
    private DefinedContact primaryDefinedContract;

    /**
     * 연락처 2 공급받는자 전용
     */
    @XmlElement(name = "SecondaryDefinedContact")
    private DefinedContact secondaryDefinedContract;

    public SpecifiedOrganization getSpecifiedOrganization() {
        return specifiedOrganization;
    }

    public void setSpecifiedOrganization(SpecifiedOrganization specifiedOrganization) {
        this.specifiedOrganization = specifiedOrganization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public DefinedContact getPrimaryDefinedContract() {
        return primaryDefinedContract;
    }

    public void setPrimaryDefinedContract(DefinedContact primaryDefinedContract) {
        this.primaryDefinedContract = primaryDefinedContract;
    }

    public DefinedContact getSecondaryDefinedContract() {
        return secondaryDefinedContract;
    }

    public void setSecondaryDefinedContract(DefinedContact secondaryDefinedContract) {
        this.secondaryDefinedContract = secondaryDefinedContract;
    }

}
