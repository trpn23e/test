/**
 * (c)BOC
 */
package net.pis.service.table;
import net.pis.dao.DefaultTableService;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIItemDTO;

import java.util.List;
/**
 * Created by achiz on 2014-12-24.
 */
public interface DTIItemService extends DefaultTableService<DTIItemDTO> {

    /**
     * 세금계산서 원본생성시 사용
     *
     *
     * @param dtiInterfaceDTO
     * @return
     */
    List<DTIItemDTO> getDtiItem(DTIInterfaceDTO dtiInterfaceDTO);

    /**
     * 거래명세서 ITEM 반환
     *
     * @param dtiInterfaceDTO
     * @return
     */
    List<DTIItemDTO> getDttItem(DTIInterfaceDTO dtiInterfaceDTO);

    /**
     * 품목 정보 반환 (DTI)
     *
     * @param conversationId
     * @param supbuyType
     * @param direction
     * @return
     */
    List<DTIItemDTO> getDTIItemDTOs(String conversationId,
                                    String supbuyType,
                                    String direction);

    /**
     * 품목 정보 반환 (DTT)
     *
     * @param conversationId
     * @param supbuyType
     * @param direction
     * @return
     */
    List<DTIItemDTO> getDTTItemDTOs(String conversationId,
                                    String supbuyType,
                                    String direction);

}
