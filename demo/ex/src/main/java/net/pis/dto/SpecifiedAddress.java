/*
 * (c)BOC
 */
package net.pis.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 주소
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SpecifiedAddress {

    /**
     * 주소
     */
    @XmlElement(name = "LineOneText")
    private String lineOneText;

    public String getLineOneText() {
        return lineOneText;
    }

    public void setLineOneText(String lineOneText) {
        this.lineOneText = lineOneText;
    }

}
