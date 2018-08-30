/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.dti.RoutingInfoMapper;
import net.pis.dto.RoutingInfoDTO;
import net.pis.message.MessageMetaInfo;
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
public class RoutingInfoServiceImpl implements RoutingInfoService {

    @Autowired
    private RoutingInfoMapper routingInfoMapper;

    @Override
    public int create(RoutingInfoDTO t) {
        return this.routingInfoMapper.create(t);
    }

    @Override
    public List<RoutingInfoDTO> read(RoutingInfoDTO t) {
        return this.routingInfoMapper.read(t);
    }

    @Override
    public int update(RoutingInfoDTO t) {
        return this.routingInfoMapper.update(t);
    }

    @Override
    public int delete(RoutingInfoDTO t) {
        return this.routingInfoMapper.delete(t);
    }

    @Override
    @Deprecated
    public RoutingInfoDTO getRoutingInfo(MessageMetaInfo messageMetainfo) {
        RoutingInfoDTO routingInfoDTOResult = null;

        RoutingInfoDTO routingInfoDTOParam = new RoutingInfoDTO();
        routingInfoDTOParam.setSystemId(messageMetainfo.getTargetSystemId());

        List<RoutingInfoDTO> routingInfoDTOList = this.read(routingInfoDTOParam);
        if (!routingInfoDTOList.isEmpty()) {
            routingInfoDTOResult = routingInfoDTOList.get(0);
        }
        return routingInfoDTOResult;
    }
}
