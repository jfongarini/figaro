app.controller('configuracionController', function ($scope, $http) {

    //INIT CONFIGURACION
    $scope.init = function(){
        $scope.activeConfiguracion = true;
        $scope.getAllServicios();
        $scope.getAllCiudades();
        $scope.getAllCategorias();
        $scope.getAllPeluqueros();
        $scope.ngCiudad={};
        $scope.ngServicio={};
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

    //AGREGAR SERVICIO
    $scope.addServicio = function() {
        if($scope.ngServicio.id  === undefined){
            $http.post('/rest/configuracion/servicios/alta', $scope.ngServicio)
                .then(function successCallback(response) {
                    $scope.servicios.push(response.data);
                    $scope.ngServicio={};
                    $scope.messageservicio='';
                }, function errorCallback(response) {
                    $scope.messageservicio=response.data.message;
                });
        }else{
            $http.put('/rest/configuracion/servicios/actualizar/'+ $scope.ngServicio.id,$scope.ngServicio)
                .then(function successCallback(response) {
                    $scope.getAllServicios();
                    $scope.ngServicio={};
                    $scope.messageservicio='';
                }, function errorCallback(response) {
                    $scope.messageservicio=response.data.message;
                });
        }
    };

    //OBTENER LISTA DE SERVICIOS
    $scope.getAllServicios = function() {
        $http.get("/rest/configuracion/servicios").then(function (response) {
            $scope.servicios = response.data;
        });
    };

    //ELIMINAR SERVICIO
    $scope.removeServicio = function(event) {
        var id = event.currentTarget.getAttribute("data-id");
        var servicio = $scope.servicios.filter(function( obj ) {
            return obj.id == id;
        });
        var index = $scope.servicios.indexOf(servicio);
        $scope.servicios.splice(index, 1);  
        $http.delete('/rest/configuracion/servicios/baja/'+ id)
            .then(function successCallback(response) {
                $scope.getAllServicios();
                $scope.ngServicio={};
                $scope.messageServicio='';
              }, function errorCallback(response) {
                $scope.messageServicio=response.data.message;
            });
    };

    //OBTENER SERVICIO
    $scope.loadServicio = function(event) {
        $scope.servicioID = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/configuracion/servicios/'+ $scope.servicioID)
            .then(function successCallback(response) {
                $scope.ngServicio=response.data;
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
    
    //OBTENER LISTA DE CATEGORIAS
    $scope.getAllPeluqueros = function() {
        $http.get("/rest/peluqueros").then(function (response) {
            $scope.peluqueros = response.data;
        });
    };

    //HABILITAR - DESHABILITAR PELUQUERO
    $scope.togglePeluquero = function(peluquero) {
      $http.patch('/rest/peluqueros/'+peluquero.id+'/habilitar');
    };

});