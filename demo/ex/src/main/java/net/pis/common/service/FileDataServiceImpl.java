/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.dao.dti.FileDataMapper;
import net.pis.dto.FileDataDTO;
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
public class FileDataServiceImpl implements FileDataService {

    @Autowired
    private FileDataMapper fileDataMapper;

    public int create(FileDataDTO t) {
        return this.fileDataMapper.create(t);
    }

    @Override
    public List<FileDataDTO> read(FileDataDTO t) {
        return this.fileDataMapper.read(t);
    }

    @Override
    public int update(FileDataDTO t) {
        return this.fileDataMapper.update(t);
    }

    @Override
    public int delete(FileDataDTO t) {
        return this.fileDataMapper.delete(t);
    }

    @Override
    public List<FileDataDTO> getFileDatas(String messageTagId) {

        List<FileDataDTO> fileDataDTOList = null;

        FileDataDTO fileDataDTOParam = new FileDataDTO();
        fileDataDTOParam.setMessageTagId(messageTagId);

        fileDataDTOList = this.read(fileDataDTOParam);

        return fileDataDTOList;
    }

    @Override
    public FileDataDTO getFileData(String messageTagId) {

        List<FileDataDTO> fileDataDTOList = this.getFileDatas(messageTagId);

        if (!fileDataDTOList.isEmpty()) {
            return fileDataDTOList.get(0);
        } else {
            return null;
        }
    }

}
