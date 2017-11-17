var app = angular.module('figaro', ['chart.js']);
app.controller('estadisticasController', function ($scope, $http) {
	
		  $scope.labels = ['Jun', 'Jul', 'Ago', 'Sep', 'Oct'];
		  $scope.series = ['Luz'];		 
		  $scope.data = [		    
		    [45, 80, 57, 87, 100]
		  ];

		    
});