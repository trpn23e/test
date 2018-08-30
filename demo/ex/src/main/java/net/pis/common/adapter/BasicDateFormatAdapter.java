/**
 * (c)BOC
 */
package net.pis.common.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by achiz on 14. 8. 27.
 */
public class BasicDateFormatAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {

        Date returnDate = null;

        if (null != v) {

            // 스마트빌 포탈의 문제로 거래명세서 요청일이 입력되지 않는 경우 00000000 문자열이 온다. 이유는 모름
            if (!"00000000".equals(v)) {
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
                returnDate = transFormat.parse(v);
            }
        }

        return returnDate;

    }

    @Override
    public String marshal(Date v) throws Exception {

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

        String odate = transFormat.format(v);

        return odate;
    }

}
