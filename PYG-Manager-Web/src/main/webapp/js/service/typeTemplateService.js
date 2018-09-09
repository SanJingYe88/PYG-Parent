//服务层
app.service('typeTemplateService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.getAll=function(){
		return $http.get('../typeTemplate/getAll.do');		
	}
	//分页 
	this.getByPage=function(page,size){
		return $http.get('../typeTemplate/getByPage.do?page='+page+'&size='+size);
	}
	//查询实体
	this.getById=function(id){
		return $http.get('../typeTemplate/getById.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../typeTemplate/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../typeTemplate/update.do',entity );
	}
	//删除
	this.deleteBatch=function(ids){
		return $http.get('../typeTemplate/deleteBatch.do?ids='+ids);
	}
	//搜索
	this.search=function(page,size,searchEntity){
		return $http.post('../typeTemplate/search.do?page='+page+"&size="+size, searchEntity);
	}    	
});
