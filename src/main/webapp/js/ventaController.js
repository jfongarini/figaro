var app = angular.module('figaro', []);
app.controller('ventaController', function ($scope, $http) {
    

    //OBTENER LISTA DE PRODUCTOS
    $scope.getAll = function() {
        $http.get("/rest/stock/todos").then(function (response) {
            $scope.productos = response.data;
        });
    };

    //CONFIRMA ELIMINTAR PRODUCTO
   // $scope.confirmDelete = function(id) {
   //     $scope.idTarget = id;
   //     openModal("modal-confirmarDelete");    
   // };

    //DESCARTAR PRODUCTO
  //  $scope.discardConfirm = function(event){
	//    $scope.ngProducto = {};
	//    closeModal("modal-confirmarDelete");
	//};
    

    //APRETAR ESCAPE
   // document.addEventListener('keyup', function(e) {
   //     if (e.keyCode == 27) {
   //         $scope.discardProducto();
   //     }
   // });

    //VER DETALLE PRODUCTO VENTA
    $scope.verProducto = function(prodID) {
        $http.get('/rest/stock/'+prodID).then(function(response){
            $scope.ngProductoVenta = response.data;
        })
        $scope.ngProdCarrito = {};
        inicializarNgProdCarrito();
    }

    //inicializa objeto ngProdCarrito
    function inicializarNgProdCarrito(){
        $scope.ngProdCarrito.unidades = 0;
        $scope.ngProdCarrito.producto = {};
        $scope.ngProdCarrito.precio = 0;
    }

    //SUMAR PRODUCTO AL CARRITO
    $scope.sumarCarrito = function(prodVenta, uniAVender){
        $scope.ngProdCarrito.producto = prodVenta;
        $scope.ngProdCarrito.unidades = uniAVender;
        $scope.ngProdCarrito.precio = ($scope.ngProdCarrito.producto.precio * $scope.ngProdCarrito.unidades);
        $scope.ngCarrito.push($scope.ngProdCarrito);
        $scope.ngTotalVenta = calculaTotalVenta();
    }

    //CALCULA TOTAL DE LA VENTA
    function calculaTotalVenta(){
        var total = 0;
        angular.forEach($scope.ngCarrito, function(key){
            total = total + key.precio;
        })
        return total;
    }


    //INIT
    $scope.activeVenta = true;
    $scope.ngProducto = {};
    $scope.ngProductoVenta = {};
    $scope.ngCarrito = [];
    $scope.getAll();

});