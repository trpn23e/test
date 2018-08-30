/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.common.MessageType;
import net.pis.common.TaxInvoiceException;
import net.pis.common.TaxInvoiceSignal;
import net.pis.dao.dti.DTIMainMapper;
import net.pis.dto.DocumentDataDTO;
import net.pis.dto.FileDataDTO;
import net.pis.dto.MessageTagDTO;
import net.pis.dto.MetaDocumentDataDTO;
import net.pis.dto.message.*;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIMainDTO;
import net.pis.dto.table.DeliveryDtiFileDTO;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.table.MessageService;
import net.pis.service.table.DTIInterfaceService;
import net.pis.service.table.MessageTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author jh,Seo
 */
@Service
public class TaxInvoiceMessageDataFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageTagService messageTagService;

    @Autowired
    private MessageService messageService;
    @Autowired
    private DocumentDataService documentDataService;

    @Autowired
    private MetaDocumentDataService metaDocumentDataService;

    @Autowired
    private FileDataService fileDataService;

    @Autowired
    private DTIFileService dtiFileService;

    @Autowired
    private DTIInterfaceService dtiInterfaceService;

    @Autowired
    private DTIMainMapper dTIMainMapper;

    @Autowired
    private TaxInvoiceSerializationService eTaxService;

    //@Autowired
    //private Oriman oriman;

    /**
     * 스마트빌에서 전송된 데이타로부터 전자세금 계산서 데이타를 추출한다.
     *
     * @param messageMetaInfo
     * @return
     */
    public TaxInvoiceMessageData getTaxInvoiceMessageData(final MessageMetaInfo messageMetaInfo) {

        String messageTagId = messageMetaInfo.getMessageTagId();

        MessageTagDTO messageTagParam = new MessageTagDTO();
        messageTagParam.setMessageTagId(messageTagId);
        MessageTagDTO messageTagDTO = messageTagService.getMessageTagDTO(messageTagId);

        final MessageHeader messageHeader = new MessageHeader();
        messageHeader.setMessageId(messageTagDTO.getMessageId());
        messageHeader.setSignal(messageTagDTO.getSignal());
        messageHeader.setReqTime(messageTagDTO.getCurrTimestamp());
        messageHeader.setSndComregno(messageTagDTO.getSendRegNo());
        messageHeader.setRcvComregno(messageTagDTO.getReceiveRegNo());
        messageHeader.setAuthTicket(null);
        messageHeader.setServiceCode(messageTagDTO.getServiceId());

        // 06.08.23 SAP or NONSAP 이지선다에서 'SMTP'가 추가 되었다.
        messageHeader.setSystemType(messageTagDTO.getToSystem());
        messageHeader.setConversationId(messageTagDTO.getConversationId());

        final List<TaxInvoiceMessageMainDocument> messageMainDocumentList = new ArrayList<>();
        List<DocumentDataDTO> documentDataDTOList = documentDataService.getDocumentDataList(messageTagId);

        if (null != documentDataDTOList) {

            for (DocumentDataDTO documentDataDTO : documentDataDTOList) {
                TaxInvoiceMessageMainDocument messageMainDocument = new TaxInvoiceMessageMainDocument();

                messageMainDocument.setDocumentDataId(UUID.randomUUID().toString());
                messageMainDocument.setMessageTagId(messageTagDTO.getMessageTagId());
                messageMainDocument.setSeq(documentDataDTO.getSeq());
                messageMainDocument.setDocumentType(
                    TaxInvoiceMessageMainDocument.DocumentType.valueOf(documentDataDTO.getDocumentType()));
                messageMainDocument.setDocumentData(documentDataDTO.getDocumentData());
                messageMainDocument.setRegTimestamp(documentDataDTO.getRegTimestamp());

                messageMainDocumentList.add(messageMainDocument);
            }

        }

        final List<TaxInvoiceMessageMetaDocument> messageMetaDocumentList = new ArrayList();
        List<MetaDocumentDataDTO> metaDocumentDataDTOList = metaDocumentDataService.getMetaDocumentDataList(messageTagId);

        if (null != metaDocumentDataDTOList) {

            for (MetaDocumentDataDTO metaDocumentDataDTO : metaDocumentDataDTOList) {
                TaxInvoiceMessageMetaDocument messageMetaDocument = new TaxInvoiceMessageMetaDocument();
                messageMetaDocument.setDocumentDataId(UUID.randomUUID().toString());
                messageMetaDocument.setMessageTagId(metaDocumentDataDTO.getMessageTagId());
                messageMetaDocument.setSeq(metaDocumentDataDTO.getSeq());

                switch (TaxInvoiceSignal.valueOf(messageTagDTO.getSignal()).toMessageType()) {
                    case REQUEST:
                    case RESULT: {
                        messageMetaDocument.setDocumentType(TaxInvoiceMessageMetaDocument.DocumentType.Interface);
                        break;
                    }
                    case STATUS: {
                        messageMetaDocument.setDocumentType(TaxInvoiceMessageMetaDocument.DocumentType.Status);
                        break;
                    }

                    case UTIL: {
                        messageMetaDocument.setDocumentType(TaxInvoiceMessageMetaDocument.DocumentType.Util);
                        break;
                    }

                }

                messageMetaDocument.setDocumentData(metaDocumentDataDTO.getDocumentData());
                messageMetaDocument.setRegTimestamp(metaDocumentDataDTO.getRegTimestamp());

                messageMetaDocumentList.add(messageMetaDocument);
            }

        }
        final List<MessageFile> messageFileList = new ArrayList<>();
        List<FileDataDTO> fileDataDTOList = fileDataService.getFileDatas(messageTagId);
        for (FileDataDTO fileDataDTO : fileDataDTOList) {
            MessageFile messageFile = new MessageFile();

            messageFile.setFileData(fileDataDTO.getFileData());
            messageFile.setFileGubun(fileDataDTO.getFileGubun());
            messageFile.setFileName(fileDataDTO.getFileName());
            messageFile.setFileSeq(fileDataDTO.getFileSeq());
            messageFile.setFileSize(fileDataDTO.getFileSize());

            messageFileList.add(messageFile);
        }

        TaxInvoiceMessageData messageData = new TaxInvoiceMessageData() {
            @Override
            public List<TaxInvoiceMessageMetaDocument> getMessageMetaDocument() {
                return messageMetaDocumentList;
            }

            @Override
            public List<TaxInvoiceMessageMainDocument> getMessageMainDocument() {
                return messageMainDocumentList;
            }

            @Override
            public MessageHeader getMessageHeader() {
                return messageHeader;
            }

            @Override
            public List<MessageFile> getMessageFiles() {
                return messageFileList;
            }

            @Override
            public MessageMetaInfo toMessageMetaInfo() {
                return messageMetaInfo;
            }
        };
        return messageData;

    }

    private MessageHeader createTaxInvoiceMessageHeader(MessageMetaInfo messageMetaInfo) {
        MessageHeader header = new MessageHeader();

        DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
        paramDTO.setMessageId(messageMetaInfo.getMessageId());

        List<DTIInterfaceDTO> interfaceList = dtiInterfaceService.read(paramDTO);

        logger.info("=== TaxInvoiceMessageDataFactory createTaxInvoiceMessageHeader ====");

        if (!interfaceList.isEmpty()) {

            DTIInterfaceDTO interfaceDTO = interfaceList.get(0);

            DTIMainDTO mainParamDTO = new DTIMainDTO();
            mainParamDTO.setConversationId(interfaceDTO.getConversationId());
            mainParamDTO.setSupbuyType(interfaceDTO.getSupbuyType());
            mainParamDTO.setDirection(interfaceDTO.getDirection());

            List<DTIMainDTO> mainList = dTIMainMapper.read(mainParamDTO);
            logger.info("dTIMainMapper.read mainList : " + mainList.size());
            DTIMainDTO mainDTO = mainList.get(0);
            header.setMessageId(messageMetaInfo.getMessageId());
            header.setSignal(interfaceDTO.getSignal());
            header.setReqTime(new Date());

            header.setSndComregno(mainDTO.getSupComRegno());
            header.setRcvComregno(mainDTO.getByrComRegno());
            if (null != mainDTO.getBrokerComRegno()) {
                if (!"".equals(mainDTO.getBrokerComRegno())) {

                    logger.trace("위수탁 CASE 센더를 수탁자로 변경한다.");
                    header.setSndComregno(mainDTO.getBrokerComRegno());
                }
            }

            header.setAuthTicket(null);
            header.setServiceCode("DTI");

            header.setSystemType("NSAP");

            header.setConversationId(interfaceDTO.getConversationId());
        }

        return header;
    }

    private List<TaxInvoiceMessageMainDocument> createTaxInvoiceMessageMainDocument(MessageMetaInfo messageMetaInfo) throws TaxInvoiceException {
        int docSeq = 1;

        /* Util Type 의 Signal은 MainDocument를 생성하지 않는다. */
        if (MessageType.UTIL.equals(
            messageService.getSignal(messageMetaInfo).toMessageType())) {
            return null;
        }

        List<TaxInvoiceMessageMainDocument> messageMainDocumentList = new ArrayList<>();

        TaxInvoiceMessageMainDocument messageMainDocument = new TaxInvoiceMessageMainDocument();

        //DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
        //paramDTO.setMessageId(messageMetaInfo.getMessageId());
        logger.info("세금 계산서 원본 생성");

        String mainDocument = eTaxService.createETaxDocument(messageMetaInfo);
        messageMainDocument.setDocumentDataId(UUID.randomUUID().toString());
        messageMainDocument.setSeq(docSeq++);
        messageMainDocument.setDocumentType(TaxInvoiceMessageMainDocument.DocumentType.DTI);
        messageMainDocument.setDocumentData(mainDocument);
        messageMainDocument.setMessageTagId(messageMetaInfo.getMessageTagId());
        messageMainDocument.setRegTimestamp(new Date());
        messageMainDocumentList.add(messageMainDocument);

        switch ((TaxInvoiceSignal) messageService.getSignal(messageMetaInfo)) {

            case DETAILARISSUE:  //거래명세서발행
            case WDETAILREQUEST: //거래명세서발행예정요청
            case RDETAILREQUEST: // 거래명세서 역발행 요청
            case RDETAILISSUE: // 거래명세서 역발행
            {
                logger.info("거래명세서 원본 생성");
                TaxInvoiceMessageMainDocument messageDetailDocument = new TaxInvoiceMessageMainDocument();
                String detailDocument = eTaxService.createSpecificationOnTransactionDocument(messageMetaInfo);
                messageDetailDocument.setDocumentDataId(UUID.randomUUID().toString());
                messageDetailDocument.setDocumentType(TaxInvoiceMessageMainDocument.DocumentType.DTT);
                messageDetailDocument.setDocumentData(detailDocument);
                messageDetailDocument.setMessageTagId(messageMetaInfo.getMessageTagId());
                messageDetailDocument.setRegTimestamp(new Date());
                messageMainDocumentList.add(messageDetailDocument);
                break;
            }
            default:
                break;
        }
        return messageMainDocumentList;
    }

    private List<TaxInvoiceMessageMetaDocument> createTaxInvoiceMessageMetaDocument(MessageMetaInfo messageMetaInfo, String signal) {
        ArrayList<TaxInvoiceMessageMetaDocument> messageMetaDocumentList = new ArrayList<>();
        int docSeq = 1;

        TaxInvoiceMessageMetaDocument messageMetaDocument = new TaxInvoiceMessageMetaDocument();
        messageMetaDocument.setDocumentDataId(UUID.randomUUID().toString());
        messageMetaDocument.setMessageTagId(messageMetaInfo.getMessageTagId());
        messageMetaDocument.setSeq(docSeq++);

        messageMetaDocument.setDocumentType(TaxInvoiceMessageMetaDocument.DocumentType.Interface);

        DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
        paramDTO.setMessageId(messageMetaInfo.getMessageId());

        List<DTIInterfaceDTO> interfaceList = dtiInterfaceService.read(paramDTO);

        DTIInterfaceDTO interfaceDTO = interfaceList.get(0);

        logger.trace("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.trace("$ {} $", new StringBuilder().append(interfaceDTO.getMetaString()).toString());
        logger.trace("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        if (null != interfaceDTO.getMetaString()) {
            messageMetaDocument.setDocumentData(
                new StringBuilder().append(interfaceDTO.getMetaString()).toString()
            );

            messageMetaDocumentList.add(messageMetaDocument);
        }

        return messageMetaDocumentList;
    }

    private List<MessageFile> createTaxInvoiceMessageFiles(MessageMetaInfo messageMetaInfo) {

        logger.trace("FileData Create !!!!@#!@#!@#!#@!!@#!@#!@#!@#!@#! ");

        List<MessageFile> messageFileList = new ArrayList<>();

        if ("NONSAP".equals(messageMetaInfo.getErpSystem())) {
            DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
            paramDTO.setMessageId(messageMetaInfo.getMessageId());

            List<DTIInterfaceDTO> interfaceList = dtiInterfaceService.read(paramDTO);

            DTIInterfaceDTO interfaceDTO = interfaceList.get(0);

            List<DeliveryDtiFileDTO> deliveryDtiFileDTOs = dtiFileService.getFileDatas(interfaceDTO.getConversationId());

            for (DeliveryDtiFileDTO deliveryDtiFileDTO : deliveryDtiFileDTOs) {
                MessageFile messageFile = new MessageFile();

                messageFile.setFileData(deliveryDtiFileDTO.getFileBinary());
                messageFile.setFileGubun(deliveryDtiFileDTO.getEventId());
                messageFile.setFileName(deliveryDtiFileDTO.getFileName());
                messageFile.setFileSeq(deliveryDtiFileDTO.getFileSeq());
                messageFile.setFileSize(deliveryDtiFileDTO.getFileSize());

                messageFileList.add(messageFile);
            }
        } else {
            // TODO : 채워야지....
        }

        return messageFileList;
    }


    /**
     * 전송 문서 생성 (NON-SAP 전용)
     *
     * @param messageMetaInfo
     * @return
     * @throws SBMSException
     */
    private TaxInvoiceMessageData createTaxInvoiceMessageDataForNonSAP(final MessageMetaInfo messageMetaInfo) throws SBMSException {

        TaxInvoiceMessageData messageData = null;

        try {

            final MessageHeader messageHeader
                = this.createTaxInvoiceMessageHeader(messageMetaInfo);
            final List<TaxInvoiceMessageMainDocument> messageMainDocument
                = this.createTaxInvoiceMessageMainDocument(messageMetaInfo);

            final List<TaxInvoiceMessageMetaDocument> messageMetaDocument
                = this.createTaxInvoiceMessageMetaDocument(messageMetaInfo, messageHeader.getSignal());

            final List<MessageFile> messageFileList
                = this.createTaxInvoiceMessageFiles(messageMetaInfo);

            messageData = new TaxInvoiceMessageData() {
                @Override
                public List<TaxInvoiceMessageMetaDocument> getMessageMetaDocument() {
                    return messageMetaDocument;
                }

                @Override
                public List<TaxInvoiceMessageMainDocument> getMessageMainDocument() {
                    return messageMainDocument;
                }

                @Override
                public MessageHeader getMessageHeader() {
                    return messageHeader;
                }

                @Override
                public List<MessageFile> getMessageFiles() {
                    return messageFileList;
                }

                @Override
                public MessageMetaInfo toMessageMetaInfo() {
                    return messageMetaInfo;
                }
            };

        } catch (TaxInvoiceException tie) {
            logger.error(tie.getMessage());

            throw new SBMSException(tie.getCodeId(), tie.getMessage());
        }
        return messageData;

    }

    /**
     * 원천 데이타로부터 전자세금 계산서 데이타를 생성한다.
     * <p>
     * 원천 데이타는 SAP의 경우 SBMS_REQUEST_SHELTER 테이블에서, NON-SAP의 경우
     * XXSB_DTI_INTERFACE 테이블에서 읽어들인다.
     *
     * </p>
     *
     * @param messageMetaInfo
     * @return
     * @throws SBMSException
     */
    public TaxInvoiceMessageData createTaxInvoiceMessageData(MessageMetaInfo messageMetaInfo)
        throws SBMSException {

        TaxInvoiceMessageData messageData = null;

        if ("SAP".equals(messageMetaInfo.getErpSystem())) {
            logger.info("==== TaxinvoiceMessageDataFactory.createTaxInvoiceMessageData() - SAP은 넘어감 ====");
            // messageData = createTaxInvoiceMessageDataForSAP(messageMetaInfo);
        } else if ("NONSAP".equals(messageMetaInfo.getErpSystem())) {
            messageData = createTaxInvoiceMessageDataForNonSAP(messageMetaInfo);
        }

        return messageData;

    }
}
