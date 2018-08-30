package net.pis.wsserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * RequestHeader complex type에 대한 Java 클래스입니다.
 *
 * <p>
 * 다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="RequestHeader"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AUTH_TICKET" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CONVERSATION_ID" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring"/&gt;
 *         &lt;element name="MESSAGE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RCV_COMREGNO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RCV_SYSTEMID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="REQ_TIME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SERVICE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SIGNAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SND_COMREGNO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SYSTEM_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeader", namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", propOrder = {
    "authticket",
    "conversationid",
    "messageid",
    "rcvcomregno",
    "rcvsystemid",
    "reqtime",
    "servicecode",
    "signal",
    "sndcomregno",
    "systemtype"
})
public class RequestHeader {

    @XmlElement(name = "AUTH_TICKET", required = true, nillable = true)
    protected String authticket;
    @XmlElement(name = "CONVERSATION_ID", required = true, nillable = true)
    protected ArrayOfstring conversationid;
    @XmlElement(name = "MESSAGE_ID", required = true, nillable = true)
    protected String messageid;
    @XmlElement(name = "RCV_COMREGNO", required = true, nillable = true)
    protected String rcvcomregno;
    @XmlElement(name = "RCV_SYSTEMID", required = true, nillable = true)
    protected String rcvsystemid;
    @XmlElement(name = "REQ_TIME", required = true, nillable = true)
    protected String reqtime;
    @XmlElement(name = "SERVICE_CODE", required = true, nillable = true)
    protected String servicecode;
    @XmlElement(name = "SIGNAL", required = true, nillable = true)
    protected String signal;
    @XmlElement(name = "SND_COMREGNO", required = true, nillable = true)
    protected String sndcomregno;
    @XmlElement(name = "SYSTEM_TYPE", required = true, nillable = true)
    protected String systemtype;

    /**
     * authticket 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAUTHTICKET() {
        return authticket;
    }

    /**
     * authticket 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAUTHTICKET(String value) {
        this.authticket = value;
    }

    /**
     * conversationid 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link ArrayOfstring }
     *
     */
    public ArrayOfstring getCONVERSATIONID() {
        return conversationid;
    }

    /**
     * conversationid 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link ArrayOfstring }
     *
     */
    public void setCONVERSATIONID(ArrayOfstring value) {
        this.conversationid = value;
    }

    /**
     * messageid 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMESSAGEID() {
        return messageid;
    }

    /**
     * messageid 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setMESSAGEID(String value) {
        this.messageid = value;
    }

    /**
     * rcvcomregno 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRCVCOMREGNO() {
        return rcvcomregno;
    }

    /**
     * rcvcomregno 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRCVCOMREGNO(String value) {
        this.rcvcomregno = value;
    }

    /**
     * rcvsystemid 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRCVSYSTEMID() {
        return rcvsystemid;
    }

    /**
     * rcvsystemid 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRCVSYSTEMID(String value) {
        this.rcvsystemid = value;
    }

    /**
     * reqtime 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getREQTIME() {
        return reqtime;
    }

    /**
     * reqtime 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setREQTIME(String value) {
        this.reqtime = value;
    }

    /**
     * servicecode 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSERVICECODE() {
        return servicecode;
    }

    /**
     * servicecode 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSERVICECODE(String value) {
        this.servicecode = value;
    }

    /**
     * signal 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSIGNAL() {
        return signal;
    }

    /**
     * signal 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSIGNAL(String value) {
        this.signal = value;
    }

    /**
     * sndcomregno 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSNDCOMREGNO() {
        return sndcomregno;
    }

    /**
     * sndcomregno 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSNDCOMREGNO(String value) {
        this.sndcomregno = value;
    }

    /**
     * systemtype 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSYSTEMTYPE() {
        return systemtype;
    }

    /**
     * systemtype 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSYSTEMTYPE(String value) {
        this.systemtype = value;
    }

}
