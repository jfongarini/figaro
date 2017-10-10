app.controller('clientesController', function ($scope, $http) {
    
    //OBTENER LISTA DE CLIENTES
    $scope.getAll = function() {
        $http.get("/rest/clientes").then(function (response) {
            $scope.clientes = response.data;
        });
    };

    //CLICK NUEVO CLIENTE
    $scope.newClient = function() {
        $scope.ngCliente={};
        $scope.message='';
        ($scope.modalOpen == true) ? $('#modal-clientes').addClass("modal-on-top") :  openModal("modal-clientes");
    }

    //CLICK FILA CLIENTE
    $scope.detailClient = function(event){
        $scope.message='';
        $scope.update = true;
        $scope.clienteID = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/clientes/'+$scope.clienteID).then(function (response) {
            $scope.ngCliente = response.data;
            openModal("modal-clientes");
	    });
    };

    //CLICK ACEPTAR FORMULARIO
    $scope.sendClient = function() {
        if($scope.update == null){
            $scope.ngCliente.fechaIngreso = getToday();
            $scope.ngCliente.ultimaVisita = getToday();
            $http.post('/rest/clientes/alta', $scope.ngCliente)
            .then(function successCallback(response) {
                $scope.clientes.push(response.data);
                closeModal("modal-clientes");
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }else{
            $http.put('/rest/clientes/actualizar/'+ $scope.clienteID, $scope.ngCliente).then(
                function successCallback(response) {
                    $scope.getAll();
                    closeModal("modal-clientes");
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
        $scope.update = null;
        $scope.ngCliente = {};
        ($scope.clientesScreen == null) ? $('#modal-clientes').removeClass("modal-on-top") : closeModal("modal-clientes");
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
            $scope.discardClient("modal-clientes");
        }
    });

    //INIT
    $scope.search = '';
    $scope.ngCliente = {};
    $scope.getAllCiudades();
    $scope.getAll();

});