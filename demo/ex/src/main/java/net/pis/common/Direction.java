/*
 * (c)BOC
 */
package net.pis.common;

/**
 * 메세지의 방향
 *
 * @author jh,Seo
 */
public enum Direction {

    /**
     * 인바운드
     */
    Inbound("I"),
    /**
     * 아웃바운드
     */
    Outbound("O");

    private final String code;

    private Direction(String code) {

        this.code = code;

    }

    public String getCode() {
        return this.code;
    }

}
