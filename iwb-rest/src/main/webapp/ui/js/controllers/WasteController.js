'use strict';

angular.module('iwbApp').controller('WasteController', ['$scope' , 'WasteService','CommonFunctionsService','$location', '$routeParams', '$modal', 
    function($scope, WasteService,CommonFunctionsService, $location, $routeParams, $modal) {
      CommonFunctionsService.unset_home_css();

      $scope.query = '';
      $scope.wastes = [];

      getAllWastes();

      function getAllWastes(){
        WasteService
          .getAllWastes()
          .then( function( result )
          {
            $scope.wastes = result.data;
          });
      }

      $scope.goToCreateItem = function(e){
        e.stopPropagation();
        $location.url("/items");
      }

      $scope.goBackHome = function(e){
        e.stopPropagation();
        $location.url("/home");
      }

      $scope.submitForm = function (e){
        e.stopPropagation();
        $location.search('query', $scope.query);
        $location.path("/search");
      }

      $scope.editWaste = function () {
        var editWasteModal = $modal.open({
            templateUrl:"partials/modalContentWaste.html",
            controller:"ModalInstanceController",
            size:'lg'
        });

        editWasteModal.result.then(function () {
            
        });
    }

  }]);
