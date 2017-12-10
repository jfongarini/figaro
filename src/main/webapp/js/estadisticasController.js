var app = angular.module('figaro', ['chart.js']);
app.controller('estadisticasController', function ($scope, $http) {
		
		//OBTENER LISTA DE CIUDADES
		    $scope.getClientesCiudad = function() {
		        $http.get("/rest/estadisticas/clientesCiudad").then(function (response) {
		            $scope.clientesCiudad = response.data;		            
		        });			      
		    };		  

		//OBTENER LISTA DE CLIENTE SEXO
		    $scope.getClientesSexo = function() {
		        $http.get("/rest/estadisticas/clientesSexo").then(function (response) {
		            $scope.clientesSexo = response.data;		            
		        });			      
		    };


		//OBTENER LISTA DE Producto mas vendido
		    $scope.getProductoMasVendido = function() {
		        $http.get("/rest/estadisticas/productoMasVendido",{params: { from: $scope.fechaInicio, to: $scope.fechaFin}}).then(function (response) {
		            $scope.productoMasVendido = response.data;		            
		        });			      
		    };	
     
		//CASE SELECCION
			$scope.selectGrafico = function(item){
				switch (item) {
            		case 'sexo':
            				seleccionado = $scope.clientesSexo;
            				$scope.muestra = false;     
            				$scope.generarSeleccionado(seleccionado);           			
                			break;
            		case 'ciudad':
            				seleccionado = $scope.clientesCiudad;
            				$scope.muestra = false;      
            				$scope.generarSeleccionado(seleccionado);          			
                			break;
                	case 'productoMasVendido':                		
                			$scope.fechaFin = $scope.searchTo;
                			$scope.fechaInicio = $scope.searchFrom;
                			seleccionado = $scope.productoMasVendido;   
                			$scope.muestra = true;          
                			$scope.getProductoMasVendido();
                			$scope.generarSeleccionado(seleccionado);   			
                			break;
            		default:
            	}
            	
			}

//GRAFICOS

		//BUSCAR CLIENTE CIUDAD
		    $scope.generarSeleccionado = function(seleccionado) {		    	
		    	arregloColumnaBar = [] ;
				arregloLabelBar = [] ;
				arregloDataBar = [] ;		    	

				for (var key in seleccionado) {
    				arregloColumnaBar.push(key);
    				arregloDataBar.push(seleccionado[key]);
				}
				arregloLabelBar = 'Total' ;		        
		        grafico1.destroy();
		        $scope.generarGraficoBarra1();
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
    	 
		var canvas1 = document.getElementById("grafico1");
		var ctx1 = canvas1.getContext('2d');
			    
		var arregloColumnaBar = [] ;
		var arregloLabelBar = [] ;
		var arregloDataBar = [] ;
		$scope.muestra = false;
		$scope.generarGraficoBarra1();	

	    $scope.fechaInicio = getToday();
	    $scope.fechaFin = getToday();
		$scope.getClientesCiudad();
		$scope.getClientesSexo();
		$scope.getProductoMasVendido();
		    
});