package it.com.pyg.manager.controller;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;
import it.com.pyg.entity.Brand;
import it.com.pyg.sellergoods.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {

	private static Logger Logger = LogManager.getLogger(BrandController.class);
	
	@Reference
	private BrandService brandService;
	
	//分页获取
	@RequestMapping("/getByPage")
	public PageResult getByPage(Integer page, Integer size){
		Logger.info("page={},size={}",page,size);
		PageResult pageResult = brandService.getByPage(page, size);
		Logger.info("pageResult={}",pageResult);
		return pageResult;
	}
	
	//根据ID获取
	@RequestMapping("/get/{id}")
	public Result getById(@PathVariable(value="id") Long id){
		Logger.info("id={}",id);
		Result result = brandService.getById(id);
		Logger.info("result={}",result);
		return result;
	}
	
	//根据ID删除
	@RequestMapping("/delete/{id}")
	public Result delete(@PathVariable(value="id") Long id){
		Logger.info("id={}",id);
		Result result = brandService.delete(id);
		Logger.info("result={}",result);
		return result;
	}
	
	//批量删除
	@RequestMapping("/deleteBatch")
	public Result deleteBatch(Long[] ids){
		Logger.info("ids={}",Arrays.toString(ids));
		Result result = brandService.deleteBatch(ids);
		Logger.info("result={}",result);
		return result;
	}
	
	//新增
	@PostMapping("/add")
	public Result add(@RequestBody Brand brand){
		Logger.info("brand={}",brand);
		Result result = brandService.add(brand);
		Logger.info("result={}",result);
		return result;
	}
	
	//修改
	@PostMapping("/update")
	public Result update(@RequestBody Brand brand){
		Logger.info("brand={}",brand);
		Result result = brandService.update(brand);
		Logger.info("result={}",result);
		return result;
	}
	
	//条件查询
	@RequestMapping("/search")
	public PageResult search(@RequestBody Brand brand,Integer page, Integer size){
		Logger.info("brand={},page={},size={}",brand,page,size);
		PageResult result = brandService.search(brand,page,size);
		Logger.info("result={}",result);
		return result;
	}
}
