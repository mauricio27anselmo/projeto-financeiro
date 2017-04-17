(function(){
	'use-strict';
	
	angular.module('relatorios',['ngAnimate', 'ngCookies', 'angular-growl'])
	.config(['growlProvider',function (growlProvider){
		growlProvider.globalDisableCountDown(true);
	}])
	
})();