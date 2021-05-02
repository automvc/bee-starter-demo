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
import org.teasoft.bee.osql.service.ObjSQLRichService;
import org.teasoft.bee.osql.service.ObjSQLService;
import org.teasoft.beex.poi.ExcelReader;
import org.teasoft.beex.util.CnNum;
import org.teasoft.honey.osql.core.HoneyConfig;

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
	@Autowired
	ObjSQLService objSQLService;

	@Autowired
	ObjSQLRichService objSQLRichService;

	@RequestMapping("/list")
	public Result list(Orderhistory orderhistory, @RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "rows", defaultValue = "20", required = false) int rows) {
		Result result = new Result();
		try {
			
			System.err.println(CnNum.tranToUpper(123.143D));
			
			System.err.println("测试配置文件中,key不用'-'也可以,但与spring整合的,要用'-'");
			System.err.println(HoneyConfig.getHoneyConfig().multiDS_type);
			
			String fullPath = "D:\\test-dataType.xlsx";
			String[] checkTitles= {"序号","班级","姓名","离深时间	","目的地","离深交通工具","返深时间","返深交通工具","家长联系电话"};
			List<String[]> list0 = ExcelReader.readExcel(fullPath);
			String col[] = null;
			for (int i = 0; list0!=null && i < list0.size(); i++) {
				col = list0.get(i);
				
				for (int j = 0; j < col.length; j++) {
					System.out.print(col[j] + "   ");
				}
			}
			System.out.println();
			
			int total = objSQLRichService.count(orderhistory);
			List<Orderhistory> list = objSQLRichService.select(orderhistory, (page - 1) * rows, rows);
			result.setRows(list);
			result.setTotal(total);
		} catch (BeeSQLException e) {
			System.err.println(e.getMessage());
			result.setErrorMsg(e.getMessage());
		}catch (Exception e) {
			System.err.println(e.getMessage());
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

	@RequestMapping("/add")
	public Result insert(Orderhistory orderhistory) {

		Result result = new Result();
		try {
			int num = objSQLService.insert(orderhistory);
			result.setTotal(num);
			if (num <= 0) result.setErrorMsg("insert failed!");
		} catch (BeeSQLException e) {
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/edit")
	public Result update(Orderhistory orderhistory) {
		Result result = new Result();
		try {
			int num = objSQLService.update(orderhistory);
			result.setTotal(num);
			if (num <= 0) result.setErrorMsg("update failed!");
		} catch (BeeSQLException e) {
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/del")
	public Result delete(String ids) {
		Result result = new Result();
		try {
			int num = objSQLRichService.deleteById(Orderhistory.class, ids);
			result.setTotal(num);
			if (num <= 0) result.setErrorMsg("delete failed!");
		} catch (BeeSQLException e) {
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

}
