/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.dao.DefaultTableService;
import net.pis.dto.FileDataDTO;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
public interface FileDataService extends DefaultTableService<FileDataDTO> {

    List<FileDataDTO> getFileDatas(String messageTagId);

    FileDataDTO getFileData(String messageTagId);

}
