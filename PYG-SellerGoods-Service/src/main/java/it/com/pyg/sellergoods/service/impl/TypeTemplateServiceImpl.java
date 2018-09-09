package it.com.pyg.sellergoods.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;
import it.com.pyg.entity.TypeTemplate;
import it.com.pyg.entity.TypeTemplateExample;
import it.com.pyg.entity.TypeTemplateExample.Criteria;
import it.com.pyg.mapper.TypeTemplateMapper;
import it.com.pyg.sellergoods.service.TypeTemplateService;

/*
 * 模板管理
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
	
	private static Logger Logger = LogManager.getLogger(TypeTemplateServiceImpl.class);
	
	@Autowired
	private TypeTemplateMapper typeTemplateMapper;

	@Override
	public List<TypeTemplate> getAll() {
		return typeTemplateMapper.selectByExample(null);
	}

	@Override
	public PageResult getByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TypeTemplate> list = typeTemplateMapper.selectByExample(null);
		PageInfo<TypeTemplate> pageInfo = new PageInfo<>(list);
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	@Override
	public Result add(TypeTemplate typeTemplate) {
		int i = typeTemplateMapper.insert(typeTemplate);
		if (i <= 0) {
			Logger.info("{}", "添加失败");
			return Result.fail("添加失败", null);
		}
		return Result.success("添加成功", null);
	}

	@Override
	public Result update(TypeTemplate typeTemplate) {
		int i = typeTemplateMapper.updateByPrimaryKey(typeTemplate);
		if(i <= 0){
			Logger.info("{}","修改失败");	
			return Result.fail("修改失败", null);		
		}
		return Result.success("修改成功", null);
	}

	@Override
	public Result getById(Long id) {
		TypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
		Logger.info("typeTemplate={}", typeTemplate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeTemplate", typeTemplate);
		Result result = Result.success("查询成功", map);
		return result;
	}

	@Override
	public Result delete(Long id) {
		int i = typeTemplateMapper.deleteByPrimaryKey(id);
		if(i > 0){
			return Result.success("删除成功", null);
		}
		return Result.fail("删除失败", null);
	}

	@Override
	public Result deleteBatch(Long[] ids) {
		TypeTemplateExample example = new TypeTemplateExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(Arrays.asList(ids));
		int i = typeTemplateMapper.deleteByExample(example );
		if(i <= 0){
			return Result.fail("删除失败", null);
		}
		return Result.success("删除成功", null);
	}

	@Override
	public PageResult search(TypeTemplate typeTemplate, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TypeTemplateExample example = new TypeTemplateExample();
		Criteria criteria = example.createCriteria();

		if (typeTemplate != null) {
			if (typeTemplate.getName() != null && typeTemplate.getName().length() > 0) {
				criteria.andNameLike("%" + typeTemplate.getName() + "%");
			}
			if (typeTemplate.getSpecIds() != null && typeTemplate.getSpecIds().length() > 0) {
				criteria.andSpecIdsLike("%" + typeTemplate.getSpecIds() + "%");
			}
			if (typeTemplate.getBrandIds() != null && typeTemplate.getBrandIds().length() > 0) {
				criteria.andBrandIdsLike("%" + typeTemplate.getBrandIds() + "%");
			}
			if (typeTemplate.getCustomAttributeItems() != null && typeTemplate.getCustomAttributeItems().length() > 0) {
				criteria.andCustomAttributeItemsLike("%" + typeTemplate.getCustomAttributeItems() + "%");
			}
		}
		List<TypeTemplate> list = typeTemplateMapper.selectByExample(example);
		Logger.info("list={}",list);
		Page<TypeTemplate> page = (Page<TypeTemplate>) list;
		return new PageResult(page.getTotal(), page.getResult());
	}
}
