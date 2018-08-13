CREATE TABLE `DEPARTMENT` (

`D_ID` varchar(40) CHARACTER SET utf8 NOT NULL COMMENT '部门ID',

`D_FULLNAME` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '部门全称',

`D_NAME` varchar(30) CHARACTER SET utf8 NULL COMMENT '部门简称',

`D_PARENT_CODE` varchar(40) CHARACTER SET UTf8 NULL COMMENT '父级编码',

`D_LEVEL_CODE` varchar(40) CHARACTER SET utf8 NULL COMMENT '级别编码',

`D_ISPUBLIC` int(2) NULL COMMENT '是否公开（0不公开；1公开）',

PRIMARY KEY (`D_ID`)

);



CREATE TABLE `USER` (

`U_ID` varchar(40) CHARACTER SET utf8 NOT NULL COMMENT '用户ID',

`U_ACCOUNT` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '用户账号',

`U_PASSWORD` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '密码',

`U_NAME` varchar(15) CHARACTER SET utf8 NULL COMMENT '名称',

`U_SEX` int(2) NULL COMMENT '性别（0女；1男）',

`U_FOUNDER_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '创建人',

`U_PHONE` varchar(11) CHARACTER SET UTf8 NULL COMMENT '手机号',

`U_TIME` date NULL COMMENT '创建时间',

`U_EMAIL` varchar(20) CHARACTER SET UTf8 NULL COMMENT '邮箱地址',

`U_ISDELETE` int(2) NULL COMMENT '是否删除（0不删除；1删除）',

`U_ISUSE` int(2) NULL COMMENT '是否可用（0不可用；1可用）',

PRIMARY KEY (`U_ID`)

);



CREATE TABLE `ROLE` (

`R_ID` varchar(40) CHARACTER SET utf8 NOT NULL COMMENT '角色ID',

`R_NAME` varchar(15) CHARACTER SET UTf8 NULL COMMENT '角色名',

`R_TYPE` int(2) NULL COMMENT '角色类型（2需求人员（部门）；4采购员；8采购经理；10管理员）',

PRIMARY KEY (`R_ID`)

);



CREATE TABLE `USER_ROLE` (

`U_ID` varchar(40) CHARACTER SET utf8 NOT NULL COMMENT '用户ID',

`R_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '角色ID',

`UR_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '创建人',

`UR_TIME` date NULL COMMENT '创建时间',

PRIMARY KEY (U_ID,R_ID,UR_UID)

);



CREATE TABLE `USER_DEPARTMENT` (

`D_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '部门ID',

`U_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '用户ID',

`UD_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '创建人',

`UD_TIME` date NULL COMMENT '创建时间',

PRIMARY KEY (D_ID,U_ID,UD_UID)

);



CREATE TABLE `PURCHASE_NOTICE` (

`PN_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '采购公告ID',

`PN_TITLE` varchar(50) CHARACTER SET utf8 NULL COMMENT '标题',

`PN_TEXT` text CHARACTER SET UTf8 NULL COMMENT '正文',

`PN_TYPE` int(2) NULL COMMENT '招标类型（1普通招标；2询价招标；3邀请招标；4单一来源招标）',

`PN_STOP_TIME` date NULL COMMENT '截止时间',

`PN_START_TIME` date NULL COMMENT '发起时间',

`PN_PUBLISH_TIME` date NULL COMMENT '发布时间',

`PN_PUBLISH_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '发起人',

`PN_CHECK_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '审核人',

`PN_CHECK_TIME` date NULL COMMENT '审核时间',

`PN_PURCHASE_TYPE` int NULL COMMENT '采购类型（1服务器；2软件；4服务；8外包）',

`PN_CHECK_STATUS` int(2) NULL COMMENT '审核状态（0不通过；1通过;2.审核中;）',

`PN_AFFIX` varchar(40) CHARACTER SET UTf8 NULL COMMENT '附件',

`PN_ISDELETE` int(2) NULL COMMENT '是否删除（0未删除；1删除）',

`PN_DELETE_TIME` date NULL COMMENT '删除时间',

`PN_SUPPIER_ID` text CHARACTER SET UTf8 NULL COMMENT '邀请供应商ID（逗号分隔）',

`PN_SELECT_SUPPIERID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '中标供应商ID',

`PN_ISUSE` int(2) NULL COMMENT '是否可用（0不可用；1可用）',

`PN_SELECT_TIME` date NULL COMMENT '中标时间',

`PN_UPDATE_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '修改人',

`PN_REASON` varchar(255) CHARACTER SET UTf8 NULL COMMENT '不通过理由',

`PN_UID` varchar(30) CHARACTER SET UTf8 NULL COMMENT '发布人',

`PN_DELETE_UID` varchar(30) CHARACTER SET UTf8 NULL COMMENT '删除人',

`PN_UPDATE_TIME` date NULL COMMENT '修改时间',

PRIMARY KEY (`PN_ID`)

);



CREATE TABLE `PURCHASE_LIST` (

`PL_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '采购需求ID',

`PN_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '采购公告ID',

`PL_TYPE` int(2) NULL COMMENT '采购类型（1服务器；2虚拟机；）',

`PL_TITLE` varchar(50) CHARACTER SET UTf8 NULL COMMENT '标题',

`PL_TEXT` text CHARACTER SET UTf8 NULL COMMENT '正文',

`PL_AFFIX` varchar(40) CHARACTER SET UTf8 NULL COMMENT '附件',

`PL_START_TIME` date NULL COMMENT '发起时间',

`PL_START_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '发起人',

`PL_STATUS` int(2) NULL COMMENT '审核状态（0未审核，1审核中，2审核通过,3审核不通过）',

`PL_CHECK_TIME` date NULL COMMENT '审核时间',

`PL_CHECK_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '审核人',

`PL_REASON` text CHARACTER SET UTf8 NULL COMMENT '不通过理由',

`PL_IS_DELETE` INT NULL COMMENT '是否删除（0：未删除 1删除）',
`PL_DELETE_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '删除者',
PRIMARY KEY (`PL_ID`)

);



CREATE TABLE `NOTICE` (

`N_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '公告ID',

`N_TITLE` varchar(40) CHARACTER SET UTf8 NULL COMMENT '标题',

`N_TEXT` text CHARACTER SET UTf8 NULL COMMENT '正文',

`N_AFFIX` varchar(40) CHARACTER SET UTf8 NULL COMMENT '附件',

`N_STARTTIME` date NULL COMMENT '发起时间',

`N_UPDATETIME` date NULL COMMENT '修改时间',

`N_TYPE` int(2) NULL COMMENT '公告类型（1普通公告；2招标公告；3中标公告）',

`N_PUBLISHTIME` date NULL COMMENT '发布时间',

`N_STARTUID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '发起人',

`N_CHECKUID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '审核人',

`N_CHECKSTATUS` int(2) NULL COMMENT '审核状态（0未审核；1已审核通过；2未审核通过;）',

`N_ISDELETE` int(2) NULL COMMENT '是否删除（0未删除；1已删除）',

`N_UPDATEUID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '修改人',

`N_CHECKTIME` date NULL COMMENT '审核时间',

`N_REASON` varchar(255) CHARACTER SET UTf8 NULL COMMENT '不通过理由',

`N_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '发布人',

`N_DELETE_UID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '删除人',

`N_DELETE_TIME` date NULL COMMENT '删除时间',


PRIMARY KEY (`N_ID`)

);



CREATE TABLE `SUPPLIER` (

`S_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '供应商ID',

`S_CHECK_TIME` date NULL COMMENT '审核时间',

`S_START_TIME` date NULL COMMENT '开始往来时间',

`S_CHECK_STATUS` int(2) NULL COMMENT '审核状态（0未审核；1审核中;2审核通过；3审核未通过）',

`S_EMAIL` varchar(30) CHARACTER SET UTf8 NULL COMMENT '邮箱',

`S_MONEY` double(10,4) NULL COMMENT '注册资本',

`S_REALMONEY` double(10,4) NULL COMMENT '实缴注册资本',

`S_BANK_NAME` varchar(20) CHARACTER SET UTf8 NULL COMMENT '银行开户信息（开户行）',

`S_BANK_CREDIT` varchar(30) CHARACTER SET UTf8 NULL COMMENT '银行信用状况',

`S_FOUND_DATE` date NULL COMMENT '公司成立日期',

`S_FAX` varchar(15) CHARACTER SET UTf8 NULL COMMENT '传真',

`S_REASON` text CHARACTER SET UTf8 NULL COMMENT '审核未通过原因',

`S_REGIST_TIME` date NULL COMMENT '注册账号时间',

`S_PHONE` int(2) NULL COMMENT '电话',

`S_ADDRESS` varchar(40) CHARACTER SET UTf8 NULL COMMENT '地址',

`S_AVG_INCOME` double(10,3) NULL COMMENT '平均月营业额',

`S_PRODUCT` text CHARACTER SET UTf8 NULL COMMENT '主要产品及服务',

`S_ABILITY` text CHARACTER SET UTf8 NULL COMMENT '产品或服务资质',

`S_CONTACT` varchar(15) CHARACTER SET UTf8 NULL COMMENT '联系人',

`S_URL` varchar(40) CHARACTER SET UTf8 NULL COMMENT '公司网址',

`S_CLIENT` text CHARACTER SET UTf8 NULL COMMENT '主要客户及客..',

`S_DEPUTY` varchar(15) CHARACTER SET UTf8 NULL COMMENT '法定代表人',

`S_SHORT_NAME` varchar(40) CHARACTER SET UTf8 NULL COMMENT '公司简称',

`S_SOCIAL_CREDIT_CODE` varchar(30) CHARACTER SET UTf8 NULL COMMENT '统一社会信用代码',

`S_FULL_NAME` varchar(40) CHARACTER SET UTf8 NULL COMMENT '公司名称',

`S_PASSWORD` varchar(25) CHARACTER SET UTf8 NULL COMMENT '密码',

`S_ACCOUNT` varchar(50) CHARACTER SET UTf8 NULL COMMENT '开户行信息（账号）',

`S_ACCOUNT_NAME` varchar(30) CHARACTER SET UTf8 NULL COMMENT '银行开户信息（账户名）',

`S_AFFIX` varchar(40) CHARACTER SET UTf8 NULL COMMENT '供应商提交附件',

`S_ISDELETE` int(2) NULL COMMENT '是否删除（0未删除；1删除）',
PRIMARY KEY (`S_ID`)

);

CREATE TABLE `SUPPLIER_SERVICE`(

`S_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '供应商ID',

`SP_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '供应商产品ID',
PRIMARY KEY (`S_ID`,`SP_ID`)
);

CREATE TABLE `SUPPLIER_PRODUCT`(

`SP_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '供应商产品ID',

`SP_TYPE_NAME` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '供应商产品名字',

`D_PARENT_CODE` varchar(40) CHARACTER SET UTf8 NULL COMMENT '父级编码',

`D_LEVEL_CODE` varchar(40) CHARACTER SET utf8 NULL COMMENT '级别编码',

PRIMARY KEY (`SP_ID`)

);


CREATE TABLE `ARCHIVE` (

`A_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '归档ID',

`S_ID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '供应商ID',

`PA_ID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '采购公告ID',

`A_TENDER_TIME` date NULL COMMENT '投标时间',

`A_ISTENDER` int(2) NULL COMMENT '是否中标（0未中标；1中标）',

`A_DEFINE_TIME` date NULL COMMENT '中标时间',

`A_AFFIX` varchar(40) CHARACTER SET UTf8 NULL COMMENT '投标附件',

PRIMARY KEY (`A_ID`)
);

CREATE TABLE `AFFIX` (
    id varchar(38) NOT NULL COMMENT '附件ID',
    description varchar(255) NULL COMMENT '附件描述',
    `path` varchar(255) NULL COMMENT '附件路径',
    create_time datetime NULL COMMENT '创建时间',
    create_user_id varchar(40) NULL COMMENT '创建人',
    PRIMARY KEY (id)
)

CREATE TABLE `EVALUATION` (

`E_ID` varchar(40) CHARACTER SET UTf8 NOT NULL COMMENT '评价ID',

`S_ID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '供应商ID',

`PA_ID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '采购公告ID',

`U_ID` varchar(40) CHARACTER SET UTf8 NULL COMMENT '评价用户ID',

`E_TOTAL` double(10,2) NULL COMMENT '总评分',

`E_TEXT` text CHARACTER SET UTf8 NULL COMMENT '评价内容',

`E_TIME` date NULL COMMENT '评价时间',

`E_AFFIX` varchar(40) CHARACTER SET UTf8 NULL COMMENT '附件',

PRIMARY KEY (`E_ID`)

);





ALTER TABLE `USER_ROLE` ADD CONSTRAINT `fk_USER_ROLE_USER_U` FOREIGN KEY (`U_ID`) REFERENCES `USER` (`U_ID`);

ALTER TABLE `USER_DEPARTMENT` ADD CONSTRAINT `fk_USER_DEPARTMENT_USER_DEPARTMENT_U` FOREIGN KEY (`U_ID`) REFERENCES `USER` (`U_ID`);

ALTER TABLE `EVALUATION` ADD CONSTRAINT `fk_EVALUATION_SUPPLIER` FOREIGN KEY (`S_ID`) REFERENCES `SUPPLIER` (`S_ID`);

ALTER TABLE `ARCHIVE` ADD CONSTRAINT `fk_ARCHIVE_SUPPLIER` FOREIGN KEY (`S_ID`) REFERENCES `SUPPLIER` (`S_ID`);

ALTER TABLE `ARCHIVE` ADD CONSTRAINT `fk_ARCHIVE_PANNOUNCEMENT` FOREIGN KEY (`PA_ID`) REFERENCES `PURCHASE_NOTICE` (`PN_ID`);

ALTER TABLE `EVALUATION` ADD CONSTRAINT `fk_EVALUATION_USER` FOREIGN KEY (`U_ID`) REFERENCES `USER` (`U_ID`);

ALTER TABLE `USER_DEPARTMENT` ADD CONSTRAINT `fk_USER_DEPARTMENT_D` FOREIGN KEY (`D_ID`) REFERENCES `DEPARTMENT` (`D_ID`);

ALTER TABLE `USER_ROLE` ADD CONSTRAINT `fk_USER_ROLE_R` FOREIGN KEY (`R_ID`) REFERENCES `ROLE` (`R_ID`);



