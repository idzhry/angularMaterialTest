
/**
 * MainCtrl - controller
 */
insigmaApp.controller('MainCtrl', function($scope, $state, comHttp, toaster){
	
	$scope.userName = 'Example user1';
	$scope.helloText = 'Welcome in SeedProject';
	$scope.descriptionText = 'It is an application skeleton for a typical AngularJS web app. You can use it to quickly bootstrap your angular webapp projects and dev environment for these projects.';
	$scope.user = {};
  
  // 系统初始化方法
  $scope.init = function() {
  	comHttp.dopost("woodpecker-user/userinfo", {}, function(meta, data, response){
  		$scope.user = data;
  	});
  }
  
  // 系统退出
  $scope.logout = function() {
  	comHttp.dopost("woodpecker-user/logout", $scope.user, function(meta, data, response){
  		$state.go("login");
  	});
  }
  
  // 初始化
  $scope.init();
  
});