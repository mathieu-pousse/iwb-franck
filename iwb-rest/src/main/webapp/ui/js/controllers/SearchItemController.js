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
              $scope.colorItemTrash = $scope.objectDetail.trashes[0].color;
            }else{
              $scope.colorItemTrash = null;
            }
            if($scope.objectDetail.constituents){
              $scope.colorConstituentsTrash = [];
              for (var i = 0; i < $scope.objectDetail.constituents.length; i++) {
                if($scope.objectDetail.constituents[i].trashes){
                  var color = ($scope.objectDetail.constituents[i].trashes[0]) ? 
                              $scope.objectDetail.constituents[i].trashes[0].color : null;
                  $scope.colorConstituentsTrash.push(color);
                }
              };
            }
          });
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


  }]);