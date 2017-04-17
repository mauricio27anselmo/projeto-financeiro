(function(){
	'use-strict';
	
	angular.module('recebimentos',['ngAnimate', 'ngCookies', 'angular-growl'])
	.config(['growlProvider',function (growlProvider){
		growlProvider.globalDisableCountDown(true);
	}])
	
})();