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
	    
	    //FILTRO DIA
	    $scope.searchMovimientoDia = function() {	    	   	
	        $http.get('/rest/movimientos/buscar',{params: { q: getStringDate($scope.search) }})		        
	        .then(function successCallback(response) {	  	        	
	            $scope.movimientos = response.data;	            
	        })
	    }
	    
	  //FILTRO MES
	    $scope.searchMovimientoMes = function() {	    	   	
	        $http.get('/rest/movimientos/buscar',{params: { q: getStringMonth($scope.search) }})		        
	        .then(function successCallback(response) {	  	        	
	            $scope.movimientos = response.data;	            
	        })
	    }
	    	    	    
	    //MOSTRAR O NO MOSTRAR DIV DE BUSQUEDA
	    $scope.IsHiddenDia = true;
	    $scope.IsHiddenEntreDia = true;
	    $scope.IsHiddenMes = true;
	    
        $scope.ShowHideDia = function () {
        	$scope.IsHiddenEntreDia = true;
        	$scope.IsHiddenMes = true;
            $scope.IsHiddenDia = $scope.IsHiddenDia ? false : true;        	
            if($scope.IsHiddenDia === true){
            	$scope.getAll();	                    	
	        }
        }        
        
        $scope.ShowHideEntreDia = function () {         
        	$scope.IsHiddenDia = true;
        	$scope.IsHiddenMes = true;
            $scope.IsHiddenEntreDia = $scope.IsHiddenEntreDia ? false : true;        	
            if($scope.IsHiddenEntreDia === true){
            	$scope.getAll();
	        }
        }        
        
        $scope.ShowHideMes = function () {  
        	$scope.IsHiddenDia = true;
        	$scope.IsHiddenEntreDia = true;
            $scope.IsHiddenMes = $scope.IsHiddenMes ? false : true;        	
            if($scope.IsHiddenMes === true){
            	$scope.getAll();
	        }
        }
	    
	    //INIT
	    $scope.search = '';
	    $scope.ngMovimiento = {};
	    $scope.getAllCategorias();
	    $scope.getAll();

	});