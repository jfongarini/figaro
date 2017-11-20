var app = angular.module('figaro', ['chart.js']);
app.controller('estadisticasController', function ($scope, $http) {
	
		//SELECCIONA GRAFICO
			$scope.seleccionarGrafico = function(){
				$scope.graficoID = event.currentTarget.getAttribute("data-id");
		        if ($scope.graficoID == "grafico1"){
		        	grafico1.destroy();
		        	$scope.generarGraficoBarra();
		        } else {
		        	grafico2.destroy();
		        	$scope.generarGraficoBarra2();
		        }
			}
	
	
		//OBTENER LISTA DE CLIENTES
		    $scope.getAllClientes = function() {
		        $http.get("/rest/estadisticas/clientes").then(function (response) {
		            $scope.clientes = response.data;		            
		        });			      
		    };
		  
		  
		//BUSCAR CLIENTE SEXO
		    $scope.searchClienteSexo = function() {	
		            var totalHombre = 0;
		            var totalMujer = 0;
			        angular.forEach($scope.clientes, function(ngCliente){	          
			          if (ngCliente.sexo == "hombre"){
			        	  totalHombre = totalHombre + 1;
			          }	else {
			        	  totalMujer = totalMujer + 1;
			          }          
			        })			        
			        arregloColumnaBar = ['Hombre', 'Mujer'] ;
			        arregloLabelBar = 'Total' ;
			        arregloDataBar = [totalHombre, totalMujer] ;
			        $scope.seleccionarGrafico();       
		    };
		    
		  //BUSCAR CLIENTE CIUDAD
		    $scope.searchClienteCiudad = function() {	
		            var totalLP = 0;
		            var totalBer = 0;
		            var totalEns = 0;
			        angular.forEach($scope.clientes, function(ngCliente){	          
			          if (ngCliente.dirCiudad == "La Plata"){
			        	  totalLP = totalLP + 1;
			          }	else if (ngCliente.dirCiudad == "Berisso") {
			        	  totalBer = totalBer + 1;
			          } else {
			        	  totalEns = totalEns + 1;
			          }     
			        })			       			       
			        arregloColumnaBar = ['La Plata', 'Berisso', 'Ensenada'] ;
			        arregloLabelBar = 'Total' ;
			        arregloDataBar = [totalLP, totalBer, totalEns] ;
			        $scope.seleccionarGrafico();			                		       
		    };
		    
//GRAFICOS
			  
		//OPTIONS
		var options = {
				      scales: {
				        yAxes: [{
				          ticks: {
				            beginAtZero: true,
				            min: 0
				          }    
				        }]
				      }
				    };

		var color11 = 'rgba(41,41,97,0.1)';
		var color14 = 'rgba(41,41,97,0.4)';
		
			// GENERAR GRAFICOS DE BARRA 1
				$scope.generarGraficoBarra = function(){					
					data1 = {
				            labels: arregloColumnaBar,
				            datasets: [
				                {
				                    label: arregloLabelBar,
				                    backgroundColor: color11,
				                    borderColor: color14,
				                    borderWidth: 2,
				                    hoverBackgroundColor: color14,
				                    hoverBorderColor: color14,
				                    data: arregloDataBar
				                }
				            ]
				        }; 
			        
			        grafico1 = new Chart(ctx1, {
					      type: 'bar',
					      data: data1,
					      options: options
					});
				}
			
				var color21 = 'rgba(255,99,132,0.1)';
				var color24 = 'rgba(255,99,132,0.4)';
				
					// GENERAR GRAFICOS DE BARRA 2
						$scope.generarGraficoBarra2 = function(){	
							ctx2.clearRect(0,0, ctx2.canvas.width, ctx2.canvas.height);
							data2 = {
						            labels: arregloColumnaBar,
						            datasets: [
						                {
						                    label: arregloLabelBar,
						                    backgroundColor: color21,
						                    borderColor: color24,
						                    borderWidth: 2,
						                    hoverBackgroundColor: color24,
						                    hoverBorderColor: color24,
						                    data: arregloDataBar
						                }
						            ]
						        }; 
					        
					        grafico2 = new Chart(ctx2, {
							      type: 'bar',
							      data: data2,
							      options: options
							});
						}
				
		
    	 
		var canvas1 = document.getElementById("grafico1");
		var ctx1 = canvas1.getContext('2d');
		var canvas2 = document.getElementById("grafico2");
		var ctx2 = canvas2.getContext('2d');
		
		

	    
		var arregloColumnaBar = [] ;
		var arregloLabelBar = [] ;
		var arregloDataBar = [] ;
		$scope.generarGraficoBarra();	
		$scope.generarGraficoBarra2();

  	    $scope.ngCliente = {};
		$scope.getAllClientes();   
		    
});