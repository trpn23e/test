/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.common.TaxInvoiceException;
import net.pis.dto.TaxInvoice;
import net.pis.message.MessageMetaInfo;

/**
 * 전자 세금 계산서 (XML - TaxInvoice object) 직렬화 서비스
 *
 * @author jh,Seo
 */
public interface TaxInvoiceSerializationService {

    /**
     * 전자 세금계산서 XML을 생성한다.
     *
     * @param messageMetaInfo
     * @return
     */
    String createETaxDocument(MessageMetaInfo messageMetaInfo) throws TaxInvoiceException;

    /**
     * 거래명세서 XML을 생성한다.
     *
     * @param messageMetaInfo
     * @return
     * @throws TaxInvoiceException
     */
    String createSpecificationOnTransactionDocument(MessageMetaInfo messageMetaInfo) throws TaxInvoiceException;

    /**
     * 전자 세금계산서 원본에서 TaxInvoice 인스턴스를 생성한다.
     *
     * @param xml
     * @return
     */
    TaxInvoice parse(String xml) throws TaxInvoiceException;

}
