/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.common.TaxInvoiceException;
import net.pis.dto.*;
import net.pis.dto.table.DTIApproveIdDTO;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIItemDTO;
import net.pis.dto.table.DTIMainDTO;
import net.pis.message.MessageMetaInfo;
import net.pis.service.table.DTIApproveIdService;
import net.pis.service.table.DTIInterfaceService;
import net.pis.service.table.DTIItemService;
import net.pis.service.table.DTIMainService;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Service
public class TaxInvoiceSerializationServiceImpl implements TaxInvoiceSerializationService {

    public static void main(String[] args) throws Exception {

        TaxInvoiceSerializationServiceImpl taxService = new TaxInvoiceSerializationServiceImpl();
        TaxInvoice etax = taxService.testCreate();

        StringWriter writer = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(TaxInvoice.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        //jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "http://www.google.com");
        jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
            "urn:kr:or:kec:standard:Tax:ReusableAggregateBusinessInformationEntitySchemaModule:1:0 http://www.kec.or.kr/standard/Tax/TaxInvoiceSchemaModule_1.0.xsd");

        //jaxbMarshaller.marshal(etax, writer);
        //System.out.println(writer.toString());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        jaxbMarshaller.marshal(etax, document);

        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        //removeNodes(document);
        transformer.transform(new DOMSource(document), sr);

        System.out.println(sw.toString());

    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DTIMainService dtiMainService;

    @Autowired
    private DTIItemService dtiItemService;

    @Autowired
    private DTIInterfaceService DtiInterfaceService;

    @Autowired
    private DTIApproveIdService dtiApproveIdService;

    // @Autowired
    // private RequestShelterService requestShelterService;

    // @Autowired
    // private TaxInvoiceParser taxInvoiceParser;

    @Override
    public String createETaxDocument(MessageMetaInfo messageMetaInfo) throws TaxInvoiceException {

        String xml;

        JAXBContext jaxbContext;
        Marshaller jaxbMarshaller;
        try {
            TaxInvoice etax = this.createDocument(messageMetaInfo, "DTI");

            jaxbContext = JAXBContext.newInstance(TaxInvoice.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            //jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "http://www.google.com");
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                "urn:kr:or:kec:standard:Tax:ReusableAggregateBusinessInformationEntitySchemaModule:1:0 http://www.kec.or.kr/standard/Tax/TaxInvoiceSchemaModule_1.0.xsd");

            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            jaxbMarshaller.marshal(etax, document);

            removeNodes(document);

            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(document), sr);

            xml = sw.toString();

            //logger.debug("계산서 원본\n{}", xml);
            return xml;

        } catch (Exception e) {
            logger.debug(e.getMessage());

            TaxInvoiceException.TaxInvoiceError te
                = TaxInvoiceException.TaxInvoiceError.CANNOT_CREATE_TAX_INVOICE;

            throw new TaxInvoiceException(
                te.getCode(), e.getMessage() == null ? te.getErrorMessage() : e.getMessage());

        }

    }

    /**
     * 원본 파싱
     *
     * @param xml
     * @return
     * @throws TaxInvoiceException
     */
    @Override
    public TaxInvoice parse(String xml) throws TaxInvoiceException {

        logger.debug(xml);

        TaxInvoice aaRS = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(TaxInvoice.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //Create an XMLReader to use with our filter
            InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));

            //FileInputStream(new File("/home/ok49/share/VM/SmartBill_20140721143511.xml"));
            InputSource source = new InputSource(is);

            final SAXParserFactory sax = SAXParserFactory.newInstance();
            //sax.setNamespaceAware(false);
            final XMLReader reader;

            reader = sax.newSAXParser().getXMLReader();

            SAXSource saxSource = new SAXSource(reader, source);

            //jaxbUnmarshaller.unmarshal(saxSource);
            ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<TaxInvoice> root = unmarshaller.unmarshal(new StreamSource(input), TaxInvoice.class);
            aaRS = root.getValue();

            //System.out.println(aaRS.getExchangedDocument().getIssueDateTime());
            //aaRS.getTaxInvocieDocument().getIssueId();
            //TaxInvoice taxInvoice = (TaxInvoice) jaxbUnmarshaller.unmarshal(saxSource);
            //TaxInvoice taxInvoice = (TaxInvoice) jaxbUnmarshaller.unmarshal(new File("/home/ok49/test.xml"));
            //System.out.println(taxInvoice.getExchangedDocument().getIssueDateTime());
            //System.out.println(taxInvoice.getTaxInvoiceSettlement().getInvoicerParty().getNameText());
        } catch (JAXBException | UnsupportedEncodingException | ParserConfigurationException | SAXException e) {
            logger.error(e.getMessage());

            throw new TaxInvoiceException(
                TaxInvoiceException.TaxInvoiceError.CANNOT_PARSE_TAX_INVOICE,
                e.getMessage());

        }

        return aaRS;

    }

    private TaxInvoice createDocument(
            MessageMetaInfo messageMetaInfo, DTIMainDTO dTIMainDTO, String documentType)
        throws Exception {

        logger.info("=== TaxInvoiceSerializationServiceImpl.createDocument(override method 3param) ======");

        TaxInvoice etax = new TaxInvoice();

        logger.trace("전자 세금계산서 원본 생성");

        logger.trace("관리정보 생성");
        ExchangedDocument exchangedDocument = new ExchangedDocument();
        Date odate = new Date();
        exchangedDocument.setId(dTIMainDTO.getExchangedDocId());
        exchangedDocument.setIssueDateTime(new Date(odate.getTime()));

        ReferencedDocument refDocument = new ReferencedDocument();
        refDocument.setId(dTIMainDTO.getSeqId());
        exchangedDocument.setReferencedDocument(refDocument);

        etax.setExchangedDocument(exchangedDocument);
        logger.trace("관리정보 생성 완료");
        logger.trace("기본정보 생성");
        TaxInvoiceDocument taxInvoiceDocument = new TaxInvoiceDocument();

        logger.trace("승인번호가 없는 경우 생성");

        if ("SAP".equals(messageMetaInfo.getErpSystem())) {
            taxInvoiceDocument.setIssueId(dTIMainDTO.getApproveId());
        } else {

            if (null == dTIMainDTO.getApproveId() || "".equals(dTIMainDTO.getApproveId())) {
                DTIApproveIdDTO dtiApproveIdDTOParm = new DTIApproveIdDTO();
                List<DTIApproveIdDTO> dtiApproveIdDTOList = dtiApproveIdService.read(dtiApproveIdDTOParm);

                logger.info("=== TaxInvoiceSerializationServiceImpl.createDocument(override method 3param) dtiApproveIdDTOList : ======" + dtiApproveIdDTOList.toString());

                if (!dtiApproveIdDTOList.isEmpty()) {

                    synchronized (this) {
                        DTIApproveIdDTO dtiApproveIdDTO = dtiApproveIdDTOList.get(0);

                        String ntscode = dtiApproveIdDTO.getNtsCode();
                        String linkcompanycode = dtiApproveIdDTO.getLinkcompanycode();
                        Integer seqNo = dtiApproveIdDTO.getSeqNo();

                        String issueId = getIssueId(ntscode, linkcompanycode, dTIMainDTO.getDtiWdate(), seqNo);

                        logger.trace("새 승인번호 : {}", issueId);

                        taxInvoiceDocument.setIssueId(issueId);

                        //시퀀스업데이트
                        dtiApproveIdDTO = new DTIApproveIdDTO();
                        dtiApproveIdDTO.setNtsCode(ntscode);
                        dtiApproveIdDTO.setLinkcompanycode(linkcompanycode);

                        // 32진수로 표현할 수 있는 5자리 최대값인 경우 reset 하루에 33554431 이상 발행 안된다.
                        if (33554430 < seqNo) {
                            dtiApproveIdDTO.setSeqNo(0);
                        } else {
                            dtiApproveIdDTO.setSeqNo(++seqNo);
                        }

                        dtiApproveIdService.update(dtiApproveIdDTO);
                    }

                } else {
                    throw new TaxInvoiceException(
                        TaxInvoiceException.TaxInvoiceError.CANNOT_CREATE_TAX_INVOICE, "승인번호를 생성하기 위한 기초 데이타가 없습니다.");
                }

            } else {
                logger.debug("승인번호가 이미 있는 경우 이미 존재하는 승인번호를 사용한다.");
                logger.debug("승인번호  : {}", dTIMainDTO.getApproveId());
                taxInvoiceDocument.setIssueId(dTIMainDTO.getApproveId());
            }
        }

        taxInvoiceDocument.setIssueDateTime(dTIMainDTO.getDtiWdate());
        taxInvoiceDocument.setTypeCode(this.replaceValueLen(dTIMainDTO.getDtiType(), 4)); //세금계산서종류 *
        taxInvoiceDocument.setPurposeCode(this.replaceValueLen(dTIMainDTO.getTaxDemand(), 2)); //영수/청구구분
        taxInvoiceDocument.setAmendmentStatusCode(this.replaceValueLen(dTIMainDTO.getAmendCode(), 2)); //수정코드

        if (dTIMainDTO.getOriIssueId() != null) {
            if (!"".equals(dTIMainDTO.getOriIssueId())) {
                taxInvoiceDocument.setOriginalIssueId(dTIMainDTO.getOriIssueId());
            }
        }

        List<String> descriptions = new ArrayList<>();
        if (null != dTIMainDTO.getRemark()) {
            descriptions.add(dTIMainDTO.getRemark());
        }
        if (null != dTIMainDTO.getRemark2()) {
            descriptions.add(dTIMainDTO.getRemark2());
        }
        if (null != dTIMainDTO.getRemark3()) {
            descriptions.add(dTIMainDTO.getRemark3());
        }

        if (0 < descriptions.size()) {
            taxInvoiceDocument.setDescriptionText(descriptions);
        }

        etax.setTaxInvoiceDocument(taxInvoiceDocument);
        logger.trace("기본정보 생성 완료");

        TaxInvoiceTradeSettlement taxInvoiceTradeSettlement = new TaxInvoiceTradeSettlement();

        // 공급자
        taxInvoiceTradeSettlement.setInvoicerParty(this.createSupplier(dTIMainDTO));

        // 공급 받는자 
        taxInvoiceTradeSettlement.setInvoiceeParty(this.createBuyer(dTIMainDTO));

        if (null != dTIMainDTO.getBrokerComRegno()) {

            if (!"".equals(dTIMainDTO.getBrokerComRegno())) {
                // 수탁자
                taxInvoiceTradeSettlement.setBrokerParty(this.createFiduciary(dTIMainDTO));
            }
        }

        logger.trace("결제 방법별 금액");
        List<SpecifiedPaymentMeans> specifiedPaymentMeansList = new ArrayList<>();
        SpecifiedPaymentMeans specifiedPaymentMeans;

        // 스마트빌 로컬 룰 하나라도 존재하면 네개를 전부 만든다. (뷰어가 그렇게 반응함)
        if (null != dTIMainDTO.getCashCode()
            || null != dTIMainDTO.getCheckCode()
            || null != dTIMainDTO.getNoteCode()
            || null != dTIMainDTO.getReceivableCode()) {

            Double summary
                = (dTIMainDTO.getCashAmount() == null ? 0 : dTIMainDTO.getCashAmount())
                + (dTIMainDTO.getCheckAmount() == null ? 0 : dTIMainDTO.getCheckAmount())
                + (dTIMainDTO.getNoteAmount() == null ? 0 : dTIMainDTO.getNoteAmount())
                + (dTIMainDTO.getReceivableAmount() == null ? 0 : dTIMainDTO.getReceivableAmount());

            if (dTIMainDTO.getCashCode() != null) {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode(dTIMainDTO.getCashCode()); // 결제방법코드
                specifiedPaymentMeans.setPaidAmount(dTIMainDTO.getCashAmount()); // 금액

                //if (null != dTIMainDTO.getCashAmount() && 0.00 != dTIMainDTO.getCashAmount()) {
                if (null != dTIMainDTO.getCashAmount() && summary > 0) {
                    specifiedPaymentMeansList.add(specifiedPaymentMeans);
                }
            } else {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode("10"); // 결제방법코드
                specifiedPaymentMeans.setPaidAmount(0.00); // 금액
                specifiedPaymentMeansList.add(specifiedPaymentMeans);
            }

            if (dTIMainDTO.getCheckCode() != null) {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode(dTIMainDTO.getCheckCode()); // 결제방법코드
                specifiedPaymentMeans.setPaidAmount(dTIMainDTO.getCheckAmount()); // 금액

                //if (null != dTIMainDTO.getCheckAmount() && 0.00 != dTIMainDTO.getCheckAmount()) {
                if (null != dTIMainDTO.getCheckAmount() && summary > 0) {
                    specifiedPaymentMeansList.add(specifiedPaymentMeans);
                }
            } else {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode("20"); // 수표
                specifiedPaymentMeans.setPaidAmount(0.00); // 금액
                specifiedPaymentMeansList.add(specifiedPaymentMeans);
            }

            if (dTIMainDTO.getNoteCode() != null) {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode(dTIMainDTO.getNoteCode()); // 결제방법코드
                specifiedPaymentMeans.setPaidAmount(dTIMainDTO.getNoteAmount()); // 금액

                //if (null != dTIMainDTO.getNoteAmount() && 0.00 != dTIMainDTO.getNoteAmount()) {
                if (null != dTIMainDTO.getNoteAmount() && summary > 0) {
                    specifiedPaymentMeansList.add(specifiedPaymentMeans);
                }
            } else {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode("30"); // 어음
                specifiedPaymentMeans.setPaidAmount(0.00); // 금액
                specifiedPaymentMeansList.add(specifiedPaymentMeans);
            }

            if (dTIMainDTO.getReceivableCode() != null) {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode(dTIMainDTO.getReceivableCode()); // 결제방법코드
                specifiedPaymentMeans.setPaidAmount(dTIMainDTO.getReceivableAmount()); // 금액

                //if (null != dTIMainDTO.getReceivableAmount() && 0.00 != dTIMainDTO.getReceivableAmount()) {
                if (null != dTIMainDTO.getReceivableAmount() && summary > 0) {
                    specifiedPaymentMeansList.add(specifiedPaymentMeans);
                }
            } else {
                specifiedPaymentMeans = new SpecifiedPaymentMeans();
                specifiedPaymentMeans.setTypeCode("40"); // 외상
                specifiedPaymentMeans.setPaidAmount(0.00); // 금액
                specifiedPaymentMeansList.add(specifiedPaymentMeans);
            }

        } // 어디까지나 스마트빌 로컬 룰이다...

        if (specifiedPaymentMeansList.size() > 0) {
            taxInvoiceTradeSettlement.setSpecifiedPaymentMeans(specifiedPaymentMeansList);
        }
        logger.info("결제 방법별 금액 완료");

        logger.info("SUMMARY");
        SpecifiedMonetarySummation specifiedMonetarySummation = new SpecifiedMonetarySummation();

        specifiedMonetarySummation.setChargeTotalAmount(dTIMainDTO.getSupAmount()); //공급가액합계 *
        specifiedMonetarySummation.setTaxTotalAmount(dTIMainDTO.getTaxAmount());    //세액합계
        specifiedMonetarySummation.setGrandTotalAmount(dTIMainDTO.getTotalAmount()); //총액(공급가액+세액)*

        taxInvoiceTradeSettlement.setSpecifiedMonetarySummation(specifiedMonetarySummation);
        logger.info("SUMMARY 완료");

        etax.setTaxInvoiceSettlement(taxInvoiceTradeSettlement);

        List<DTIItemDTO> dTIItemDTOs = null;

        if ("NONSAP".equals(messageMetaInfo.getErpSystem())) {

            if ("DTT".equals(documentType)) {

                dTIItemDTOs = dtiItemService.getDTTItemDTOs(
                    dTIMainDTO.getConversationId(),
                    dTIMainDTO.getSupbuyType(),
                    dTIMainDTO.getDirection());

                //etax.setTaxInvoiceTradeLineItem(this.createLineItemForSOT(dTIMainDTO));//.createLineItemForSOT(dtiInterfaceDto));
            } else {

                dTIItemDTOs = dtiItemService.getDTIItemDTOs(
                    dTIMainDTO.getConversationId(),
                    dTIMainDTO.getSupbuyType(),
                    dTIMainDTO.getDirection());

                //etax.setTaxInvoiceTradeLineItem(this.createLineItem(dTIMainDTO));//.createLineItem(dtiMainD.createLineItem(dtiInterfaceDto));
            }

        } else if ("SAP".equals(messageMetaInfo.getErpSystem())) {
            // SAP 요청은 Pass!
            logger.info("=== TaxInvoiceSerializationServiceImpl.crateDocument if(SAP) ===");
            logger.info("=== SAP은 패스! 1===");
            /***
            RequestShelterDTO requestShelterDTO = requestShelterService.getRequestShelterDTO(messageMetaInfo.getMessageTagId());
            String xml = requestShelterDTO.getData();

            if ("DTT".equals(documentType)) {

                dTIItemDTOs = taxInvoiceParser.getTaxInvoiceItemDTOForSOT(xml);

            } else {
                dTIItemDTOs = taxInvoiceParser.getTaxInvoiceItemDTO(xml);

            }
             ***/
        }

        etax.setTaxInvoiceTradeLineItem(this.createLineItem(dTIItemDTOs));

        /*logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("++++ {} ++++", dTIMainDTO.getDtiWdate());
        logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++");*/
        if ("DTT".equals(documentType)) {

            etax.setEtcNo1(dTIMainDTO.getEtcnum1());
            etax.setEtcNo2(dTIMainDTO.getEtcnum2());
            etax.setEtcNo3(dTIMainDTO.getEtcnum3());
            etax.setEtcNo4(dTIMainDTO.getEtcnum4());
        } else { // DTI
            etax.setEtcNo1(null);
            etax.setEtcNo2(null);
            etax.setEtcNo3(null);
            etax.setEtcNo4(null);
        }

        logger.trace("전자 세금계산서 원본 생성 완료");

        return etax;

    }

    private TaxInvoice createDocument(MessageMetaInfo messageMetaInfo, String documentType) throws Exception {

        logger.info("=== TaxInvoiceSerializationServiceImpl.createDocument() ======");
        TaxInvoice taxInvoice;
        DTIMainDTO dtiMainDTO = null;

        if ("NONSAP".equals(messageMetaInfo.getErpSystem())) {
            DTIInterfaceDTO dTIInterfaceDTOParam = new DTIInterfaceDTO();
            dTIInterfaceDTOParam.setMessageId(messageMetaInfo.getMessageId());

            List<DTIInterfaceDTO> dtiInterfaceDTOList = DtiInterfaceService.read(dTIInterfaceDTOParam);

            logger.info("원본 생성 수 : {}", dtiInterfaceDTOList.size());

            dtiMainDTO = dtiMainService.getDtiMain(dtiInterfaceDTOList.get(0));

        } else if ("SAP".equals(messageMetaInfo.getErpSystem())) {
            // SAP 요청은 Pass!
            logger.info("=== TaxInvoiceSerializationServiceImpl.crateDocument if(SAP) ===");
            logger.info("=== SAP은 패스! 2===");
            /**
            RequestShelterDTO requestShelterDTO
                = requestShelterService.getRequestShelterDTO(messageMetaInfo.getMessageTagId());

            dtiMainDTO = taxInvoiceParser.getTaxInvoiceMainDTO(requestShelterDTO.getData());
             ***/
        }

        taxInvoice = createDocument(messageMetaInfo, dtiMainDTO, documentType);

        return taxInvoice;

    }

    /**
     * 수탁자
     *
     * @param dtiMainDTO
     * @return
     * @throws Exception
     */
    private BrokerParty createFiduciary(DTIMainDTO dtiMainDTO) throws Exception {
        logger.debug("수탁자 정보 생성");
        BrokerParty brokerParty = new BrokerParty();
        brokerParty.setId(this.replaceValueLen(dtiMainDTO.getBrokerComRegno(), 13)); // 공급자사업자등록번호*

        SpecifiedOrganization specifiedOrganization = new SpecifiedOrganization();
        specifiedOrganization.setTaxRegistrationId(this.replaceValueLen(dtiMainDTO.getBrkBizplaceCode(), 4)); //종사업장번호
        if (this.checkNullElement(specifiedOrganization)) {
            brokerParty.setSpecifiedOrganization(specifiedOrganization);
        }

        brokerParty.setNameText(this.replaceValueLen(dtiMainDTO.getBrokerComName(), 70)); //상호*
        SpecifiedPerson specifiedPerson = new SpecifiedPerson();
        specifiedPerson.setNameText(this.replaceValueLen(dtiMainDTO.getBrokerRepName(), 30)); //대표자성명*
        if (this.checkNullElement(specifiedPerson)) {
            brokerParty.setSpecifiedPerson(specifiedPerson);
        }

        SpecifiedAddress specifiedAddress = new SpecifiedAddress();
        specifiedAddress.setLineOneText(this.replaceValueLen(dtiMainDTO.getBrokerComAddr(), 150)); // 주소
        if (this.checkNullElement(specifiedAddress)) {
            brokerParty.setSpecifiedAddress(specifiedAddress);
        }

        brokerParty.setTypeCode(dtiMainDTO.getBrokerComType()); // 업태
        brokerParty.setClassfigicationCode(this.replaceValueLen(dtiMainDTO.getBrokerComClassify(), 40)); //업종

        DefinedContact definedContact = new DefinedContact();
        definedContact.setDepartmentNameText(this.replaceValueLen(dtiMainDTO.getBrokerDeptName(), 40)); // 담당부서명
        definedContact.setPersonNameText(this.replaceValueLen(dtiMainDTO.getBrokerEmpName(), 30)); // 담당자명
        definedContact.setTelephoneCommuncation(this.replaceValueLen(dtiMainDTO.getBrokerTelNum(), 20)); // 담당자전화번호
        definedContact.setUriCommunication(this.replaceValueLen(dtiMainDTO.getBrokerEmail(), 40)); // 담당자이메일
        if (this.checkNullElement(definedContact)) {
            brokerParty.setDefinedContact(definedContact);
        }

        logger.debug("수탁자 정보 생성 완료");

        return brokerParty;
    }

    /**
     * 공급자
     *
     * @param dtiMainDTO
     * @return
     * @throws Exception
     */
    private InvoicerParty createSupplier(DTIMainDTO dtiMainDTO) throws Exception {
        logger.debug("공급자 정보 생성");

        InvoicerParty invoicerParty = new InvoicerParty();
        invoicerParty.setId(this.replaceValueLen(dtiMainDTO.getSupComRegno(), 13)); // 공급자사업자등록번호*

        SpecifiedOrganization specifiedOrganization = new SpecifiedOrganization();
        specifiedOrganization.setTaxRegistrationId(this.replaceValueLen(dtiMainDTO.getSupBizplaceCode(), 4)); //종사업장번호
        if (this.checkNullElement(specifiedOrganization)) {
            invoicerParty.setSpecifiedOrganization(specifiedOrganization);
        }

        invoicerParty.setNameText(this.replaceValueLen(dtiMainDTO.getSupComName(), 70)); //상호*

        SpecifiedPerson specifiedPerson = new SpecifiedPerson();
        specifiedPerson.setNameText(this.replaceValueLen(dtiMainDTO.getSupRepName(), 30)); //대표자성명*
        if (this.checkNullElement(specifiedPerson)) {
            invoicerParty.setSpecifiedPerson(specifiedPerson);
        }

        SpecifiedAddress specifiedAddress = new SpecifiedAddress();
        specifiedAddress.setLineOneText(this.replaceValueLen(dtiMainDTO.getSupComAddr(), 150)); // 주소
        if (this.checkNullElement(specifiedAddress)) {
            invoicerParty.setSpecifiedAddress(specifiedAddress);
        }

        invoicerParty.setTypeCode(this.replaceValueLen(dtiMainDTO.getSupComType(), 40)); // 업태
        invoicerParty.setClassfigicationCode(this.replaceValueLen(dtiMainDTO.getSupComClassify(), 40)); //업종
        DefinedContact definedContact = new DefinedContact();
        definedContact.setDepartmentNameText(this.replaceValueLen(dtiMainDTO.getSupDeptName(), 40)); // 담당부서명
        definedContact.setPersonNameText(this.replaceValueLen(dtiMainDTO.getSupEmpName(), 30)); // 담당자명
        definedContact.setTelephoneCommuncation(this.replaceValueLen(dtiMainDTO.getSupTelNum(), 20)); // 담당자전화번호
        definedContact.setUriCommunication(this.replaceValueLen(dtiMainDTO.getSupEmail(), 40)); // 담당자이메일
        if (this.checkNullElement(definedContact)) {
            invoicerParty.setDefinedContact(definedContact);
        }

        logger.debug("공급자 정보 생성 완료");

        return invoicerParty;
    }

    /**
     * 공급받는자
     *
     * @param dtiMainDTO
     * @return
     * @throws Exception
     */
    private InvoiceeParty createBuyer(DTIMainDTO dtiMainDTO) throws Exception {

        logger.debug("공급받는자 정보 생성");
        InvoiceeParty invoiceeParty = new InvoiceeParty();
        SpecifiedOrganization specifiedOrganization = new SpecifiedOrganization();

        invoiceeParty.setId(this.replaceValueLen(dtiMainDTO.getByrComRegno(), 13)); // 공급받는자사업자등록번호*
        switch (dtiMainDTO.getByrComRegno().length()) {
            case 10:
                specifiedOrganization.setBusinessTypeCode("01"); // 공급받는자사업자등록번호코드*
                break;
            case 13:
                specifiedOrganization.setBusinessTypeCode("02");
                break;
            default:
                specifiedOrganization.setBusinessTypeCode("03");
                break;
        }

        specifiedOrganization.setTaxRegistrationId(this.replaceValueLen(dtiMainDTO.getByrBizplaceCode(), 4)); //종사업장번호
        if (this.checkNullElement(specifiedOrganization)) {
            invoiceeParty.setSpecifiedOrganization(specifiedOrganization);
        }

        invoiceeParty.setNameText(this.replaceValueLen(dtiMainDTO.getByrComName(), 70)); //상호*
        SpecifiedPerson specifiedPerson = new SpecifiedPerson();
        specifiedPerson.setNameText(this.replaceValueLen(dtiMainDTO.getByrRepName(), 30)); //대표자성명*

        if (this.checkNullElement(specifiedPerson)) {
            invoiceeParty.setSpecifiedPerson(specifiedPerson);
        }

        SpecifiedAddress specifiedAddress = new SpecifiedAddress();
        specifiedAddress.setLineOneText(this.replaceValueLen(dtiMainDTO.getByrComAddr(), 150)); // 주소
        if (this.checkNullElement(specifiedAddress)) {
            invoiceeParty.setSpecifiedAddress(specifiedAddress);
        }

        invoiceeParty.setTypeCode(this.replaceValueLen(dtiMainDTO.getByrComType(), 40)); // 업태
        invoiceeParty.setClassfigicationCode(this.replaceValueLen(dtiMainDTO.getByrComClassify(), 40)); //업종

        DefinedContact definedContact = new DefinedContact();
        definedContact.setDepartmentNameText(this.replaceValueLen(dtiMainDTO.getByrDeptName(), 40)); // 담당부서명1
        definedContact.setPersonNameText(this.replaceValueLen(dtiMainDTO.getByrEmpName(), 30)); // 담당자명1
        definedContact.setTelephoneCommuncation(this.replaceValueLen(dtiMainDTO.getByrTelNum(), 20)); // 담당자전화번호1
        definedContact.setUriCommunication(this.replaceValueLen(dtiMainDTO.getByrEmail(), 40)); // 담당자이메일1
        if (this.checkNullElement(definedContact)) {
            invoiceeParty.setPrimaryDefinedContract(definedContact);
        }

        definedContact = new DefinedContact();
        definedContact.setDepartmentNameText(this.replaceValueLen(dtiMainDTO.getByrDeptName2(), 40)); // 담당부서명2
        definedContact.setPersonNameText(this.replaceValueLen(dtiMainDTO.getByrEmpName2(), 30)); // 담당자명2
        definedContact.setTelephoneCommuncation(this.replaceValueLen(dtiMainDTO.getByrTelNum2(), 20)); // 담당자전화번호2
        definedContact.setUriCommunication(this.replaceValueLen(dtiMainDTO.getByrEmail2(), 40)); // 담당자이메일2
        if (this.checkNullElement(definedContact)) {
            invoiceeParty.setSecondaryDefinedContract(definedContact);
        }

        logger.debug("공급받는자 정보 생성 완료");

        return invoiceeParty;

    }

    private List<TaxInvoiceTradeLineItem> createLineItem(List<DTIItemDTO> dTIItemDTOs) {
        logger.debug("상품정보");

        List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItemList = new ArrayList<>();
        TaxInvoiceTradeLineItem taxInvoiceTradeLineItem;
        UnitPrice unitPrice;
        TotalTax totalTax;

        for (DTIItemDTO itemDTO : dTIItemDTOs) {
            taxInvoiceTradeLineItem = new TaxInvoiceTradeLineItem();
            taxInvoiceTradeLineItem.setSequenceNumeric(itemDTO.getDtiLineNum()); // 일련번호 *
            taxInvoiceTradeLineItem.setPurchaseExpiryDateTime(itemDTO.getItemMd()); //공급년월일
            taxInvoiceTradeLineItem.setNameText(itemDTO.getItemName()); // 품목명
            taxInvoiceTradeLineItem.setInformationText(itemDTO.getItemSize()); // 규격
            taxInvoiceTradeLineItem.setDescriptionText(itemDTO.getItemRemark()); // 비고
            taxInvoiceTradeLineItem.setChargeableUnitQuantity(itemDTO.getItemQty()); //수량

            taxInvoiceTradeLineItem.setItemCode(itemDTO.getItemCode());

            unitPrice = new UnitPrice();
            unitPrice.setUnitAmount(itemDTO.getUnitPrice()); //단가
            taxInvoiceTradeLineItem.setUnitPrice(unitPrice);

            taxInvoiceTradeLineItem.setInvoiceAmount(itemDTO.getSupAmount()); // 공급가액

            totalTax = new TotalTax();
            totalTax.setCaculatedAmount(itemDTO.getTaxAmount()); // 세액
            taxInvoiceTradeLineItem.setTotalTax(totalTax);

            taxInvoiceTradeLineItemList.add(taxInvoiceTradeLineItem);

        }
        logger.debug("상품정보 완료");

        return taxInvoiceTradeLineItemList;
    }

    private String getIssueId(String ntsCode, String companyCode, Date wdate, Integer sequence) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String date = sdf.format(wdate);

        Long longSeq = Long.parseLong(sequence.toString());

        String string32dep = Long.toString(longSeq, 32);

        String string32depPadded = String.format("%1$5s", string32dep).replace(' ', '0');

        return date + ntsCode + companyCode + string32depPadded;
    }

    /**
     * 노드에 존재하는 빈 엘리먼트를 제거
     *
     * @param node
     */
    public void removeNodes(Node node) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            removeNodes(list.item(i));
        }
        boolean emptyElement = node.getNodeType() == Node.ELEMENT_NODE
            && node.getChildNodes().getLength() == 0;
        boolean emptyText = node.getNodeType() == Node.TEXT_NODE
            && node.getNodeValue().trim().isEmpty();
        if (emptyElement || emptyText) {
            node.getParentNode().removeChild(node);
        }
    }

    private TaxInvoice testCreate() throws Exception {

        TaxInvoice etax = new TaxInvoice();

        // EXCHANGE DOCUMENT
        ExchangedDocument exchangedDocument = new ExchangedDocument();
        exchangedDocument.setIssueDateTime(Calendar.getInstance().getTime());

        etax.setExchangedDocument(exchangedDocument);
        // end of EXCHANGE DOCUMENT

        // INVOICE SETTLEMENT
        TaxInvoiceTradeSettlement taxInvoiceTradeSettlement = new TaxInvoiceTradeSettlement();

        InvoicerParty supplyer = new InvoicerParty();

        supplyer.setId("1111111119");
        supplyer.setTypeCode("서비스");
        supplyer.setNameText("비지니스온(스마트빌)test");
        supplyer.setClassfigicationCode("서비스");

        SpecifiedPerson specifiedPerson = new SpecifiedPerson();
        specifiedPerson.setNameText("대표자");
        supplyer.setSpecifiedPerson(specifiedPerson);

        DefinedContact definedContract = new DefinedContact();
        definedContract.setPersonNameText("비지니스온(스마트빌)test");
        definedContract.setTelephoneCommuncation("");
        definedContract.setUriCommunication("jh.seo@businesson.co.kr");
        supplyer.setDefinedContact(definedContract);

        SpecifiedAddress specifiedAddress = new SpecifiedAddress();
        specifiedAddress.setLineOneText("서울시 서초 어쩌구");
        supplyer.setSpecifiedAddress(specifiedAddress);

        taxInvoiceTradeSettlement.setInvoicerParty(supplyer);

        SpecifiedOrganization specifiedOrganization = new SpecifiedOrganization();

        specifiedOrganization.setTaxRegistrationId(null);

        supplyer.setSpecifiedOrganization(null);

        InvoiceeParty buyer = new InvoiceeParty();
        buyer.setId("3118503753");
        buyer.setTypeCode("발전설비유지외");
        buyer.setNameText("당진사업처");
        buyer.setClassfigicationCode("제조,건설,서비스");

        SpecifiedOrganization specifiedOrganization2 = new SpecifiedOrganization();
        specifiedOrganization2.setBusinessTypeCode(null);
        if (this.checkNullElement(specifiedOrganization2)) {
            buyer.setSpecifiedOrganization(specifiedOrganization2);
        }

        specifiedPerson = new SpecifiedPerson();
        specifiedPerson.setNameText("최외근");
        buyer.setSpecifiedPerson(specifiedPerson);

        definedContract = new DefinedContact();
        definedContract.setDepartmentNameText("기획처/정보화전략팀");
        definedContract.setPersonNameText("김철수");
        definedContract.setTelephoneCommuncation("111-222-4444");
        definedContract.setUriCommunication("korea@korea.net");

        //buyer.setPrimaryDefinedContract(definedContract);
        specifiedAddress = new SpecifiedAddress();
        specifiedAddress.setLineOneText("서울시 서초 어쩌구");
        buyer.setSpecifiedAddress(specifiedAddress);

        taxInvoiceTradeSettlement.setInvoiceeParty(buyer);

        SpecifiedMonetarySummation specifiedMonetarySummation = new SpecifiedMonetarySummation();
        specifiedMonetarySummation.setChargeTotalAmount(1000.0);
        specifiedMonetarySummation.setGrandTotalAmount(1100.0);
        specifiedMonetarySummation.setTaxTotalAmount(100.0);

        taxInvoiceTradeSettlement.setSpecifiedMonetarySummation(specifiedMonetarySummation);

        SpecifiedPaymentMeans specifiedPaymentMeans = new SpecifiedPaymentMeans();
        specifiedPaymentMeans.setTypeCode("10");
        specifiedPaymentMeans.setPaidAmount(1100.0);

        List<SpecifiedPaymentMeans> specifiedPaymentMeanses = new ArrayList<SpecifiedPaymentMeans>();

        specifiedPaymentMeanses.add(specifiedPaymentMeans);

        taxInvoiceTradeSettlement.setSpecifiedPaymentMeans(specifiedPaymentMeanses);

        etax.setTaxInvoiceSettlement(taxInvoiceTradeSettlement);

        // end of INVOICE SETTLEMENT
        // ITEM
        TaxInvoiceTradeLineItem taxInvoiceTradeLineItem = new TaxInvoiceTradeLineItem();

        taxInvoiceTradeLineItem.setSequenceNumeric(1);
        taxInvoiceTradeLineItem.setInvoiceAmount(1000.0);

        TotalTax totalTax = new TotalTax();
        totalTax.setCaculatedAmount(100.0);

        UnitPrice unitPrice = new UnitPrice();
        unitPrice.setUnitAmount(0.0);

        taxInvoiceTradeLineItem.setTotalTax(totalTax);
        taxInvoiceTradeLineItem.setUnitPrice(unitPrice);

        TaxInvoiceTradeLineItem taxInvoiceTradeLineItem2 = new TaxInvoiceTradeLineItem();

        taxInvoiceTradeLineItem2.setSequenceNumeric(2);
        taxInvoiceTradeLineItem2.setInvoiceAmount(0.0);

        TotalTax totalTax2 = new TotalTax();
        totalTax2.setCaculatedAmount(0.0);

        UnitPrice unitPrice2 = new UnitPrice();
        unitPrice2.setUnitAmount(0.0);

        taxInvoiceTradeLineItem2.setTotalTax(totalTax2);
        taxInvoiceTradeLineItem2.setUnitPrice(unitPrice2);

        List<TaxInvoiceTradeLineItem> taxInvoiceTradeLineItems = new ArrayList<TaxInvoiceTradeLineItem>();

        taxInvoiceTradeLineItems.add(taxInvoiceTradeLineItem);
        taxInvoiceTradeLineItems.add(taxInvoiceTradeLineItem2);

        etax.setTaxInvoiceTradeLineItem(taxInvoiceTradeLineItems);
        // END OF ITEM

        // INVOICE DOCUMENT
        TaxInvoiceDocument taxInvoiceDocument = new TaxInvoiceDocument();
        taxInvoiceDocument.setIssueId("201402284100000801x04a5x");
        taxInvoiceDocument.setTypeCode("0101");
//        taxInvoiceDocument.setDescriptionText("호이호이");
        //taxInvoiceDocument.setAmendmentStatusCode("01");
        taxInvoiceDocument.setPurposeCode("02");
        taxInvoiceDocument.setIssueDateTime(Calendar.getInstance().getTime());
        //taxInvoiceDocument.setOriginalIssueId("201402284100000801x04a5w");

        etax.setTaxInvoiceDocument(taxInvoiceDocument);
        // end of INVOICE DOCUMENT*/

        return etax;

    }

    private boolean checkNullElement(Object obj) throws Exception {

        Field[] f = obj.getClass().getDeclaredFields();

        //int ifieldSize = f.length;
        //int ichk = 1;
        boolean isNotNull = false;
        for (int i = 0; i < f.length; i++) {

            //if (null != org.apache.commons.beanutils.PropertyUtils.getProperty(obj, f[i].getName())) {
            // if (null != PropertyUtils.getProperty(obj, f[i].getName())) {
                isNotNull = true;
            //}
        }

        isNotNull = true;
//		System.out.println(ifieldSize == ichk);
//		if(ifieldSize == ichk){
//			isNotNull = false;
//		}
        return isNotNull;
    }

    @Override
    public String createSpecificationOnTransactionDocument(MessageMetaInfo messageMetaInfo) throws TaxInvoiceException {

        String xml = "";

        JAXBContext jaxbContext = null;
        Marshaller jaxbMarshaller = null;
        try {
            TaxInvoice etax = this.createDocument(messageMetaInfo, "DTT");

            jaxbContext = JAXBContext.newInstance(TaxInvoice.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:kr:or:kec:standard:Tax:ReusableAggregateBusinessInformationEntitySchemaModule:1:0 http://www.kec.or.kr/standard/Tax/TaxInvoiceSchemaModule_1.0.xsd");

            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            jaxbMarshaller.marshal(etax, document);

            removeNodes(document);

            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(document), sr);

            xml = sw.toString();

            //logger.debug("거래 명세서 \n{}", xml);
            return xml;
        } catch (Exception e) {
            logger.debug(e.getMessage());

            throw new TaxInvoiceException(
                TaxInvoiceException.TaxInvoiceError.CANNOT_CREATE_TAX_INVOICE, e.getMessage());

        }

    }

    private String replaceValueLen(String objStr, int delim) {
        String chgValue = null;

        if (objStr != null) {
            if (!objStr.isEmpty()) {
                //  System.out.println("받은문자열 : " + objStr + "   받은문자자릿수 : " + objStr.length() + "      허용자릿수 : " + delim);
                if (delim < objStr.length()) {
                    chgValue = objStr.substring(0, delim);
                    // System.out.println("@@@@@@@@- " + chgValue);
                } else {
                    chgValue = objStr;
                }
            }
        }
        return chgValue;
    }

    private String randomString(int len) {

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

}
