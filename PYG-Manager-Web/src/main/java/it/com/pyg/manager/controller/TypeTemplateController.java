package it.com.pyg.manager.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;
import it.com.pyg.entity.TypeTemplate;
import it.com.pyg.sellergoods.service.TypeTemplateService;

@RestController
@RequestMapping("typeTemplate")
public class TypeTemplateController {
	
	private static Logger Logger = LogManager.getLogger(TypeTemplateController.class);
	
	@Reference
	TypeTemplateService templateService;
	
	@RequestMapping("getAll")
	public List<TypeTemplate> getAll(){
		return templateService.getAll();
	}
	
	@RequestMapping("getByPage")
	public PageResult getByPage(int page, int size) {
		Logger.info("page={},size={}",page,size);
		return templateService.getByPage(page, size);
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Result add(@RequestBody TypeTemplate typeTemplate) {
		Logger.info("typeTemplate={}",typeTemplate);
		return templateService.add(typeTemplate);
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Result update(@RequestBody TypeTemplate typeTemplate) {
		Logger.info("typeTemplate={}",typeTemplate);
		return templateService.update(typeTemplate);
	}
	
	@RequestMapping("/getById")
	public Result getById(Long id){
		Logger.info("id={}",id);
		return templateService.getById(id);		
	}
	
	@RequestMapping("/deleteBatch")
	public Result deleteBatch(Long [] ids){
		Logger.info("ids={}",Arrays.toString(ids));
		return templateService.deleteBatch(ids);
	}
	
	@RequestMapping("/search")
	public PageResult search(@RequestBody TypeTemplate typeTemplate, int page, int size){
		Logger.info("typeTemplate={}",typeTemplate);
		Logger.info("page={}",page);
		Logger.info("size={}",size);
		return templateService.search(typeTemplate, page, size);		
	}
}
