
# 创建车辆信息表
CREATE TABLE vehicle (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,  #自增主键
                         vid VARCHAR(16) NOT NULL COMMENT '车辆识别码',  #唯一标识每辆车，16位随机字符串，通过UUID生成
                         frame_number VARCHAR(50) NOT NULL COMMENT '车架编号',
                         battery_type VARCHAR(20) NOT NULL COMMENT '电池类型：三元电池、铁锂电池',
                         total_mileage DOUBLE DEFAULT 0 COMMENT '总里程(km)',
                         battery_health DOUBLE DEFAULT 100 COMMENT '电池健康状态(%)',
                         UNIQUE KEY uk_vid (vid),
                         UNIQUE KEY uk_frame_number (frame_number)
) COMMENT='车辆信息表';

# 创建规则表
CREATE TABLE rule (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      rule_number VARCHAR(50) NOT NULL COMMENT '规则编号',
                      rule_name VARCHAR(100) NOT NULL COMMENT '规则名称',
                      warning_rule TEXT NOT NULL COMMENT '预警规则',
                      warning_level INT NOT NULL COMMENT '预警等级',
                      battery_type VARCHAR(20) NOT NULL COMMENT '电池类型',
                      INDEX idx_rule_number(rule_number)
) COMMENT='规则表';



-- 创建预警信息表
CREATE TABLE warning (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         vid VARCHAR(16) NOT NULL COMMENT '车辆识别码',
                         rule_code BIGINT NOT NULL COMMENT '触发的规则编号',
                         warning_level INT NOT NULL COMMENT '预警等级',
                         warning_content TEXT NOT NULL COMMENT '预警内容',
                         signal_id BIGINT NOT NULL COMMENT '触发预警的信号ID',
                         status INT DEFAULT 0 COMMENT '处理状态：0-未处理，1-已处理',
#     create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
#     update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         INDEX idx_vid (vid),
                         INDEX idx_warning_level (warning_level),
                         INDEX idx_signal_id (signal_id),
                         INDEX idx_status (status)
) COMMENT='预警信息表';

-- 状态0（未处理）2025年5月表
CREATE TABLE battery_signal_0_202505 (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         car_id BIGINT NOT NULL COMMENT '车辆ID',
                                         battery_type VARCHAR(50) COMMENT '电池类型',
                                         max_voltage DOUBLE COMMENT '最高电压 Mx',
                                         min_voltage DOUBLE COMMENT '最小电压 Mi',
                                         max_current DOUBLE COMMENT '最高电流 Ix',
                                         min_current DOUBLE COMMENT '最小电流 Ii',
                                         status INT DEFAULT 0 COMMENT '信号状态：0-未处理',
                                         create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         INDEX idx_car_id (car_id),
                                         INDEX idx_status (status),
                                         INDEX idx_create_time (create_time)
) COMMENT='未处理电池信号表-2025年5月';

-- 状态1（已处理）2025年5月表
CREATE TABLE battery_signal_1_202505 (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         car_id BIGINT NOT NULL COMMENT '车辆ID',
                                         battery_type VARCHAR(50) COMMENT '电池类型',
                                         max_voltage DOUBLE COMMENT '最高电压 Mx',
                                         min_voltage DOUBLE COMMENT '最小电压 Mi',
                                         max_current DOUBLE COMMENT '最高电流 Ix',
                                         min_current DOUBLE COMMENT '最小电流 Ii',
                                         status INT DEFAULT 1 COMMENT '信号状态：1-已处理',
                                         create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         INDEX idx_car_id (car_id),
                                         INDEX idx_status (status),
                                         INDEX idx_create_time (create_time)
) COMMENT='已处理电池信号表-2025年5月';
