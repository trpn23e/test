/*
 * (c)BOC
 */
package net.pis.common.service;


import net.pis.dao.DefaultTableService;
import net.pis.dto.MetaDocumentDataDTO;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
public interface MetaDocumentDataService extends DefaultTableService<MetaDocumentDataDTO> {

    MetaDocumentDataDTO getMetaDocumentData(String messageTagId);

    List<MetaDocumentDataDTO> getMetaDocumentDataList(String messageTagId);

}
