var app = angular.module('figaro', []);
app.controller('turnosController', function ($scope, $http) {
    
    $scope.horarios = [{"hora":"08"},{"hora":"09"},{"hora":"10"},{"hora":"11"},{"hora": "12"},{"hora": "13"},{"hora": "14"},{"hora": "15"},{"hora": "16"},{"hora": "17"},{"hora": "18"},{"hora": "19"},{"hora": "20"}];
    $scope.minutos  = [{"minuto":"00"},{"minuto":"15"},{"minuto":"30"},{"minuto": "45"}];


    //OBTENER LISTA DE TURNOS
    $scope.getAll = function() {
        $http.get("/rest/turnos").then(function (response) {
            $scope.clientes = response.data;
        });
    };

    //CLICK NUEVO TURNO
    $scope.newTurno = function() {
        $scope.ngTurno = {};
        $scope.ngTurno.cobrado = false;
        $scope.getAllPeluqueros();
        $scope.isNuevoTurno = true;
        openModal();
    };


    //CLICK ACEPTAR FORMULARIO
    $scope.sendTurno = function() {
        $scope.ngTurno.desde = getStringDate($scope.ngDateTurno)+" "+$scope.startHour.hora + ":" + $scope.startMinutes.minuto;
        $scope.ngTurno.hasta = getStringDate($scope.ngDateTurno)+" "+$scope.endHour.hora + ":" + $scope.endMinutes.minuto;
        $scope.ngTurno.peluquero = $scope.peluquero.nombre;
        $scope.ngTurno.trabajos = $scope.trabajosSeleccionados;
        if($scope.isNuevoTurno === true && $scope.validateTurno() === true){
            $http.post('/rest/turnos/alta', $scope.ngTurno)
            .then(function successCallback(response) {
                $scope.discardTurno();
                $scope.getTurnos();
                closeModal();
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }
    };


    //OBTENER TURNOS
    $scope.getTurnos = function() {
        $http.get('rest/turnos',{params:{fecha: getStringDate($scope.ngDateTurno)}})
        .then(function successCallback(response) {
            $scope.turnos = response.data;
            $scope.getTotalDiario($scope.turnos);
        });
    };

    //OBTENER TOTAL DE TURNO
    $scope.getTotalTurno = function(trabajos) {
        var total = 0;
        for(var i = 0; i < trabajos.length; i++)
            total += trabajos[i].precio;
    return total;
    };

    //OBTENER TOTAL DIARIO
    $scope.getTotalDiario = function(turnos) {
        var total = 0;
        for(var i = 0; i < turnos.length; i++)
        for(var j = 0; j < turnos[i].trabajos.length; j++)
            total += turnos[i].trabajos[j].precio;
    return total;
    };



    //VALIDAR FORMULARIO
    $scope.validateTurno = function() {
        if (!("cliente" in $scope.ngTurno)){
            $scope.message="Seleccione un cliente.";
            return false
        }
       
        if (!($scope.trabajosSeleccionados.length > 0)){
            $scope.message="Seleccione al menos un trabajo.";
            return false
        }
        return true
    }



    // AGREGAR TRABAJOS
    $scope.addTrabajo = function toggleSelection(trabajo) {
        var idx = $scope.trabajosSeleccionados.indexOf(trabajo);
        if (idx == -1) {
          trabajo.id = null;
          $scope.totalTrabajosSeleccionados += trabajo.precio;
          $scope.trabajosSeleccionados.push(trabajo);
        }
    };

    // QUITAR TRABAJOS
    $scope.removeTrabajo = function toggleSelection(trabajo) {
        var idx = $scope.trabajosSeleccionados.indexOf(trabajo);
        if (idx > -1) {
            $scope.totalTrabajosSeleccionados -= trabajo.precio;
            $scope.trabajosSeleccionados.splice(idx, 1);
        }
    };

    //BUSCAR CLIENTE
    $scope.searchCliente = function() {
        if ($scope.queryCliente == "") 
            return $scope.clientes=[];       
        $http.get('/rest/clientes/buscar',{params: { search: $scope.queryCliente }})
        .then(function successCallback(response) {
            $scope.clientes = response.data; 
        });  
    };

    //BUSCAR TRABAJO
    $scope.searchTrabajo = function() {
        if ($scope.queryTrabajo == "") 
            return $scope.trabajos=[];       
        $http.get('rest/configuracion/trabajos/buscar',{params: { search: $scope.queryTrabajo }})
        .then(function successCallback(response) {
            $scope.trabajos = response.data;
        });  
    };

    //OBTENER LISTA DE PELUQUEROS
    $scope.getAllPeluqueros = function() {
        $http.get("/rest/configuracion/peluqueros").then(function (response) {
            $scope.peluqueros = response.data;
        });
    };


    //DESCARTAR FORMULARIO
    $scope.discardTurno = function(event){
        $scope.startHour= "";
        $scope.startMinutes= "";
        $scope.endHour = ""; 
        $scope.endMinutes = "";
        $scope.peluquero = "";
        $scope.clientes=[];
        $scope.trabajos=[];
        $scope.trabajosSeleccionados=[];
        $scope.totalTrabajosSeleccionados=0;
        $scope.queryCliente ='';
        $scope.queryTrabajo ='';
        $scope.message='';    
        closeModal();
    };

   

    $scope.trabajosSeleccionados=[];
    $scope.totalDiario=0;
    $scope.turnos={};
    $scope.ngTurno={};
    $scope.ngDateTurno = stringToDate(getToday());
    $scope.getTurnos();
    

});