var app = angular.module('figaro', []);
app.controller('stockController', function ($scope, $http) {
    
    //OBTENER LISTA DE PRODUCTOS
    $scope.getAll = function() {
        $http.get("/rest/stock/todos").then(function (response) {
            $scope.productos = response.data;
        });
    };

    //CLICK NUEVO PRODUCTO
    $scope.newProducto = function() {
        $scope.isNuevoProducto = true
    	openModal();
        $scope.ngProducto={}; 
    };

    //CLICK FILA PRODUCTO
    $scope.detailProducto = function(event){
        $scope.isNuevoProducto = false
        $scope.productoID = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/stock/'+$scope.productoID).then(function (response) {
            $scope.ngProducto = response.data;
            openModal();
	    });
    };

    //CLICK ACEPTAR FORMULARIO
    $scope.sendProducto = function() {
        if($scope.isNuevoProducto === true){
            $http.post('/rest/stock/alta', $scope.ngProducto).then(function (response) {
                $scope.productos.push(response.data);
            });
            $scope.ngProducto={};
        }else{
            $http.put('/rest/stock/actualizar/'+ $scope.productoID, $scope.ngProducto).then(function (response) {
                $scope.getAll();
            });
        }
        closeModal();
    };

    //DESCARTAR FORMULARIO
    $scope.discardProducto = function(event){
        $scope.ngProducto = {};
        closeModal();
    };
    
    //APRETAR ESCAPE
    document.addEventListener('keyup', function(e) {
        if (e.keyCode == 27) {
            $scope.discardProducto();
        }
    });

    //INIT
    $scope.search = '';
    $scope.ngProducto = {};
    $scope.getAll();

});