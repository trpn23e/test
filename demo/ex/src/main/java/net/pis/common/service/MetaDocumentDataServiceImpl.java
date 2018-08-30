/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.dao.dti.MetaDocumentDataMapper;
import net.pis.dto.MetaDocumentDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Service
//@Transactional(transactionManager = "transactionManagerSBMS")
@Transactional(transactionManager = "db2TransactionManager")
public class MetaDocumentDataServiceImpl implements MetaDocumentDataService {

    @Autowired
    private MetaDocumentDataMapper metaDocumentDataMapper;

    @Override
    public MetaDocumentDataDTO getMetaDocumentData(String messageTagId) {
        MetaDocumentDataDTO metaDocumentDataDTOResult = null;

        MetaDocumentDataDTO metaDocumentDataDTOParam = new MetaDocumentDataDTO();
        metaDocumentDataDTOParam.setMessageTagId(messageTagId);
        List<MetaDocumentDataDTO> metaDocumentDataDTOList = this.read(metaDocumentDataDTOParam);
        if (!metaDocumentDataDTOList.isEmpty()) {
            metaDocumentDataDTOResult = metaDocumentDataDTOList.get(0);
        }

        return metaDocumentDataDTOResult;

    }

    @Override
    public List<MetaDocumentDataDTO> getMetaDocumentDataList(String messageTagId) {
        MetaDocumentDataDTO metaDocumentDataDTOParam = new MetaDocumentDataDTO();
        metaDocumentDataDTOParam.setMessageTagId(messageTagId);
        List<MetaDocumentDataDTO> metaDocumentDataDTOList = this.read(metaDocumentDataDTOParam);

        return metaDocumentDataDTOList;

    }

    @Override
    public int create(MetaDocumentDataDTO t) {
        return this.metaDocumentDataMapper.create(t);
    }

    @Override
    public List<MetaDocumentDataDTO> read(MetaDocumentDataDTO t) {
        return this.metaDocumentDataMapper.read(t);
    }

    @Override
    public int update(MetaDocumentDataDTO t) {
        return this.metaDocumentDataMapper.update(t);
    }

    @Override
    public int delete(MetaDocumentDataDTO t) {
        return this.metaDocumentDataMapper.delete(t);
    }

}
