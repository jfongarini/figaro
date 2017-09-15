var app = angular.module('figaro', []);
app.controller('clientesController', function ($scope, $http) {
    
    //OBTENER LISTA DE CLIENTES
    $scope.getAll = function() {
        $http.get("/rest/clientes/todos").then(function (response) {
            $scope.clientes = response.data;
        });
    };

    //CLICK NUEVO CLIENTE
    $scope.newClient = function() {
        $scope.isNuevoCliente = true
    	openModal();
        $scope.ngCliente={};
        $scope.message='';
    };

    //CLICK FILA CLIENTE
    $scope.detailClient = function(event){
        $scope.message='';
        $scope.isNuevoCliente = false
        $scope.clienteID = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/clientes/'+$scope.clienteID).then(function (response) {
            $scope.ngCliente = response.data;
            openModal();
	    });
    };

    //CLICK ACEPTAR FORMULARIO
    $scope.sendClient = function() {
        if($scope.isNuevoCliente === true){
            $scope.ngCliente.fechaIngreso = getToday();
            $scope.ngCliente.ultimaVisita = getToday();
            $http.post('/rest/clientes/alta', $scope.ngCliente)
            .then(function successCallback(response) {
                $scope.clientes.push(response.data);
                closeModal();
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }else{
            $http.put('/rest/clientes/actualizar/'+ $scope.clienteID, $scope.ngCliente).then(
                function successCallback(response) {
                    $scope.getAll();
                    closeModal();
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }
    };

    //BUSCAR
    $scope.searchCliente = function() {        
        $http.get('/rest/clientes/buscar',{params: { search: $scope.search }})
        .then(function successCallback(response) {
            $scope.clientes = response.data; 
        });  
    };

    //DESCARTAR FORMULARIO
    $scope.discardClient = function(event){
        $scope.ngCliente = {};
        closeModal();
    };

    //OBTENER LISTA DE CIUDADES
    $scope.getAllCiudades = function() {
        $http.get("/rest/configuracion/ciudades").then(function (response) {
            $scope.ciudades = response.data;
        });
    };
    
    //APRETAR ESCAPE
    document.addEventListener('keyup', function(e) {
        if (e.keyCode == 27) {
            $scope.discardClient();
        }
    });

    //INIT
    $scope.search = '';
    $scope.ngCliente = {};
    $scope.getAllCiudades();
    $scope.getAll();

});