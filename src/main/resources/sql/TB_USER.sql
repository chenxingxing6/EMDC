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

Date: 2018-06-14 17:21:53
*/


-- ----------------------------
-- Table structure for TB_USER
-- ----------------------------
DROP TABLE "SA"."TB_USER";
CREATE TABLE "SA"."TB_USER" (
"ID" NUMBER(10) NOT NULL ,
"USERNAME" VARCHAR2(50 BYTE) NULL ,
"PASSWORD" VARCHAR2(50 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of TB_USER
-- ----------------------------
INSERT INTO "SA"."TB_USER" VALUES ('1', 'admin', '123456');
INSERT INTO "SA"."TB_USER" VALUES ('2', 'user', '123456');
INSERT INTO "SA"."TB_USER" VALUES ('3', 'cxx', '123456');
INSERT INTO "SA"."TB_USER" VALUES ('4', null, null);

-- ----------------------------
-- Indexes structure for table TB_USER
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table TB_USER
-- ----------------------------
ALTER TABLE "SA"."TB_USER" ADD PRIMARY KEY ("ID");
