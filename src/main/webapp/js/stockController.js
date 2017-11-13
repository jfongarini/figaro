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
    	openModal("modal-stock");
        $scope.ngProducto={}; 
    };

    //ELIMINTAR PRODUCTO
    $scope.deleteProducto = function(event) {
        $scope.productoId = event.currentTarget.getAttribute("data-id");
        $http.delete('/rest/stock/eliminar/'+ $scope.productoId).then(function (response) {
            $scope.getAll();
        });
    };

    //ACTUALIZAR CANTIDAD DISPONIBLE PRODUCTO
    $scope.updateCantidad = function(id, cantidad){
        $http.patch('/rest/stock/editar/'+id,{} ,{params: { cantidad: cantidad }}).then(function (response) {
            $scope.getAll();
        });
    };

    //CLICK FILA PRODUCTO
    $scope.detailProducto = function(event){
        $scope.isNuevoProducto = false
        $scope.productoId = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/stock/'+$scope.productoId).then(function (response) {
            $scope.ngProducto = response.data;
            openModal("modal-stock");
	    });
    };

    //CLICK ACEPTAR FORMULARIO
    $scope.sendProducto = function() {
        if($scope.isNuevoProducto === true){
            $http.post('/rest/stock/alta', $scope.ngProducto).then(function (response) {
                $scope.productos.push(response.data);
            });
            $scope.ngProducto={};
        }
        else{ 
            $http.put('/rest/stock/actualizar/'+ $scope.productoId, $scope.ngProducto).then(
                function (response) {
                    $scope.getAll();
            });
        }
        closeModal("modal-stock");
    };

    //DESCARTAR FORMULARIO
    $scope.discardProducto = function(event){
        $scope.ngProducto = {};
        closeModal("modal-stock");
    };
    
    //BUSCAR
    $scope.searchProducto = function() {        
        $http.get('/rest/stock/buscar',{params: { search: $scope.search }})
        .then(function successCallback(response) {
            $scope.productos = response.data; 
        })
    };

    //APRETAR ESCAPE
    document.addEventListener('keyup', function(e) {
        if (e.keyCode == 27) {
            $scope.discardProducto();
        }
    });

    //INIT
    $scope.activeStock = true;
    $scope.search = '';
    $scope.ngProducto = {};
    $scope.getAll();

});