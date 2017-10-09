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
	    	openModal("modal-caja");
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
	            openModal("modal-caja");
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
	        closeModal("modal-caja");
	    };

	    //DESCARTAR FORMULARIO
	    $scope.discardMovimiento = function(event){
	        $scope.ngMovimiento = {};
	        closeModal("modal-caja");
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
	    
	  //ELIMINTAR MOVIMIENTO
	    $scope.deleteMovimiento = function(event) {	       
	        $scope.movimientoID = event.currentTarget.getAttribute("data-id");
	        $http.delete('/rest/movimientos/eliminar/'+$scope.movimientoID).then(function (response) {	           
	            $scope.getAll();
	        });
	    };
	    
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
	    
	    //FILTRO SEMANA
	    $scope.searchMovimientoEntreDias = function() {	    	
	    	var dStart = new Date($scope.search);
	    	var dEnd = new Date($scope.search);
	    	dStart.setDate(dStart.getDate() - 3);
	    	dEnd.setDate(dEnd.getDate() + 3);
	    	var q1 = getStringDate(dStart); 
	    	var q2 = getStringDate(dEnd);	    
	    	
	        $http.get('/rest/movimientos/buscarEntre',{params: { q1, q2 }})		        
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
