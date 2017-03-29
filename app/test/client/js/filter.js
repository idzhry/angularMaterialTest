/**
 * Created by dis.wangyzh
 */

'use strict';

/**
 * register the http interceptor as a service
 */
insigmaApp.factory('httpFactoryFilters', function ($q, $sessionStorage, $location) {
    var httpInjector = {
        request: function (config) {
            //before request
            if ($sessionStorage.tokenInfo && $sessionStorage.tokenInfo.uid) {
                config.headers['woodpecker-x-token'] = $sessionStorage.tokenInfo.uid;
            }
            return config;
        },
        requestError: function (rejection) {
        	// do something on error
          return $q.reject(rejection);
        },
        response: function (response) {
            //after response
            return response;
        },
        responseError: function (rejection) {
            //do something on error
        	  /* ... */
            return $q.reject(rejection);
        }
    };
    return httpInjector;
});
insigmaApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpFactoryFilters');
}]);