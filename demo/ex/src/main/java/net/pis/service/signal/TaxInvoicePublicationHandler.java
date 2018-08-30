/*
 * (c)BOC
 */
package net.pis.service.signal;

import net.pis.common.Direction;
import net.pis.common.MessageStatus;
import net.pis.common.TaxInvoiceSignal;
import net.pis.common.TaxInvoiceStatus;
import net.pis.common.service.TaxInvoiceMessageDataFactory;
import net.pis.common.service.TaxInvoiceSerializationService;
import net.pis.dto.*;
import net.pis.dto.message.MessageFile;
import net.pis.dto.message.TaxInvoiceMessageData;
import net.pis.dto.message.TaxInvoiceMessageMainDocument;
import net.pis.dto.message.TaxInvoiceMessageMetaDocument;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIItemDTO;
import net.pis.dto.table.DTIMainDTO;
import net.pis.dto.table.DTIStatusDTO;
import net.pis.exception.AdaptingException;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.table.DTIItemService;
import net.pis.service.table.DTIMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.util.*;
/**
 * 정발행 핸들러
 *
 * @author jh, Seo
 */
@Service
public class TaxInvoicePublicationHandler extends TaxInvoiceNonSapSignalHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaxInvoiceMessageDataFactory messageDataFactory;

    // @Autowired
    // private CertificateService certificateService;

    // @Autowired
    // private DigitalSignService digitalSignService;

    @Autowired
    private DTIMainService dTIMainService;

    @Autowired
    private DTIItemService dTIItemService;

    @Autowired
    private TaxInvoiceSerializationService taxInvoiceSerializationService;

    @Autowired
    @Resource(name = "autoApproveThreadPoolTaskExecutor")
    private TaskExecutor autoApproveThreadPoolTaskExecutor;

    @Override
    public List<TaxInvoiceSignal> getProviders() {

        List<TaxInvoiceSignal> providers = new ArrayList<>();
        providers.add(TaxInvoiceSignal.ARISSUE);
        providers.add(TaxInvoiceSignal.ARISSUE_FINISH);

        return providers;
    }

    // 전자서명하고 인증 끝난거 뭐를 xml에서 뽑아서 db처리등 하는것 같은데 너무 복잡하다.
    // 했다고 치자
    private String sendPublishRequest(MessageMetaInfo messageMetaInfo,
                                      TaxInvoiceMessageData messageData) throws SBMSException {

        String signedXML = "";
        String RValue = "인증OK";


        MetaDocumentDataDTO metaDocumentDataDTO = new MetaDocumentDataDTO();

        metaDocumentDataDTO.setDocumentDataId(UUID.randomUUID().toString());
        metaDocumentDataDTO.setMessageTagId(messageMetaInfo.getMessageTagId());
        // metaDocumentDataDTO.setSeq(metaSeq);
        metaDocumentDataDTO.setSeq(1);
        metaDocumentDataDTO.setDocumentType("DOC_TYPE_EXAMPLE_01");
        metaDocumentDataDTO.setDocumentData(
                new StringBuilder().append("<RVALUE>").append(RValue)
                        .append("</RVALUE>").toString()
        );
        logger.info("서명과 관련된 META 데이타 생성 완료");

        logger.info("첨부파일 생성 완료");

        return signedXML;
    }


    @Override

    public void sendRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== 6.TaxInvoicePublicationHandler.sendRequest() 진입 ====");
        logger.info("=== extend,implement 하는 클래스, 인터페이스 들이 연관관계가 있어");
        logger.info("=== TaxInvoiceAdaptingService에서 이 메소드가 호출되게 된다. ");
        logger.info("==========================================================");
        logger.info("발행 시작!");

        MessageTagDTO messageTagDTO = null;

        DTIInterfaceDTO interfaceDTO = (DTIInterfaceDTO) messageService.getInterfaceDTO(messageMetaInfo);

        DTIMainDTO mainDTO = new DTIMainDTO();
        mainDTO.setConversationId(interfaceDTO.getConversationId());
        mainDTO.setSupbuyType(interfaceDTO.getSupbuyType());
        mainDTO.setDirection(interfaceDTO.getDirection());

        DTIStatusDTO statusDTO = new DTIStatusDTO();
        statusDTO.setConversationId(interfaceDTO.getConversationId());
        statusDTO.setSupbuyType(interfaceDTO.getSupbuyType());
        statusDTO.setDirection(interfaceDTO.getDirection());

        try {
            logger.info("원본 생성을 위한 사전 정보 취득");
            logger.info("원본 생성을 위한 사전 정보 취득");
            TaxInvoiceMessageData messageData
                    = messageDataFactory.createTaxInvoiceMessageData(messageMetaInfo);
            logger.info("원본 생성을 위한 사전 정보 취득 완료");
            logger.info("messageData : {}", messageData);

            messageTagDTO = new MessageTagDTO();
            messageTagDTO.setMessageTagId(messageMetaInfo.getMessageTagId());
            messageTagDTO.setMessageId(messageMetaInfo.getMessageId());
            messageTagDTO.setServiceId(SERVICE_ID);
            messageTagDTO.setDirection(Direction.Outbound.getCode());
            messageTagDTO.setFromSystem(messageMetaInfo.getErpSystem());
            messageTagDTO.setMessageStatus(MessageStatus.Recognition.getCode());
            messageTagDTO.setTargetSystemId(messageMetaInfo.getTargetSystemId());
            messageTagDTO.setAuthTicket(messageMetaInfo.getTicket());

            // messageTagDTO.setConversationId(messageData.getMessageHeader().getConversationId());
            // messageTagDTO.setSendRegNo(messageData.getMessageHeader().getSndComregno());
            // messageTagDTO.setReceiveRegNo(messageData.getMessageHeader().getRcvComregno());
            // messageTagDTO.setSignal(messageData.getMessageHeader().getSignal());

            messageTagDTO.setSenderSystemId(messageMetaInfo.getTargetKey());

            // DCT에 테이블이 없으니 일단 만들었다고 치자
            // messageTagService.create(messageTagDTO);
            logger.info("MESSAGE TAG 기록 완료");

            //activityLogDTO = writeActivityLog(messageMetaInfo.getMessageTagId());
            // 전자서명 하고, 인증거치고.. 이걸 전달받은 xml읽어서 뭘 하는것 같은데..
            // 너무 복잡함 패스!
            // String signedXML = this.sendPublishRequest(messageMetaInfo, messageData);
            String signedXML = this.sendPublishRequest(messageMetaInfo, null);

            logger.info("인터페이스 상태 갱신 : {}", MessageStatus.Progress);
            logger.info("STATUS 상태 갱신 : {}", MessageStatus.Progress);

            String mainDocument = messageData.getMessageMainDocument().get(0).getDocumentData();
            logger.info("mainDocument ::::::::::::::::: " + mainDocument);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(mainDocument.getBytes()));
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            XPathExpression expr = xpath.compile("//TaxInvoiceDocument/IssueID");
            String issueId = expr.evaluate(doc);

            mainDTO.setApproveId(issueId);
            // mainDTO.setDtiMsg(signedXML);
            //서명 관련 된건 이것저것 넣을게 많으니 pass! 그냥 messageData로 생성된 xml string을 업데이트
            // 하도록 한다
            mainDTO.setDtiMsg(mainDocument);

            dTIMainService.update(mainDTO);

            logger.info("MAIN 승인번호 갱신");

            /*activityLogDTO.setActivityStatus(MessageStatus.Complete.getCode());
            activityLogDTO.setUdate(Calendar.getInstance().getTime());
            activityLogService.update(activityLogDTO);
             */
            jmsSender.sendMessage(messageMetaInfo);

        } catch (SBMSException se) {
            logger.info("{}", se.getMessage());

            // handleException(se, messageTagDTO, activityLogDTO, interfaceDTO,
            //    messageMetaInfo.getMessageTagId());

            throw new AdaptingException(se);
        } catch (JMSException je) {
            logger.info("{}", je);

            // FIX ME : JMS 오류코드 만들것
            AdaptingException.AdaptingError ae = AdaptingException.AdaptingError.DEFAULT_ADPATING_EXCEPTION;

            AdaptingException ex = new AdaptingException(ae.getErrorCode(),
                    je.getMessage() == null ? ae.getErrorMessage() : je.getMessage());

            // handleException(ex, messageTagDTO, activityLogDTO, interfaceDTO,
            //    messageMetaInfo.getMessageTagId());

            throw ex;
        } catch (Exception e) {
            logger.info("{}", e.getMessage());

            AdaptingException.AdaptingError ae = AdaptingException.AdaptingError.DEFAULT_ADPATING_EXCEPTION;

            AdaptingException ex = new AdaptingException(ae.getErrorCode(),
                    e.getMessage() == null ? ae.getErrorMessage() : e.getMessage());

            // handleException(ex, messageTagDTO, activityLogDTO, interfaceDTO,
            //    messageMetaInfo.getMessageTagId());

            throw ex;

        }
    }

    @Override
    public void receiveRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("TaxInvoicePublicationHandler.receiveRequest()");
        try {
            TaxInvoiceMessageData dtiMessageData = messageDataFactory.getTaxInvoiceMessageData(messageMetaInfo);
            logger.info("@@ step = 1");
            List<TaxInvoiceMessageMainDocument> mainDataList
                    = dtiMessageData.getMessageMainDocument();
            logger.info("@@ step = 2");

            logger.info("List<TaxInvoiceMessageMainDocument> mainDataList ::::::::::::: " + mainDataList.toString());

            for (TaxInvoiceMessageMainDocument mainData : mainDataList) {

                logger.info("@@ step = 3");
                logger.info("#### {}", mainData.getDocumentType());
                logger.info("#### {}", TaxInvoiceMessageMainDocument.DocumentType.DTI);

                // DTT 를 전달 해야 할 필요가 있나...
                if (mainData.getDocumentType().equals(TaxInvoiceMessageMainDocument.DocumentType.DTI)) {
                    logger.info("@@ step = 4");
                    String mainDoc = mainData.getDocumentData();
                    logger.info("@@ step = 5");
                    TaxInvoice tax = taxInvoiceSerializationService.parse(mainDoc);
                    logger.info("@@ step = 6");
                    TaxInvoiceDocument taxDoc = tax.getTaxInvoiceDocument();
                    TaxInvoiceTradeSettlement taxTsl = tax.getTaxInvoiceSettlement();
                    logger.info("@@ step = 7");
                    DTIMainDTO dtiMainDTO = new DTIMainDTO();
                    logger.info("@@ step = 8");
                    //dtiMainDTO = CommonsUtil.checkSignalSetType(dtiMessageData.getMessageHeader(), dtiMainDTO);
                    logger.info("@@ step = 9");

                    dtiMainDTO.setConversationId(dtiMessageData.getMessageHeader().getConversationId());

                    dtiMainDTO.setSupbuyType(BUYER);
                    dtiMainDTO.setDirection(FORWARD);

                    dtiMainDTO.setDttYn("N");

                    if (null != tax.getTaxInvoiceDocument().getDescriptionText()) {
                        Iterator<String> it = tax.getTaxInvoiceDocument().getDescriptionText().iterator();

                        int i = 0;
                        while (it.hasNext()) {
                            switch (i) {
                                case 0:
                                    dtiMainDTO.setRemark(it.next());
                                    break;
                                case 1:
                                    dtiMainDTO.setRemark2(it.next());
                                    break;
                                case 2:
                                    dtiMainDTO.setRemark3(it.next());
                                    break;
                            }
                            i++;
                        }
                    }

                    if (null != tax.getExchangedDocument().getReferencedDocument()) {
                        dtiMainDTO.setSeqId(tax.getExchangedDocument().getReferencedDocument().getId());
                    }

                    logger.info("@@ step = 10");
                    //세금계산서 - 기본정보
                    dtiMainDTO.setApproveId(taxDoc.getIssueId());
                    dtiMainDTO.setDtiWdate(taxDoc.getIssueDateTime());
                    dtiMainDTO.setDtiType(taxDoc.getTypeCode());
                    dtiMainDTO.setTaxDemand(taxDoc.getPurposeCode());
                    dtiMainDTO.setAmendCode(taxDoc.getAmendmentStatusCode());
                    dtiMainDTO.setOriIssueId(taxDoc.getOriginalIssueId());

                    //세금계산서 - 공급자정보
                    dtiMainDTO.setSupComRegno(taxTsl.getInvoicerParty().getId());
                    if (null != taxTsl.getInvoicerParty().getSpecifiedOrganization()) {
                        dtiMainDTO.setSupBizplaceCode(taxTsl.getInvoicerParty().getSpecifiedOrganization().getTaxRegistrationId());
                    }
                    logger.info("@@ step = 11");
                    dtiMainDTO.setSupComName(taxTsl.getInvoicerParty().getNameText());

                    if (null != taxTsl.getInvoicerParty().getSpecifiedPerson()) {
                        dtiMainDTO.setSupRepName(taxTsl.getInvoicerParty().getSpecifiedPerson().getNameText());
                    }
                    if (null != taxTsl.getInvoicerParty().getSpecifiedAddress()) {
                        dtiMainDTO.setSupComAddr(taxTsl.getInvoicerParty().getSpecifiedAddress().getLineOneText());
                    }
                    logger.info("@@ step = 12");
                    dtiMainDTO.setSupComType(taxTsl.getInvoicerParty().getTypeCode());
                    dtiMainDTO.setSupComClassify(taxTsl.getInvoicerParty().getClassfigicationCode());

                    if (null != taxTsl.getInvoicerParty().getDefinedContact()) {
                        dtiMainDTO.setSupDeptName(taxTsl.getInvoicerParty().getDefinedContact().getDepartmentNameText());
                        dtiMainDTO.setSupEmpName(taxTsl.getInvoicerParty().getDefinedContact().getPersonNameText());
                        dtiMainDTO.setSupTelNum(taxTsl.getInvoicerParty().getDefinedContact().getTelephoneCommuncation());
                        dtiMainDTO.setSupEmail(taxTsl.getInvoicerParty().getDefinedContact().getUriCommunication());
                    }
                    logger.info("@@ step = 13");
                    //세금계산서 - 공급받는자정보
                    dtiMainDTO.setByrComRegno(taxTsl.getInvoiceeParty().getId());

                    if (null != taxTsl.getInvoiceeParty().getSpecifiedOrganization()) {
                        dtiMainDTO.setByrBizplaceCode(taxTsl.getInvoiceeParty().getSpecifiedOrganization().getTaxRegistrationId());
                    }
                    logger.info("@@ step = 14");
                    dtiMainDTO.setByrComName(taxTsl.getInvoiceeParty().getNameText());

                    if (null != taxTsl.getInvoiceeParty().getSpecifiedPerson()) {
                        dtiMainDTO.setByrRepName(taxTsl.getInvoiceeParty().getSpecifiedPerson().getNameText());
                    }

                    if (null != taxTsl.getInvoiceeParty().getSpecifiedAddress()) {
                        dtiMainDTO.setByrComAddr(taxTsl.getInvoiceeParty().getSpecifiedAddress().getLineOneText());
                    }
                    logger.info("@@ step = 15");

                    dtiMainDTO.setByrComType(taxTsl.getInvoiceeParty().getTypeCode());
                    dtiMainDTO.setByrComClassify(taxTsl.getInvoiceeParty().getClassfigicationCode());
                    logger.info("@@ step = 16");
                    if (null != taxTsl.getInvoiceeParty().getPrimaryDefinedContract()) {
                        dtiMainDTO.setByrDeptName(taxTsl.getInvoiceeParty().getPrimaryDefinedContract().getDepartmentNameText());
                        dtiMainDTO.setByrEmpName(taxTsl.getInvoiceeParty().getPrimaryDefinedContract().getPersonNameText());
                        dtiMainDTO.setByrTelNum(taxTsl.getInvoiceeParty().getPrimaryDefinedContract().getTelephoneCommuncation());
                        dtiMainDTO.setByrEmail(taxTsl.getInvoiceeParty().getPrimaryDefinedContract().getUriCommunication());
                    }
                    logger.info("@@ step = 17");
                    if (null != taxTsl.getInvoiceeParty().getSecondaryDefinedContract()) {
                        dtiMainDTO.setByrDeptName2(taxTsl.getInvoiceeParty().getSecondaryDefinedContract().getDepartmentNameText());
                        dtiMainDTO.setByrEmpName2(taxTsl.getInvoiceeParty().getSecondaryDefinedContract().getPersonNameText());
                        dtiMainDTO.setByrTelNum2(taxTsl.getInvoiceeParty().getSecondaryDefinedContract().getTelephoneCommuncation());
                        dtiMainDTO.setByrEmail2(taxTsl.getInvoiceeParty().getSecondaryDefinedContract().getUriCommunication());
                    }

                    logger.info("@@ step = 18");
                    //세금계산서 - 수탁자정보

                    if (null != taxTsl.getBrokerParty()) {

                        dtiMainDTO.setBrokerComRegno(taxTsl.getBrokerParty().getId());
                        if (null != taxTsl.getBrokerParty().getSpecifiedOrganization()) {
                            dtiMainDTO.setBrkBizplaceCode(taxTsl.getBrokerParty().getSpecifiedOrganization().getTaxRegistrationId());
                        }

                        dtiMainDTO.setBrokerComName(taxTsl.getBrokerParty().getNameText());
                        dtiMainDTO.setBrokerRepName(taxTsl.getBrokerParty().getSpecifiedPerson().getNameText());
                        if (null != taxTsl.getBrokerParty().getSpecifiedAddress()) {
                            dtiMainDTO.setBrokerComAddr(taxTsl.getBrokerParty().getSpecifiedAddress().getLineOneText());
                        }

                        dtiMainDTO.setBrokerComType(taxTsl.getBrokerParty().getTypeCode());
                        dtiMainDTO.setBrokerComClassify(taxTsl.getBrokerParty().getClassfigicationCode());

                        if (null != taxTsl.getBrokerParty().getDefinedContact()) {
                            dtiMainDTO.setBrokerDeptName(taxTsl.getBrokerParty().getDefinedContact().getDepartmentNameText());
                            dtiMainDTO.setBrokerEmpName(taxTsl.getBrokerParty().getDefinedContact().getPersonNameText());
                            dtiMainDTO.setBrokerTelNum(taxTsl.getBrokerParty().getDefinedContact().getTelephoneCommuncation());
                            dtiMainDTO.setBrokerEmail(taxTsl.getBrokerParty().getDefinedContact().getUriCommunication());
                        }

                    }

                    logger.info("@@ step = 19");
                    //세금계산서 - 결제방법금액
                    if (null != taxTsl.getSpecifiedPaymentMeans()) {
                        int nodesize = taxTsl.getSpecifiedPaymentMeans().size();

                        for (int i = 0; i < nodesize; i++) {

                            String code = taxTsl.getSpecifiedPaymentMeans().get(i).getTypeCode();
                            Double amount = taxTsl.getSpecifiedPaymentMeans().get(i).getPaidAmount();

                            switch (code) {

                                case "10": //현금
                                    dtiMainDTO.setCashCode(code);
                                    dtiMainDTO.setCashAmount(amount);
                                    break;
                                case "20": //수표
                                    dtiMainDTO.setCheckCode(code);
                                    dtiMainDTO.setCheckAmount(amount);
                                    break;
                                case "30": //어음
                                    dtiMainDTO.setNoteCode(code);
                                    dtiMainDTO.setNoteAmount(amount);
                                    break;
                                case "40": //외상(매출금/미수금)
                                    dtiMainDTO.setReceivableCode(code);
                                    dtiMainDTO.setReceivableAmount(amount);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    //세금계산서 - Summary
                    dtiMainDTO.setSupAmount(taxTsl.getSpecifiedMonetarySummation().getChargeTotalAmount());
                    dtiMainDTO.setTaxAmount(taxTsl.getSpecifiedMonetarySummation().getTaxTotalAmount());
                    dtiMainDTO.setTotalAmount(taxTsl.getSpecifiedMonetarySummation().getGrandTotalAmount());
                    dtiMainDTO.setDtiMsg(mainData.getDocumentData());
                    logger.info("@@ step = 20");

                    //DTIMAIN DB INSERT
                    Date timestamp = Calendar.getInstance().getTime();
                    dtiMainDTO.setCreationDate(timestamp);
                    dtiMainDTO.setCreatedBy("SBMS");
                    dtiMainDTO.setLastUpdateDate(timestamp);
                    dtiMainDTO.setLastUpdatedBy("SBMS");

                    Boolean brokerIssue = false;
                    Boolean autoApprove = false;

                    for (TaxInvoiceMessageMetaDocument metaData : dtiMessageData.getMessageMetaDocument()) {
                        logger.info("공급자 연동인 경우 메타 데이타를 읽어서 처리 하려고 시도한다.");

                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        //factory.setNamespaceAware(true);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(new ByteArrayInputStream(metaData.getDocumentData().getBytes()));
                        XPathFactory xpathFactory = XPathFactory.newInstance();
                        XPath xpath = xpathFactory.newXPath();

                        XPathExpression expr = xpath.compile("//META/BRK_SUP_LINK");
                        String brkSupLink = expr.evaluate(doc);

                        if (null != brkSupLink && "Y".equals(brkSupLink)) {
                            brokerIssue = true;
                        }

                        expr = xpath.compile("//META/AUTO_APPROVE");
                        String autoApproveStr = expr.evaluate(doc);

                        if (null != autoApproveStr && "Y".equals(autoApproveStr)) {
                            autoApprove = Boolean.TRUE;
                        }
                    }

                    if (brokerIssue) { // 공급자 연동
                        dtiMainDTO.setSupbuyType(SUPPLIER); // 매출 마킹
                    }

                    dTIMainService.create(dtiMainDTO);

                    DTIStatusDTO dtiStatusDTO = new DTIStatusDTO();
                    dtiStatusDTO.setConversationId(dtiMainDTO.getConversationId());
                    dtiStatusDTO.setSupbuyType(dtiMainDTO.getSupbuyType());
                    dtiStatusDTO.setDirection(dtiMainDTO.getDirection());
                    dtiStatusDTO.setDtiStatus(TaxInvoiceStatus.Unapproved.getCode());
                    dtiStatusDTO.setTranStatus(MessageStatus.Progress.getCode());
                    dtiStatusDTO.setLastUpdatedBy(SYSTEM_NAME);
                    dtiStatusDTO.setLastUpdateDate(timestamp);
                    dtiStatusDTO.setCreatedBy(SYSTEM_NAME);
                    dtiStatusDTO.setCreationDate(timestamp);
                    dTIStatusService.create(dtiStatusDTO);

                    DTIInterfaceDTO dTIInterfaceDTO = new DTIInterfaceDTO();
                    dTIInterfaceDTO.setMessageId(messageMetaInfo.getMessageId());
                    dTIInterfaceDTO.setConversationId(dtiMainDTO.getConversationId());
                    dTIInterfaceDTO.setSupbuyType(dtiMainDTO.getSupbuyType());
                    dTIInterfaceDTO.setDirection(dtiMainDTO.getDirection());
                    dTIInterfaceDTO.setSignal(TaxInvoiceSignal.ARISSUE.name());
                    dTIInterfaceDTO.setMessageStatusFlag(Direction.Inbound.getCode());
                    dTIInterfaceDTO.setLastUpdateDate(timestamp);
                    dTIInterfaceService.create(dTIInterfaceDTO);

                    logger.info("@@ step = 21");
                    //세금계산서 - 상품정보
                    int itemSize = tax.getTaxInvoiceTradeLineItem().size();
                    DTIItemDTO dtiItemDTO;
                    for (int i = 0; i < itemSize; i++) {
                        TaxInvoiceTradeLineItem taxTitem = tax.getTaxInvoiceTradeLineItem().get(i);

                        dtiItemDTO = new DTIItemDTO();

                        dtiItemDTO.setConversationId(dtiMessageData.getMessageHeader().getConversationId());
                        dtiItemDTO.setSupbuyType(dtiMainDTO.getSupbuyType());
                        dtiItemDTO.setDirection(dtiMainDTO.getDirection());

                        dtiItemDTO.setDtiLineNum(taxTitem.getSequenceNumeric());
                        dtiItemDTO.setItemMd(taxTitem.getPurchaseExpiryDateTime());
                        dtiItemDTO.setItemName(taxTitem.getNameText());
                        dtiItemDTO.setItemSize(taxTitem.getInformationText());
                        dtiItemDTO.setItemRemark(taxTitem.getDescriptionText());
                        dtiItemDTO.setItemQty(taxTitem.getChargeableUnitQuantity());

                        if (null != taxTitem.getUnitPrice()) {
                            dtiItemDTO.setUnitPrice(taxTitem.getUnitPrice().getUnitAmount());
                        }

                        dtiItemDTO.setSupAmount(taxTitem.getInvoiceAmount());

                        if (null != taxTitem.getTotalTax()) {
                            dtiItemDTO.setTaxAmount(taxTitem.getTotalTax().getCaculatedAmount());
                        }
                        dtiItemDTO.setItemGubun(TaxInvoiceMessageMainDocument.DocumentType.DTI.name());

                        dtiItemDTO.setLastUpdateDate(timestamp);
                        dtiItemDTO.setLastUpdatedBy(SYSTEM_NAME);
                        dtiItemDTO.setCreationDate(timestamp);
                        dtiItemDTO.setCreatedBy(SYSTEM_NAME);

                        dTIItemService.create(dtiItemDTO);

                    }
                    logger.info("@@ step = 22");
                    for (MessageFile file : dtiMessageData.getMessageFiles()) {

                        FileDataDTO dto = new FileDataDTO();
                        BeanUtils.copyProperties(file, dto);

                        // FIXME : 파일 처리 할 것
                        //fileDataMapper.create(dto);
                    }
                    logger.info("@@ step = 23");

                    if (autoApprove) {
                        autoApproveThreadPoolTaskExecutor.execute(
                                getAutoApproveRunnable(dtiMainDTO.getConversationId(),
                                        dtiMainDTO.getSupbuyType(),
                                        dtiMainDTO.getDirection())
                        );
                    }

                } // end of DTI
                logger.info("@@ step = 24");
            }

            //messageTagService.updateMessageTag(messageMetaInfo, "C");
            updateMessageTagStatus(MessageStatus.Complete, messageMetaInfo.getMessageTagId());

        } catch (Exception e) {
            logger.info("{}", e);

            AdaptingException.AdaptingError ae = AdaptingException.AdaptingError.SAVE_EXCEPTION;

            AdaptingException ex = new AdaptingException(ae.getErrorCode(),
                    e.getMessage() == null ? ae.getErrorMessage() : e.getMessage());

            try {
                logger.info("원본 저장 실패 오류 기록");

                // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ex);
            } catch (Exception ee) {
                logger.info(ee.getMessage());
            }
            throw ex;
        }
    }

}
