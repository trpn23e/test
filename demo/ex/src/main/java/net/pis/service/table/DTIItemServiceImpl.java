/**
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.dti.DTIItemMapper;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by achiz on 2014-12-24.
 */
@Service
//@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class DTIItemServiceImpl implements DTIItemService {

    @Autowired
    private DTIItemMapper dtiItemMapper;

    @Deprecated
    public List<DTIItemDTO> getDtiItem(DTIInterfaceDTO dtiInterfaceDTO) {

        DTIItemDTO paramDto = new DTIItemDTO();
        paramDto.setConversationId(dtiInterfaceDTO.getConversationId());
        paramDto.setSupbuyType(dtiInterfaceDTO.getSupbuyType());
        paramDto.setDirection(dtiInterfaceDTO.getDirection());
        paramDto.setItemGubun("DTI");

        List<DTIItemDTO> dtiItemDtoResult = dtiItemMapper.read(paramDto);

        return dtiItemDtoResult;
    }

    @Override
    @Deprecated
    public List<DTIItemDTO> getDttItem(DTIInterfaceDTO dtiInterfaceDTO) {
        DTIItemDTO paramDto = new DTIItemDTO();
        paramDto.setConversationId(dtiInterfaceDTO.getConversationId());
        paramDto.setSupbuyType(dtiInterfaceDTO.getSupbuyType());
        paramDto.setDirection(dtiInterfaceDTO.getDirection());
        paramDto.setItemGubun("DTT");

        List<DTIItemDTO> dtiItemDtoResult = dtiItemMapper.read(paramDto);

        return dtiItemDtoResult;
    }

    @Override
    public List<DTIItemDTO> getDTIItemDTOs(String conversationId, String supbuyType, String direction) {

        DTIItemDTO paramDto = new DTIItemDTO();
        paramDto.setConversationId(conversationId);
        paramDto.setSupbuyType(supbuyType);
        paramDto.setDirection(direction);
        paramDto.setItemGubun("DTI");

        List<DTIItemDTO> dtiItemDtoResult = dtiItemMapper.read(paramDto);

        return dtiItemDtoResult;

    }

    @Override
    public List<DTIItemDTO> getDTTItemDTOs(String conversationId, String supbuyType, String direction) {
        DTIItemDTO paramDto = new DTIItemDTO();
        paramDto.setConversationId(conversationId);
        paramDto.setSupbuyType(supbuyType);
        paramDto.setDirection(direction);
        paramDto.setItemGubun("DTT");

        List<DTIItemDTO> dtiItemDtoResult = dtiItemMapper.read(paramDto);

        return dtiItemDtoResult;
    }

    @Override
    public int create(DTIItemDTO t) {
        return dtiItemMapper.create(t);
    }

    @Override
    public List<DTIItemDTO> read(DTIItemDTO t) {
        return dtiItemMapper.read(t);
    }

    @Override
    public int update(DTIItemDTO t) {
        return dtiItemMapper.update(t);
    }

    @Override
    public int delete(DTIItemDTO t) {
        return dtiItemMapper.delete(t);
    }

}
