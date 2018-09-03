package it.com.pyg.sellergoods.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;
import it.com.pyg.entity.Brand;
import it.com.pyg.entity.BrandExample;
import it.com.pyg.entity.BrandExample.Criteria;
import it.com.pyg.mapper.BrandMapper;
import it.com.pyg.sellergoods.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	private static Logger Logger = LogManager.getLogger(BrandServiceImpl.class);
	
	@Autowired
	public BrandMapper brandMapper;
	
	@Override
	public List<Brand> getAll() {
		return brandMapper.selectByExample(null);
	}

	@Override
	public PageResult getByPage(Integer page, Integer size) {
		PageHelper.startPage(page,size);
		List<Brand> brands = brandMapper.selectByExample(null);
		Logger.info("brands={}",brands);
		PageInfo<Brand> pageInfo = new PageInfo<>(brands);
		Logger.info("pageInfo={}",pageInfo);
		return new PageResult(pageInfo.getTotal(),pageInfo.getList());
	}

	@Override
	public Result getById(Long id) {
		Brand brand = brandMapper.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brand", brand);
		Result result = Result.success("查询成功", map);
		return result;
	}

	@Override
	public Result delete(Long id) {
		int i = brandMapper.deleteByPrimaryKey(id);
		if(i > 0){
			return Result.success("删除成功", null);
		}
		return Result.fail("删除失败", null);
	}

	@Override
	public Result add(Brand brand) {
		//先查询是否存在该名称的品牌
		BrandExample example = new BrandExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andNameEqualTo(brand.getName());
		List<Brand> list = brandMapper.selectByExample(example);
		if(list.size() > 0){
			Logger.info("{}","该品牌已经存在");
			return Result.fail("该品牌已经存在", null);
		}
		
		//保存到数据库
		int i = brandMapper.insert(brand);
		
		if(i <= 0){
			Logger.info("{}","添加失败");
			return Result.fail("添加失败", null);	
		}
		return Result.success("添加成功", null);
	}

	@Override
	public Result update(Brand brand) {
		//先查找该brand是否存在
		Brand brand2 = brandMapper.selectByPrimaryKey(brand.getId());
		if(brand2 == null){
			Logger.info("{}","该品牌不存在");
			return Result.fail("该品牌不存在", null);
		}
		
		//有选择的修改
		int i = brandMapper.updateByPrimaryKeySelective(brand);
		if(i <= 0){
			Logger.info("{}","修改失败");	
			return Result.fail("修改失败", null);		
		}
		return Result.success("修改成功", null);
	}

	@Override
	public PageResult search(Brand brand,Integer page,Integer size) {
		BrandExample example = new BrandExample();
		Criteria createCriteria = example.createCriteria();
		if(brand != null){
			if(brand.getName() != null && !brand.getName().trim().equals("")){
				createCriteria.andNameEqualTo(brand.getName());
			}
			if(brand.getFirstChar() != null && !brand.getFirstChar().trim().equals("")){
				createCriteria.andFirstCharEqualTo(brand.getFirstChar());
			}
		}
		PageHelper.startPage(page,size);
		List<Brand> list = brandMapper.selectByExample(example);
		PageInfo<Brand> pageInfo = new PageInfo<>(list);
		PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());
		return pageResult;
	}

	@Override
	public Result deleteBatch(Long[] ids) {
		BrandExample example = new BrandExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(Arrays.asList(ids));
		int i = brandMapper.deleteByExample(example );
		if(i <= 0){
			return Result.fail("删除失败", null);
		}
		return Result.success("删除成功", null);
	}
}
