(function(){
	'use-strict';
	
	angular.module('recebimentos')
	.controller('recebimentosController',['$scope','$http', '$window', '$cookies', 'growl', recebimentosController]);
	
	function recebimentosController($scope, $http, $window, $cookies, growl){
		carregarPagina();
		
		function carregarPagina(){
			var url = "http://localhost:8080/financeiro/services/conta_receber_pendente";
			$http.get(url,
	    			  {headers:{'Content-Type': "application/json"}})
	    			  .then(function(response){
	    				  $scope.titulos = response.data;
	    			  }
	    	);
			$window.sessionStorage.setItem("baixa", "vazio");
		}
		
		$scope.efetuarBaixa = function(titulo){
			var dadosTitulo = angular.toJson(titulo);
			$window.sessionStorage.setItem("baixa", dadosTitulo)
			window.location.href = "baixas.html";
		}
	}
})();
