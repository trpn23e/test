/*
 * (c)BOC
 */
package net.pis.service;

/**
 *
 * @author jh,Seo
 */
public class DynamicDSContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new InheritableThreadLocal<>();

    public static String defaultKey = "default";

    public static void setDynamicDSType(String dynamicDSType) {

        CONTEXT_HOLDER.set(dynamicDSType);
    }

    public static String getDynamicDSType() {

        if (null == CONTEXT_HOLDER.get()) {

            return defaultKey;
        }

        return CONTEXT_HOLDER.get();
    }

    public static void clearDynamicDSType() {

        CONTEXT_HOLDER.remove();

    }
}
