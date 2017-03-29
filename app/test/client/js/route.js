'use strict';

/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
  $urlRouterProvider.otherwise("/login");

  $ocLazyLoadProvider.config({
    // Set to true if you want to see what and when is dynamically loaded
    debug: true
  });

  $stateProvider

    .state('index', {
      abstract: true,
      url: "/index",
      templateUrl: "views/common/content.html",
      resolve: {
        loadPlugin: function ($ocLazyLoad) {
          return $ocLazyLoad.load([
            {
              files: ['js/controllers/index.js']
            }
          ]);
        }
      }
    })
    .state('index.main', {
      url: "/main",
      templateUrl: "views/main.html",
      data: {pageTitle: 'Example view'}
    })
    .state('index.attendanceList', {
      url: "/attendanceList",
      templateUrl: "views/attendanceList.html",
      data: {pageTitle: 'Example view'}
    })
    .state('index.monthlyAttendance', {
      url: "/monthlyAttendance",
      templateUrl: "views/monthlyAttendance.html",
      data: {pageTitle: 'Example view'}
    })
    .state('index.fillingAttendance', {
      url: "/fillingAttendance",
      templateUrl: "views/fillingAttendance.html",
      data: {pageTitle: 'Example view'}
    })
    .state('index.newProject', {
      url: "/newProject",
      templateUrl: "views/newProject.html",
      data: {pageTitle: 'Example view'}
    })
    .state('index.projectList', {
      url: "/projectList",
      templateUrl: "views/projectList.html",
      data: {pageTitle: 'Example view'}
    })
    .state('index.staffList', {
      url: "/staffList",
      templateUrl: "views/staffList.html",
      data: {pageTitle: 'Example view'},
      resolve: {
        loadPlugin: function ($ocLazyLoad) {
          return $ocLazyLoad.load([
            {
              files: ['css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css']
            }
          ]);
        }
      }
    })
    .state('index.staffDetail', {
      url: "/staffDetail",
      templateUrl: "views/staffDetail.html",
      data: {pageTitle: 'Example view'},
      resolve: {
        loadPlugin: function ($ocLazyLoad) {
          return $ocLazyLoad.load([
            {
              files: ['css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css']
            }
          ]);
        }
      }
    })
    .state('index.attendanceRecognition', {
      url: "/attendanceRecognition",
      templateUrl: "views/attendanceRecognition.html",
      data: {pageTitle: 'Example view'},
      resolve: {
        loadPlugin: function ($ocLazyLoad) {
          return $ocLazyLoad.load([
            {
              files: ['css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css']
            }
          ]);
        }
      }
    })
    .state('index.minor', {
      url: "/minor",
      templateUrl: "views/minor.html",
      data: {pageTitle: 'Example view'}
    })
    .state('login', {
      url: "/login",
      templateUrl: "views/login.html",
      data: {pageTitle: 'Login', specialClass: 'gray-bg'},
      resolve: {
        loadPlugin: function ($ocLazyLoad) {
          return $ocLazyLoad.load([
            {
              files: ['js/controllers/loginCtrl.js']
            }
          ]);
        }
      }
    })
    .state('register', {
			url : "/register",
			templateUrl : "views/register.html",
			data : {pageTitle : 'Register', specialClass : 'gray-bg'},
			resolve: {
        loadPlugin: function ($ocLazyLoad) {
          return $ocLazyLoad.load([
            {
              files: ['js/controllers/registerCtrl.js']
            }
          ]);
        }
      }
		})
    .state('404', {
      url: "/404",
      templateUrl: "views/common/404.html",
      data: {pageTitle: '404', specialClass: 'gray-bg'}
    })
    .state('500', {
      url: "/500",
      templateUrl: "views/common/500.html",
      data: {pageTitle: '500', specialClass: 'gray-bg'}
    })
}
insigmaApp.config(config).run(function ($rootScope, $state) {
    $rootScope.$state = $state;
});
