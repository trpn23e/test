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
public class FullDateFormatAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);

        return transFormat.parse(v);

    }

    @Override
    public String marshal(Date v) throws Exception {

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);

        String odate = transFormat.format(v);

        return odate;
    }

}
