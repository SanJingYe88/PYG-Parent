package it.com.pyg.sellergoods.service;

import java.util.List;

import it.com.pyg.bean.PageResult;
import it.com.pyg.bean.Result;

public interface BaseService<T> {

	// 获取所有
	List<T> getAll();

	// 分页获取
	PageResult getByPage(int pageNum, int pageSize);

	// 根据id获取
	Result getById(Long id);

	// 根据id删除
	Result delete(Long id);

	// 批量删除
	Result deleteBatch(Long[] ids);

	// 新增
	Result add(T t);

	// 修改
	Result update(T t);

	// 分页条件查询
	PageResult findPage(T t, int pageNum, int pageSize);
}
