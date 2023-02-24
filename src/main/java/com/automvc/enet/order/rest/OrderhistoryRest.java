/*
 * Copyright 2016-2019 the original author.All rights reserved.
 * Kingstar(aiteasoft@163.com)
 * The license,see the LICENSE file.
 */

package com.automvc.enet.order.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.teasoft.bee.osql.BeeSQLException;
import org.teasoft.beex.osql.SuidRichExt;
import org.teasoft.honey.osql.core.Logger;

import com.automvc.common.jquery.Result;
import com.automvc.enet.order.entity.Orderhistory;
import com.automvc.moreds.RwDsTest;

/**
 * @author AiTeaSoft.com
 * @since  1.0
 * Create on 2019-04-16 11:48:24
 */
@RestController
@RequestMapping("/orderhistory")
public class OrderhistoryRest {
//	@Autowired
//	ObjSQLService objSQLService;

//	@Autowired
//	ObjSQLRichService objSQLRichService;  //会生成两个对象,导致setDataSourceName("ds0")设置的,与获取的,不是一个对象.
	
	
	//Mongodb使用
//	@Resource(name = "mongodbObjSQLRichService")
//	ObjSQLRichService objSQLRichService;
	
//	SuidRich suidRich=BF.getSuidRich();  //这样是可以的.

	//使用非mongodb
//	@Autowired
//	SuidRich suidRich; // 可以
	
	
	//使用mongodb  type1
//	@Autowired
//	@Qualifier(value = "mongoSuidRich")
//	SuidRich suidRich; // 可以
	
//	//使用mongodb  type2
//	@Resource(name = "mongoSuidRich")
//	SuidRich suidRich; // 可以
	
	
	@Autowired
	SuidRichExt suidRichExt; // 可以

	@RequestMapping("/list")
	public Result list(Orderhistory orderhistory, @RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "rows", defaultValue = "20", required = false) int rows) {
		Result result = new Result();
		try {
			
//			HoneyConfig.getHoneyConfig().refreshDataSourceMap();
			
			suidRichExt.setDataSourceName("ds0");
			
			
			int total = suidRichExt.count(orderhistory);
			List<Orderhistory> list = suidRichExt.select(orderhistory, (page - 1) * rows, rows);
//			List<Orderhistory> list = suidRichExt.select(orderhistory, Orderhistory::getName,Orderhistory::getRemark);
			result.setRows(list);
			result.setTotal(total);
		} catch (BeeSQLException e) {
			Logger.error(e.getMessage(),e);
			result.setErrorMsg(e.getMessage());
		}catch (Exception e) {
			Logger.error(e.getMessage(),e);
			result.setErrorMsg(e.getMessage());
		}

		return result;
	}

	@RequestMapping("/testMoreDatasource")
	public Result testMoreDatasource() {
		Result result = new Result();
		RwDsTest.testMoreDatasource();
		result.setMsg("testMoreDatasource");
		return result;
	}

//	@RequestMapping("/add")
//	public Result insert(Orderhistory orderhistory) {
//
//		Result result = new Result();
//		try {
//			int num = objSQLService.insert(orderhistory);
//			result.setTotal(num);
//			if (num <= 0) result.setErrorMsg("insert failed!");
//		} catch (BeeSQLException e) {
//			result.setErrorMsg(e.getMessage());
//		}
//		return result;
//	}
//
//	@RequestMapping("/edit")
//	public Result update(Orderhistory orderhistory) {
//		Result result = new Result();
//		try {
//			int num = objSQLService.update(orderhistory);
//			result.setTotal(num);
//			if (num <= 0) result.setErrorMsg("update failed!");
//		} catch (BeeSQLException e) {
//			result.setErrorMsg(e.getMessage());
//		}
//		return result;
//	}
//
//	@RequestMapping("/del")
//	public Result delete(String ids) {
//		Result result = new Result();
//		try {
//			int num = objSQLRichService.deleteById(Orderhistory.class, ids);
//			result.setTotal(num);
//			if (num <= 0) result.setErrorMsg("delete failed!");
//		} catch (BeeSQLException e) {
//			result.setErrorMsg(e.getMessage());
//		}
//		return result;
//	}

}
