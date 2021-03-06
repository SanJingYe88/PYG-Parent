 //控制层 
app.controller('typeTemplateController' ,function($scope,$controller,typeTemplateService,brandService,specificationService){	
	
	$controller('baseController',{$scope:$scope});	//继承
	
    //读取列表数据绑定到表单中  
	$scope.getAll = function(){
		typeTemplateService.getAll().success(
			function(response){
				$scope.list = response;
			}			
		);
	}    
	
	//分页
	$scope.getByPage = function(page,size){			
		typeTemplateService.getByPage(page,size).success(
			function(response){
				$scope.list = response.rows;	
				$scope.paginationConf.totalItems = response.total;	//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.getById = function(id){				
		typeTemplateService.getById(id).success(
			function(response){
				$scope.entity = response.data.typeTemplate;
				console.log($scope.entity);
				$scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
				$scope.entity.specIds = JSON.parse($scope.entity.specIds);
				$scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
			}
		);				
	}
	
	//保存 
	$scope.save = function(){	
		var serviceObject;	//服务层对象  				
		if($scope.entity.id != null){	//如果有ID
			serviceObject = typeTemplateService.update($scope.entity); //修改  
		}else{
			serviceObject = typeTemplateService.add($scope.entity);//增加 
		}
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	 
	//批量删除 
	$scope.dele = function(){			
		//获取选中的复选框			
		typeTemplateService.deleteBatch( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};	//定义搜索对象 
	
	//搜索
	$scope.search = function(page,size){			
		typeTemplateService.search(page,size,$scope.searchEntity).success(
			function(response){
				$scope.list = response.rows;	
				$scope.paginationConf.totalItems = response.total;//更新总记录数
			}			
		);
	}
	
	$scope.brandList={data:[]};	//品牌列表
	//读取品牌列表
	$scope.findBrandList = function(){
		brandService.findBrandList().success(
			function(response){
				$scope.brandList = {data:response.data.data};
			}
		);		
	}
	
	$scope.specList={data:[]};//规格列表
	
	//读取规格列表
	$scope.findSpecList=function(){
		specificationService.selectOptionList().success(
				function(response){
					$scope.specList = {data:response.data.data};
				}
		);		
	}
	
	//增加扩展属性行, 需要在弹出窗口的按钮上进行初始化
	$scope.addTableRow = function(){
		$scope.entity.customAttributeItems.push({});
	}
	
	//删除扩展属性行
	$scope.deleTableRow = function(index){
		$scope.entity.customAttributeItems.splice(index,1);
	}
});	
