/*
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.dao.dti.DeliveryDtiFileMapper;
import net.pis.dto.table.DeliveryDtiFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Service
//@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class DTIFileServiceImpl implements DTIFileService {

    @Autowired
    private DeliveryDtiFileMapper deliveryDtiFileMapper;

    @Override
    public int create(DeliveryDtiFileDTO t) {
        return this.deliveryDtiFileMapper.create(t);
    }

    @Override
    public List<DeliveryDtiFileDTO> read(DeliveryDtiFileDTO t) {
        return this.deliveryDtiFileMapper.read(t);
    }

    @Override
    public int update(DeliveryDtiFileDTO t) {
        return this.deliveryDtiFileMapper.update(t);
    }

    @Override
    public int delete(DeliveryDtiFileDTO t) {
        return this.deliveryDtiFileMapper.delete(t);
    }

    @Override
    public List<DeliveryDtiFileDTO> getFileDatas(String conversationId) {

        List<DeliveryDtiFileDTO> fileDataDTOList = null;

        DeliveryDtiFileDTO fileDataDTOParam = new DeliveryDtiFileDTO();
        fileDataDTOParam.setConversationId(conversationId);

        fileDataDTOList = this.read(fileDataDTOParam);

        return fileDataDTOList;
    }

    @Override
    public DeliveryDtiFileDTO getFileData(String messageTagId) {

        List<DeliveryDtiFileDTO> fileDataDTOList = this.getFileDatas(messageTagId);

        if (!fileDataDTOList.isEmpty()) {
            return fileDataDTOList.get(0);
        } else {
            return null;
        }
    }

}
