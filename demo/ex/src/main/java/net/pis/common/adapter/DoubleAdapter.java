/*
 * (c)BOC
 */
package net.pis.common.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DecimalFormat;

/**
 *
 * @author jh,Seo
 */
public class DoubleAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String v) throws Exception {
        return Double.parseDouble(v);
    }

    @Override
    public String marshal(Double v) throws Exception {
        if (Double.MAX_VALUE == v) {
            return null;
        } else {

            return new DecimalFormat("#.##").format(v);

        }
    }

    public static void main(String[] args) {

        Double a = -1234.567;

        System.out.println(new DecimalFormat("#").format(a));

    }
}
