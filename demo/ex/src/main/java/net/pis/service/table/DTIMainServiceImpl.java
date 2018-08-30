/**
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.dti.DTIMainMapper;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIMainDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by achiz on 2014-12-24.
 */
@Service
//@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class DTIMainServiceImpl implements DTIMainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DTIMainMapper dtiMainMapper;

    public List<DTIMainDTO> read(DTIMainDTO t) {
        return dtiMainMapper.read(t);
    }

    public int create(DTIMainDTO t) {
        return dtiMainMapper.create(t);
    }

    public int update(DTIMainDTO t) {
        return dtiMainMapper.update(t);
    }

    public int delete(DTIMainDTO t) {
        return dtiMainMapper.delete(t);
    }

    public DTIMainDTO getDtiMain(DTIInterfaceDTO dtiInterfaceDTO) {

        return this.getDTIMainDTO(dtiInterfaceDTO.getConversationId(),
                dtiInterfaceDTO.getSupbuyType(),
                dtiInterfaceDTO.getDirection());

    }

    @Override
    public DTIMainDTO getDTIMainDTO(String conversationId, String subBuyType, String direction) {

        DTIMainDTO dtiMainDTO = null;

        DTIMainDTO paramDTO = new DTIMainDTO();
        paramDTO.setConversationId(conversationId);
        paramDTO.setSupbuyType(subBuyType);
        paramDTO.setDirection(direction);

        List<DTIMainDTO> dtiMainDTOList = this.read(paramDTO);

        if (!dtiMainDTOList.isEmpty()) {
            dtiMainDTO = dtiMainDTOList.get(0);
        }

        return dtiMainDTO;

    }


}
