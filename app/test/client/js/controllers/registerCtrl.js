insigmaApp.controller('registerCtrl', function($scope, $state, comHttp, toaster){
	$scope.aa = "234";
  console.log("form:" +	$scope.myForm);

	//返回按钮
	$scope.register=function(){
		var user = {
				email : $scope.email,
				password : $scope.password
		}
	  comHttp.dopost("woodpecker-user/register", user, function(meta, data, response){
			if (!meta.success) {
				console.log(">>>>>err>>>>>:", meta);
				return
			}
			console.log(">>>>>data:", data);
			$state.go("index.main");
		});
	}
	
});

