(function(){
	'use-strict';
	
	angular.module('contasReceber',['ngAnimate','angular-growl'])
	.config(['growlProvider',function (growlProvider){
		growlProvider.globalDisableCountDown(true);
	}])
	
})();