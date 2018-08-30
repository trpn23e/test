/*
  -- 테스트 페이지 호출을 위한 DB 스크립트
   resources 디렉토리에 schema.sql, data.sql을 default이름으로 database가 h2로 설정되있을때
   자동으로 스크립트를 읽어들인다.
*/

DROP TABLE IF EXISTS TBL_PT_CTR;
DROP TABLE IF EXISTS XXSB_DTI_INTERFACE;
DROP TABLE IF EXISTS XXSB_DTI_MAIN;
DROP TABLE IF EXISTS XXSB_DTI_ITEM;
DROP TABLE IF EXISTS XXSB_DTI_STATUS;
DROP TABLE IF EXISTS XXSB_DELIVERY_DTI_FILE;
DROP TABLE IF EXISTS XXSB_DTI_INTERFACE;
DROP TABLE IF EXISTS XXSB_DTI_APPROVE_ID;


CREATE TABLE IF NOT EXISTS TBL_PT_CTR (
	CTR_SEQ IDENTITY PRIMARY KEY,
	CTR_NO varchar(100) NULL,
	CTR_ADR varchar(100) NULL,
	TX_ID varchar(100) NULL,
	CTR_STAT char(2) NULL,
	TRN_STAT char(1) NULL,
	OWN_ADR varchar(100) NULL,
	SND_ADR varchar(100) NULL,
	RCV_ADR varchar(100) NULL,
	BRK_SND_ADR varchar(100) NULL,
	BRK_RCV_ADR varchar(100) NULL,
	OWN_NM varchar(200) NULL,
	SND_NM varchar(200) NULL,
	RCV_NM varchar(200) NULL,
	BRK_SND_NM varchar(200) NULL,
	BRK_RCV_NM varchar(200) NULL,
	CTR_FILE_NM varchar(2000) NULL,
	CTR_FILE_HASH varchar(1000) NULL,
	CTR_FILE_LINK varchar(2000) NULL,
	CTR_FIN_FILE_HASH varchar(1000) NULL,
	CTR_FIN_FILE_LINK varchar(2000) NULL,
	ETC_FILE_GRP_ID varchar(100) NULL,
	META_ID varchar(100) NULL,
	CDATE datetime NULL,
	UDATE datetime NULL,
	SDATE datetime NULL,
	RDATE datetime NULL,
	CUSR_ID varchar(200) NULL,
	UUSR_ID varchar(200) NULL
);

CREATE TABLE IF NOT EXISTS XXSB_DTI_MAIN (
  CONVERSATION_ID char(35) NOT NULL,
  SUPBUY_TYPE char(2) NOT NULL,
  DIRECTION char(1) NOT NULL,
  INTERFACE_BATCH_ID varchar(40) DEFAULT NULL,
  DTI_WDATE datetime NOT NULL,
  DTI_TYPE varchar(4) NOT NULL,
  TAX_DEMAND varchar(2) NOT NULL,
  SEQ_ID varchar(24) DEFAULT NULL,
  SUP_COM_ID varchar(12) DEFAULT NULL,
  SUP_COM_REGNO char(10) NOT NULL,
  SUP_REP_NAME varchar(30) NOT NULL,
  SUP_COM_NAME varchar(70) NOT NULL,
  SUP_COM_TYPE varchar(40) DEFAULT NULL,
  SUP_COM_CLASSIFY varchar(40) DEFAULT NULL,
  SUP_COM_ADDR varchar(150) NOT NULL,
  SUP_DEPT_NAME varchar(40) DEFAULT NULL,
  SUP_EMP_NAME varchar(30) DEFAULT NULL,
  SUP_TEL_NUM varchar(20) DEFAULT NULL,
  SUP_EMAIL varchar(40) DEFAULT NULL,
  BYR_COM_ID varchar(12) DEFAULT NULL,
  BYR_COM_REGNO varchar(13) NOT NULL,
  BYR_REP_NAME varchar(30) NOT NULL,
  BYR_COM_NAME varchar(70) NOT NULL,
  BYR_COM_TYPE varchar(40) DEFAULT NULL,
  BYR_COM_CLASSIFY varchar(40) DEFAULT NULL,
  BYR_COM_ADDR varchar(150) NOT NULL,
  BYR_DEPT_NAME varchar(40) DEFAULT NULL,
  BYR_EMP_NAME varchar(30) DEFAULT NULL,
  BYR_TEL_NUM varchar(20) DEFAULT NULL,
  BYR_EMAIL varchar(40) DEFAULT NULL,
  BROKER_COM_ID varchar(12) DEFAULT NULL,
  BROKER_COM_REGNO char(10) DEFAULT NULL,
  BROKER_REP_NAME varchar(30) DEFAULT NULL,
  BROKER_COM_NAME varchar(70) DEFAULT NULL,
  BROKER_COM_TYPE varchar(40) DEFAULT NULL,
  BROKER_COM_CLASSIFY varchar(40) DEFAULT NULL,
  BROKER_COM_ADDR varchar(150) DEFAULT NULL,
  BROKER_DEPT_NAME varchar(40) DEFAULT NULL,
  BROKER_EMP_NAME varchar(30) DEFAULT NULL,
  BROKER_TEL_NUM varchar(20) DEFAULT NULL,
  BROKER_EMAIL varchar(40) DEFAULT NULL,
  CASH_CODE varchar(3) DEFAULT '10',
  CASH_AMOUNT decimal(18,0) DEFAULT '0',
  CHECK_CODE varchar(3) DEFAULT '20',
  CHECK_AMOUNT decimal(18,0) DEFAULT '0',
  NOTE_CODE varchar(3) DEFAULT '2AA',
  NOTE_AMOUNT decimal(18,0) DEFAULT '0',
  RECEIVABLE_CODE varchar(3) DEFAULT '30',
  RECEIVABLE_AMOUNT decimal(18,0) DEFAULT '0',
  SUP_AMOUNT decimal(18,0) DEFAULT '0',
  TAX_AMOUNT decimal(18,0) DEFAULT '0',
  TOT_FOREIGN_AMOUNT decimal(18,0) DEFAULT '0',
  TOTAL_AMOUNT decimal(18,0) DEFAULT '0',
  TOTAL_QUANTITY decimal(18,0) DEFAULT '0',
  DTT_YN char(1) DEFAULT 'N',
  REMARK varchar(150) DEFAULT NULL,
  CREATED_BY varchar(15) DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  LAST_UPDATED_BY varchar(15) DEFAULT NULL,
  LAST_UPDATE_DATE datetime DEFAULT NULL,
  ETCNUM1 varchar(50) DEFAULT NULL,
  ETCNUM2 varchar(50) DEFAULT NULL,
  ETCNUM3 varchar(50) DEFAULT NULL,
  ETCNUM4 varchar(50) DEFAULT NULL,
  DTI_MSG CLOB,
  AMEND_CODE varchar(2) DEFAULT NULL,
  REMARK2 varchar(150) DEFAULT NULL,
  REMARK3 varchar(150) DEFAULT NULL,
  EXCHANGED_DOC_ID varchar(24) DEFAULT NULL,
  APPROVE_ID varchar(24) DEFAULT NULL,
  SUP_BIZPLACE_CODE varchar(4) DEFAULT NULL,
  BYR_BIZPLACE_CODE varchar(4) DEFAULT NULL,
  BRK_BIZPLACE_CODE varchar(4) DEFAULT NULL,
  BYR_DEPT_NAME2 varchar(40) DEFAULT NULL,
  BYR_EMP_NAME2 varchar(30) DEFAULT NULL,
  BYR_TEL_NUM2 varchar(20) DEFAULT NULL,
  BYR_EMAIL2 varchar(40) DEFAULT NULL,
  ATTACHFILE_YN char(1) DEFAULT NULL,
  ASP_SMTP_MAIL varchar(200) DEFAULT NULL,
  ORI_ISSUE_ID varchar(24) DEFAULT NULL,
  DTI_IDATE datetime DEFAULT NULL,
  DTI_SDATE datetime DEFAULT NULL,
  PRIMARY KEY (CONVERSATION_ID,SUPBUY_TYPE,DIRECTION)
);


CREATE TABLE IF NOT EXISTS XXSB_DTI_ITEM (
  CONVERSATION_ID char(35) NOT NULL DEFAULT '',
  SUPBUY_TYPE char(2) NOT NULL DEFAULT '',
  DIRECTION char(1) NOT NULL DEFAULT '',
  DTI_LINE_NUM decimal(4,0) NOT NULL DEFAULT '0',
  ITEM_CODE varchar(50) DEFAULT NULL,
  ITEM_NAME varchar(100) DEFAULT NULL,
  ITEM_SIZE varchar(60) DEFAULT NULL,
  ITEM_MD datetime DEFAULT NULL,
  UNIT_PRICE decimal(18,2) DEFAULT '0.00',
  ITEM_QTY decimal(18,2) DEFAULT '0.00',
  SUP_AMOUNT decimal(18,0) DEFAULT '0',
  TAX_AMOUNT decimal(18,0) DEFAULT '0',
  FOREIGN_AMOUNT decimal(18,0) DEFAULT '0',
  CURRENCY_CODE varchar(3) DEFAULT NULL,
  ITEM_GUBUN varchar(3) DEFAULT 'DTI',
  REMARK varchar(100) DEFAULT NULL,
  CREATED_BY varchar(15) DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  LAST_UPDATED_BY varchar(15) DEFAULT NULL,
  LAST_UPDATE_DATE datetime DEFAULT NULL,
  PRIMARY KEY (CONVERSATION_ID,SUPBUY_TYPE,DIRECTION,DTI_LINE_NUM)
);


CREATE TABLE IF NOT EXISTS XXSB_DTI_STATUS (
  CONVERSATION_ID char(35) NOT NULL,
  SUPBUY_TYPE char(2) NOT NULL,
  DIRECTION char(1) NOT NULL,
  DTI_STATUS char(1) NOT NULL,
  TRAN_STATUS char(1) DEFAULT NULL,
  SUP_PRINT_CNT SMALLINT DEFAULT NULL,
  BYR_PRINT_CNT SMALLINT DEFAULT NULL,
  RETURN_CODE char(5) DEFAULT NULL,
  RETURN_DESCRIPTION varchar(2000) DEFAULT NULL,
  SBDESCRIPTION varchar(70) DEFAULT NULL,
  DEPT_NAME varchar(40) DEFAULT NULL,
  CREATED_BY varchar(15) DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  LAST_UPDATED_BY varchar(15) DEFAULT NULL,
  LAST_UPDATE_DATE datetime DEFAULT NULL,
  ATT01 varchar(100) DEFAULT NULL,
  ATT02 varchar(100) DEFAULT NULL,
  ATT03 varchar(100) DEFAULT NULL,
  ATT04 varchar(100) DEFAULT NULL,
  ATT05 varchar(100) DEFAULT NULL,
  ATT06 varchar(100) DEFAULT NULL,
  ATT07 varchar(100) DEFAULT NULL,
  ATT08 varchar(100) DEFAULT NULL,
  ATT09 varchar(100) DEFAULT NULL,
  ATT10 varchar(100) DEFAULT NULL,
  ATT11 varchar(100) DEFAULT NULL,
  PRIMARY KEY (CONVERSATION_ID,SUPBUY_TYPE,DIRECTION)
);


CREATE TABLE IF NOT EXISTS XXSB_DELIVERY_DTI_FILE (
  CONVERSATION_ID varchar(35) NOT NULL,
  FILE_SEQ decimal(3,0) NOT NULL,
  EVENT_ID varchar(40) DEFAULT NULL,
  FILE_NAME varchar(200) DEFAULT NULL,
  FILE_SAVE_TYPE varchar(6) DEFAULT NULL,
  FILE_SIZE decimal(18,0) DEFAULT NULL,
  FILE_BINARY blob,
  FILE_FULL_PATH varchar(512) DEFAULT NULL,
  FILE_STATUS varchar(20) DEFAULT NULL,
  ERROR_CODE varchar(5) DEFAULT NULL,
  ERROR_MESSAGE varchar(400) DEFAULT NULL,
  CREATION_BY varchar(50) DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  LAST_UPDATE_BY varchar(50) DEFAULT NULL,
  LAST_UPDATE_DATE datetime DEFAULT NULL,
  IN_OUT varchar(3) DEFAULT NULL,
  EXT_XML varchar(255) DEFAULT NULL,
  PRIMARY KEY (CONVERSATION_ID,FILE_SEQ)
);

CREATE TABLE IF NOT EXISTS XXSB_DTI_INTERFACE(
  MESSAGE_ID VARCHAR(40),
  CONVERSATION_ID CHAR(35),
  SUPBUY_TYPE CHAR(2),
  DIRECTION CHAR(1),
  SIGNAL VARCHAR(20),
  MESSAGE_STATUS_FLAG CHAR(1),
  TARGET_SYSTEM_ID VARCHAR(100),
  META_STRING VARCHAR(5000),
  AUTH_TICKET VARCHAR(200),
  LAST_UPDATE_DATE TIMESTAMP,
  PRIMARY KEY(MESSAGE_ID, CONVERSATION_ID, SUPBUY_TYPE, DIRECTION)
);

CREATE TABLE IF NOT EXISTS XXSB_DTI_APPROVE_ID (
  NTSCODE varchar(8) NULL,
  LINKCOMPANYCODE varchar(3),
  SEQ_NO NUMBER(20,0)
);