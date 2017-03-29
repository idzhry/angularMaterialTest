/**
 * INSPINIA - Responsive Admin Theme
 *
 */
var insigmaApp = angular.module('insigma', [
                                            'ui.router',                    // Routing
                                            'oc.lazyLoad',                  // ocLazyLoad
                                            'ui.bootstrap',                 // Ui Bootstrap
                                            'ngStorage',                    // ngStorage
                                            'ngAnimate',                    // ngAnimate
                                            'toaster'
                                            ]);

insigmaApp.config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
                   function ($controllerProvider, $compileProvider, $filterProvider, $provide) {
  //lazy controller, directive and service
	insigmaApp.controller = $controllerProvider.register;
	insigmaApp.directive  = $compileProvider.directive;
	insigmaApp.filter     = $filterProvider.register;
	insigmaApp.factory    = $provide.factory;
	insigmaApp.service    = $provide.service;
	insigmaApp.constant   = $provide.constant;
	insigmaApp.value      = $provide.value;
}]);