package it.com.pyg.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import it.com.pyg.entity.Brand;
import it.com.pyg.mapper.BrandMapper;
import it.com.pyg.sellergoods.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	public BrandMapper brandMapper;
	
	@Override
	public List<Brand> getAll() {
		return brandMapper.selectByExample(null);
	}

}
