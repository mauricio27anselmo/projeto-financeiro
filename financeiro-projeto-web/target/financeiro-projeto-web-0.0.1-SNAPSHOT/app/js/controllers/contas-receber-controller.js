(function(){
	'use-strict';
	
	angular.module('contasReceber')
	.controller('contasReceberController',['$scope', '$http', 'growl', contasReceberController]);
	
	function contasReceberController($scope, $http, growl){
		var vm = this;
		carregarPagina();
		console.log($scope.titulos);
		
		function carregarPagina(){
			var url = "http://localhost:8080/financeiro/services/conta_receber";
			$http.get(url,
	    			  {headers:{'Content-Type': "application/json"}})
	    			  .then(function(response){
	    				  $scope.titulos = response.data;
	    				 
	    			  }
	    	);
			
		}
		
		$scope.cadastrarContaReceber = function(cadastro){
			var titulo = {};
			titulo.dadosPessoa = {};
			titulo.dadosPessoa.cpfCnpjPessoa = cadastro.cpfCnpj;
			titulo.dataCriacaoTitulo = cadastro.dataCriacao;
			titulo.dataVencimentoTitulo = cadastro.dataVencimento;
			titulo.dadosPessoa.nomePessoa = cadastro.nomePessoa;
			titulo.valorTitulo = cadastro.valorTitulo;
			titulo.valorDescontoTitulo = cadastro.valorDesconto;
			titulo.valorJurosTitulo = cadastro.valorJuros;
			console.log(titulo);
			var url = "http://localhost:8080/financeiro/services/conta_receber";
			$http.post(url, titulo, 
	    			  {headers:{'Content-Type': "application/json"}} )
	    			  .then(function(response){
	    				  if (response.data.sucesso) {
								growl.success(response.data.resposta.bold(), {ttl : 2000});
							}else{
								growl.error(response.data.resposta.bold(), {ttl : 5000});
							}
	    			  }
	    	);
			carregarPagina();
		};
		
		$scope.excluirContaReceber = function(titulo){
			var url = "http://localhost:8080/financeiro/services/conta_receber";
			url = url.concat("?titulo=", titulo.numeroTitulo);
			$http.delete(url)
	    			  .then(function(response){
	    				  if (response.data.resposta) {
								growl.success(response.data.resposta.bold(), {ttl : 2000});
							}else{
								growl.error(response.data.resposta.bold(), {ttl : 5000});
							}
	    			  }
	    	);
			carregarPagina();
		}
	}
})();