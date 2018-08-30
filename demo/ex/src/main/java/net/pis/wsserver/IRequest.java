package net.pis.wsserver;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
/**
 * This class was generated by Apache CXF 3.1.5 2017-04-14T18:17:57.580+09:00
 * Generated source version: 3.1.5
 *
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "IRequest")
@XmlSeeAlso({ObjectFactory.class})
public interface IRequest {

    @WebMethod(operationName = "ResultReceive", action = "http://tempuri.org/IRequest/ResultReceive")
    @Action(input = "http://tempuri.org/IRequest/ResultReceive", output = "http://tempuri.org/IRequest/ResultReceiveResponse")
    @RequestWrapper(localName = "ResultReceive", targetNamespace = "http://tempuri.org/", className = "net.pis.wsserver.ResultReceive")
    @ResponseWrapper(localName = "ResultReceiveResponse", targetNamespace = "http://tempuri.org/", className = "net.pis.wsserver.ResultReceiveResponse")
    @WebResult(name = "ResultReceiveResult", targetNamespace = "http://tempuri.org/")
    public net.pis.wsserver.ItgBillACKResponse resultReceive(
            @WebParam(name = "result", targetNamespace = "http://tempuri.org/") net.pis.wsserver.ItgBillResponse result
    );

    @WebMethod(operationName = "DoTest", action = "http://tempuri.org/IRequest/DoTest")
    @Action(input = "http://tempuri.org/IRequest/DoTest", output = "http://tempuri.org/IRequest/DoTestResponse")
    @RequestWrapper(localName = "DoTest", targetNamespace = "http://tempuri.org/", className = "net.pis.wsserver.DoTest")
    @ResponseWrapper(localName = "DoTestResponse", targetNamespace = "http://tempuri.org/", className = "net.pis.wsserver.DoTestResponse")
    @WebResult(name = "DoTestResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String doTest(
            @WebParam(name = "p", targetNamespace = "http://tempuri.org/") java.lang.String p
    );

    @WebMethod(operationName = "ServiceRequest", action = "http://tempuri.org/IRequest/ServiceRequest")
    @Action(input = "http://tempuri.org/IRequest/ServiceRequest", output = "http://tempuri.org/IRequest/ServiceRequestResponse")
    @RequestWrapper(localName = "ServiceRequest", targetNamespace = "http://tempuri.org/", className = "net.pis.wsserver.ServiceRequest")
    @ResponseWrapper(localName = "ServiceRequestResponse", targetNamespace = "http://tempuri.org/", className = "net.pis.wsserver.ServiceRequestResponse")
    @WebResult(name = "ServiceRequestResult", targetNamespace = "http://tempuri.org/")
    public net.pis.wsserver.ItgBillACKResponse serviceRequest(
            @WebParam(name = "request", targetNamespace = "http://tempuri.org/") net.pis.wsserver.ItgBillRequest request
    );
}