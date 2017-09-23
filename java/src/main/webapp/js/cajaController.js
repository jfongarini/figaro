var app = angular.module('figaro', []);
app.controller('cajaController', function ($scope, $http) {
    
    //OBTENER LISTA
    $scope.getAll = function() {
        $http.get("/rest/caja/todos").then(function (response) {
            $scope.caja = response.data;
        });
    };

    //CLICK NUEVO Caja
    $scope.newCaja = function() {
        $scope.isNuevoCaja = true
    	openModal();
        $scope.ngCaja={}; 
    };

    //CLICK FILA Caja
    $scope.detailCaja = function(event){
        $scope.isNuevoCaja = false
        $scope.cajaID = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/caja/'+$scope.cajaID).then(function (response) {
            $scope.ngCaja = response.data;
            openModal();
	    });
    };


    //INIT
    $scope.ngCaja = {};
    $scope.getAll();

});