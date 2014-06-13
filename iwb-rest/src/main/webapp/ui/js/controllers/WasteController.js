'use strict';

angular.module('iwbApp').controller('WasteController', ['$scope' , 'WasteService','CommonFunctionsService','$location', '$routeParams', '$modal', 
    function($scope, WasteService,CommonFunctionsService, $location, $routeParams, $modal) {
      CommonFunctionsService.unset_home_css();

      $scope.query = '';
      $scope.wastes = [];
      $scope.selectedWaste = {};
      $scope.acronyms = [];

      getAllWastes();

      function getAllWastes(){
        WasteService
          .getAllWastes()
          .then( function( result )
          {
            $scope.wastes = result.data;
            WasteService
            .getAllAcronyms()
              .then( function( result )
              {
                $scope.acronyms = result.data;
              });
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

      $scope.editWaste = function (index) {
        $scope.selectWaste(index);
        var editWasteModal = $modal.open({
            templateUrl:"partials/modalContentWaste.html",
            controller:"ModalInstanceController",
            size:'lg',
            resolve: {
            selectedWaste: function () {
              return $scope.selectedWaste;
            },
            acronyms: function () {
              return $scope.acronyms;
            },
            update: function () {
              return true;
            }
          }
        });

        editWasteModal.result.then(function () {

        });
      }

      $scope.createWaste = function () {
        var editWasteModal = $modal.open({
            templateUrl:"partials/modalContentWaste.html",
            controller:"ModalInstanceController",
            size:'lg',
            resolve: {
            selectedWaste: function () {
              return {};
            },
            acronyms: function () {
              return $scope.acronyms;
            },
            update: function () {
              return false;
            }
          }
        });

        editWasteModal.result.then(function (wasteCreated) {
          $scope.wastes.push(wasteCreated);
        });
      }

      $scope.deleteWaste = function (index) {
        WasteService
            .deleteWaste($scope.wastes[index]._id)
            .then( function( result )
            {
              $scope.wastes.splice(index, 1);
          });
      };

      $scope.selectWaste =  function(index){
        $scope.selectedWaste = $scope.wastes[index];
      }

  }]);
