'use strict';

angular.module('iwbApp').controller('HomeController', ['$scope' ,'CommonFunctionsService','$location',  
    function($scope, CommonFunctionsService, $location) {
      CommonFunctionsService.init_home_css();
      $scope.name = 'HomeController';
      $scope.query = '';
      $scope.list = [];
      $scope.submitForm = function (e){
        $location.search('query', $scope.query);
        $location.path("/search");
      }
      
      $scope.goToCreateItem = function(e){
        e.stopPropagation();
        $location.url("/items");
      }
     
  }]);
