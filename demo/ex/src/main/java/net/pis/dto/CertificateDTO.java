/*
 * (c)BOC
 */
package net.pis.dto;


import net.pis.common.cipher.Cipher;

import java.io.Serializable;
import java.util.Date;
/**
 * TABLE DTO (SBMS_CERTIFICATE)
 *
 * 공인인증서 DTO
 *
 * @author jh,Seo
 */
public class CertificateDTO implements Serializable {

	/**
	 * 인증서 ID
	 */
	private Integer certId;
	/**
	 * 사업자 등록번호
	 */
	private String corpRegNo;

	/**
	 * 발급자 OID
	 */
	private String oid;
	/**
	 * 유효기간 시작일
	 */
	private Date validDate;
	/**
	 * 유효기간 만료일
	 */
	private Date expirationDate;
	/**
	 * 서명 공개키
	 */
	private String signCertPublic;
	/**
	 * 서명 개인키
	 */
	private String signCertPrivate;
	/**
	 * 암호화 공개키
	 */
	private String kmCertPublic;
	/**
	 * 암호화 개인키
	 */
	private String kmCertPrivate;

	/**
	 * 개인키 패스워드
	 */
	@Cipher
	private String password;

	/**
	 * 신원확인 정보
	 */
	private String rvalue;
	/**
	 * 패스워드 암호화 방식
	 */
	private String encryptionMethod;
	/**
	 * 인증서 사용 여부
	 */
	private Boolean useYn;

	/**
	 * 등록일
	 */
	private Date regTimestamp;

	public Integer getCertId() {
		return certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public String getCorpRegNo() {
		return corpRegNo;
	}

	public void setCorpRegNo(String corpRegNo) {
		this.corpRegNo = corpRegNo;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSignCertPublic() {
		return signCertPublic;
	}

	public void setSignCertPublic(String signCertPublic) {
		this.signCertPublic = signCertPublic;
	}

	public String getSignCertPrivate() {
		return signCertPrivate;
	}

	public void setSignCertPrivate(String signCertPrivate) {
		this.signCertPrivate = signCertPrivate;
	}

	public String getKmCertPublic() {
		return kmCertPublic;
	}

	public void setKmCertPublic(String kmCertPublic) {
		this.kmCertPublic = kmCertPublic;
	}

	public String getKmCertPrivate() {
		return kmCertPrivate;
	}

	public void setKmCertPrivate(String kmCertPrivate) {
		this.kmCertPrivate = kmCertPrivate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptionMethod() {
		return encryptionMethod;
	}

	public void setEncryptionMethod(String encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}

	public Boolean getUseYn() {
		return useYn;
	}

	public void setUseYn(Boolean useYn) {
		this.useYn = useYn;
	}

	public Date getRegTimestamp() {
		return regTimestamp;
	}

	public void setRegTimestamp(Date regTimestamp) {
		this.regTimestamp = regTimestamp;
	}

	public String getRvalue() {
		return rvalue;
	}

	public void setRvalue(String rvalue) {
		this.rvalue = rvalue;
	}

}
