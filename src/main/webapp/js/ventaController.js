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
        $scope.ngProductoVenta = {};
        $http.get('/rest/stock/'+prodID).then(function(response){
            $scope.ngProductoVenta = response.data;
            if (existeEnCarrito($scope.ngProductoVenta.nombre, $scope.ngProductoVenta.descripcion) === true){
                actualizarVistaStock($scope.ngProductoVenta);
            }
        })  
    }

    //VERIFICA SI EXISTE STOCK SUFICIENTE PARA LA VENTA
    function alcanzaStock (producto, unidades){
        if (producto.cantidad >= unidades){
            return true;
        }
        else{
            return false;
        }
    }

    //MODIFICA STOCK EN MODAL DETALLE PRODUCTO
    function actualizarVistaStock(producto){
        for(var i = 0; i < $scope.ngCarrito.length; i++)
            if ($scope.ngCarrito[i].nombreProducto === producto.nombre
                 && $scope.ngCarrito[i].descripcionProducto === producto.descripcion)
                producto.cantidad = producto.cantidad - $scope.ngCarrito[i].cantidad;
    }

    //VERIFICA SI EL ITEM YA EXISTE EN EL CARRITO
    function existeEnCarrito (iNombre, iDescripcion){
        for(var i = 0; i < $scope.ngCarrito.length; i++)
            if ($scope.ngCarrito[i].nombreProducto === iNombre
                 && $scope.ngCarrito[i].descripcionProducto === iDescripcion)
                return true;
            else{
                return false;
            }
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
            resetDetalleProducto();
        }
        else{
            $scope.message="Cantidad necesaria no disponibe.";
        }
    }

    //CREAR VENTA
    $scope.crearVenta = function(){
        $scope.ngVenta = {};        
        $scope.ngFecha = stringToDate(getToday());
        $scope.ngVenta.precio   = $scope.ngTotalVenta; 
        $scope.ngVenta.fecha    = $scope.ngFecha;
        $scope.ngVenta.items    = $scope.ngCarrito;

        $http.post('/rest/venta/alta', $scope.ngVenta)
        .then(function successCallback(response){
            $scope.ngVentasCreadas.push(response.data);
        },
        function errorCallback(response) {
            $scope.message="No se ha podido guardar la Venta";
        });
        resetCarrito();
    }

    //CALCULA TOTAL DE LA VENTA
    function totalVenta(){
        var total = 0.00;
        angular.forEach($scope.ngCarrito, function(key){
            total = total + key.precioTotal;
        })
        return total;
    }

    //LIMPIA MODAL DETALLE PRODUCTO
    function resetDetalleProducto(){
        $scope.producto = {};
        $scope.ngProductoVenta = {};
        $scope.aVender = 0;
    }

    //LIMPIA MODAL DETALLE PRODUCTO
    function resetCarrito(){
        $scope.ngVenta={};
        $scope.ngCarrito=[];
    }

    //QUITAR ITEM DE CARRITO
    $scope.quitarItem = function(index){
        $scope.ngCarrito.splice(index);
    };

    //INIT
    $scope.activeVenta = true;
    $scope.ngProductoVenta = {}; // se usa para visualizar detalle
    $scope.ngCarrito = []; //Lista de Item
    $scope.ngVenta={}; 
    $scope.ngVentasCreadas = [];
    $scope.message='';
    $scope.getAllProductos();
    $scope.getAllVentas();
});