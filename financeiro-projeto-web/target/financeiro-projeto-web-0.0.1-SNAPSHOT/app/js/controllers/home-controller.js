(function(){
	'use-strict';
	
	angular.module('home')
	.controller('homeController',['$scope', homeController]);
	
	function homeController($scope){
		console.log('Funcionou!');
	}
})();