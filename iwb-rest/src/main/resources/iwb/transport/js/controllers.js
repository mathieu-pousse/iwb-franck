'use strict';

/* Controllers */

angular.module('iwbApp.controllers', ['iwbApp.services','iwbApp.configuration']).

  controller('MyCtrl1', ['$scope', 'AngularIssues', function($scope, AngularIssues) {
    $scope.data = {};
    AngularIssues.query(function(response) {
      $scope.data.issues = response;
    });
  }])

  /*
    HomeController
    gets the query string inside the text fields, sets the url and redirect to the correct view
  */
  .controller('HomeController', ['$scope' ,'SearchItemService','$location',  function($scope, SearchItemService, $location) {
    init_css();
    $scope.query = '';
    $scope.list = [];
    $scope.submitForm = function (e){
      e.stopPropagation();
      $location.search('query', $scope.query);
      $location.path("/search");
    }
    function init_css(){
      document.getElementById("home_css").setAttribute("href","css/personalized.css");
    }
  }])

  /*
    SearchItemController
  */
  .controller('SearchItemController', ['$scope', 'SearchItemService', '$routeParams','BASE_PATH_IMG','$location',  function($scope, SearchItemService, $routeParams, BASE_PATH_IMG, $location) {
    init_css();
    $scope.queryString = $routeParams.query;
    $scope.results = [];
    $scope.objectDetail = {};
    $scope.queryObject = {q_string:$scope.queryString};
    getItems();
    function getItems(){
      SearchItemService.query($scope.queryObject,function(response) {
        $scope.results = response;
        for (var i=0;i<$scope.results.length;i++)
        { 
          set_img($scope.results[i]);
          if($scope.results[i].constituents && $scope.results[i].constituents.length >0)
          {
            for(var j=0; j<$scope.results[i].constituents.length; j++)
            {
              set_img($scope.results[i].constituents[j]);
            }
          }
        }
        $scope.objectDetail = $scope.results[0];
      });
    }
    function init_css(){
      document.getElementById("home_css").setAttribute("href","");
    }
    function set_img(item){
      var name_img = item.image;
      if(name_img && name_img.length >0){
        item.image = BASE_PATH_IMG+name_img;
      }else{
        item.image = BASE_PATH_IMG+'imageNotFound.jpg';
      }
    }
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

  }])

  /*
    SelectedItemController
  */
  .controller('SelectedItemController', ['$scope','$routeParams','SearchItemWithIdService','TransverseService','WastesService','BASE_PATH_IMG','UpdateItemService','$route',  
    function($scope,$routeParams, SearchItemWithIdService,TransverseService,WastesService,BASE_PATH_IMG,UpdateItemService,$route) {
    init_css();
    $scope.item = {};
    $scope.wastes = [];
    $scope.waste_selected = {};
    $scope.id = $routeParams.id;
    $scope.isConstituent = false;
    $scope.curIndex = 0;
    $scope.name_field = '';
    $scope.barcode_field ='';
    $scope.image_field = '';
    $scope.waste_type_field = '';

    getItem();
    getAllWastes();

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
    function getItem(){
      SearchItemWithIdService.get({id:$scope.id},function(response) {
        $scope.item = response;

        TransverseService.set_img($scope.item,BASE_PATH_IMG);
        //Set images constituents
        if($scope.item.constituents && $scope.item.constituents.length >0)
        {
          for(var j=0; j<$scope.item.constituents.length; j++)
          {
            TransverseService.set_img($scope.item.constituents[j],BASE_PATH_IMG);
          }
        }
        //Init form fields
        $scope.name_field = $scope.item.name;
        $scope.barcode_field = $scope.item.barcode;
        $scope.waste_selected = $scope.item.constituents[0].wasteType;
      });
    }
    function init_css(){
      document.getElementById("home_css").setAttribute("href","");
    }
    function getAllWastes(){
      WastesService.wastes(function(response) {
      $scope.wastes = response;
    });
    }
    function updateChanges(){
      //alert($scope.item_edited.image);
      if($scope.curIndex === 0){
        //Thi updates the item fields
        $scope.item.name = $scope.name_field;
        $scope.item.barcode = $scope.barcode_field;
      }else{
        //This updates the constituents fields
        $scope.item.constituents[$scope.curIndex-1].name = $scope.name_field;
      }
    }
    $scope.submitForm = function (event){
        event.preventDefault;
        updateChanges();
        TransverseService.unset_img($scope.item);
        //Set images constituents
        if($scope.item.constituents && $scope.item.constituents.length >0)
        {
          for(var j=0; j<$scope.item.constituents.length; j++)
          {
            TransverseService.unset_img($scope.item.constituents[j]);
          }
        }
        UpdateItemService.update({id:$scope.id}, $scope.item);
        $route.reload();
    }
  }])

  .controller('HeaderController', ['$scope', '$location', function($scope, $location) {
      $scope.isActive = function (viewLocation) {
          return viewLocation === $location.path();
      };
  }])

;