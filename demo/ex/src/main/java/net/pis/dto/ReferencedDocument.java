/*
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 사업자 관리번호
 *
 * @author jh,Seo
 * @version
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferencedDocument {

    /**
     * 사업자 관리번호
     */
    @XmlElement(name = "ID")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
