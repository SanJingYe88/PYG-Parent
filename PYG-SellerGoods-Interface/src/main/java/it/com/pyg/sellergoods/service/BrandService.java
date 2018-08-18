package it.com.pyg.sellergoods.service;

import java.util.List;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;
import it.com.pyg.entity.Brand;


public interface BrandService {

	//获取所有
	List<Brand> getAll();
	
	//分页获取
	PageResult getByPage(Integer page,Integer size);

	//根据id获取
	Result getById(Long id);

	//根据id删除
	Result delete(Long id);

	//批量删除
	Result deleteBatch(Long[] ids);

	//新增
	Result add(Brand brand);

	//修改
	Result update(Brand brand);

	//分页条件查询
	PageResult search(Brand brand,Integer page,Integer size);
}
