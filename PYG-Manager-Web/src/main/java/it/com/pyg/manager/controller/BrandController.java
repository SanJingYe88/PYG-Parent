package it.com.pyg.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import it.com.pyg.entity.Brand;
import it.com.pyg.sellergoods.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference
	private BrandService brandService;
	
	@RequestMapping("/getAll")
	public List<Brand> getAll(){
		return brandService.getAll();
	}
}
