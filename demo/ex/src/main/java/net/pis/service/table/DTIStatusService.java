/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.DefaultTableService;
import net.pis.dto.table.DTIStatusDTO;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
public interface DTIStatusService extends DefaultTableService<DTIStatusDTO> {

    List<DTIStatusDTO> readNtsTargets();

}
