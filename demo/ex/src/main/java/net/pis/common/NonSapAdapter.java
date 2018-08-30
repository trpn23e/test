/*
 * (c)BOC
 */
package net.pis.common;

import java.io.Serializable;

/**
 *
 * @author jh,Seo
 */
public class NonSapAdapter implements Adapter, Serializable {

    private String destination;

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String getAdapterName() {

        return "NONSAP";

    }

    @Override
    public String getDestination() {
        return this.destination;
    }

}
