var app = angular.module('figaro', []);
app.controller('clientesController', function ($scope, $http) {
    
	$http.get("http://localhost:8080/clientes/todos").then(function (response) {
		$scope.clientes = response.data;
	});

    $scope.nuevoCliente = {};
    $scope.submitForm = function() {
    	$scope.nuevoCliente.fechaIngreso=getToday();
    	$scope.nuevoCliente.ultimaVisita=getToday();
        $http.post('http://localhost:8080/clientes/alta', $scope.nuevoCliente);
        $scope.clientes.push($scope.nuevoCliente);
        $scope.nuevoCliente={};
        closeModal();
    };

});