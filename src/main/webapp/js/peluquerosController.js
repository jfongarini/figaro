app.controller('peluquerosController', function ($scope, $http) {
    
    //INIT PELUQUEROS
    $scope.init = function(){
        $scope.activePeluqueros = true;
        $scope.ngPeluquero = {};
        $scope.ngPeluquero.trabajos = [];
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
        ($scope.isModalOpen == true) ? $('#modal-peluqueros').addClass("modal-on-top") : openModal("modal-peluqueros");
        $('#modal-peluqueros-focus').focus();
    }

    //CLICK FILA PELUQUERO
    $scope.detailPeluquero = function(event){
        $scope.message='';
        $scope.update = true;
        $scope.peluqueroId = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/peluqueros/'+$scope.peluqueroId).then(function (response) {
            $scope.ngPeluquero = response.data;
            openModal("modal-peluqueros");
	    });
    };

    //CLICK ACEPTAR FORMULARIO
    $scope.sendPeluquero = function() {
               
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

    // Toggle selection for a given fruit by name
    $scope.toggleSelection = function toggleSelection(trabajo) {
        var idx =  $scope.ngPeluquero.trabajos.indexOf(trabajo);

        // Is currently selected
        if (idx > -1) {
          $scope.ngPeluquero.trabajos.splice(idx, 1);
        }

        // Is newly selected
        else {
          $scope.ngPeluquero.trabajos.push(trabajo);
        }
    };

    $scope.isSelected = function isSelected(trabajo) {
        return $scope.ngPeluquero.trabajos.indexOf(trabajo) > -1
    }

    //DESCARTAR FORMULARIO
    $scope.discardPeluquero = function(event){
        $scope.update = null;
        $scope.ngPeluquero = {};
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
        $http.get("/rest/configuracion/trabajos").then(function (response) {
            $scope.trabajos = response.data;
        });
    };
    
    //APRETAR ESCAPE
    document.addEventListener('keyup', function(e) {
        if (e.keyCode == 27) {
            $scope.discardPeluquero("modal-peluqueros");
        }
    });

});