package net.pis.wsserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.bizon.server.dti.connector.wsserver
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ItgBillRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", "ItgBillRequest");
    private final static QName _ArrayOfAttachFile_QNAME = new QName("http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", "ArrayOfAttachFile");
    private final static QName _AttachFile_QNAME = new QName("http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", "AttachFile");
    private final static QName _RequestHeader_QNAME = new QName("http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", "RequestHeader");
    private final static QName _ItgBillACKResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", "ItgBillACKResponse");
    private final static QName _ItgBillResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", "ItgBillResponse");
    private final static QName _ArrayOfstring_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _DoTestP_QNAME = new QName("http://tempuri.org/", "p");
    private final static QName _DoTestResponseDoTestResult_QNAME = new QName("http://tempuri.org/", "DoTestResult");
    private final static QName _ServiceRequestRequest_QNAME = new QName("http://tempuri.org/", "request");
    private final static QName _ServiceRequestResponseServiceRequestResult_QNAME = new QName("http://tempuri.org/", "ServiceRequestResult");
    private final static QName _ResultReceiveResult_QNAME = new QName("http://tempuri.org/", "result");
    private final static QName _ResultReceiveResponseResultReceiveResult_QNAME = new QName("http://tempuri.org/", "ResultReceiveResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package:
     * com.bizon.server.dti.connector.wsserver
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoTest }
     *
     */
    public DoTest createDoTest() {
        return new DoTest();
    }

    /**
     * Create an instance of {@link DoTestResponse }
     *
     */
    public DoTestResponse createDoTestResponse() {
        return new DoTestResponse();
    }

    /**
     * Create an instance of {@link ServiceRequest }
     *
     */
    public ServiceRequest createServiceRequest() {
        return new ServiceRequest();
    }

    /**
     * Create an instance of {@link ItgBillRequest }
     *
     */
    public ItgBillRequest createItgBillRequest() {
        return new ItgBillRequest();
    }

    /**
     * Create an instance of {@link ServiceRequestResponse }
     *
     */
    public ServiceRequestResponse createServiceRequestResponse() {
        return new ServiceRequestResponse();
    }

    /**
     * Create an instance of {@link ItgBillACKResponse }
     *
     */
    public ItgBillACKResponse createItgBillACKResponse() {
        return new ItgBillACKResponse();
    }

    /**
     * Create an instance of {@link ResultReceive }
     *
     */
    public ResultReceive createResultReceive() {
        return new ResultReceive();
    }

    /**
     * Create an instance of {@link ItgBillResponse }
     *
     */
    public ItgBillResponse createItgBillResponse() {
        return new ItgBillResponse();
    }

    /**
     * Create an instance of {@link ResultReceiveResponse }
     *
     */
    public ResultReceiveResponse createResultReceiveResponse() {
        return new ResultReceiveResponse();
    }

    /**
     * Create an instance of {@link ArrayOfAttachFile }
     *
     */
    public ArrayOfAttachFile createArrayOfAttachFile() {
        return new ArrayOfAttachFile();
    }

    /**
     * Create an instance of {@link AttachFile }
     *
     */
    public AttachFile createAttachFile() {
        return new AttachFile();
    }

    /**
     * Create an instance of {@link RequestHeader }
     *
     */
    public RequestHeader createRequestHeader() {
        return new RequestHeader();
    }

    /**
     * Create an instance of {@link ArrayOfstring }
     *
     */
    public ArrayOfstring createArrayOfstring() {
        return new ArrayOfstring();
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ItgBillRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", name = "ItgBillRequest")
    public JAXBElement<ItgBillRequest> createItgBillRequest(ItgBillRequest value) {
        return new JAXBElement<ItgBillRequest>(_ItgBillRequest_QNAME, ItgBillRequest.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfAttachFile }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", name = "ArrayOfAttachFile")
    public JAXBElement<ArrayOfAttachFile> createArrayOfAttachFile(ArrayOfAttachFile value) {
        return new JAXBElement<ArrayOfAttachFile>(_ArrayOfAttachFile_QNAME, ArrayOfAttachFile.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link AttachFile }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", name = "AttachFile")
    public JAXBElement<AttachFile> createAttachFile(AttachFile value) {
        return new JAXBElement<AttachFile>(_AttachFile_QNAME, AttachFile.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link RequestHeader }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", name = "RequestHeader")
    public JAXBElement<RequestHeader> createRequestHeader(RequestHeader value) {
        return new JAXBElement<RequestHeader>(_RequestHeader_QNAME, RequestHeader.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ItgBillACKResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", name = "ItgBillACKResponse")
    public JAXBElement<ItgBillACKResponse> createItgBillACKResponse(ItgBillACKResponse value) {
        return new JAXBElement<ItgBillACKResponse>(_ItgBillACKResponse_QNAME, ItgBillACKResponse.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ItgBillResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", name = "ItgBillResponse")
    public JAXBElement<ItgBillResponse> createItgBillResponse(ItgBillResponse value) {
        return new JAXBElement<ItgBillResponse>(_ItgBillResponse_QNAME, ItgBillResponse.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfstring")
    public JAXBElement<ArrayOfstring> createArrayOfstring(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_ArrayOfstring_QNAME, ArrayOfstring.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Float }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Long }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link QName }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Short }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Short }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Long }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "p", scope = DoTest.class)
    public JAXBElement<String> createDoTestP(String value) {
        return new JAXBElement<String>(_DoTestP_QNAME, String.class, DoTest.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "DoTestResult", scope = DoTestResponse.class)
    public JAXBElement<String> createDoTestResponseDoTestResult(String value) {
        return new JAXBElement<String>(_DoTestResponseDoTestResult_QNAME, String.class, DoTestResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ItgBillRequest }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ServiceRequest.class)
    public JAXBElement<ItgBillRequest> createServiceRequestRequest(ItgBillRequest value) {
        return new JAXBElement<ItgBillRequest>(_ServiceRequestRequest_QNAME, ItgBillRequest.class, ServiceRequest.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ItgBillACKResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ServiceRequestResult", scope = ServiceRequestResponse.class)
    public JAXBElement<ItgBillACKResponse> createServiceRequestResponseServiceRequestResult(ItgBillACKResponse value) {
        return new JAXBElement<ItgBillACKResponse>(_ServiceRequestResponseServiceRequestResult_QNAME, ItgBillACKResponse.class, ServiceRequestResponse.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ItgBillResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "result", scope = ResultReceive.class)
    public JAXBElement<ItgBillResponse> createResultReceiveResult(ItgBillResponse value) {
        return new JAXBElement<ItgBillResponse>(_ResultReceiveResult_QNAME, ItgBillResponse.class, ResultReceive.class, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link ItgBillACKResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ResultReceiveResult", scope = ResultReceiveResponse.class)
    public JAXBElement<ItgBillACKResponse> createResultReceiveResponseResultReceiveResult(ItgBillACKResponse value) {
        return new JAXBElement<ItgBillACKResponse>(_ResultReceiveResponseResultReceiveResult_QNAME, ItgBillACKResponse.class, ResultReceiveResponse.class, value);
    }

}
