'use strict';
app.config([
  '$stateProvider', '$urlRouterProvider',
    function($stateProvider,$urlRouterProvider) {
      $urlRouterProvider.otherwise('home');

      $stateProvider
          .state('home', {
            url: '/home',
            templateUrl: "./src/main/view/home.html",
            controller: 'HomeCtrl as hc'
          })
          .state('test1', {
            url: '/test1',
            templateUrl: "./src/main/view/test1.html",
            controller: 'Test1Ctrl'
          })
    }
]);

app.run( ['$rootScope', '$state', '$stateParams',
  function ($rootScope,   $state,   $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
  }
]);