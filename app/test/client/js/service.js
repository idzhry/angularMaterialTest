/**
 * create by dis.wangyzh
 */

'use strict';

/**
 * http service
 */
insigmaApp.service("comHttp", function($http, $location, toaster){
	
	var pathName = window.location.pathname.substring(1);   
	var hostName = pathName == "" ? "" : pathName.substring(0, pathName.indexOf("/"));
	var urlPrefix = hostName == "" ? "/api/" : "/" + hostName + "/api/";
	
	/**
	 * get request
	 */
	this.doget = function (url, calback, config) {
		return $http.get(urlPrefix + url, config).then(
			function successCallback(response) {
			console.log("do ajax " + url + "  success");
			// business
			/* ... */
			calback(response.data.meta, response.data.data, response);
		}, function errorCallback(response) {
			// business
			/* ... */
			console.log("do ajax " + url + "  error");
			calback(response.data.meta);
		});
	}
	
	/**
	 * post request
	 */
	this.dopost = function (url, data, calback, config) {
		return $http.post(urlPrefix + url, data, config).then(
			function successCallback(response) {
				console.log("do ajax " + url + "  success");
			  //business
				var data = response.data;
				if (data) {
          var messageString = "";
          if (!data.meta.success) {
            if (data.meta.message) {
              messageString = data.meta.message + "<br />";
            }

            if (data.validateErrorList && data.validateErrorList.length > 0) {
              for ( var i in data.validateErrorList) {
                messageString += data.validateErrorList[i].validateMessage
                    + "<br />";
              }
            }
            toaster.pop('error', '错误', messageString, null, 'trustedHtml');
          } else {
          	calback(response.data.meta, response.data.data, response);
          }
        }
			}, function errorCallback(response) {
			  //business
				/* ... */
				console.log("do ajax " + url + "  error");
				if (response.status == 404) {
					$location.path("/404");
					return;
				}
				else if (response.status == 500) {
					$location.path("/500");
					return;
				}
				calback(response.data.meta);
			}
		);
	}
	
	/**
	 * head request
	 */
	this.dohead = function (url, fun, config) {
		return $http.head(urlPrefix + url, config).then(
			function successCallback(response) {
				console.log("do ajax " + url + "  success");
			  //business
				/* ... */
				fun(response.data.meta, response.data.data, response);
			}, function errorCallback(response) {
			  //business
				/* ... */
				console.log("do ajax " + url + "  error");
				fun(response.data.meta);
			}
		);
	}
	
//	/**
//	 * delete request
//	 */
//	this.dodelete = function (url, fun, config) {
//		return $http.delete(url, config).then(
//			function successCallback(response) {
//				console.log("do ajax " + url + "  success");
//			  //business
//				/* ... */
//				fun(response.data.meta, response.data.data, response);
//			}, function errorCallback(response) {
//			  //business
//				/* ... */
//				console.log("do ajax " + url + "  error");
//				fun(response.data.meta);
//			}
//		);
//	}
	
	/**
	 * put request
	 */
	this.doput = function (url, data, fun, config) {
		return $http.put(urlPrefix + url, data, config).then(
			function successCallback(response) {
				console.log("do ajax " + url + "  success");
			  //business
				/* ... */
				fun(response.data.meta, response.data.data, response);
			}, function errorCallback(response) {
			  //business
				/* ... */
				console.log("do ajax " + url + "  error");
				fun(response.data.meta);
			}
		);
	}
	
});