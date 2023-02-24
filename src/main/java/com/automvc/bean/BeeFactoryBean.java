/*
 * Copyright 2016-2021 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.automvc.bean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.teasoft.honey.osql.core.BeeFactory;
import org.teasoft.honey.osql.core.HoneyContext;
//import org.teasoft.spring.boot.config.BeeManageConfig;
import org.teasoft.honey.osql.core.Logger;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author Kingstar
 * @since  1.9
 */

//@Configuration   //just use in test more datasource
//@AutoConfigureAfter(BeeManageConfig.class)   //just use in test more datasource
public class BeeFactoryBean {
	
	public BeeFactoryBean() {
		Logger.info("---------------------BeeFactoryBean--构造方法----");
	}

	@Bean
	public BeeFactory beeFactory() {
		BeeFactory beeFactory = BeeFactory.getInstance();
		try {

			DruidDataSource dataSource1;
			dataSource1 = new DruidDataSource();
			dataSource1.setUrl("jdbc:mysql://localhost:3306/pro?characterEncoding=UTF-8&amp;useSSL=false");
			dataSource1.setUsername("root");
			dataSource1.setPassword("123456");
			dataSource1.init();

			DruidDataSource dataSource2;
			dataSource2 = new DruidDataSource();
			dataSource2.setUrl("jdbc:mysql://localhost:3306/bee?characterEncoding=UTF-8&amp;useSSL=false");
			dataSource2.setUsername("root");
			dataSource2.setPassword("123456");
			dataSource2.init();

			DruidDataSource dataSource3;
			dataSource3 = new DruidDataSource();
			dataSource3.setUrl("jdbc:mysql://localhost:3306/teasoft?characterEncoding=UTF-8&amp;useSSL=false");
			dataSource3.setUsername("root");
			dataSource3.setPassword("123456");
			dataSource3.init();

			Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
			dataSourceMap.put("ds1", dataSource1);
			dataSourceMap.put("ds2", dataSource2); //ds2
			dataSourceMap.put("ds3", dataSource3); //ds3

			beeFactory.setDataSourceMap(dataSourceMap);
			HoneyContext.setConfigRefresh(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return beeFactory;
	}

}
