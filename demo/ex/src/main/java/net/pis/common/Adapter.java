/*
 * (c)BOC
 */
package net.pis.common;

import java.io.Serializable;

/**
 * ERP 시스템에 종류를 나타낸다 정확하게는 ERP가 SAP인지 아닌지를 나타내는데 쓰인다.
 *
 * <p>
 * non-SAP의 경우는 Biz코드와 Client코드가 무의미 하지만 SAP 의 경우 메타 데이타를 생성하는자가 두 값을 할당해야 한다. 두
 * 값은 수신된 데이타가 들어갈 곳을 찾는데 사용된다.
 * </p>
 *
 * @author jh,Seo
 */
public interface Adapter extends Serializable {

    String getAdapterName();

    String getDestination();
}
