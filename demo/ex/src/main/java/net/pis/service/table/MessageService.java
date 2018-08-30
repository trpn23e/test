package net.pis.service.table;

import net.pis.common.MessageStatus;
import net.pis.common.Signal;
import net.pis.message.MessageMetaInfo;

/**
 * Created by achiz on 14. 6. 11.
 */
public interface MessageService {

	/**
	 * 메타에서 SIGNAL 정보를 반환한다.
	 *
	 * <p>
	 * 메타정보에 SIGNAL이 포함됨에 따라 더이상 의미가 없음
	 * </p>
	 * <p>
	 * 다시 메타에서 SIGNAL을 제외함
	 * </p>
	 *
	 * @param messageMetaInfo
	 * @return
	 */
	Signal getSignal(MessageMetaInfo messageMetaInfo);

	Object getInterfaceDTO(MessageMetaInfo messageMetaInfo);

	/**
	 * 상태 정보를 기준으로 TAG 정보를 갱신한다.
	 *
	 * @param messageTagId
	 * @param messageStatus
	 */
	void updateMessageTag(String messageTagId, MessageStatus messageStatus);

}
