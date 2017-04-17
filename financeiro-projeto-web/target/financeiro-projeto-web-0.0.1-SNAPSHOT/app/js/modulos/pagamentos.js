(function(){
	'use-strict';
	
	angular.module('pagamentos',['ngAnimate', 'ngCookies', 'angular-growl'])
	.config(['growlProvider',function (growlProvider){
		growlProvider.globalDisableCountDown(true);
	}])
	
})();