package it.com.pyg.sellergoods.service.impl;
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
import it.com.pyg.entity.Specification;
import it.com.pyg.entity.SpecificationExample;
import it.com.pyg.entity.SpecificationOption;
import it.com.pyg.entity.SpecificationOptionExample;
import it.com.pyg.entity.SpecificationOptionExample.Criteria;
import it.com.pyg.group.SpecificationGroup;
import it.com.pyg.mapper.SpecificationMapper;
import it.com.pyg.mapper.SpecificationOptionMapper;
import it.com.pyg.sellergoods.service.SpecificationService;

@Service
public class SpecificationServiceImpl implements SpecificationService {

	private static Logger Logger = LogManager.getLogger(SpecificationServiceImpl.class);
	
	@Autowired
	private SpecificationMapper specificationMapper;
	
	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	//获取所有
	@Override
	public List<Specification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	//分页获取
	@Override
	public PageResult getByPage(Integer pageNum, Integer pageSize) {
		Logger.info("pageNum={},pageSize={}",pageNum,pageSize);
		PageHelper.startPage(pageNum, pageSize);
		List<Specification> list = specificationMapper.selectByExample(null);
		Logger.info("list={}",list);
		PageInfo<Specification> pageInfo = new PageInfo<>(list);
		Logger.info("pageInfo={}",pageInfo);
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	@Override
	public Result add(SpecificationGroup specificationGroup) {
		//获取规格实体
		Specification specification = specificationGroup.getSpecification();
		Logger.info("specification={}",specification);
		specificationMapper.insert(specification);	
		Logger.info("specification={}",specification);
		
		//获取规格选项集合
		List<SpecificationOption> specificationOptions = specificationGroup.getSpecificationOptions();
		Logger.info("specificationOptions={}",specificationOptions.toString());
		for(SpecificationOption option:specificationOptions){
			option.setSpecId(specification.getId());		//设置规格ID
			specificationOptionMapper.insert(option);		//新增规格
		}
		
		return Result.success("添加成功", null);
	}

	@Override
	public Result update(SpecificationGroup specificationGroup) {
		// 修改规格实体
		Specification specification = specificationGroup.getSpecification();
		specificationMapper.updateByPrimaryKey(specification);

		// 删除原来规格对应的规格选项
		SpecificationOptionExample example = new SpecificationOptionExample();
		Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(specification.getId());
		specificationOptionMapper.deleteByExample(example);

		// 新增规格选项集合
		List<SpecificationOption> specificationOptions = specificationGroup.getSpecificationOptions();
		for (SpecificationOption option : specificationOptions) {
			option.setSpecId(specification.getId()); 	// 设置规格ID
			specificationOptionMapper.insert(option); 	// 新增规格
		}
		return Result.success("修改成功", null);
	}
	
	//根据主键获取SpecificationGroup
	@Override
	public Result getById(Long id){
		SpecificationGroup specificationGroup = new SpecificationGroup();
		
		//获取规格实体
		Specification specification = specificationMapper.selectByPrimaryKey(id);
		Logger.info("specification={}",specification);
		if(specification == null){
			Logger.info("{}","不存在的记录");
			return Result.fail("不存在的记录", null);
		}
		specificationGroup.setSpecification(specification);
		
		//获取规格选项列表
		SpecificationOptionExample example = new SpecificationOptionExample();
		Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(id);
		List<SpecificationOption> specificationOptions = specificationOptionMapper.selectByExample(example);
		specificationGroup.setSpecificationOptions(specificationOptions);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("specificationGroup", specificationGroup);
		
		Logger.info("map={}",map);
		return Result.success("获取成功", map);//组合实体类
	}

	@Override
	public Result delete(Long[] ids) {
		for(Long id:ids){
			//删除规格表数据
			specificationMapper.deleteByPrimaryKey(id);
			
			//删除规格选项表数据		
			SpecificationOptionExample example=new SpecificationOptionExample();
			Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
		return Result.success("删除成功", null);
	}
	
	
	@Override
	public PageResult search(Specification specification, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		SpecificationExample example = new SpecificationExample();
		it.com.pyg.entity.SpecificationExample.Criteria criteria = example.createCriteria();

		if (specification != null) {
			if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
				criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
			}
		}

		List<Specification> list = specificationMapper.selectByExample(example);
		PageInfo<Specification> pageInfo = new PageInfo<>(list);
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	@Override
	public List<Map<String, Object>> selectOptionList() {
		// TODO Auto-generated method stub
		return null;
	}
}
