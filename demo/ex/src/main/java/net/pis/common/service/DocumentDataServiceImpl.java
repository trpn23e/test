/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.dao.dti.DocumentDataMapper;
import net.pis.dto.DocumentDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Service
// @Transactional(transactionManager = "transactionManagerSBMS")
@Transactional(transactionManager = "db2TransactionManager")
public class DocumentDataServiceImpl implements DocumentDataService {

    @Autowired
    private DocumentDataMapper documentDataMapper;

    @Override
    public DocumentDataDTO getDocumentData(String messageTagId) {
        DocumentDataDTO documentDataDTOResult = null;
        DocumentDataDTO documentDataDTOParam = new DocumentDataDTO();
        documentDataDTOParam.setMessageTagId(messageTagId);
        List<DocumentDataDTO> documentDataDTOList = this.read(documentDataDTOParam);

        if (!documentDataDTOList.isEmpty()) {

            documentDataDTOResult = documentDataDTOList.get(0);
        }

        return documentDataDTOResult;

    }

    @Override
    public List<DocumentDataDTO> getDocumentDataList(String messageTagId) {
        DocumentDataDTO documentDataDTOParam = new DocumentDataDTO();
        documentDataDTOParam.setMessageTagId(messageTagId);
        List<DocumentDataDTO> documentDataDTOList = this.read(documentDataDTOParam);

        return documentDataDTOList;

    }

    @Override
    public int create(DocumentDataDTO t) {
        return this.documentDataMapper.create(t);
    }

    @Override
    public List<DocumentDataDTO> read(DocumentDataDTO t) {

        return this.documentDataMapper.read(t);

    }

    @Override
    public int update(DocumentDataDTO t) {
        return this.documentDataMapper.update(t);
    }

    @Override
    public int delete(DocumentDataDTO t) {
        return this.documentDataMapper.delete(t);
    }

}
