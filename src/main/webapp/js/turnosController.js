app.controller('turnosController', function ($scope, $http) {
    
    $scope.horarios = ["08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45","16:00","16:15","16:30","16:45","17:00","17:15","17:30","17:45","18:00","18:15","18:30","18:45","19:00","19:15","19:30","19:45","20:00","20:15","20:30","20:45"];

    //INIT TURNOS
    $scope.init = function(){
        $scope.activeTurnos = true;
        $scope.ngDateTurno = stringToDate(getToday());
        $scope.getTurnos();
        $scope.queryCliente ='';
        $scope.trabajosSeleccionados=[];
        $scope.totalDiario=0;
        $scope.turnos={};
        $scope.ngTurno={};
        $scope.clientes=[];
    }


    //INIT TURNOS DE CLIENTE
    $scope.getTurnosDeCliente = function(){
        $scope.activeTurnos = true;
        var clienteId = window.location.href.split("/").pop();
        $http.get('/rest/turnos/cliente/'+clienteId)
        .then(function successCallback(response) {
            $scope.turnos = response.data;
            if ( $scope.turnos.length > 0){
                $scope.cliente = ($scope.turnos[0].cliente.nombre +' '+ $scope.turnos[0].cliente.apellido) 
                $scope.getTotalDiario($scope.turnos);
            }
        });
    }


    //CLICK NUEVO TURNO
    $scope.newTurno = function() {
        $scope.ngTurno = {};
        $scope.totalTrabajosSeleccionados=0;
        $scope.ngTurno.cobrado = false;
        $scope.isNuevoTurno = true;
        $scope.getAllPeluqueros();
        openModal("modal-turnos");
    };

    //CLICK FILA TURNO
    $scope.detailTurno = function(event){
        $scope.discardTurno();
        $scope.isNuevoTurno = false;
        $scope.getAllPeluqueros();
        $scope.turnoId = event.currentTarget.getAttribute("data-id");
        $http.get('/rest/turnos/'+$scope.turnoId).then(function (response) {
            $scope.ngTurno = response.data;
            $scope.startHour = $scope.ngTurno.desde.split(' ')[1];
            $scope.endHour = $scope.ngTurno.hasta.split(' ')[1];
            $scope.peluquero = $scope.ngTurno.peluquero;
            $scope.trabajosSeleccionados = $scope.ngTurno.trabajos;
            $scope.totalTrabajosSeleccionados=$scope.getTotalTurno($scope.trabajosSeleccionados);
            $scope.update=true;
            openModal("modal-turnos");
        });
    };

    //CLICK ACEPTAR FORMULARIO
    $scope.sendTurno = function() {
        $scope.ngTurno.desde = getStringDate($scope.ngDateTurno)+" "+$scope.startHour;
        $scope.ngTurno.hasta = getStringDate($scope.ngDateTurno)+" "+$scope.endHour;
        $scope.ngTurno.peluquero = $scope.peluquero;
        $scope.ngTurno.trabajos = $scope.trabajosSeleccionados;
        if($scope.isNuevoTurno === true && $scope.validateTurno() === true){
            $http.post('/rest/turnos/alta', $scope.ngTurno)
            .then(function successCallback(response) {
                $scope.discardTurno();
                $scope.getTurnos();
                closeModal("modal-turnos");
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }else if ($scope.validateTurno() === true){
            $http.put('/rest/turnos/actualizar/'+ $scope.turnoId, $scope.ngTurno).then(
                function successCallback(response) {
                    $scope.getTurnos();
                    $scope.discardTurno();
                    closeModal("modal-turnos");
              }, function errorCallback(response) {
                $scope.message=response.data.message;
            });
        }     
    };

    //OBTENER TURNOS
    $scope.getTurnos = function() {
        $http.get('/rest/turnos',{params:{fecha: getStringDate($scope.ngDateTurno)}})
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
        if (!("cliente" in $scope.ngTurno) || $scope.ngTurno.cliente === null){
            $scope.message="Seleccione un cliente.";
            return false
        }
        if (!($scope.trabajosSeleccionados.length > 0)){
            $scope.message="Seleccione al menos un trabajo.";
            return false
        }
        return true
    }

    //ELIMINTAR TURNO
    $scope.deleteTarget = function(id) {                
        $http.delete('/rest/turnos/eliminar/'+id).then(function (response) {
            $scope.getTurnos();
        });
        closeModal("modal-confirmarDelete");
    };
   
    //CONFIRMA ELIMINTAR MOVIMIENTO
    $scope.confirmDelete = function(id) {
        $scope.idTarget = id;
        openModal("modal-confirmarDelete");
        
    };

    //CANCELAR ELIMINAR
    $scope.discardConfirm = function(event){
        $scope.ngMovimiento = {};
        closeModal("modal-confirmarDelete");
    };

    //AGREGAR TRABAJOS
    $scope.addTrabajo = function (trabajo) {
        if($scope.isSeleccionado(trabajo) === false) {
          trabajo.id = null;
          $scope.totalTrabajosSeleccionados += trabajo.precio;
          $scope.trabajosSeleccionados.push(trabajo);
        }
    };

    //QUITAR TRABAJOS
    $scope.removeTrabajo = function (trabajo) {
        $scope.trabajosSeleccionados.splice($scope.isSeleccionado(trabajo), 1);
        $scope.totalTrabajosSeleccionados -= trabajo.precio;

    };

    //TRABAJO ESTA SELECCIONADO
    $scope.isSeleccionado = function (trabajo) {
        for(var i = 0; i < $scope.trabajosSeleccionados.length; i++)
        if ($scope.trabajosSeleccionados[i].descripcion === trabajo.descripcion)
            return i;
        return false;
    };

    //SET CLIENTE
    $scope.setCliente = function (cliente) {
        $scope.ngTurno.cliente = cliente;
        $scope.queryCliente='';
        $scope.clientes=[];
    }
    
    //TOGGLE COBRADO
    $scope.setCobrado = function (turnoId) {
       $http.patch('/rest/turnos/'+turnoId+'/cobrado');
    }

    //BUSCAR CLIENTE
    $scope.searchCliente = function() {
        if ($scope.queryCliente.length < 2) 
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
        $http.get('/rest/configuracion/trabajos/buscar',{params: { search: $scope.queryTrabajo }})
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
        $scope.endHour = ""; 
        $scope.peluquero = "";
        $scope.clientes=[];
        $scope.trabajos=[];
        $scope.trabajosSeleccionados=[];      
        $scope.queryCliente ='';
        $scope.queryTrabajo ='';
        $scope.message='';
        $scope.update=false;
        closeModal("modal-turnos");
    };

    //APRETAR ESCAPE
    document.addEventListener('keyup', function(e) {
        if (e.keyCode == 27) {
            $scope.discardTurno();
        }
    });
    
   
});