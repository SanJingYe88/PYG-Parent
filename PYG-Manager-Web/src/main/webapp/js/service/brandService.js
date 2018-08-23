//品牌服务层
app.service('brandService',function($http){
	//分页获取
	this.getByPage = function(page,rows){
		return $http.get('../brand/getByPage.do?page=' + page + '&size=' + size);
	}
	//查询实体
    this.getById = function(id){
        return $http.get('../brand/get/' + id + ".do");
    }
	//新增
    this.add = function(entity){
        return $http.post('../brand/add.do',entity);
    }
	//修改
    this.update = function(entity){
        return $http.post('../brand/update.do',entity);
    }
	//删除
    this.dele = function(ids){
        return $http.get('../brand/deleteBatch.do?ids=' + ids);
    }
	//搜索
	this.search = function(page,size,searchEntity){
		return $http.post('../brand/search.do?page='+page+"&size="+size, searchEntity);
	}  
});