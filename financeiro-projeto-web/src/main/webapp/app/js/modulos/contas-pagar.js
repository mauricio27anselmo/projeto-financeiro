(function(){
	'use-strict';
	
	angular.module('contasPagar',['ngAnimate','angular-growl'])
	.config(['growlProvider',function (growlProvider){
		growlProvider.globalDisableCountDown(true);
	}])
	
})();