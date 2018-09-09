package it.com.pyg.sellergoods.service;

import java.util.List;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;
import it.com.pyg.entity.Specification;
import it.com.pyg.group.SpecificationGroup;

public interface SpecificationService {

	List<Specification> findAll();

	PageResult getByPage(Integer pageNum, Integer pageSize);

	Result add(SpecificationGroup specificationGroup);

	Result update(SpecificationGroup specificationGroup);

	Result getById(Long id);

	Result delete(Long[] ids);

	PageResult search(Specification specification, Integer pageNum, Integer pageSize);
	
	//获取规格选项
	Result selectOptionList();
}
