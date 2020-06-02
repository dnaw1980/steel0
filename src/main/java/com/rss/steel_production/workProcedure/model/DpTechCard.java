package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 工艺卡
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dp_tech_card")
public class DpTechCard {
	/*
	drop table if exists dp_tech_card;

	create table dp_tech_card
			(
	tech_card_id         varchar(36) not null comment '工艺卡ID',
	tech_card_nm         varchar(64) not null comment '产品名称',
	steel_brand          varchar(64) comment '钢牌号',
	steel_class          varchar(64) comment '钢种号',
	model                varchar(128) comment '规格',
	note                 text comment '备注',
	gmt_create           timestamp not null default CURRENT_TIMESTAMP comment '插入时间',
	gmt_modified         timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
	lock_key             bigint not null default 0 comment '乐观锁键',
	is_del               tinyint not null default 0 comment '是否删除 0:未删除; 1:已删除',
	primary key (tech_card_id)
);

	alter table dp_tech_card comment '工艺卡';
	 */

	//tech_card_id         varchar(36) not null comment '工艺卡ID',
	@Id
	@Column(name="tech_card_id")
	private String techCardId;

	//tech_card_nm         varchar(64) not null comment '产品名称',
	@Column(name="tech_card_nm")
	private String techCardNm;

	//steel_brand          varchar(64) comment '钢牌号',
	@Column(name="steel_brand")
	private String steelBrand;

	//steel_class          varchar(64) comment '钢种号',
	@Column(name="steel_class")
	private String steelClass;

	//model                varchar(128) comment '规格',
	@Column(name="model")
	private String model;

	//note                 text comment '备注',
	@Column(name="note")
	private String note;

	/**
	 * 工艺路径列表
	 */
	private List<DpTechRoute> techRouteList;

}

