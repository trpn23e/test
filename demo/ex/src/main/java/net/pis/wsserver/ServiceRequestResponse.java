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
 *         &lt;element name="ServiceRequestResult" type="{http://schemas.datacontract.org/2004/07/BizOn.Biz.Model}ItgBillACKResponse" minOccurs="0"/&gt;
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
    "serviceRequestResult"
})
@XmlRootElement(name = "ServiceRequestResponse")
public class ServiceRequestResponse {

    @XmlElementRef(name = "ServiceRequestResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ItgBillACKResponse> serviceRequestResult;

    /**
     * serviceRequestResult 속성의 값을 가져옵니다.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link ItgBillACKResponse }{@code >}
     *
     */
    public JAXBElement<ItgBillACKResponse> getServiceRequestResult() {
        return serviceRequestResult;
    }

    /**
     * serviceRequestResult 속성의 값을 설정합니다.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ItgBillACKResponse }{@code >}
     *
     */
    public void setServiceRequestResult(JAXBElement<ItgBillACKResponse> value) {
        this.serviceRequestResult = value;
    }

}
