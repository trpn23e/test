/*
 * (c)BOC
 */
package net.pis.common.cipher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 암호화 필드 대상 어노테이션
 *
 * @author jh,Seo
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cipher {

}
