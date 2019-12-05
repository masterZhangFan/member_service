/*
Navicat MySQL Data Transfer

Source Server         : galileo
Source Server Version : 50724
Source Host           : 123.207.181.240:3306
Source Database       : sales_release

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-05-22 11:58:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for NewTable
-- ----------------------------
DROP TABLE IF EXISTS `NewTable`;
CREATE TABLE `NewTable` (
  `bill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '客户id',
  `amount` bigint(20) NOT NULL COMMENT '金额，单位分',
  `balance` bigint(20) NOT NULL COMMENT '余额，单位分',
  `multiplier` int(11) NOT NULL COMMENT '-1 支出，1收入',
  `detail` varchar(50) DEFAULT NULL COMMENT '支付详情',
  `bill_type` int(11) NOT NULL COMMENT '订单类别 1.订单结算  2.订单退款 3.余额充值 4.余额退款  ',
  `pay_method` varchar(20) NOT NULL COMMENT '1.现金支付 2.银行转账 3.余额支付',
  `order` varchar(20) NOT NULL COMMENT '订单号',
  `time` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_assigned
-- ----------------------------
DROP TABLE IF EXISTS `tbl_assigned`;
CREATE TABLE `tbl_assigned` (
  `assigned_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_corp_id` int(11) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  `approval_manual_weight` double(11,8) DEFAULT NULL COMMENT '手动批准重量',
  `approval_autol_weight` double(255,0) DEFAULT NULL COMMENT '自动控制的允许量',
  `assigned_time` datetime DEFAULT NULL COMMENT '分配时间',
  `booking_order_id` int(11) DEFAULT NULL COMMENT '是没分配的,其它指向当前预约订单表，说明已分配',
  PRIMARY KEY (`assigned_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_bill_flow
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bill_flow`;
CREATE TABLE `tbl_bill_flow` (
  `bill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_corp_id` int(11) NOT NULL COMMENT '客户id',
  `amount` bigint(20) NOT NULL COMMENT '金额，单位分',
  `balance` bigint(20) NOT NULL COMMENT '余额，单位分',
  `detail` varchar(50) DEFAULT NULL COMMENT '支付详情',
  `bill_type` int(11) NOT NULL COMMENT '订单类别 1.订单结算  2.订单退款 3.余额充值 4.余额退款  5.运费退款 6.运费结算 ',
  `pay_method` int(20) NOT NULL DEFAULT '3' COMMENT '1.现金支付 2.银行转账 3.余额支付4.POS机 5.银行承兑汇票6.电汇',
  `order` varchar(20) NOT NULL COMMENT '订单号',
  `time` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_bill_flow_2010
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bill_flow_2010`;
CREATE TABLE `tbl_bill_flow_2010` (
  `bill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_corp_id` int(11) NOT NULL COMMENT '客户id',
  `amount` bigint(20) NOT NULL COMMENT '金额，单位分',
  `balance` bigint(20) NOT NULL COMMENT '余额，单位分',
  `detail` varchar(50) DEFAULT NULL COMMENT '支付详情',
  `bill_type` int(11) NOT NULL COMMENT '订单类别 1.订单结算  2.订单退款 3.余额充值 4.余额退款  ',
  `pay_method` int(20) NOT NULL DEFAULT '3' COMMENT '1.现金支付 2.银行转账 3.余额支付',
  `order` varchar(20) NOT NULL COMMENT '订单号',
  `time` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_bill_flow_2019
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bill_flow_2019`;
CREATE TABLE `tbl_bill_flow_2019` (
  `bill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_corp_id` int(11) NOT NULL COMMENT '客户id',
  `amount` bigint(20) NOT NULL COMMENT '金额，单位分',
  `balance` bigint(20) NOT NULL COMMENT '余额，单位分',
  `detail` varchar(50) DEFAULT NULL COMMENT '支付详情',
  `bill_type` int(11) NOT NULL COMMENT '订单类别 1.订单结算  2.订单退款 3.余额充值 4.余额退款  ',
  `pay_method` int(20) NOT NULL DEFAULT '3' COMMENT '1.现金支付 2.银行转账 3.余额支付',
  `order` varchar(20) NOT NULL COMMENT '订单号',
  `time` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_bill_lading
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bill_lading`;
CREATE TABLE `tbl_bill_lading` (
  `bill_lading_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '提单ID',
  `order_id` bigint(11) NOT NULL COMMENT '指向主表',
  `bill_lading_order` varchar(30) DEFAULT NULL COMMENT '提单订单号',
  `sys_company_id` int(11) DEFAULT '0',
  `identification_code` varchar(50) DEFAULT NULL COMMENT '识别码',
  `plate_number` varchar(15) DEFAULT NULL COMMENT '车牌号',
  `driver_name` varchar(50) DEFAULT NULL COMMENT '司机名称',
  `driver_phone` varchar(20) DEFAULT NULL COMMENT '司机电话',
  `handle_status` int(11) DEFAULT '0' COMMENT '0.正常 1.拒绝 2.作废 3.完成 4.回空车（结束） 6.回空车（重新拉）',
  `step_flag` int(11) DEFAULT '1' COMMENT '1.待拉货 2.进一道门3.已开票（打印下料单）4.财务审核已拒绝5.排队中（财务审核+进入一道门）6.呼叫中（等待进入水泥门卫的车辆）7.进入水泥门卫8.待下料（入二道门至下料间,袋装跳过）9.下料中（进入下料间）10.下料完成11.出二道门（出下料间）12.出水泥门卫出一道门（已完成）',
  `truck_run_status_des` varchar(60) DEFAULT NULL COMMENT '车辆当前运行状态',
  `pickup_time` datetime DEFAULT NULL COMMENT '提货时间',
  `pickup_package_bages` int(20) DEFAULT NULL COMMENT '请发袋数',
  `pickup_weight` double(20,8) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL COMMENT '产线',
  `product_type_lane_id` int(11) DEFAULT NULL COMMENT '车道',
  `brand_name` varchar(50) DEFAULT NULL,
  `print_time` datetime DEFAULT NULL COMMENT '(上)次打印(开票)时间',
  `replenish_print_count` int(11) DEFAULT '0' COMMENT '补打次数',
  `unit_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '单价',
  `transport_totol_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '运输总价格',
  `settlement_price` bigint(11) NOT NULL DEFAULT '0',
  `finance_review_time` datetime DEFAULT NULL,
  `finance_review_user` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注/拒绝原因',
  `one_door_into_time` datetime DEFAULT NULL COMMENT '一道门进时间',
  `once_door_out_time` datetime DEFAULT NULL COMMENT '一道门出时间',
  `two_door_into_time` datetime DEFAULT NULL COMMENT '一道门出时间',
  `two_door_out_time` datetime DEFAULT NULL COMMENT '二道门出时间',
  `once_weight` double(20,8) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `contact_latitude` double(20,5) DEFAULT NULL,
  `contact_longitude` double(20,5) DEFAULT NULL,
  `second_weight` double(20,8) DEFAULT NULL,
  `net_weight` double(20,8) DEFAULT NULL COMMENT '净重',
  `qrcode` varchar(100) DEFAULT NULL COMMENT 'TOKEN:(包含ID+时间)',
  `status_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bill_lading_id`),
  KEY `order` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_bill_lading_history_2018
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bill_lading_history_2018`;
CREATE TABLE `tbl_bill_lading_history_2018` (
  `bill_lading_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '提单ID',
  `cust_corp_id` int(11) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL COMMENT '产品类别表',
  `order_id` int(11) NOT NULL COMMENT '指向主表',
  `bill_lading_order` varchar(30) DEFAULT NULL,
  `sys_company_id` int(11) DEFAULT '0',
  `identification_code` varchar(50) DEFAULT NULL COMMENT '识别码',
  `plate_number` varchar(15) DEFAULT NULL COMMENT '车牌号',
  `driver_name` varchar(50) DEFAULT NULL COMMENT '司机名称',
  `driver_phone` varchar(20) DEFAULT NULL COMMENT '司机电话',
  `handle_status` int(11) DEFAULT '0' COMMENT '0.正常 1.拒绝 2.作废 3.完成',
  `step_flag` int(11) DEFAULT '1' COMMENT '1.申请 2.创建 3.开票 4.财务 5.一道门 6.二道门',
  `truck_run_status_des` varchar(60) DEFAULT NULL COMMENT '车辆当前运行状态',
  `pickup_time` datetime DEFAULT NULL COMMENT '提货时间',
  `pickup_package_bages` int(20) DEFAULT NULL COMMENT '请发袋数',
  `pickup_weight` double(20,8) DEFAULT NULL COMMENT '本次拉货重量',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID,默认0',
  `brand_name` varchar(255) DEFAULT NULL,
  `product_type_lane_id` int(10) DEFAULT NULL,
  `print_time` datetime DEFAULT NULL COMMENT '(上)次打印(开票)时间',
  `replenish_print_count` int(11) DEFAULT NULL COMMENT '补打次数',
  `unit_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '单价',
  `transport_totol_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '运输总价格',
  `settlement_price` bigint(11) NOT NULL DEFAULT '0',
  `finance_review_time` datetime DEFAULT NULL,
  `finance_review_user` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注/拒绝原因',
  `one_door_into_time` datetime DEFAULT NULL COMMENT '一道门进时间',
  `once_door_out_time` datetime DEFAULT NULL COMMENT '一道门出时间',
  `two_door_into_time` datetime DEFAULT NULL COMMENT '一道门出时间',
  `two_door_out_time` datetime DEFAULT NULL COMMENT '二道门出时间',
  `once_weight` double(20,8) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `second_weight` double(20,8) DEFAULT NULL,
  `net_weight` double(20,8) DEFAULT NULL COMMENT '净重',
  `qrcode` varchar(100) DEFAULT NULL COMMENT 'TOKEN:(包含ID+时间)',
  `status_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bill_lading_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_bill_lading_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bill_lading_history_2019`;
CREATE TABLE `tbl_bill_lading_history_2019` (
  `bill_lading_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '提单ID',
  `cust_corp_id` int(11) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL COMMENT '产品类别表',
  `order_id` int(11) NOT NULL COMMENT '指向主表',
  `bill_lading_order` varchar(30) DEFAULT NULL,
  `sys_company_id` int(11) DEFAULT '0',
  `identification_code` varchar(50) DEFAULT NULL COMMENT '识别码',
  `plate_number` varchar(15) DEFAULT NULL COMMENT '车牌号',
  `driver_name` varchar(50) DEFAULT NULL COMMENT '司机名称',
  `driver_phone` varchar(20) DEFAULT NULL COMMENT '司机电话',
  `handle_status` int(11) DEFAULT '0' COMMENT '0.正常 1.拒绝 2.作废 3.完成',
  `step_flag` int(11) DEFAULT '1' COMMENT '1.申请 2.创建 3.开票 4.财务 5.一道门 6.二道门',
  `truck_run_status_des` varchar(60) DEFAULT NULL COMMENT '车辆当前运行状态',
  `pickup_time` datetime DEFAULT NULL COMMENT '提货时间',
  `pickup_package_bages` int(20) DEFAULT NULL COMMENT '请发袋数',
  `pickup_weight` double(20,8) DEFAULT NULL COMMENT '本次拉货重量',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID,默认0',
  `brand_name` varchar(255) DEFAULT NULL,
  `product_type_lane_id` int(10) DEFAULT NULL,
  `print_time` datetime DEFAULT NULL COMMENT '(上)次打印(开票)时间',
  `replenish_print_count` int(11) DEFAULT NULL COMMENT '补打次数',
  `unit_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '单价',
  `transport_totol_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '运输总价格',
  `settlement_price` bigint(11) NOT NULL DEFAULT '0',
  `finance_review_time` datetime DEFAULT NULL,
  `finance_review_user` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注/拒绝原因',
  `one_door_into_time` datetime DEFAULT NULL COMMENT '一道门进时间',
  `once_door_out_time` datetime DEFAULT NULL COMMENT '一道门出时间',
  `two_door_into_time` datetime DEFAULT NULL COMMENT '一道门出时间',
  `two_door_out_time` datetime DEFAULT NULL COMMENT '二道门出时间',
  `once_weight` double(20,8) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `second_weight` double(20,8) DEFAULT NULL,
  `net_weight` double(20,8) DEFAULT NULL COMMENT '净重',
  `qrcode` varchar(100) DEFAULT NULL COMMENT 'TOKEN:(包含ID+时间)',
  `status_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bill_lading_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_bill_lading_status
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bill_lading_status`;
CREATE TABLE `tbl_bill_lading_status` (
  `bill_lading_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_lading_id` bigint(20) NOT NULL COMMENT '指向主表',
  `status` int(11) NOT NULL COMMENT '状态，参考提单表',
  `status_update_time` datetime NOT NULL COMMENT '更新时间',
  `sys_company_id` int(11) NOT NULL COMMENT '所属公司ID',
  `remark` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '备注',
  PRIMARY KEY (`bill_lading_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=607 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_brand
-- ----------------------------
DROP TABLE IF EXISTS `tbl_brand`;
CREATE TABLE `tbl_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `sys_company_id` int(11) DEFAULT '0',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_charge_refund
-- ----------------------------
DROP TABLE IF EXISTS `tbl_charge_refund`;
CREATE TABLE `tbl_charge_refund` (
  `charge_id` int(11) NOT NULL AUTO_INCREMENT,
  `order` varchar(20) DEFAULT NULL COMMENT '订单号',
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `refund_type` int(11) DEFAULT NULL COMMENT '1.退款 2.退运费',
  `amount` bigint(20) DEFAULT NULL COMMENT '支出/退还金额，单位分',
  `balance` bigint(20) DEFAULT NULL COMMENT '操作后的余额',
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `charge_numer` varchar(40) DEFAULT NULL COMMENT '收(退)款编号',
  `cb_number` varchar(255) DEFAULT NULL COMMENT '支（汇）编',
  `pay_method` int(11) DEFAULT NULL COMMENT '支付方式:1.现金 2 转账 3 授信 4 POS机 5 银行承兑汇票 6 电汇',
  `ticketing_bank` varchar(50) DEFAULT NULL COMMENT '出票银行',
  `opening_bank` varchar(50) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `transactor` varchar(20) DEFAULT NULL COMMENT '处理人(交款/退款人)',
  `user_id` int(11) DEFAULT '0' COMMENT '系统操作员ID',
  `sys_user` varchar(20) DEFAULT NULL COMMENT '系统操作员',
  `sales_person_id` int(11) DEFAULT NULL,
  `sales_person_name` varchar(50) DEFAULT NULL COMMENT '销售人名',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `charge_time` datetime DEFAULT NULL COMMENT '支/汇时间',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  PRIMARY KEY (`charge_id`),
  KEY `create_time_index` (`charge_time`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_charge_refund_2010
-- ----------------------------
DROP TABLE IF EXISTS `tbl_charge_refund_2010`;
CREATE TABLE `tbl_charge_refund_2010` (
  `charge_id` int(11) NOT NULL AUTO_INCREMENT,
  `order` varchar(20) DEFAULT NULL COMMENT '订单号',
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `refund_type` int(11) DEFAULT NULL COMMENT '1.退款 2.退运费',
  `amount` bigint(20) DEFAULT NULL COMMENT '支出/退还金额，单位分',
  `balance` bigint(20) DEFAULT NULL COMMENT '操作后的余额',
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `charge_numer` varchar(40) DEFAULT NULL COMMENT '收(退)款编号',
  `cb_number` varchar(255) DEFAULT NULL COMMENT '支（汇）编',
  `pay_method` int(11) DEFAULT NULL COMMENT '支付方式:1.现金 2 转账 3 授信 4 POS机 5 银行承兑汇票 6 电汇',
  `ticketing_bank` varchar(50) DEFAULT NULL COMMENT '出票银行',
  `opening_bank` varchar(50) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `transactor` varchar(20) DEFAULT NULL COMMENT '处理人(交款/退款人)',
  `user_id` int(11) DEFAULT '0' COMMENT '系统操作员ID',
  `sys_user` varchar(20) DEFAULT NULL COMMENT '系统操作员',
  `sales_person_id` int(11) DEFAULT NULL,
  `sales_person_name` varchar(50) DEFAULT NULL COMMENT '销售人名',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `charge_time` datetime DEFAULT NULL COMMENT '支/汇时间',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  PRIMARY KEY (`charge_id`),
  KEY `create_time_index` (`charge_time`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_charge_refund_2019
-- ----------------------------
DROP TABLE IF EXISTS `tbl_charge_refund_2019`;
CREATE TABLE `tbl_charge_refund_2019` (
  `charge_id` int(11) NOT NULL AUTO_INCREMENT,
  `order` varchar(20) DEFAULT NULL COMMENT '订单号',
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `refund_type` int(11) DEFAULT NULL COMMENT '1.退款 2.退运费',
  `amount` bigint(20) DEFAULT NULL COMMENT '支出/退还金额，单位分',
  `balance` bigint(20) DEFAULT NULL COMMENT '操作后的余额',
  `company_name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `charge_numer` varchar(40) DEFAULT NULL COMMENT '收(退)款编号',
  `cb_number` varchar(255) DEFAULT NULL COMMENT '支（汇）编',
  `pay_method` int(11) DEFAULT NULL COMMENT '支付方式:1.现金 2 转账 3 授信 4 POS机 5 银行承兑汇票 6 电汇',
  `ticketing_bank` varchar(50) DEFAULT NULL COMMENT '出票银行',
  `opening_bank` varchar(50) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `transactor` varchar(20) DEFAULT NULL COMMENT '处理人(交款/退款人)',
  `user_id` int(11) DEFAULT '0' COMMENT '系统操作员ID',
  `sys_user` varchar(20) DEFAULT NULL COMMENT '系统操作员',
  `sales_person_id` int(11) DEFAULT NULL,
  `sales_person_name` varchar(50) DEFAULT NULL COMMENT '销售人名',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `charge_time` datetime DEFAULT NULL COMMENT '支/汇时间',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  PRIMARY KEY (`charge_id`),
  KEY `create_time_index` (`charge_time`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_contract
-- ----------------------------
DROP TABLE IF EXISTS `tbl_contract`;
CREATE TABLE `tbl_contract` (
  `contract_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_corp_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `contract_file_id` int(11) DEFAULT NULL COMMENT '合同文件ID',
  `contract_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '合同时间',
  `sys_company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`contract_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_cust_corp
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_corp`;
CREATE TABLE `tbl_cust_corp` (
  `cust_corp_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT NULL,
  `cust_corp_use_status` int(11) DEFAULT NULL COMMENT '客户启用状态 1.启用 0.禁用',
  `cust_corp_name_short` varchar(100) DEFAULT NULL COMMENT '客户公司名(简写)',
  `cust_corp_name` varchar(100) DEFAULT NULL COMMENT '经办单位',
  `cust_type_id` int(11) DEFAULT NULL COMMENT '客户类别',
  `cust_corp_address` varchar(200) DEFAULT NULL COMMENT '公司地址',
  `cust_corp_code` varchar(40) DEFAULT NULL,
  `cust_corp_tax_number` varchar(20) DEFAULT NULL,
  `cust_corp_corporation` varchar(20) DEFAULT NULL COMMENT '法人',
  `cust_corp_corporation_phone` varchar(20) DEFAULT NULL COMMENT '法人电话',
  `cust_corp_bank` varchar(40) DEFAULT NULL COMMENT '开户银行\n',
  `cust_corp_bank_account` varchar(20) DEFAULT NULL,
  `cust_corp_email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `cust_corp_fax` varchar(20) DEFAULT NULL,
  `cust_corp_phone` varchar(20) DEFAULT NULL,
  `sales_person_id` int(11) DEFAULT NULL COMMENT '销售人id(蜂巢)',
  `sales_person_name` varchar(50) DEFAULT NULL COMMENT '销售人姓名',
  `creater` varchar(50) DEFAULT NULL COMMENT '建档人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `account_balance` bigint(20) DEFAULT '0' COMMENT '余额(单位分)',
  `totol_payment_amount` bigint(11) DEFAULT '0' COMMENT '拉货总支付金额(分)',
  `totol_invoice_amount` bigint(11) DEFAULT '0' COMMENT '发票总金额',
  PRIMARY KEY (`cust_corp_id`),
  KEY `cust_type` (`cust_type_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=483 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_cust_corp_apply
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_corp_apply`;
CREATE TABLE `tbl_cust_corp_apply` (
  `apply_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT NULL,
  `applicant_name` varchar(20) NOT NULL COMMENT '申请人',
  `applicant_phone` varchar(20) NOT NULL COMMENT '申请人电话',
  `cust_corp_name_short` varchar(25) DEFAULT NULL COMMENT '客户公司名(简写)',
  `cust_corp_name` varchar(100) NOT NULL COMMENT '经办单位/公司全称',
  `cust_type_id` int(11) DEFAULT NULL COMMENT '客户类别',
  `cust_corp_address` varchar(500) NOT NULL COMMENT '公司地址',
  `cust_corp_phone` varchar(20) DEFAULT NULL COMMENT '客户电话',
  `cust_corp_tax_number` varchar(20) DEFAULT NULL,
  `cust_corp_bank` varchar(40) DEFAULT NULL COMMENT '开户银行\n',
  `cust_corp_bank_account` varchar(20) DEFAULT NULL,
  `cust_corp_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sales_person_id` int(11) DEFAULT NULL COMMENT '销售人员ID',
  `sales_person_name` varchar(200) DEFAULT NULL COMMENT '销售人员名称',
  `cust_corp_fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `identifier` varchar(50) DEFAULT NULL COMMENT '微信唯一标识',
  `credential` varchar(50) DEFAULT NULL COMMENT '微信token',
  `remark` varchar(200) DEFAULT NULL COMMENT '拒绝/备注',
  `status` int(11) DEFAULT NULL COMMENT '1.申请中 2.已拒绝 3.已通过',
  PRIMARY KEY (`apply_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_cust_corp_apply_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_corp_apply_attachment`;
CREATE TABLE `tbl_cust_corp_apply_attachment` (
  `apply_attachment_id` int(11) NOT NULL,
  `apply_id` int(11) NOT NULL COMMENT '申请表ID',
  `sys_company_id` int(11) DEFAULT NULL COMMENT '系统公司ID',
  `attachment_path` varchar(200) NOT NULL COMMENT '附件文件相对路径',
  `attachment_name` varchar(200) NOT NULL,
  PRIMARY KEY (`apply_attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_cust_corp_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_corp_attachment`;
CREATE TABLE `tbl_cust_corp_attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_corp_id` int(11) NOT NULL COMMENT '主表客户单位ID',
  `sys_company_id` int(11) DEFAULT NULL COMMENT '系统公司ID',
  `attachment_path` varchar(200) NOT NULL COMMENT '附件文件相对路径',
  `attachment_name` varchar(200) NOT NULL,
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_cust_corp_mod_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_corp_mod_record`;
CREATE TABLE `tbl_cust_corp_mod_record` (
  `mod_id` bigint(20) NOT NULL,
  `mod_company_name` varchar(100) DEFAULT NULL,
  `mod_company_address` varchar(200) DEFAULT NULL,
  `mod_company_bank` varchar(40) DEFAULT NULL,
  `mod_company_tax_number` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0: 申请 -1: 拒绝 1: 通过',
  `mod_reply_desc` varchar(255) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人ID',
  `operator_company_id` int(11) DEFAULT NULL COMMENT '申请公司ID',
  `operator_name` varchar(20) DEFAULT NULL COMMENT '操作人 Name',
  PRIMARY KEY (`mod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_cust_corp_queue_level
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_corp_queue_level`;
CREATE TABLE `tbl_cust_corp_queue_level` (
  `cust_corp_id` int(11) NOT NULL,
  `cust_corp_queue_level` int(11) NOT NULL COMMENT '优先级,0最高 1.其次',
  `sys_company_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cust_corp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_cust_modify_apply
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_modify_apply`;
CREATE TABLE `tbl_cust_modify_apply` (
  `cust_corp_id` int(12) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT NULL,
  `cust_corp_name` varchar(25) DEFAULT NULL COMMENT '客户公司名',
  `cust_corp_address` varchar(500) DEFAULT NULL COMMENT '公司地址',
  `cust_corp_tax_number` varchar(20) DEFAULT NULL COMMENT '税号',
  `cust_corp_bank` varchar(50) DEFAULT NULL COMMENT '开户银行',
  `cust_corp_bank_account` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `apply_remark` varchar(50) DEFAULT NULL COMMENT '申请备注',
  `handle_remark` varchar(100) DEFAULT NULL COMMENT '拒绝/备注',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间\n',
  `status` int(1) DEFAULT NULL COMMENT '0 未处理 1 已通过 -1已拒绝 1 已通过 0 未处理 1 已通过 0 未处理 1 已通过  -1已拒绝',
  PRIMARY KEY (`cust_corp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_cust_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_type`;
CREATE TABLE `tbl_cust_type` (
  `sys_company_id` int(11) NOT NULL,
  `cust_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户类别ID',
  `cust_type_level` int(11) NOT NULL COMMENT '客户等级,1-n依次由高到低',
  `cust_type_name` varchar(255) NOT NULL COMMENT '中文名',
  `cust_type_enable` bit(1) DEFAULT NULL COMMENT '是否启用',
  `preffix` varchar(20) DEFAULT NULL COMMENT '前缀',
  `plan_sug_weight` double DEFAULT NULL,
  `plan_sug_weight_float_ratio` double DEFAULT NULL,
  PRIMARY KEY (`cust_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_cust_type_price
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_type_price`;
CREATE TABLE `tbl_cust_type_price` (
  `cust_corp_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '客户ID',
  `year` int(11) NOT NULL DEFAULT '0' COMMENT '年份',
  `product_type_id` int(11) NOT NULL DEFAULT '0',
  `product_type_name` varchar(50) NOT NULL COMMENT '如:散装32.5 ',
  `price` bigint(20) NOT NULL COMMENT '价格，分'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_cust_type_price_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cust_type_price_config`;
CREATE TABLE `tbl_cust_type_price_config` (
  `cust_type_id` int(11) NOT NULL,
  `product_type_id` int(11) NOT NULL,
  `price` bigint(20) DEFAULT NULL COMMENT '分/吨',
  PRIMARY KEY (`cust_type_id`,`product_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_day_limit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_day_limit`;
CREATE TABLE `tbl_day_limit` (
  `product_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `limit_weight` double DEFAULT NULL,
  PRIMARY KEY (`product_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_distribute_not_enough
-- ----------------------------
DROP TABLE IF EXISTS `tbl_distribute_not_enough`;
CREATE TABLE `tbl_distribute_not_enough` (
  `distribute_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT '0' COMMENT '公司ID',
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `product_type_id` int(11) DEFAULT NULL COMMENT '包装类别ID',
  `not_enough_weight` double(20,8) DEFAULT NULL,
  `distribute_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '分配时间',
  PRIMARY KEY (`distribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_driver
-- ----------------------------
DROP TABLE IF EXISTS `tbl_driver`;
CREATE TABLE `tbl_driver` (
  `user_id` int(11) NOT NULL COMMENT '司机对应的用户信息',
  `sys_company_id` int(11) DEFAULT NULL,
  `name` varchar(40) NOT NULL COMMENT '司机名称',
  `plate_number` varchar(20) NOT NULL COMMENT '车牌号',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_file_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_file_info`;
CREATE TABLE `tbl_file_info` (
  `file_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(4000) NOT NULL COMMENT '原始文件名',
  `extend_name` varchar(4000) NOT NULL COMMENT '扩展名',
  `size` bigint(20) NOT NULL DEFAULT '0' COMMENT '文件大小',
  `path` varchar(255) NOT NULL COMMENT '文件路径',
  `refrence_count` int(11) NOT NULL DEFAULT '0' COMMENT '文件被引用次数，为0时会被删除',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_hardware_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_hardware_config`;
CREATE TABLE `tbl_hardware_config` (
  `hardware_config_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) NOT NULL DEFAULT '0' COMMENT '公司ID',
  `hardware_type` varchar(50) NOT NULL COMMENT '硬件类型',
  `hardware_func_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `hardware_func_coding` varchar(250) NOT NULL COMMENT '硬件编码',
  `hardware_func_parameter` varchar(1000) NOT NULL COMMENT '硬件参数',
  `hardware_enable` bit(1) NOT NULL COMMENT '1 启用 0 停用',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`hardware_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_invoice
-- ----------------------------
DROP TABLE IF EXISTS `tbl_invoice`;
CREATE TABLE `tbl_invoice` (
  `invoice_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  `invoice_year_month` varchar(7) DEFAULT NULL COMMENT 'yyyy-MM',
  `invoice_status` int(1) DEFAULT NULL COMMENT '1. 正常 0.作废',
  `invoice_import_method` int(255) DEFAULT NULL COMMENT '1.自动 2.手动',
  `invoice_number` varchar(50) DEFAULT NULL,
  `invoice_company_name` varchar(100) DEFAULT NULL COMMENT '发票企业名称',
  `product_name` varchar(50) DEFAULT NULL COMMENT '规格',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `number` double(20,2) DEFAULT NULL COMMENT '数量',
  `amount` bigint(11) DEFAULT NULL COMMENT '金额（分）',
  `tax_rate` double(20,2) DEFAULT NULL COMMENT '税率',
  `tax` bigint(11) DEFAULT NULL COMMENT '税额(分)',
  `import_time` datetime DEFAULT NULL COMMENT '导入时间',
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `product_type_id` int(11) NOT NULL COMMENT '产品类别ID',
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_license_plate_number
-- ----------------------------
DROP TABLE IF EXISTS `tbl_license_plate_number`;
CREATE TABLE `tbl_license_plate_number` (
  `license_plate_number_id` int(11) NOT NULL AUTO_INCREMENT,
  `license_plate_number` varchar(20) DEFAULT NULL,
  `sys_company_id` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`license_plate_number_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_logger
-- ----------------------------
DROP TABLE IF EXISTS `tbl_logger`;
CREATE TABLE `tbl_logger` (
  `logger_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `logger_type` varchar(50) NOT NULL COMMENT '简称，类别',
  `logger_des` varchar(200) DEFAULT NULL,
  `sys_user_id` int(11) DEFAULT NULL COMMENT '日志操作员ID',
  `sys_user_name` varchar(50) NOT NULL COMMENT '日志操作员名称',
  `logger_create_time` datetime DEFAULT NULL,
  `sys_company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`logger_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_not_distribute
-- ----------------------------
DROP TABLE IF EXISTS `tbl_not_distribute`;
CREATE TABLE `tbl_not_distribute` (
  `not_distribute_id` int(11) NOT NULL,
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `product_type_id` int(11) DEFAULT NULL COMMENT '产品类型ID',
  `not_distribute_time` datetime DEFAULT NULL COMMENT '不分配时间',
  PRIMARY KEY (`not_distribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_operator_mod_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_operator_mod_record`;
CREATE TABLE `tbl_operator_mod_record` (
  `mod_id` bigint(11) NOT NULL,
  `mod_name` varchar(20) DEFAULT NULL,
  `mod_phone` varchar(16) DEFAULT NULL,
  `mod_desc` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `mod_attachment_path` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0: 申请 -1: 拒绝 1: 通过',
  `mod_reply_desc` varchar(255) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `operator_company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`mod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_oprator
-- ----------------------------
DROP TABLE IF EXISTS `tbl_oprator`;
CREATE TABLE `tbl_oprator` (
  `oprator_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作员ID',
  `sys_company_id` int(11) DEFAULT NULL,
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '所属客户',
  `oprator_name` varchar(20) DEFAULT NULL COMMENT '操作员名称',
  `oprator_phone` varchar(20) DEFAULT NULL COMMENT '操作',
  `is_default` int(1) DEFAULT NULL COMMENT '是否默认',
  `identifier` varchar(128) DEFAULT NULL COMMENT '微信唯一标识',
  `credential` varchar(50) DEFAULT NULL COMMENT '微信token',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `use_end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`oprator_id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_oprator_apply
-- ----------------------------
DROP TABLE IF EXISTS `tbl_oprator_apply`;
CREATE TABLE `tbl_oprator_apply` (
  `apply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `sys_company_id` int(11) NOT NULL,
  `cust_corp_id` int(11) NOT NULL COMMENT '所属客户',
  `oprator_id` int(11) DEFAULT NULL COMMENT '默认为0，创建申请1 修改申请 2',
  `oprator_name` varchar(50) NOT NULL COMMENT '操作员名称',
  `oprator_phone` varchar(20) NOT NULL COMMENT '电话',
  `apply_time` datetime NOT NULL,
  `status` int(11) NOT NULL COMMENT '0 未处理 1 已通过 -1已拒绝',
  PRIMARY KEY (`apply_id`,`sys_company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_oprator_apply_history
-- ----------------------------
DROP TABLE IF EXISTS `tbl_oprator_apply_history`;
CREATE TABLE `tbl_oprator_apply_history` (
  `apply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `sys_company_id` int(11) DEFAULT NULL,
  `cust_corp_id` int(11) NOT NULL COMMENT '所属客户',
  `oprator_id` int(11) DEFAULT NULL COMMENT '默认为0，创建申请1 修改申请 2',
  `oprator_name` varchar(50) NOT NULL COMMENT '操作员名称',
  `oprator_phone` varchar(20) NOT NULL COMMENT '电话',
  `apply_time` datetime NOT NULL,
  `status` int(11) NOT NULL COMMENT '0 未处理 1 已通过 -1已拒绝',
  PRIMARY KEY (`apply_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
  `order_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `unit_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '单价',
  `order_number` varchar(40) DEFAULT NULL COMMENT '流水号',
  `sys_company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  `cust_corp_id` int(11) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL COMMENT '0. 待审核 1.等待派车2.执行中3.已完成 4已拒绝 ',
  `apply_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  `pickup_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '提货时间',
  `applay_package_bages` bigint(11) DEFAULT '0' COMMENT '请发袋数，默认0',
  `applay_weight` double(20,8) DEFAULT NULL COMMENT '请发吨',
  `weight` double(20,8) DEFAULT '0.00000000' COMMENT '审核重量',
  `distribute_method` int(11) DEFAULT '0' COMMENT '0 自动分配   1  手动分配 ',
  `create_method` int(11) NOT NULL DEFAULT '0' COMMENT '0.小程序  1.余量分配 2.系统创建',
  `product_type_id` int(11) DEFAULT NULL COMMENT '产品类型',
  `transport_method` int(11) DEFAULT NULL COMMENT '1.物流 2.自提',
  `transport_unit_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '运输价格，单位(分)',
  `transport_company` varchar(255) DEFAULT NULL COMMENT '运输公司',
  `qrcode` varchar(255) DEFAULT NULL COMMENT 'base6字符串,包含ID和本次生成二维码的字符串',
  `review_method` int(11) DEFAULT NULL COMMENT '1.手动审核  2.自动审核',
  `contact` varchar(40) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(40) DEFAULT NULL COMMENT '联系人电话',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '下货联系地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `amount` bigint(11) DEFAULT NULL COMMENT '余额',
  `operator` varchar(255) DEFAULT NULL,
  `pick_up_addr_id` bigint(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `physical_status` int(4) NOT NULL DEFAULT '1' COMMENT '数据状态  -1: 删除 , 其它: 正常',
  PRIMARY KEY (`order_id`),
  KEY `cust` (`cust_corp_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=448 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_peak_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_peak_config`;
CREATE TABLE `tbl_peak_config` (
  `peak_config_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) NOT NULL DEFAULT '0',
  `start_date` datetime NOT NULL COMMENT '开始日期2019-03-01 00:00:00',
  `end_date` datetime NOT NULL COMMENT '结束日期2019-03-29 00:00:00',
  PRIMARY KEY (`peak_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_plan_by_year
-- ----------------------------
DROP TABLE IF EXISTS `tbl_plan_by_year`;
CREATE TABLE `tbl_plan_by_year` (
  `plan_by_year_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_corp_id` int(11) NOT NULL COMMENT '客户ID',
  `year` int(11) NOT NULL COMMENT '年份',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `deposit` int(11) DEFAULT NULL COMMENT '押金，单位分',
  `apply_status` int(1) DEFAULT NULL COMMENT '0.申请中 1.通过申请  -1.已拒绝',
  `excute_status` int(11) DEFAULT '1' COMMENT '1执行中2已作废',
  `day_limit_weight` double(20,8) DEFAULT '0.00000000',
  `apply_plan_weight` double(20,8) DEFAULT '0.00000000' COMMENT '申请计划量',
  `sys_company_id` int(11) DEFAULT NULL,
  `oprator_id` int(11) DEFAULT NULL COMMENT '经办人id',
  `oprator_name` varchar(50) DEFAULT NULL COMMENT '签协议的经办人名称',
  `oprator_phone` varchar(20) DEFAULT NULL COMMENT '经办人电话',
  `balance` int(11) DEFAULT NULL COMMENT '余量',
  `remark` varchar(255) DEFAULT NULL COMMENT '拒绝原因',
  PRIMARY KEY (`plan_by_year_id`)
) ENGINE=MyISAM AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_plan_month_split
-- ----------------------------
DROP TABLE IF EXISTS `tbl_plan_month_split`;
CREATE TABLE `tbl_plan_month_split` (
  `month_split_id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_by_year_id` int(11) NOT NULL,
  `cust_corp_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `year_month` int(11) DEFAULT NULL COMMENT 'yyyy-MM 01 00:00:00',
  `sys_company_id` int(11) DEFAULT NULL,
  `weight` double(20,8) DEFAULT NULL,
  PRIMARY KEY (`month_split_id`)
) ENGINE=MyISAM AUTO_INCREMENT=625 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_printing_settings
-- ----------------------------
DROP TABLE IF EXISTS `tbl_printing_settings`;
CREATE TABLE `tbl_printing_settings` (
  `printing_settings_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT NULL,
  `number` varchar(50) NOT NULL COMMENT '编号',
  `type` varchar(50) NOT NULL COMMENT '类型',
  `template` varchar(100) NOT NULL COMMENT '模板',
  `path` varchar(200) NOT NULL COMMENT '相对路径',
  `remark` varchar(500) NOT NULL,
  PRIMARY KEY (`printing_settings_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_product_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_type`;
CREATE TABLE `tbl_product_type` (
  `product_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '型号类别ID',
  `number` varchar(50) DEFAULT NULL COMMENT '编号',
  `sys_company_id` int(11) DEFAULT NULL,
  `package_type` int(11) DEFAULT NULL COMMENT '1 包装 2 散装',
  `product_type_name` varchar(50) DEFAULT NULL COMMENT '类别名',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品全称',
  `product_short_name` varchar(30) DEFAULT NULL COMMENT '产品简称',
  `preffix` varchar(10) DEFAULT NULL COMMENT '前缀',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `is_enable` bit(1) DEFAULT NULL COMMENT '1.启用 0.停用',
  PRIMARY KEY (`product_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_product_type_lane
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_type_lane`;
CREATE TABLE `tbl_product_type_lane` (
  `product_type_lane_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  `product_type_id` int(11) DEFAULT NULL COMMENT '品种类别',
  `brand_id` int(11) NOT NULL DEFAULT '0',
  `lane_number` varchar(50) DEFAULT NULL COMMENT '车道编号',
  `lane_name` varchar(50) DEFAULT NULL COMMENT '车道名称',
  `package_machine_number` varchar(25) DEFAULT NULL COMMENT '包装机编号',
  `lane_enbale` int(255) DEFAULT NULL COMMENT '1 启用 0 停用',
  `has_measure` int(11) DEFAULT NULL COMMENT '是否有衡1 有0 无',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `update_user_id` int(11) DEFAULT NULL COMMENT '调整人ID',
  `update_user` varchar(20) DEFAULT NULL COMMENT '调整人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '调整时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`product_type_lane_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_queue_truck
-- ----------------------------
DROP TABLE IF EXISTS `tbl_queue_truck`;
CREATE TABLE `tbl_queue_truck` (
  `queue_truck_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT NULL,
  `bill_lading_id` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1排队2呼叫3呼叫超时',
  `product_type_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sign` int(11) DEFAULT NULL COMMENT '重新排队',
  PRIMARY KEY (`queue_truck_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_queue_truck_limit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_queue_truck_limit`;
CREATE TABLE `tbl_queue_truck_limit` (
  `queue_truck_limit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_company_id` int(11) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  `limit_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`queue_truck_limit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_receipt
-- ----------------------------
DROP TABLE IF EXISTS `tbl_receipt`;
CREATE TABLE `tbl_receipt` (
  `sys_company_id` int(11) NOT NULL COMMENT '公司ID',
  `receipt_order` varchar(20) NOT NULL COMMENT '回单号码',
  `receipt_number` varchar(25) NOT NULL COMMENT '编码,规则： yyyyMMddHHmmssReceipt_order',
  `power_level` varchar(50) NOT NULL COMMENT '强度等级',
  `product_type_id` int(11) NOT NULL,
  `receipt_weight` double(20,8) NOT NULL COMMENT '出厂总总量',
  `receipt_factory_time` varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
  `receipt_create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`receipt_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_receipt_2019
-- ----------------------------
DROP TABLE IF EXISTS `tbl_receipt_2019`;
CREATE TABLE `tbl_receipt_2019` (
  `sys_company_id` int(11) NOT NULL COMMENT '公司ID',
  `receipt_order` varchar(20) NOT NULL COMMENT '回单号码',
  `receipt_number` varchar(25) NOT NULL COMMENT '编码,规则： yyyyMMddHHmmssReceipt_order',
  `product_type_id` int(11) NOT NULL,
  `power_level` varchar(50) NOT NULL COMMENT '强度等级',
  `receipt_weight` double(20,8) NOT NULL COMMENT '出厂总总量',
  `receipt_factory_time` varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
  `receipt_create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`receipt_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_receipt_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_receipt_detail`;
CREATE TABLE `tbl_receipt_detail` (
  `sys_company_id` int(11) NOT NULL COMMENT '公司ID',
  `receipt_detail_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '详情ID',
  `receipt_number` varchar(25) NOT NULL COMMENT '指向主表',
  `cust_corp_id` int(11) NOT NULL COMMENT '客户ID',
  `weight` double(20,8) NOT NULL COMMENT '重量',
  `date_of_shipment` varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
  PRIMARY KEY (`receipt_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_receipt_detail_2019
-- ----------------------------
DROP TABLE IF EXISTS `tbl_receipt_detail_2019`;
CREATE TABLE `tbl_receipt_detail_2019` (
  `sys_company_id` int(11) NOT NULL COMMENT '公司ID',
  `receipt_detail_id` bigint(11) NOT NULL COMMENT '详情ID',
  `receipt_number` varchar(25) NOT NULL COMMENT '指向主表',
  `cust_corp_id` int(11) NOT NULL COMMENT '客户ID',
  `weight` double NOT NULL COMMENT '重量',
  `date_of_shipment` varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
  PRIMARY KEY (`receipt_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tbl_receipt_order_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_receipt_order_record`;
CREATE TABLE `tbl_receipt_order_record` (
  `receipt_order` varchar(20) NOT NULL,
  `record_time` datetime NOT NULL COMMENT '记录时间',
  `product_type_id` int(11) NOT NULL,
  `sys_company_id` int(11) DEFAULT '0',
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_review_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_review_config`;
CREATE TABLE `tbl_review_config` (
  `cust_type_id` int(11) NOT NULL,
  `sys_company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cust_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_config`;
CREATE TABLE `tbl_sys_config` (
  `sys_company_id` int(11) NOT NULL COMMENT '公司ID',
  `is_review_enable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否开启审核:1.开启 0.不开启',
  `day_plan_weight` double(20,2) NOT NULL DEFAULT '0.00' COMMENT '余量分配配置用。低于重量限额，默认0',
  `minWeightOfTrunk` double(20,2) NOT NULL DEFAULT '0.00' COMMENT '每车最小量',
  `bagged_call_limit` int(11) DEFAULT '60' COMMENT '袋装呼叫时限（分钟）',
  `bulk_call_limit` int(11) DEFAULT '60' COMMENT '散装呼叫时限（分钟）',
  `queue_call_limit` int(11) DEFAULT '180' COMMENT '排队呼叫时限',
  `storage_enable` bit(1) NOT NULL DEFAULT b'0' COMMENT '暂存是否启用',
  PRIMARY KEY (`sys_company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_sys_config_plan
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_config_plan`;
CREATE TABLE `tbl_sys_config_plan` (
  `sys_company_id` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `plan_Total_Weight` double DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_truck_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_truck_record`;
CREATE TABLE `tbl_truck_record` (
  `truck_record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `plate_number` varchar(20) DEFAULT NULL,
  `identification_code` varchar(50) DEFAULT NULL,
  `truck_status` int(11) DEFAULT NULL COMMENT '0.已绑卡 1.已进入厂2. 已出厂',
  `into_door_time` datetime DEFAULT NULL COMMENT '进入时间',
  `out_door_time` datetime DEFAULT NULL COMMENT '外出时间',
  `sys_company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`truck_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_unloading_address
-- ----------------------------
DROP TABLE IF EXISTS `tbl_unloading_address`;
CREATE TABLE `tbl_unloading_address` (
  `unloading_address_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_company_id` int(11) DEFAULT NULL,
  `cust_corp_id` int(12) NOT NULL COMMENT '客户ID',
  `cust_corp_name` varchar(50) DEFAULT NULL COMMENT '客户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `address_area` varchar(100) NOT NULL COMMENT '区域',
  `address_detail` varchar(100) DEFAULT NULL COMMENT '详情',
  `is_default` bit(1) NOT NULL COMMENT '0 否 1 是',
  `longitude` double(10,5) DEFAULT '0.00000' COMMENT '经度',
  `latitude` double(10,5) DEFAULT '0.00000' COMMENT '纬度',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,按创建时间倒叙排列',
  PRIMARY KEY (`unloading_address_id`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `sys_company_id` int(11) DEFAULT NULL,
  `nickname` varchar(40) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `sex` int(11) DEFAULT '0' COMMENT '0 女 1 男',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_user_autho
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_autho`;
CREATE TABLE `tbl_user_autho` (
  `autho_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_company_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `identity_type` int(11) DEFAULT NULL COMMENT '授权类别 1.微信',
  `identifier` varchar(50) DEFAULT NULL COMMENT '微信Id,手机号，邮箱或其它唯一识别标志',
  `credential` varchar(50) DEFAULT NULL COMMENT '密码,token,凭证等',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`autho_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vehicle`;
CREATE TABLE `tbl_vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `driver_name` varchar(64) DEFAULT NULL,
  `driver_phono` varchar(16) DEFAULT NULL,
  `destination` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Function structure for getBillStatus
-- ----------------------------
DROP FUNCTION IF EXISTS `getBillStatus`;
DELIMITER ;;
CREATE DEFINER=`galileo`@`%` FUNCTION `getBillStatus`(`handle_status` int,`step_flag` int) RETURNS varchar(40) CHARSET utf8mb4
BEGIN
	#Routine body goes here...
	DECLARE statusDes varchar(40);
	SELECT(CASE handle_status
		WHEN 0 THEN
			CASE step_flag
				WHEN 1 THEN '待拉货'
				WHEN 2 THEN '已进一道门'
				WHEN 3 THEN '已开票'
				WHEN 4 THEN '已拒绝'
				WHEN 5 THEN '排队中'
				WHEN 7 THEN '进入水泥门卫'
		  	WHEN 8 THEN '待下料'
				WHEN 9 THEN '下料中'
				WHEN 10 THEN '下料完成'
				WHEN 11 THEN '出二道门'
				WHEN 12 THEN '出水泥门卫'
				WHEN 13 THEN '出一道门'
				WHEN 14 THEN '已审核'
        END
		WHEN 1 THEN '已拒绝'
		WHEN 2 THEN '已完成'
		WHEN 3 THEN '回空车'
		WHEN 4 THEN '回空车（重新拉货）' END ) into statusDes;
	RETURN statusDes;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getPackageType
-- ----------------------------
DROP FUNCTION IF EXISTS `getPackageType`;
DELIMITER ;;
CREATE DEFINER=`galileo`@`%` FUNCTION `getPackageType`(`package_type` int) RETURNS varchar(6) CHARSET utf8mb4
BEGIN
	#Routine body goes here...
	DECLARE package_type_des varchar(6);
	SELECT(CASE package_type
		WHEN 1 THEN '袋装'
		WHEN 2 THEN '散装' END ) into package_type_des;
	RETURN package_type_des;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getTransportMethodDes
-- ----------------------------
DROP FUNCTION IF EXISTS `getTransportMethodDes`;
DELIMITER ;;
CREATE DEFINER=`galileo`@`%` FUNCTION `getTransportMethodDes`(`transport_method` int) RETURNS varchar(6) CHARSET utf8mb4
BEGIN
	#Routine body goes here...
	DECLARE transport_method_des varchar(6);
	SELECT(CASE transport_method
		WHEN 1 THEN '物流'
		WHEN 2 THEN '自提' END ) into transport_method_des;
	RETURN transport_method_des;
END
;;
DELIMITER ;
