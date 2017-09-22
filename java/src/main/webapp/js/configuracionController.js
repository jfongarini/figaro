var app = angular.module('figaro', []);
app.controller('configuracionController', function ($scope, $http) {
  
    //AGREGAR CIUDAD
    $scope.addCiudad = function() {
        $http.post('/rest/configuracion/ciudades/alta', $scope.ngCiudad)
            .then(function successCallback(response) {
                $scope.ciudades.push(response.data);
                $scope.ngCiudad={};
                $scope.messageCiudad='';
              }, function errorCallback(response) {
                $scope.messageCiudad=response.data.message;
            });
    };

    //ELIMINAR CIUDAD
    $scope.removeCiudad = function(event) {
        $scope.ciudadID = event.currentTarget.getAttribute("data-id");
        $http.delete('/rest/configuracion/ciudades/baja/'+ $scope.ciudadID)
            .then(function successCallback(response) {
                $scope.getAllCiudades();
                $scope.messageCiudad='';
              }, function errorCallback(response) {
                $scope.messageCiudad=response.data.message;
            });
    };

    //OBTENER LISTA DE CIUDADES
    $scope.getAllCiudades = function() {
        $http.get("/rest/configuracion/ciudades").then(function (response) {
            $scope.ciudades = response.data;
        });
    };

    //AGREGAR TRABAJO
    $scope.addTrabajo = function() {
        if($scope.ngTrabajo.id  === undefined){
            $http.post('/rest/configuracion/trabajos/alta', $scope.ngTrabajo)
                .then(function successCallback(response) {
                    $scope.trabajos.push(response.data);
                    $scope.ngTrabajo={};
                    $scope.messageTrabajo='';
                }, function errorCallback(response) {
                    $scope.messageTrabajo=response.data.message;
                });
        }else{
            $http.put('/rest/configuracion/trabajos/actualizar/'+ $scope.ngTrabajo.id,$scope.ngTrabajo)
                .then(function successCallback(response) {
                    $scope.getAllTrabajos();
                    $scope.ngTrabajo={};
                    $scope.messageTrabajo='';
                }, function errorCallback(response) {
                    $scope.messageTrabajo=response.data.message;
                });
            
        }
    };

    //OBTENER LISTA DE TRABAJOS
    $scope.getAllTrabajos = function() {
        $http.get("/rest/configuracion/trabajos").then(function (response) {
            $scope.trabajos = response.data;
        });
    };

    //ELIMINAR TRABAJO
    $scope.removeTrabajo = function(event) {
        $scope.trabajoID = event.currentTarget.getAttribute("data-id");
        $http.delete('/rest/configuracion/trabajos/baja/'+ $scope.trabajoID)
            .then(function successCallback(response) {
                $scope.getAllTrabajos();
                $scope.ngTrabajo={};
                $scope.messageTrabajo='';
              }, function errorCallback(response) {
                $scope.messageTrabajo=response.data.message;
            });
    };

    //OBTENER TRABAJO
    $scope.loadTrabajo = function(event) {
        $scope.trabajoID = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/configuracion/trabajos/'+ $scope.trabajoID)
            .then(function successCallback(response) {
                $scope.ngTrabajo=response.data;
            });
    };

    //AGREGAR PELUQUERO
    $scope.addPeluquero = function() {
        $http.post('/rest/configuracion/peluqueros/alta', $scope.ngPeluquero)
            .then(function successCallback(response) {
                $scope.peluqueros.push(response.data);
                $scope.ngPeluquero={};
                $scope.messagePeluquero='';
              }, function errorCallback(response) {
                $scope.messagePeluquero=response.data.message;
            });
    };

    //OBTENER LISTA DE PELUQUEROS
    $scope.getAllPeluqueros = function() {
        $http.get("/rest/configuracion/peluqueros").then(function (response) {
            $scope.peluqueros = response.data;
        });
    };

    //ELIMINAR PELUQUERO
    $scope.removePeluquero = function(event) {
        $scope.peluqueroID = event.currentTarget.getAttribute("data-id");
        $http.delete('/rest/configuracion/peluqueros/baja/'+ $scope.peluqueroID)
            .then(function successCallback(response) {
                $scope.getAllPeluqueros();
                $scope.messagePeluquero='';
              }, function errorCallback(response) {
                $scope.messagePeluquero=response.data.message;
            });
    };

    $scope.getAllCiudades();
    $scope.getAllTrabajos();
    $scope.getAllPeluqueros();
    $scope.ngCiudad={};
    $scope.ngTrabajo={};
    $scope.ngPeluquero={};
});