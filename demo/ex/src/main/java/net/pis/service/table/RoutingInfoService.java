/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.DefaultTableService;
import net.pis.dto.RoutingInfoDTO;
import net.pis.message.MessageMetaInfo;

/**
 *
 * @author jh,Seo
 */
public interface RoutingInfoService extends DefaultTableService<RoutingInfoDTO> {

    RoutingInfoDTO getRoutingInfo(MessageMetaInfo messageMetainfo);
}
