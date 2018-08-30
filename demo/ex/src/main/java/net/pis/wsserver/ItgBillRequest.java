package net.pis.wsserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * ItgBillRequest complex type에 대한 Java 클래스입니다.
 *
 * <p>
 * 다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="ItgBillRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FILES" type="{http://schemas.datacontract.org/2004/07/BizOn.Biz.Model}ArrayOfAttachFile"/&gt;
 *         &lt;element name="MSG_DOC" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring"/&gt;
 *         &lt;element name="REQUEST_HEADER" type="{http://schemas.datacontract.org/2004/07/BizOn.Biz.Model}RequestHeader"/&gt;
 *         &lt;element name="REQ_DOC" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItgBillRequest", namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", propOrder = {
    "files",
    "msgdoc",
    "requestheader",
    "reqdoc"
})
public class ItgBillRequest {

    @XmlElement(name = "FILES", required = true, nillable = true)
    protected ArrayOfAttachFile files;
    @XmlElement(name = "MSG_DOC", required = true, nillable = true)
    protected ArrayOfstring msgdoc;
    @XmlElement(name = "REQUEST_HEADER", required = true, nillable = true)
    protected RequestHeader requestheader;
    @XmlElement(name = "REQ_DOC", required = true, nillable = true)
    protected ArrayOfstring reqdoc;

    /**
     * files 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link ArrayOfAttachFile }
     *
     */
    public ArrayOfAttachFile getFILES() {
        return files;
    }

    /**
     * files 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link ArrayOfAttachFile }
     *
     */
    public void setFILES(ArrayOfAttachFile value) {
        this.files = value;
    }

    /**
     * msgdoc 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link ArrayOfstring }
     *
     */
    public ArrayOfstring getMSGDOC() {
        return msgdoc;
    }

    /**
     * msgdoc 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link ArrayOfstring }
     *
     */
    public void setMSGDOC(ArrayOfstring value) {
        this.msgdoc = value;
    }

    /**
     * requestheader 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link RequestHeader }
     *
     */
    public RequestHeader getREQUESTHEADER() {
        return requestheader;
    }

    /**
     * requestheader 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link RequestHeader }
     *
     */
    public void setREQUESTHEADER(RequestHeader value) {
        this.requestheader = value;
    }

    /**
     * reqdoc 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link ArrayOfstring }
     *
     */
    public ArrayOfstring getREQDOC() {
        return reqdoc;
    }

    /**
     * reqdoc 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link ArrayOfstring }
     *
     */
    public void setREQDOC(ArrayOfstring value) {
        this.reqdoc = value;
    }

}
