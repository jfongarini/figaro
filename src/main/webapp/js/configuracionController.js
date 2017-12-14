app.controller('configuracionController', function ($scope, $http) {

    //INIT CONFIGURACION
    $scope.init = function(){
        $scope.activeConfiguracion = true;
        $scope.getAllTrabajos();
        $scope.getAllCiudades();
        $scope.getAllCategorias();
        $scope.ngCiudad={};
        $scope.ngTrabajo={};
        $scope.ngCategoria={};
    }
  
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
        var id = event.currentTarget.getAttribute("data-id");
        var ciudad = $scope.ciudades.filter(function( obj ) {
            return obj.id == id;
        });
        var index = $scope.ciudades.indexOf(ciudad);
        $scope.ciudades.splice(index, 1);  
        $http.delete('/rest/configuracion/ciudades/baja/'+ id)
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
        var id = event.currentTarget.getAttribute("data-id");
        var trabajo = $scope.trabajos.filter(function( obj ) {
            return obj.id == id;
        });
        var index = $scope.trabajos.indexOf(trabajo);
        $scope.trabajos.splice(index, 1);  
        $http.delete('/rest/configuracion/trabajos/baja/'+ id)
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

    //AGREGAR CATEGORIA
    $scope.addCategoria = function() {
        $http.post('/rest/configuracion/categorias/alta', $scope.ngCategoria)
            .then(function successCallback(response) {
                $scope.categorias.push(response.data);
                $scope.ngCategoria={};
                $scope.messageCategoria='';
              }, function errorCallback(response) {
                $scope.messageCategoria=response.data.message;
            });
    };


    //OBTENER LISTA DE CATEGORIAS
    $scope.getAllCategorias = function() {
        $http.get("/rest/configuracion/categorias").then(function (response) {
            $scope.categorias = response.data;
        });
    };

    //ELIMINAR CATEGORIA
    $scope.removeCategoria = function(event) {
        var id = event.currentTarget.getAttribute("data-id");
        var categoria = $scope.categorias.filter(function( obj ) {
            return obj.id == id;
        });
        var index = $scope.categorias.indexOf(categoria);
        $scope.categorias.splice(index, 1);
        $http.delete('/rest/configuracion/categorias/baja/'+ id)
            .then(function successCallback(response) {
                $scope.getAllCategorias();
                $scope.messageCategoria='';
              }, function errorCallback(response) {
                $scope.messageCategoria=response.data.message;
            });
    };
    
});