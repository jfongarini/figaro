app.controller('peluquerosController', function ($scope, $http) {
    
    //INIT PELUQUEROS
    $scope.init = function(){
        $scope.activePeluqueros = true;
        $scope.ngPeluquero = {"trabajos" :[]};
        $scope.getAll();
        $scope.getAllCiudades();
        $scope.getAllTrabajos();
    }

    //OBTENER LISTA DE PELUQUEROS
    $scope.getAll = function() {
        $http.get("/rest/peluqueros").then(function (response) {
            $scope.peluqueros = response.data;
        });
    };

    //CLICK NUEVO PELUQUERO
    $scope.newPeluquero = function() {
        $scope.message='';
        $scope.allChecked = false;
        $scope.checkAllTrabajos();
        ($scope.isModalOpen == true) ? $('#modal-peluqueros').addClass("modal-on-top") : openModal("modal-peluqueros");
        $('#modal-peluqueros-focus').focus();
    }

    //CLICK FILA PELUQUERO
    $scope.detailPeluquero = function(event){
        $scope.message='';
        $scope.allChecked = false;
        $scope.update = true;
        $scope.peluqueroId = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/peluqueros/'+$scope.peluqueroId).then(function (response) {
            $scope.ngPeluquero = response.data;
            $scope.servicios.forEach(function(trabajo) {
                trabajo.selected = $scope.isInPeluquero(trabajo);
            });
            openModal("modal-peluqueros");
	    });
    };
    
    //CLICK ACEPTAR FORMULARIO
    $scope.sendPeluquero = function() {

        $scope.actualizarTrabajos();
    
        if($scope.update == null){
            $scope.ngPeluquero.fechaIngreso = getToday();
            $http.post('/rest/peluqueros/alta', $scope.ngPeluquero)
            .then(function successCallback(response) {
                $scope.peluqueros.push(response.data);
                $scope.discardPeluquero();
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }else{
            $http.put('/rest/peluqueros/actualizar/'+ $scope.peluqueroId, $scope.ngPeluquero)
            .then(function successCallback(response) {
                $scope.getAll();
                $scope.discardPeluquero();
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }
    };

    //BUSCA UN TRABAJO EN PELUQUERO
    $scope.isInPeluquero = function (servicio){ 
        let found = false;
        $scope.ngPeluquero.trabajos.forEach(function(trabajo) {
            if(trabajo.servicio != null && trabajo.servicio.id==servicio.id)
                found = true;
        });
    return found;
    };

    //ASIGNAR TRABAJOS SELECCIONADOS A PELUQUEROS
    $scope.actualizarTrabajos = function() {
        $scope.ngPeluquero.trabajos = [];
        $scope.servicios.forEach(function(servicio) {
            if (servicio.selected){
                var trabajo = {}; 
                trabajo.servicio = servicio;
                trabajo.comision = servicio.comision;
                $scope.ngPeluquero.trabajos.push(trabajo);
            }
        });    
    };

    //SELECCIONAR TODOS
    $scope.checkAllTrabajos = function (){ 
        $scope.servicios.forEach(function(servicio) {
            servicio.selected = $scope.allChecked
        });    
    };
    
    //DESCARTAR FORMULARIO
    $scope.discardPeluquero = function(event){
        $scope.update = null;
        $scope.ngPeluquero = {"servicios" :[]};
        closeModal("modal-peluqueros");
    };

    //OBTENER LISTA DE CIUDADES
    $scope.getAllCiudades = function() {
        $http.get("/rest/configuracion/ciudades").then(function (response) {
            $scope.ciudades = response.data;
        });
    };

    //OBTENER LISTA DE TRABAJOS
    $scope.getAllTrabajos = function() {
        $http.get("/rest/configuracion/servicios").then(function (response) {
            $scope.servicios = response.data;
        });
    };
    
    //APRETAR ESCAPE
    document.addEventListener('keyup', function(e) {
        if (e.keyCode == 27) {
            $scope.discardPeluquero("modal-peluqueros");
        }
    });

});