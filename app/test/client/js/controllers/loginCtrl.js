insigmaApp.controller('loginCtrl', function($scope, $state, $sessionStorage, comHttp, toaster){

	//登录按钮
	$scope.login=function(){
		var user = {
				email : $scope.email,
				password : $scope.password
		};
	  comHttp.dopost("woodpecker-user/login", user, function(meta, tokenInfo, response){
			if (!meta.success) {
				return false;
			}
			$sessionStorage.tokenInfo = tokenInfo;
			$state.go("index.main");
		});
	}
	
});

