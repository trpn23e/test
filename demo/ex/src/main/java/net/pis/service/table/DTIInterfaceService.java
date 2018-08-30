/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.DefaultTableService;
import net.pis.dao.dti.DTIInterfaceMapper;
import net.pis.dto.table.DTIInterfaceDTO;
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
public class DTIInterfaceService implements DefaultTableService<DTIInterfaceDTO> {

    @Autowired
    private DTIInterfaceMapper mapper;

    @Override
    public int create(DTIInterfaceDTO t) {
        return mapper.create(t);
    }

    @Override
    public List<DTIInterfaceDTO> read(DTIInterfaceDTO t) {
        return mapper.read(t);
    }

    public List<DTIInterfaceDTO> read(DTIInterfaceDTO t, int limit) {
        return mapper.read(t, limit);
    }

    @Override
    public int update(DTIInterfaceDTO t) {
        return mapper.update(t);
    }

    @Override
    public int delete(DTIInterfaceDTO t) {
        return mapper.delete(t);
    }

}
