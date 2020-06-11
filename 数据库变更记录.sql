
#修改铁水工序信息表
ALTER TABLE `ssm`.`wp_iron_info` 
DROP COLUMN `blastOrder`,
ADD COLUMN `desc` INT NULL COMMENT '去向0-未定，1-炼钢，2-铸铁' AFTER `arriveTime`,
CHANGE COLUMN `track` `track` VARCHAR(32) NULL DEFAULT NULL COMMENT '轨道' AFTER `blastNo`,
CHANGE COLUMN `carNo` `carNo` VARCHAR(32) NULL DEFAULT NULL COMMENT '车号' AFTER `track`,
CHANGE COLUMN `tareWeight` `tareWeight` DECIMAL(16,2) NULL DEFAULT NULL COMMENT '皮重' AFTER `ladleNo`,
CHANGE COLUMN `blastNo` `blastNo` VARCHAR(32) NULL DEFAULT NULL COMMENT '高炉炉次' ,
CHANGE COLUMN `ladleNo` `ladleNo` VARCHAR(32) NULL DEFAULT NULL COMMENT '罐号' ,
CHANGE COLUMN `scrabNet` `scrabNet` DECIMAL(16,2) NULL DEFAULT NULL COMMENT '废钢净重' ,
CHANGE COLUMN `netWeight` `netWeight` DECIMAL(16,2) NULL DEFAULT NULL COMMENT '铁水净重为铁毛重减去皮重' ,
CHANGE COLUMN `grossWeight` `scrabGrossWeight` DECIMAL(16,2) NULL DEFAULT NULL COMMENT '废钢毛重为废钢净重与皮重的和' ;

##########################################################
ALTER TABLE `ssm`.`wp_iron_info`
ADD COLUMN `grossWeight` DECIMAL(16,2) NULL COMMENT '铁水毛重' AFTER `netWeight`,
CHANGE COLUMN `scrabGrossWeight` `scrabGrossWeight` DECIMAL(16,2) NULL DEFAULT NULL COMMENT '废钢毛重，为废钢净重与皮重的和' AFTER `scrabNet`,
CHANGE COLUMN `netWeight` `netWeight` DECIMAL(16,2) NULL DEFAULT NULL COMMENT '铁水净重，为铁水毛重减去皮重' ;
