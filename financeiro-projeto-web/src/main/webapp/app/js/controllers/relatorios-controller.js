(function(){
	'use-strict';
	
	angular.module('relatorios')
	.controller('relatoriosController',['$scope','$http', '$window', '$cookies', 'growl', relatoriosController]);
	
	function relatoriosController($scope, $http, $window, $cookies, growl){
		$scope.dadosRelatorio;
		
		$scope.listarContasReceber = function(filtroContasReceber){
			$scope.filtroContasReceber = filtroContasReceber;
			if($scope.filtroContasReceber != null && $scope.filtroContasReceber.listagem != null
					&& $scope.filtroContasReceber.ordenacao != null){
				var url = "http://localhost:8080/financeiro/services/conta_receber/relatorio";
				$http.put(url, $scope.filtroContasReceber,
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  if(response.data != null){
		    					  $scope.dadosRelatorio = response.data;
		    				  }else{
		    					  var mensagemErro = "Não foram encontrados registros para os parâmetros de pesquisa informados!";
	    						  growl.error(mensagemErro.bold(), {ttl : 5000});
		    				  }
		    				 
		    			  }
		    	);
			}else{
				var mensagemAviso = "Os filtros devem ser informados para consulta!";
				growl.warning(mensagemAviso.bold(), {ttl : 5000});
			}
		}
		
		$scope.listarContasPagar = function(filtroContasPagar){
			$scope.filtroContasPagar = filtroContasPagar;
			if($scope.filtroContasPagar != null && $scope.filtroContasPagar.listagem != null
					&& $scope.filtroContasPagar.ordenacao != null){
				var url = "http://localhost:8080/financeiro/services/conta_pagar/relatorio";
				$http.put(url, $scope.filtroContasPagar,
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  if(response.data != null){
		    					  $scope.dadosRelatorio = response.data;
		    				  }else{
		    					  var mensagemErro = "Não foram encontrados registros para os parâmetros de pesquisa informados!";
	    						  growl.error(mensagemErro.bold(), {ttl : 5000});
		    				  }
		    			  }
		    	);
			}else{
				var mensagemAviso = "Os filtros devem ser informados para consulta!";
				growl.warning(mensagemAviso.bold(), {ttl : 5000});
			}
		}
		
		$scope.listarPessoas = function(filtroPessoas){
			$scope.filtroPessoas = filtroPessoas;
			if($scope.filtroPessoas != null && $scope.filtroPessoas.listagem != null){
				var url = "http://localhost:8080/financeiro/services/pessoa/relatorio";
				$http.put(url, $scope.filtroPessoas,
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  if(response.data != null){
		    					  $scope.dadosRelatorio = response.data;
		    				  }else{
		    					  var mensagemErro = "Não foram encontrados registros para os parâmetros de pesquisa informados!";
	    						  growl.error(mensagemErro.bold(), {ttl : 5000});
		    				  }
		    			  }
		    	);
			}else{
				var mensagemAviso = "Os filtros devem ser informados para consulta!";
				growl.warning(mensagemAviso.bold(), {ttl : 5000});
			}
		}
		
		$scope.gerarRelatorioContasReceber = function(filtroContasReceber){
			$scope.filtroContasReceber = filtroContasReceber;
			if($scope.filtroContasReceber != null && $scope.filtroContasReceber.listagem != null
					&& $scope.filtroContasReceber.ordenacao != null){
				var url = "http://localhost:8080/financeiro/services/conta_receber/relatorio";
				$http.post(url, $scope.filtroContasReceber,
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  if(response.data.sucesso){
		    					  growl.success(response.data.resposta.bold(), {ttl : 5000});
		    				  }else{
		    					  growl.error(response.data.resposta.bold(), {ttl : 5000});
		    				  }
		    			  }
		    	);
			}else{
				var mensagemAviso = "Os filtros devem ser informados para gerar relatório";
				growl.warning(mensagemAviso.bold(), {ttl : 5000});
			}
		}
		
		$scope.gerarRelatorioContasPagar = function(filtroContasPagar){
			$scope.filtroContasPagar = filtroContasPagar;
			if($scope.filtroContasPagar != null && $scope.filtroContasPagar.listagem != null
					&& $scope.filtroContasPagar.ordenacao != null){
				var url = "http://localhost:8080/financeiro/services/conta_pagar/relatorio";
				$http.post(url, $scope.filtroContasPagar,
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  if(response.data.sucesso){
		    					  growl.success(response.data.resposta.bold(), {ttl : 5000});
		    				  }else{
		    					  growl.error(response.data.resposta.bold(), {ttl : 5000});
		    				  }
		    			  }
		    	);
			}else{
				var mensagemAviso = "Os filtros devem ser informados para gerar relatório";
				growl.warning(mensagemAviso.bold(), {ttl : 5000});
			}
		}
		
		$scope.gerarRelatorioPessoas = function(filtroPessoas){
			$scope.filtroPessoas = filtroPessoas;
			if($scope.filtroPessoas != null && $scope.filtroPessoas.listagem != null){
				var url = "http://localhost:8080/financeiro/services/pessoa/relatorio";
				$http.post(url, $scope.filtroPessoas,
		    			  {headers:{'Content-Type': "application/json"}})
		    			  .then(function(response){
		    				  if(response.data.sucesso){
		    					  growl.success(response.data.resposta.bold(), {ttl : 5000});
		    				  }else{
		    					  growl.error(response.data.resposta.bold(), {ttl : 5000});
		    				  }
		    			  }
		    	);
			}else{
				var mensagemAviso = "Os filtros devem ser informados para gerar relatório!";
				growl.warning(mensagemAviso.bold(), {ttl : 5000});
			}
		}
	}
})();
