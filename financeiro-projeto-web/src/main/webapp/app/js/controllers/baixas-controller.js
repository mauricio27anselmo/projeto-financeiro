(function(){
	'use-strict';
	
	angular.module('baixas')
	.controller('baixasController',['$scope','$http', '$window', '$cookies', 'growl', baixasController]);
	
	function baixasController($scope, $http, $window, $cookies, growl){
		var baixa = $window.sessionStorage.getItem("baixa");
		
		if (baixa != "vazio") {
			$scope.taxas = {};
			$scope.taxas.porcentagemJuros = 0;
			var dadosBaixa = angular.fromJson(baixa);
			$scope.baixas = dadosBaixa;
			$scope.baixas.dataCriacaoTitulo = new Date($scope.baixas.dataCriacaoTitulo);
			$scope.baixas.dataCriacaoTitulo.setDate($scope.baixas.dataCriacaoTitulo.getDate() + 1);
			$scope.baixas.dataVencimentoTitulo = new Date($scope.baixas.dataVencimentoTitulo);
			$scope.baixas.dataVencimentoTitulo.setDate($scope.baixas.dataVencimentoTitulo.getDate() + 1);
		}
		
		$scope.calcularValorDesconto = function(){
			var porcentagem =  $scope.taxas.porcentagemDesconto;
			if(porcentagem != null){
				var desconto = {
						porcentagemDesconto : porcentagem,
						valorTitulo : $scope.baixas.valorTitulo
				}
				var url = "http://localhost:8080/financeiro/services/baixa/descontos";
				$http.put(url, desconto, 
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  $scope.baixas.valorDescontoTitulo = response.data.valorDesconto;
		    			  }
		    	);
			}
				
		}

		$scope.calcularValorJuros = function(){
			if ($scope.baixas.dataPagamentoTitulo != null && $scope.baixas.dataPagamentoTitulo != "") {
				var taxas = {
					dataPagamento : $scope.baixas.dataPagamentoTitulo,
					dataVencimento : $scope.baixas.dataVencimentoTitulo,
					valorTitulo : $scope.baixas.valorTitulo,
					porcentagemJuros : $scope.taxas.porcentagemJuros
				}
				var url = "http://localhost:8080/financeiro/services/baixa/juros";
				$http.put(url, taxas, 
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  $scope.baixas.valorJurosCalculadoTitulo = response.data.valorJurosCalculado;
		    				  $scope.taxas.valorJurosSugeridoTitulo = response.data.valorJurosSugerido;
		    			  }
		    	);
			}
		}
		
		$scope.registrarBaixa = function(baixas){
			console.log(baixas);
			$scope.baixas = baixas;
			var url = "http://localhost:8080/financeiro/services/baixa/confirmacao";
			$http.put(url, $scope.baixas, 
	    			  {headers:{'Content-Type': "application/json"}})
	    			  .then(function(response){
	    				  if(response.data.sucesso){
	    					  growl.success(response.data.resposta.bold(), {ttl : 2000});
	    					  window.location.href = "/index.html";
	    				  }else{
	    					  growl.error(response.data.resposta.bold(), {ttl : 5000});
	    				  }
	    			  }
	    	);
		}
	}
})();
