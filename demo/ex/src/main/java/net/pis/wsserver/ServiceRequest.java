package net.pis.wsserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;

/**
 * <p>
 * anonymous complex type에 대한 Java 클래스입니다.
 *
 * <p>
 * 다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="request" type="{http://schemas.datacontract.org/2004/07/BizOn.Biz.Model}ItgBillRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request"
})
@XmlRootElement(name = "ServiceRequest")
public class ServiceRequest {

    @XmlElementRef(name = "request", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ItgBillRequest> request;

    /**
     * request 속성의 값을 가져옵니다.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link ItgBillRequest }{@code >}
     *
     */
    public JAXBElement<ItgBillRequest> getRequest() {
        return request;
    }

    /**
     * request 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ItgBillRequest }{@code >}
     *
     */
    public void setRequest(JAXBElement<ItgBillRequest> value) {
        this.request = value;
    }

}
