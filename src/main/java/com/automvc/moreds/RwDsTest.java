/*
 * Copyright 2016-2020 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.automvc.moreds;

import org.teasoft.bee.osql.Condition;
import org.teasoft.bee.osql.DatabaseConst;
import org.teasoft.bee.osql.SuidRich;
import org.teasoft.bee.osql.transaction.Transaction;
import org.teasoft.honey.osql.core.BeeFactory;
import org.teasoft.honey.osql.core.BeeFactoryHelper;
import org.teasoft.honey.osql.core.ConditionImpl;
import org.teasoft.honey.osql.core.HoneyConfig;
import org.teasoft.honey.osql.core.HoneyContext;
import org.teasoft.honey.osql.core.Logger;
import org.teasoft.honey.osql.core.SessionFactory;

/**
 * @author Kingstar
 * @since  1.8
 */
public class RwDsTest {

	private static SuidRich suidRich = BeeFactoryHelper.getSuidRich();
	
	
	public static void testMoreDatasource() {
		test();
	}

	private static boolean isMysql() {
		return DatabaseConst.MYSQL.equalsIgnoreCase(HoneyContext.getDbDialect());
	}

	public static void test() {
		
		HoneyConfig.getHoneyConfig().multiDS_enable = true;
		HoneyConfig.getHoneyConfig().multiDS_type = 1;
		HoneyConfig.getHoneyConfig().multiDS_defalutDS = "ds1";
		HoneyConfig.getHoneyConfig().multiDS_writeDB = "ds1";
		HoneyConfig.getHoneyConfig().multiDS_readDB = "ds2,ds3";
		HoneyContext.setConfigRefresh(true);
		
	    String  oldDbName=HoneyConfig.getHoneyConfig().getDbName();
	    int oldTranslateTyp=HoneyConfig.getHoneyConfig().naming_translateType;
	    
		HoneyConfig.getHoneyConfig().setDbName(DatabaseConst.MYSQL);
		HoneyConfig.getHoneyConfig().naming_translateType=1;
//		if (isMysql()) initDS();
		
//		if (isMysql()) {


			test1();
			test2();
			
			HoneyConfig.getHoneyConfig().multiDS_enable = false;
			HoneyConfig.getHoneyConfig().multiDS_type = 0;
			HoneyConfig.getHoneyConfig().multiDS_defalutDS = null;
			HoneyConfig.getHoneyConfig().multiDS_writeDB = null;
			HoneyConfig.getHoneyConfig().multiDS_readDB = null;
			BeeFactory.getInstance().setDataSourceMap(null);
			HoneyContext.setConfigRefresh(true);
//		}
		HoneyConfig.getHoneyConfig().setDbName(oldDbName);
		HoneyConfig.getHoneyConfig().naming_translateType=oldTranslateTyp;
		HoneyContext.setConfigRefresh(true);
		
	}


	public static void test1() {

		LeafAlloc leafAlloc = new LeafAlloc();
		leafAlloc.setBizTag("bee");
		Condition condition = new ConditionImpl();
		condition.setAdd("maxId", "step");
		int num = suidRich.update(leafAlloc, "maxId", condition);
		Logger.info("---------------------------------update num is :" + num);

		LeafAlloc result = suidRich.selectOne(leafAlloc);
		if (result != null) Logger.info(result.toString());

		result = suidRich.selectOne(leafAlloc);
		if (result != null) Logger.info(result.toString());
		
		suidRich.select(leafAlloc,0,10);
	}

	public static void test2() {
		LeafAlloc result = null;
		Transaction transaction = SessionFactory.getTransaction();
		try {
			transaction.begin();

			LeafAlloc leafAlloc = new LeafAlloc();
			leafAlloc.setBizTag("bee");
			Condition condition = new ConditionImpl();
			condition.setAdd("maxId", "step");
//		    suidRich.update(leafAlloc, "maxId", condition);
			suidRich.update(leafAlloc, condition); //v1.8

			result = suidRich.selectOne(leafAlloc);
			if(result!=null) Logger.info(result.toString());
			
			suidRich.select(leafAlloc,0,10);

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

}
