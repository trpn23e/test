/*
 * (c)BOC
 */
package net.pis.common.service;


import net.pis.dao.DefaultTableService;
import net.pis.dto.DocumentDataDTO;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
public interface DocumentDataService extends DefaultTableService<DocumentDataDTO> {

    DocumentDataDTO getDocumentData(String messageTagId);

    List<DocumentDataDTO> getDocumentDataList(String messageTagId);

}
