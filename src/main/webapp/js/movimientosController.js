app.controller('movimientosController', function ($scope, $http) {
 	 
	//OBTENER LISTA DE MOVIMIENTOS
	    $scope.getAll = function() {
	    	
	    	if ($scope.filtro == '') {
	    		$scope.filtro = 'day';
	    		$scope.search = stringToDate(getToday());
	    	}	    	
	    	$scope.varGetAll = true;
	    	$scope.searchMovimiento();
	    	
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
	    $scope.deleteTarget = function(id) {	       
	        //$scope.movimientoID = event.currentTarget.getAttribute("data-id");
	        $http.delete('/rest/movimientos/eliminar/'+ id).then(function (response) {	           
	        	closeModal("modal-confirmarDelete");
	        	$scope.getAll();	            
	        });
	    };
	    
	  //CONFIRMA ELIMINTAR MOVIMIENTO
	    $scope.confirmDelete = function(id) {
	    	$scope.idTarget = id;
	    	openModal("modal-confirmarDelete");
	        
	    };
	    
	  //DESCARTAR FORMULARIO
	    $scope.discardConfirm = function(event){
	        $scope.ngMovimiento = {};
	        closeModal("modal-confirmarDelete");
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

/*	    
	    
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

	    
	  //FILTRO CATEGORIA
	    $scope.searchCategoria = function() {
	        $http.get('/rest/movimientos/buscarCategoria',{params: { q: $scope.searchC }})		        
	        .then(function successCallback(response) {	  	        	
	            $scope.movimientos = response.data;	            
	        })
	    }
	    
*/	    
	    
	  //FILTRO CATEGORIA
	    $scope.searchCategoria = function() {
	    	$scope.varCategoria = true;
	    	$scope.searchMovimiento();
	    	$scope.varCategoria = false;
	    }
	    
	    
	  //FILTRO TOTAL
	    $scope.searchMovimiento = function() {		    	
	    	if ((!$scope.varGetAll) && (!$scope.varCategoria)){
	    		$scope.filtro = event.currentTarget.getAttribute("data-id");	    	
	    	  } else {
	    		  $scope.varGetAll = false;
	    	  }	    	
	    	if ($scope.filtro == "day"){
	    		var dStart = new Date($scope.search);
		    	var dEnd = new Date($scope.search);
		    	dStart.setDate(dStart.getDate());
		    	dEnd.setDate(dEnd.getDate());
	          }
	    	if ($scope.filtro == "week"){
	    		var dStart = new Date($scope.search);
		    	var dEnd = new Date($scope.search);
		    	dStart.setDate(dStart.getDate() - 3);
		    	dEnd.setDate(dEnd.getDate() + 3);
	          } 
	    	if ($scope.filtro == "month"){
	    		var dStart = new Date($scope.search);
		    	var dEnd = new Date($scope.search);
		    	
		    	var month = '' + (dStart.getMonth() + 1);
		    	var day = '01'
		    	var year = dStart.getFullYear();
		    	if (month.length < 2) month = '0' + month;			    	
		    	var fecha = [year, month, day].join('-');	
		    	dStart = new Date(fecha);
		    	dStart.setDate(dStart.getDate());
		    			    	
		    	var month = '' + (dEnd.getMonth() + 2);
		    	var day = '01'
		    	var year = dEnd.getFullYear();
		    	if (month.length < 2) month = '0' + month;			    	
		    	var fecha = [year, month, day].join('-');
		    	dEnd = new Date(fecha);
		    	dEnd.setDate(dEnd.getDate() - 1);
		    	
	          } 	    	
	    	
	    	var q1 = getStringDate(dStart); 
	    	var q2 = getStringDate(dEnd);	    
	    	
	    	if ($scope.varCategoria) {
	    		var q3 = $scope.searchC;
	    		$http.get('/rest/movimientos/buscarEntreC',{params: { q1, q2, q3 }})		        
		        .then(function successCallback(response) {	  	        	
		            $scope.movimientos = response.data;	            
		        })
	    	} else {
	    		$http.get('/rest/movimientos/buscarEntre',{params: { q1, q2 }})		        
		        .then(function successCallback(response) {	  	        	
		            $scope.movimientos = response.data;	            
		        })
	    	}
	        
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
	    $scope.activeCaja = true;
	    $scope.search = '';
	    $scope.filtro = '';
	    $scope.varGetAll = false;
	    $scope.varCategoria = false;
	    $scope.ngMovimiento = {};
	    $scope.getAllCategorias();
	    $scope.getAll();

	});
