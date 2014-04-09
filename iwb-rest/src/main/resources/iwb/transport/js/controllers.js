'use strict';

/* CONTROLLERS MODULE: containts controllers used in the application */

angular.module('iwbApp.controllers', ['iwbApp.services','iwbApp.configuration']).


  /*
    controller: HomeController
    gets the query string inside the text fields, sets the url and redirect to the correct view
  */
  controller('HomeController', ['$scope' ,'QueryItemService','CommonFunctionsService','$location',  
    function($scope, QueryItemService, CommonFunctionsService, $location) {
      CommonFunctionsService.init_home_css();
      $scope.name = 'HomeController';
      $scope.query = '';
      $scope.list = [];
      $scope.submitForm = function (e){
        e.stopPropagation();
        $location.search('query', $scope.query);
        $location.path("/search");
      }
  }])


  /*
    controller: SearchItemController
    gets the query_string and use the restx API to retreive matching items
  */
  .controller('SearchItemController', ['$scope', 'QueryItemService','CommonFunctionsService', '$routeParams','BASE_PATH_IMG','$location',  
    function($scope, QueryItemService, CommonFunctionsService, $routeParams, BASE_PATH_IMG, $location) {
      //init css
      CommonFunctionsService.unset_home_css();
      //get query string from url
      $scope.queryString = $routeParams.query;
      //some variables
      $scope.results = [];
      $scope.objectDetail = {};
      //retreive informations to print using restx API
      getItems();

      /*Scope functions*/
      $scope.submitForm = function (e){
        e.stopPropagation();
        $location.search('query', $scope.queryString);
        $location.path("/search");
      }
      $scope.updateDesc = function(index){
        $scope.objectDetail = $scope.results[index];
      }
      $scope.gtItem =  function(event,index){
        event.stopPropagation();
        $scope.updateDesc(index);
        $location.url($scope.objectDetail.link.href);
      }

      /*Other functions*/
      function getItems(){
        QueryItemService.query({q_string:$scope.queryString},function(response) {
          $scope.results = response;
          for (var i=0;i<$scope.results.length;i++)
          { 
            CommonFunctionsService.set_img($scope.results[i],BASE_PATH_IMG);
            if($scope.results[i].constituents && $scope.results[i].constituents.length >0)
            {
              for(var j=0; j<$scope.results[i].constituents.length; j++)
              {
                CommonFunctionsService.set_img($scope.results[i].constituents[j],BASE_PATH_IMG);
              }
            }
          }
          $scope.objectDetail = $scope.results[0];
        });
      }

  }])


  /*
    controller: SelectedItemController
    get the id parameter from the url, use the restx API retreive te matching item and update item using PUT query when submit
  */
  .controller('SelectedItemController', ['$scope','$routeParams','ItemService','CommonFunctionsService','WastesService','BASE_PATH_IMG','$route',  
    function($scope,$routeParams, ItemService, CommonFunctionsService, WastesService, BASE_PATH_IMG, $route) {
    //init css
    CommonFunctionsService.unset_home_css();
    //gets the item id from the url
    $scope.id = $routeParams.id;
    //some used variables
    $scope.item = {};
    $scope.wastes = [];
    $scope.waste_selected = {};
    $scope.isConstituent = false;
    $scope.curIndex = 0;
    //properties bind with form fields
    $scope.name_field = '';
    $scope.barcode_field ='';
    $scope.image_field = '';
    $scope.waste_type_field = '';
    //retreive informations to print using API
    getItem();
    getAllWastes();

    /*Scope functions*/
    $scope.editform = function (index){
      updateChanges();
      $scope.curIndex = index;
      if(index === 0){
        $scope.isConstituent = false;
        $scope.name_field = $scope.item.name;
      }else{
        $scope.isConstituent = true;
        $scope.name_field = $scope.item.constituents[index-1].name;
      }  
    }
    $scope.submitForm = function (event){
        event.preventDefault;
        updateChanges();
        CommonFunctionsService.unset_img($scope.item);
        //Set images constituents
        if($scope.item.constituents && $scope.item.constituents.length >0)
        {
          for(var j=0; j<$scope.item.constituents.length; j++)
          {
            CommonFunctionsService.unset_img($scope.item.constituents[j]);
          }
        }
        ItemService.update({id:$scope.id}, $scope.item);
        $route.reload();
    }

    /*Other function*/
    function getItem(){
      ItemService.get({id:$scope.id},function(response) {
        $scope.item = response;

        CommonFunctionsService.set_img($scope.item,BASE_PATH_IMG);
        //Set images constituents
        if($scope.item.constituents && $scope.item.constituents.length >0)
        {
          for(var j=0; j<$scope.item.constituents.length; j++)
          {
            CommonFunctionsService.set_img($scope.item.constituents[j],BASE_PATH_IMG);
          }
        }
        //Init form fields
        $scope.name_field = $scope.item.name;
        $scope.barcode_field = $scope.item.barcode;
        $scope.waste_selected = ($scope.item.constituents) ? $scope.item.constituents[0].wasteType : {};
      });
    }
    function getAllWastes(){
      WastesService.wastes(function(response) {
      $scope.wastes = response;
    });
    }
    function updateChanges(){
      if($scope.curIndex === 0){
        //This updates the item fields
        $scope.item.name = $scope.name_field;
        $scope.item.barcode = $scope.barcode_field;
      }else{
        //This updates the constituents fields
        $scope.item.constituents[$scope.curIndex-1].name = $scope.name_field;
      }
    }
  }])

;