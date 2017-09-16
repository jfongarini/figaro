var app = angular.module('figaro', []);
app.controller('stockController', function ($scope, $http) {
    
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
    };

    //CLICK FILA CLIENTE
    $scope.detailClient = function(event){
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
            $http.post('/rest/clientes/alta', $scope.ngCliente).then(function (response) {
                $scope.clientes.push(response.data);
            });
            $scope.ngCliente={};
        }else{
            $http.put('/rest/clientes/actualizar/'+ $scope.clienteID, $scope.ngCliente).then(function (response) {
                $scope.getAll();
            });
        }
        closeModal();
    };

    //DESCARTAR FORMULARIO
    $scope.discardClient = function(event){
        $scope.ngCliente = {};
        closeModal();
    };
    
    //APRETAR ESCAPE
    document.addEventListener('keyup', function(e) {
        if (e.keyCode == 27) {
            $scope.discardClient();
        }
    });

    //INIT
    $scope.ngCliente = {};
    $scope.getAll();

});