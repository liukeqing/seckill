--数据库初始化脚本
--开始创建一个数据库
create database seckill;
--使用数据库
use seckill;
--创建秒杀库存表
create table seckill(
	`seckill_id` bigint not null auto_increment comment '商品库存ID',
	`name` varchar(120) not null comment '商品名称',
	`number` int not null comment '库存数量',
	`start_time` timestamp not null default current_timestamp() comment '秒杀开启的时间',
	`end_time` timestamp not null default current_timestamp() comment '秒杀结束的时间',
	`create_time` timestamp not null default current_timestamp() comment '创建的时间',
	primary key(seckill_id),
	key idx_start_time(seckill_id),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
) engine=InnoDB auto_increment=1000 default charset=utf8 comment='秒杀库存表';
--插入初始化数据
insert into 
	seckill(name,number,start_time,end_time)
values
	('100元秒杀iphone6',100,'2017-6-2 00:00:00','2017-6-3 00:00:00'),
	('500元秒杀ipad2',300,'2017-6-2 00:00:00','2017-6-3 00:00:00'),
	('300元秒杀小米4',100,'2017-6-2 00:00:00','2017-6-3 00:00:00'),
	('200元秒杀红米note',100,'2017-6-2 00:00:00','2017-6-3 00:00:00');
--秒杀成功明细表
create table success_killed(
	`seckill_id` bigint not null comment '秒杀商品id',
	`user_phone` bigint not null comment '用户手机号',
	`state` tinyint not null default -1 comment '状态表示：-1无效；0成功；1已付款',
	`create_time` timestamp not null comment '创建时间',
	primary key (seckill_id,user_phone),/**联合主键**/
	key idx_create_time (create_time)
) engine =InnoDB DEFAULT charset=utf8 comment='秒杀成功明细表';	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	