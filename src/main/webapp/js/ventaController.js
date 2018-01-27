var app = angular.module('figaro', []);
app.controller('ventaController', function ($scope, $http) {
    

    //OBTENER LISTA DE PRODUCTOS
    $scope.getAllProductos = function() {
        $http.get("/rest/stock/todos").then(function (response) {
            $scope.productos = response.data;
        });
    };

    //OBTENER LISTA DE VENTAS
    $scope.getAllVentas = function() {
        $http.get("/rest/venta/todos").then(function (response) {
            $scope.ngVentasCreadas = response.data;
        });
    };    

    //VER DETALLE PRODUCTO VENTA
    $scope.verProducto = function(prodID) {
        $http.get('/rest/stock/'+prodID).then(function(response){
            $scope.ngProductoVenta = response.data;
        })
    }

    //SUMAR PRODUCTO AL CARRITO
    $scope.sumarCarrito = function(prodVenta, uniAVender){       
        if (alcanzaStock(prodVenta, uniAVender) === true){
            $scope.ngItem                       = {};
            $scope.ngItem.nombreProducto        = prodVenta.nombre;
            $scope.ngItem.descripcionProducto   = prodVenta.descripcion;
            $scope.ngItem.precioUnitario        = prodVenta.precio;
            $scope.ngItem.cantidad              = uniAVender;
            $scope.ngItem.precioTotal           = ($scope.ngItem.precioUnitario * $scope.ngItem.cantidad);
            $scope.ngCarrito.push($scope.ngItem);
            $scope.ngTotalVenta = totalVenta();
        }
        else{
            $scope.message="Cantidad necesaria no disponibe.";
        }
    }

    //CREAR VENTA
    $scope.crearVenta = function(){
        $scope.ngVenta = {};
        //$scope.CurrentDate = new Date();        
        //$scope.ngFecha = new Date(18/12/1987);
        $scope.ngVenta.precio   = $scope.ngTotalVenta; 
        //$scope.ngVenta.fecha    = $scope.CurrentDate;
        $scope.ngVenta.items    = $scope.ngCarrito;

        $http.get('/rest/venta/alta', $scope.ngVenta)
        .then(function successCallback (response){
            $scope.ngVentasCreadas.push(response.data);
        });
        $scope.ngVenta={};
    }




    //VERIFICA SI EXISTE STOCK SUFICIENTE PARA LA VENTA
    function alcanzaStock(producto, unidades){
        if (producto.cantidad >= unidades){
            return true;
        }
        else{
            return false;
        }
    }

    //CALCULA TOTAL DE LA VENTA
    function totalVenta(){
        var total = 0.00;
        angular.forEach($scope.ngCarrito, function(key){
            total = total + key.precioTotal;
        })
        return total;
    }


    


    //INIT
    $scope.activeVenta = true;
    $scope.ngProductoVenta = {};
    $scope.ngCarrito = [];
    $scope.ngVentasCreadas = [];
    $scope.message='';
    $scope.getAllProductos();
    $scope.getAllVentas();
});