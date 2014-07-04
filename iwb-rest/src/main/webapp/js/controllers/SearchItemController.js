'use strict';

angular.module('iwbApp').controller('SearchItemController', ['$scope', 'ItemService','CommonFunctionsService', '$routeParams', '$location',
    function($scope, ItemService, CommonFunctionsService, $routeParams, $location) {
      //init css
      CommonFunctionsService.unset_home_css();
      $scope.query = '';
      $scope.queryString = $routeParams.query;

      //retreive informations to print using restx API
      getItems();

      /*Scope functions*/
      $scope.submitForm = function (e){
        e.stopPropagation();
        $location.search('query', $scope.queryString);
        $location.path("/search");
      }

      $scope.refreshDescription = function(ObjectId){
        $scope.getObjectDetail(ObjectId);
      }
      $scope.gtItem =  function(event,index){
        event.stopPropagation();
        $location.url($scope.results[index].link.href);
      }

      $scope.updateDesc = function (object){
        $scope.getObjectDetail(object._id);
      }

      /*Other functions*/
      function getItems(){
        ItemService
            .query($scope.queryString)
            .then( function( matchingResults )
            {
              $scope.results = matchingResults.data;
              var firstElement = $scope.results[0];
              if(firstElement){
                $scope.getObjectDetail(firstElement._id);
              }
            });
      }

      $scope.getObjectDetail = function(objectDetailId){
        ItemService
          .getItemObjectDetail(objectDetailId)
          .then( function( response )
          {
            $scope.objectDetail = response.data;
            if($scope.objectDetail.trashes){
              if($scope.checkWasteItem("HOME-YELLOW")){
                $scope.colorItemTrash = 'jaune';
              }else if($scope.checkWasteItem("HOME-GREEN")){
                $scope.colorItemTrash = 'vert';
              }

            }else{
              $scope.colorItemTrash = null;
            }
            if($scope.objectDetail.constituents){
              $scope.colorConstituentsTrash = [];
              for (var i = 0; i < $scope.objectDetail.constituents.length; i++) {
                if($scope.objectDetail.constituents[i].trashes){
                  var color = null;
                  if($scope.checkWasteConstituent(i,'HOME-YELLOW')){
                    color = 'jaune';
                  }else if($scope.checkWasteConstituent(i,'HOME-GREEN')){
                    color = 'vert';
                  }
                  $scope.colorConstituentsTrash.push(color);
                }
              };
            }
          });
      }

      $scope.checkWasteItem =  function (wasteString) {
          return ($scope.objectDetail.trashes.indexOf(wasteString) !== -1);
      }

      $scope.checkWasteConstituent = function (index, wasteString) {
        return ($scope.objectDetail.constituents[index].trashes.indexOf(wasteString) !== -1);
      }
      
      $scope.editItem = function(e){
        e.stopPropagation();
        $location.url($scope.objectDetail.link.href);
      }

      $scope.goToCreateItem = function(e){
        e.stopPropagation();
        $location.url("/items");
      }

      $scope.goToWastes = function(e){
        e.stopPropagation();
        $location.url("/wastes");
      }

      $scope.goToTrashes = function(e){
        e.stopPropagation();
        $location.url("/trashes");
      }

      $scope.goBackHome = function(e){
        e.stopPropagation();
        $location.url("/home");
      }

      $scope.results = {};
      $scope.objectDetail = {};
      $scope.colorConstituentsTrash = [];
      $scope.itemTrashTypes = {
        isSac: false,
        isCompost: false,
        isPharmacy: false,
        isSupermarket: false,
        isBornerelais: false,
      }
      $scope.constituentsTrashTypes = [];
  }]);