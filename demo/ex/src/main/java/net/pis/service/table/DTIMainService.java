/**
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.DefaultTableService;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIMainDTO;

/**
 * Created by achiz on 2014-12-24.
 */
public interface DTIMainService extends DefaultTableService<DTIMainDTO> {

    /**
     * 세금계산서 원본생성시 사용
     *
     *
     * @param dtiInterfaceDTO
     * @return
     */
    DTIMainDTO getDtiMain(DTIInterfaceDTO dtiInterfaceDTO);

    /**
     * 메인 테이블 데이타 반환
     *
     * @param conversationId
     * @param subBuyType
     * @param direction
     * @return
     */
    DTIMainDTO getDTIMainDTO(
            String conversationId,
            String subBuyType,
            String direction
    );

}
