package com.xrafece.do_sport.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xrafece
 */
@Configuration
@MapperScan("com.xrafece.do_sport.mapper")
public class MybatisConfig {
}
