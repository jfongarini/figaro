var app = angular.module('figaro', ['chart.js']);
app.controller('estadisticasController', function ($scope, $http) {
		
		//OBTENER LISTA DE CIUDADES
		    $scope.getClientesCiudad = function() {
		        $http.get("/rest/estadisticas/clientesCiudad").then(function (response) {
		            $scope.clientesCiudad = response.data;		            
		        });			      
		    };		   

		//BUSCAR CLIENTE CIUDAD
		    $scope.searchClienteCiudad = function() {		    	
		    	arregloColumnaBar = [] ;
				arregloLabelBar = [] ;
				arregloDataBar = [] ;
		    	var cliCiu = $scope.clientesCiudad;

				for (var key in cliCiu) {
    				arregloColumnaBar.push(key);
    				arregloDataBar.push(cliCiu[key]);
				}
				arregloLabelBar = 'Total' ;
		        $scope.seleccionarGrafico();
		    }
		    
		  //OBTENER LISTA DE CLIENTE SEXO
		    $scope.getClientesSexo = function() {
		        $http.get("/rest/estadisticas/clientesSexo").then(function (response) {
		            $scope.clientesSexo = response.data;		            
		        });			      
		    };	
		    
		  //BUSCAR CLIENTE SEXO
		    $scope.searchClienteSexo = function() {
		    	arregloColumnaBar = [] ;
				arregloLabelBar = [] ;
				arregloDataBar = [] ;
		    	var cliSex = $scope.clientesSexo;

				for (var key in cliSex) {
    				arregloColumnaBar.push(key);
    				arregloDataBar.push(cliSex[key]);
				}
				arregloLabelBar = 'Total' ;
		        $scope.seleccionarGrafico();		    	
		    }

		      
//GRAFICOS
		    
		  //SELECCIONA GRAFICO
			$scope.seleccionarGrafico = function(){
				$scope.graficoID = event.currentTarget.getAttribute("data-id");
		        if ($scope.graficoID == "grafico1"){
		        	grafico1.destroy();
		        	$scope.generarGraficoBarra1();
		        } else {
		        	grafico2.destroy();
		        	$scope.generarGraficoBarra2();
		        }
			}
			  
		//OPTIONS
		    var options = { scales: { yAxes: [{ ticks: { beginAtZero: true, min: 0 } }] } };

		    var color11 = 'rgba(41,41,97,0.1)';
		    var color14 = 'rgba(41,41,97,0.4)';
		
	   // GENERAR GRAFICOS DE BARRA 1
			$scope.generarGraficoBarra1 = function(){					
				data1 = { labels: arregloColumnaBar, datasets: [{ label: arregloLabelBar, backgroundColor: color11, borderColor: color14, borderWidth: 2, hoverBackgroundColor: color14, hoverBorderColor: color14, data: arregloDataBar}]}; 
			    grafico1 = new Chart(ctx1, { type: 'bar', data: data1, options: options	});
			}
			
			var color21 = 'rgba(255,99,132,0.1)';
			var color24 = 'rgba(255,99,132,0.4)';
				
		// GENERAR GRAFICOS DE BARRA 2
			$scope.generarGraficoBarra2 = function(){								
				data2 = { labels: arregloColumnaBar, datasets: [ { label: arregloLabelBar, backgroundColor: color21, borderColor: color24, borderWidth: 2, hoverBackgroundColor: color24, hoverBorderColor: color24, data: arregloDataBar}]}; 
				grafico2 = new Chart(ctx2, { type: 'bar', data: data2, options: options });
			}
    	 
		var canvas1 = document.getElementById("grafico1");
		var ctx1 = canvas1.getContext('2d');
		var canvas2 = document.getElementById("grafico2");
		var ctx2 = canvas2.getContext('2d');
			    
		var arregloColumnaBar = [] ;
		var arregloLabelBar = [] ;
		var arregloDataBar = [] ;
		$scope.generarGraficoBarra1();	
		$scope.generarGraficoBarra2();

		$scope.getClientesCiudad();
		$scope.getClientesSexo();
		    
});