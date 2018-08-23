//前端控制层
app.controller("brandController", function($scope, $controller, brandService) { // 引入brandService
	
	//继承
	$controller('baseController',{$scope:$scope});    

	// 查询实体
	$scope.getById = function(id) {
		brandService.getById(id).success(function(response) {
			console.log(response);
			$scope.entity = response.data.brand;
		});
	}

	// 保存
	$scope.save = function() {
		var serviceObject; // 服务层对象
		if ($scope.entity.id != null) {
			serviceObject = brandService.update($scope.entity);
		} else {
			serviceObject = brandService.add($scope.entity);
		}
		serviceObject.success(function(response) {
			if (response.success) {
				$scope.reloadList();
			} else {
				alert(response.message);
			}
		});
	}

	// 批量删除
	$scope.dele = function() {
		// 获取选中的复选框
		brandService.dele($scope.selectIds).success(function(response) {
			if (response.success) {
				$scope.reloadList();
				$scope.selectIds = [];
			}
		});
	}

	$scope.searchEntity = {};
	// 搜索
	$scope.search = function(page, size) {
		console.log("search");
		console.log($scope.searchEntity);
		brandService.search(page, size, $scope.searchEntity).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;
		});
	}
});