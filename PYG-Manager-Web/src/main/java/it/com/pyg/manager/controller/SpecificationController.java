package it.com.pyg.manager.controller;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;
import it.com.pyg.entity.Specification;
import it.com.pyg.group.SpecificationGroup;
import it.com.pyg.sellergoods.service.SpecificationService;


@RestController
@RequestMapping("/specification")
public class SpecificationController {

	private static Logger Logger = LogManager.getLogger(SpecificationController.class);
	
	@Reference
	private SpecificationService specificationService;
	
	//获取所有
	@RequestMapping("/findAll")
	public List<Specification> findAll(){			
		return specificationService.findAll();
	}
	
	//分页
	@RequestMapping("/getByPage")
	public PageResult getByPage(Integer page,Integer size){
		Logger.info("page={},size={}",page,size);
		return specificationService.getByPage(page, size);
	}
	
	//新增
	@RequestMapping("/add")
	public Result add(@RequestBody SpecificationGroup specificationGroup){
		Logger.info("specificationGroup={}",specificationGroup);
		return specificationService.add(specificationGroup);
	}
	
	//修改
	@RequestMapping("/update")
	public Result update(@RequestBody SpecificationGroup specificationGroup){
		Logger.info("specificationGroup={}",specificationGroup);
		return specificationService.update(specificationGroup);
	}
	
	//根据主键获取SpecificationGroup
	@RequestMapping("/getById")
	public Result getById(Long id){
		Logger.info("id={}",id);
		return specificationService.getById(id);		
	}
	
	@RequestMapping("/deleteBatch")
	public Result deleteBatch(Long [] ids){
		Logger.info("ids={}",Arrays.toString(ids));
		return specificationService.delete(ids);
	}

	@RequestMapping("/search")
	public PageResult search(@RequestBody Specification specification, int page, int rows){
		Logger.info("specification={}",specification);
		return specificationService.search(specification, page, rows);		
	}
	
//	@RequestMapping("/selectOptionList")
//	public List<Map> selectOptionList(){
//		return specificationService.selectOptionList();
//	}
}
