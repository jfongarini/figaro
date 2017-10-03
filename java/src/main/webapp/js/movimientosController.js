var app = angular.module('figaro', []);
app.controller('movimientosController', function ($scope, $http) {
 	    
	//OBTENER LISTA DE MOVIMIENTOS
	    $scope.getAll = function() {
	        $http.get("/rest/movimientos").then(function (response) {
	            $scope.movimientos = response.data;
	        });
	    };

	    //CLICK NUEVO MOVIMIENTO
	    $scope.newMovimiento = function() {
	        $scope.isNuevoMovimiento = true
	    	openModal();
	        $scope.ngMovimiento={};
	        $scope.ngMovimiento.fecha = new Date(getToday()); 
	        $scope.message='';
	    };

	    //CLICK FILA MOVIMIENTO
	    $scope.detailMovimiento = function(event){
	        $scope.message='';
	        $scope.isNuevoMovimiento = false
	        $scope.movimientoID = event.currentTarget.getAttribute("data-id");
	        $http.get('/rest/movimientos/'+$scope.movimientoID).then(function (response) {
	            $scope.ngMovimiento = response.data;
	            $scope.ngMovimiento.fecha = new Date($scope.ngMovimiento.fecha); 
	            openModal();
		    });
	    };

	    //CLICK ACEPTAR FORMULARIO
	    $scope.sendMovimiento = function() {
	        if($scope.isNuevoMovimiento === true){
	            $http.post('/rest/movimientos/alta', $scope.ngMovimiento).then(function (response) {
	                $scope.movimientos.push(response.data);
	            });
	            $scope.ngMovimiento={};
	        }else{
	            $http.put('/rest/movimientos/actualizar/'+ $scope.movimientoID, $scope.ngMovimiento).then(function (response) {
	                $scope.getAll();
	            });
	        }
	        closeModal();
	    };

	    //DESCARTAR FORMULARIO
	    $scope.discardMovimiento = function(event){
	        $scope.ngMovimiento = {};
	        closeModal();
	    };

    
	    //OBTENER LISTA DE CATEGORIAS
	    $scope.getAllCategorias = function() {
	        $http.get("/rest/configuracion/categorias").then(function (response) {
	            $scope.categorias = response.data;
	        });
	    };
	    
	    //APRETAR ESCAPE
	    document.addEventListener('keyup', function(e) {
	        if (e.keyCode == 27) {
	            $scope.discardMovimiento();
	        }
	    });
	    
	    //CALCULAR EL TOTAL DE CAJA
	    $scope.calculaTotal = function(){
	        var total = 0;
	        angular.forEach($scope.movimientos, function(ngMovimiento){	          
	          if (ngMovimiento.isGasto == true){
	        	  total = total - ngMovimiento.precio
	          } else {
	        	  total = total + ngMovimiento.precio
	          }	          
	        })
	        return total;
	    }
	    
	    //ORDENAR POR FECHA	    
	    $scope.sortFecha = function(ngMovimiento) {
	        var date = new Date(ngMovimiento.fecha);
	        return date;
	    };
	    
	    //FILTRO
	    $scope.searchMovimiento = function() {        
	        $http.get('/rest/movimientos/buscar/',{params: { search: $scope.search }})
	        .then(function successCallback(response) {
	            $scope.movimientos = response.data; 
	        })
	    };

	    //INIT	   
	    $scope.ngMovimiento = {};
	    $scope.getAllCategorias();
	    $scope.getAll();

	});
