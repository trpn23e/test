/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.DefaultTableService;
import net.pis.dao.dti.DTIApproveIdMapper;
import net.pis.dto.table.DTIApproveIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * (TABLE)XXSB_DTI_INTERFACE Default Table Service
 *
 * @author jh,Seo
 */
@Service
//@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class DTIApproveIdService implements DefaultTableService<DTIApproveIdDTO> {

    @Autowired
    private DTIApproveIdMapper mapper;

    @Override
    public int create(DTIApproveIdDTO t) {
        return mapper.create(t);
    }

    @Override
    public List<DTIApproveIdDTO> read(DTIApproveIdDTO t) {
        return mapper.read(t);
    }

    @Override
    public int update(DTIApproveIdDTO t) {
        return mapper.update(t);
    }

    @Override
    public int delete(DTIApproveIdDTO t) {
        return mapper.delete(t);
    }

}
