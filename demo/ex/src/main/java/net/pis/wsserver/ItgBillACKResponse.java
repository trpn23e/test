package net.pis.wsserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * ItgBillACKResponse complex type에 대한 Java 클래스입니다.
 *
 * <p>
 * 다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="ItgBillACKResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CONVERSATION_ID" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring"/&gt;
 *         &lt;element name="MESSAGE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RESULT_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RESULT_MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RES_TIME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SIGNAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItgBillACKResponse", namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", propOrder = {
    "conversationid",
    "messageid",
    "resultcode",
    "resultmessage",
    "restime",
    "signal"
})
public class ItgBillACKResponse {

    @XmlElement(name = "CONVERSATION_ID", required = true, nillable = true)
    protected ArrayOfstring conversationid;
    @XmlElement(name = "MESSAGE_ID", required = true, nillable = true)
    protected String messageid;
    @XmlElement(name = "RESULT_CODE", required = true, nillable = true)
    protected String resultcode;
    @XmlElement(name = "RESULT_MESSAGE", required = true, nillable = true)
    protected String resultmessage;
    @XmlElement(name = "RES_TIME", required = true, nillable = true)
    protected String restime;
    @XmlElement(name = "SIGNAL", required = true, nillable = true)
    protected String signal;

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
     * resultcode 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRESULTCODE() {
        return resultcode;
    }

    /**
     * resultcode 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRESULTCODE(String value) {
        this.resultcode = value;
    }

    /**
     * resultmessage 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRESULTMESSAGE() {
        return resultmessage;
    }

    /**
     * resultmessage 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRESULTMESSAGE(String value) {
        this.resultmessage = value;
    }

    /**
     * restime 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRESTIME() {
        return restime;
    }

    /**
     * restime 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRESTIME(String value) {
        this.restime = value;
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

}
