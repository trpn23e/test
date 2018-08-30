/**
 * (c)BOC
 */
package net.pis.common.service;

import net.pis.dao.DefaultTableService;
import net.pis.dto.table.DeliveryDtiFileDTO;

import java.util.List;
/**
 * Created by achiz on 2014-12-24.
 */
public interface DTIFileService extends DefaultTableService<DeliveryDtiFileDTO> {

    List<DeliveryDtiFileDTO> getFileDatas(String messageTagId);

    DeliveryDtiFileDTO getFileData(String messageTagId);

}
