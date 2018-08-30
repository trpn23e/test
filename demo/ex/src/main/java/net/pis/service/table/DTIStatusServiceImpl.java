/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.dti.DTIStatusMapper;
import net.pis.dto.table.DTIStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Service
//@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class DTIStatusServiceImpl implements DTIStatusService {

    @Autowired
    private DTIStatusMapper mapper;

    @Override
    public int create(DTIStatusDTO t) {
        return mapper.create(t);
    }

    @Override
    public List<DTIStatusDTO> read(DTIStatusDTO t) {
        return mapper.read(t);
    }

    @Override
    public int update(DTIStatusDTO t) {
        return mapper.update(t);
    }

    @Override
    public int delete(DTIStatusDTO t) {

        return mapper.delete(t);
    }

    @Override
    public List<DTIStatusDTO> readNtsTargets() {
        return mapper.readNtsTargets();

    }

}
