(function(){
	'use-strict';
	
	angular.module('contasPagar')
	.controller('contasPagarController',['$scope', '$http', 'growl', contasPagarController]);
	
	function contasPagarController($scope, $http, growl){
		var vm = this;
		carregarPagina();
		
		function carregarPagina(){
			var url = "http://localhost:8080/financeiro/services/conta_pagar";
			$http.get(url,
	    			  {headers:{'Content-Type': "application/json"}})
	    			  .then(function(response){
	    				  $scope.titulos = response.data;
	    				 
	    			  }
	    	);
			
		}
		
		function intervaloData(data){
			var date1 = new Date(data);
			var date2 = new Date();
			var timeDiff = date2.getTime() - date1.getTime();
			return Math.ceil(timeDiff / (1000 * 3600 * 24));
		}
		
		$scope.intervaloDataVencimento = function(data, valorPago){
			if(valorPago == null){
				var intervalo = intervaloData(data);
				var texto = "";
				if(intervalo > 0){
					texto = texto.concat("Faltam ", intervalo, " dia(s)");
					return texto;
				}
				return texto.concat("Atrasado em ", Math.abs(intervalo), " dia(s)");
			}
			return "Conta paga";
		}
		
		$scope.intervaloDataCriacao = function(data){
			var intervalo = intervaloData(data);
			var texto = ""; 
			return texto.concat(intervalo, " dia(s)");
		}
		
		$scope.cadastrarContaPagar = function(cadastro){
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
			var url = "http://localhost:8080/financeiro/services/conta_pagar";
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
		
		$scope.excluirContaPagar = function(titulo){
			var url = "http://localhost:8080/financeiro/services/conta_pagar";
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