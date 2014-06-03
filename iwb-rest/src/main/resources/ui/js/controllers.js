'use strict';

/* CONTROLLERS MODULE: containts controllers used in the application */

angular.module('iwbApp.controllers', ['iwbApp.services','iwbApp.configuration', 'google-maps', 'angularFileUpload']).


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
  .controller('SearchItemController', ['$scope', 'QueryItemService','CommonFunctionsService', '$routeParams', '$location',  
    function($scope, QueryItemService, CommonFunctionsService, $routeParams, $location) {
      //init css
      CommonFunctionsService.unset_home_css();
      //get query string from url
      $scope.query = '';
      $scope.queryString = $routeParams.query;
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
          $scope.objectDetail = $scope.results[0];
        });
      }

      $scope.editItem = function(e){
        e.stopPropagation();
        $location.url($scope.objectDetail.link.href);
      }

  }])

  /*
    controller: HomeController
    gets the query string inside the text fields, sets the url and redirect to the correct view
  */
  .controller('EditItemController', ['$scope' ,'ItemService','CommonFunctionsService','$location', 'WastesService', '$routeParams', '$upload','$route', 'BASE_PATH_URL',
    function($scope, ItemService, CommonFunctionsService, $location, WastesService, $routeParams, $upload, $route, BASE_PATH_URL) {
      CommonFunctionsService.unset_home_css();

      $scope.item = {};
      $scope.id = $routeParams.id;
      $scope.wastes = [];
      $scope.waste_selected;
      $scope.query = '';

      getItem();
      getAllWastes();

      $scope.submitForm = function (e){
        e.stopPropagation();
        $location.search('query', $scope.query);
        $location.path("/search");
      }

      function getAllWastes(){
        WastesService.wastes(function(response) {
          $scope.wastes = response;
        });
      }

      function getItem(){
        ItemService.get({id:$scope.id},function(response) {
          $scope.item = response;
        });
      }

      $scope.newConstituent = function(){
        if(typeof $scope.item.constituents === 'undefined'){
          $scope.item.constituents = [];
        }
        $scope.item.constituents.push({"name":''});
      }

      $scope.deleteConstituent = function(index){
        $scope.item.constituents.splice(index, 1);
      }

      $scope.pushUpdateToServer = function(){
        ItemService.update({id:$scope.id},$scope.item);
        $route.reload();
      }

      $scope.deleteItem = function(){
        ItemService.deleteItem({id:$scope.id});
        $location.path("/search");
      }

      $scope.uploadImage = function ($files, index){
        //$files: an array of files selected, each file has name, size, and type.
        for (var i = 0; i < $files.length; i++) {
          var timeStamp = new Date().getTime();
          var $file = $files[i];
          $upload.upload({
            url: BASE_PATH_URL+'/api/upload',
            method: 'POST',
            data: {},
            file: $file,
            progress: function(e){}
          }).then(function(data, status, headers, config){
            // file is uploaded successfully
            if(index === -1){
              $scope.item.image = data.data;
            }else{
              $scope.item.constituents[index].image = data.data;
            }  
          });
        }
      }
  }])
  
  /*
    controller: HomeController
    gets the query string inside the text fields, sets the url and redirect to the correct view
  */
  .controller('NewItemController', ['$scope' ,'ItemService', 'ItemServicePost','CommonFunctionsService','$location', 'WastesService', '$upload','$route','BASE_PATH_URL',
    function($scope, ItemService, ItemServicePost, CommonFunctionsService, $location, WastesService, $upload, $route, BASE_PATH_URL) {
      CommonFunctionsService.unset_home_css();

      $scope.item = {'image': ''};
      $scope.wastes = [];
      $scope.waste_selected;
      $scope.query;
      
      getAllWastes();

      function getAllWastes(){
        WastesService.wastes(function(response) {
          $scope.wastes = response;
        });
      }

      $scope.submitForm = function (e){
        e.stopPropagation();
        $location.search('query', $scope.query);
        $location.path("/search");
      }

      $scope.newConstituent = function(){
        if(typeof $scope.item.constituents === 'undefined'){
          $scope.item.constituents = [];
        }
        $scope.item.constituents.push({"name":''});
      }

      $scope.deleteConstituent = function(index){
        $scope.item.constituents.splice(index, 1);
      }

      $scope.pushUpdateToServer = function(){
        ItemServicePost.post($scope.item,function(response) {
          var itemCreated = response;
          $location.path("/items/"+itemCreated._id);
        });
      }

      $scope.uploadImage = function ($files, index){
        //$files: an array of files selected, each file has name, size, and type.
        for (var i = 0; i < $files.length; i++) {
          var timeStamp = new Date().getTime();
          var $file = $files[i];
          $upload.upload({
            url: BASE_PATH_URL+'/api/upload',
            method: 'POST',
            data: {},
            file: $file,
            progress: function(e){}
          }).then(function(data, status, headers, config){
            // file is uploaded successfully
            if(index === -1){
              $scope.item.image = data.data;
            }else{
              $scope.item.constituents[index].image = data.data;
            }  
          });
        }
      }
  }]);