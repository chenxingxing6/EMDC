/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.1.0

Source Server         : sa_oracle
Source Server Version : 100200
Source Host           : localhost:1521
Source Schema         : SA

Target Server Type    : ORACLE
Target Server Version : 100200
File Encoding         : 65001

Date: 2018-06-14 17:21:41
*/


-- ----------------------------
-- Table structure for E_DETAIL_1
-- ----------------------------
DROP TABLE "SA"."E_DETAIL_1";
CREATE TABLE "SA"."E_DETAIL_1" (
"NAME" VARCHAR2(20 BYTE) NULL ,
"SRCID" VARCHAR2(5 BYTE) NULL ,
"DSTID" VARCHAR2(5 BYTE) NULL ,
"SERSORADDRESS" VARCHAR2(7 BYTE) NULL ,
"COUNT" NUMBER(2) NULL ,
"CMD" VARCHAR2(5 BYTE) NULL ,
"STATUS" NUMBER(2) NULL ,
"DATA" NUMBER(9,4) NULL ,
"GAHER_DATE" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of E_DETAIL_1
-- ----------------------------
