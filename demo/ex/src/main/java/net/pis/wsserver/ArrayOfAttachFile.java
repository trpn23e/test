package net.pis.wsserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ArrayOfAttachFile complex type에 대한 Java 클래스입니다.
 *
 * <p>
 * 다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="ArrayOfAttachFile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AttachFile" type="{http://schemas.datacontract.org/2004/07/BizOn.Biz.Model}AttachFile" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAttachFile", namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", propOrder = {
    "attachFile"
})
public class ArrayOfAttachFile {

    @XmlElement(name = "AttachFile", nillable = true)
    protected List<AttachFile> attachFile;

    /**
     * Gets the value of the attachFile property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the attachFile property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachFile().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttachFile }
     *
     *
     */
    public List<AttachFile> getAttachFile() {
        if (attachFile == null) {
            attachFile = new ArrayList<AttachFile>();
        }
        return this.attachFile;
    }

}
