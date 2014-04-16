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
  .controller('SelectedItemController', ['$scope','$routeParams','ItemService','ItemServiceTrashes' ,'CommonFunctionsService','WastesService', 'WastesServiceRecyling','BASE_PATH_IMG','$route','$upload',  
    function($scope,$routeParams, ItemService, ItemServiceTrashes, CommonFunctionsService, WastesService, WastesServiceRecyling, BASE_PATH_IMG, $route, $upload) {
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
    $scope.curImage;
    $scope.markers = [];
    //properties bind with form fields
    $scope.name_field = '';
    $scope.barcode_field ='';
    $scope.image_field = '';
    $scope.waste_type_field = '';
    $scope.file_field = '';
    //retreive informations to print using API
    getItem();
    getAllWastes();

    //-----------------------------
    //Pagination
    $scope.currentPage = 0;
    $scope.thumbnailData = [];
    $scope.thumbnailDataResized = [];
    $scope.pageSize = 4;
    $scope.numberOfPages= function(){
        return Math.ceil($scope.thumbnailData.length/$scope.pageSize);                
    }
    $scope.righClick = function(){
      if($scope.currentPage < $scope.thumbnailData.length/$scope.pageSize - 1){
        $scope.currentPage++;
        var startIndex = $scope.currentPage*$scope.pageSize;
        var lastIndex = startIndex + $scope.pageSize -1;
        if(lastIndex > $scope.thumbnailData.length)
        {
          lastIndex = $scope.thumbnailData.length -1;
        }
        $scope.thumbnailDataResized = $scope.thumbnailData.slice(startIndex, lastIndex+1);
      }
    }

    $scope.leftClick = function(){
      if($scope.currentPage > 0){
        $scope.currentPage--;
        var startIndex = $scope.currentPage*$scope.pageSize;
        var lastIndex = startIndex + $scope.pageSize -1;
        $scope.thumbnailDataResized = $scope.thumbnailData.slice(startIndex, lastIndex+1);
      }
    }
    //-----------------------------
    $scope.map = {
        center: {
            latitude: 48.111933799999996,
            longitude: -1.6838946999999962
        },
        zoom: 16
    };

    /*Scope functions*/
    $scope.editform = function (index){
      updateChanges();
      $scope.curIndex = index;
      if(index === 0){
        $scope.curImage = $scope.item.image;
        $scope.isConstituent = false;
        $scope.name_field = $scope.item.name;
      }else{
        $scope.isConstituent = true;
        $scope.curImage = $scope.item.constituents[index-1].image;
        $scope.name_field = $scope.item.constituents[index-1].name;
        editMapMarkers($scope.item.constituents[index-1].trashes);
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
      ItemServiceTrashes.get({id:$scope.id,nb_trash:3},function(response) {
        $scope.item = response;
        CommonFunctionsService.set_img($scope.item,BASE_PATH_IMG);
        //Set images constituents
        if($scope.item.constituents && $scope.item.constituents.length >0)
        {
          editMapMarkers($scope.item.constituents[0].trashes);
          for(var j=0; j<$scope.item.constituents.length; j++)
          {
            CommonFunctionsService.set_img($scope.item.constituents[j],BASE_PATH_IMG);
          }
        }else{
          editMapMarkers($scope.item.trashes);
        }
        console.log($scope.markers)
        //Init Thumbnails
        initDataThumbnails();
        //Init form fields
        $scope.curImage = $scope.item.image;
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
      $scope.file_field = '';
    }
    function initDataThumbnails(){
      var thumbObject = {'position': 0, 'image': $scope.item.image};
      $scope.thumbnailData.push(thumbObject);
      if($scope.item.constituents){
        for (var i = 0; i < $scope.item.constituents.length; i++) {
          thumbObject = {'position': i+1, 'image': $scope.item.constituents[i].image}
          $scope.thumbnailData.push(thumbObject);
        };
      }
      $scope.thumbnailDataResized = $scope.thumbnailData.slice(0,$scope.pageSize);
    }
    function editMapMarkers(trashes){
      $scope.markers = [];
      for (var i = 1; i < trashes.length; i++) {
        $scope.markers.push(
          {
            latitude: parseFloat(trashes[i].latitude),
            longitude: parseFloat(trashes[i].longitude)
          }
        );
      };
    }

    $scope.onFileSelect = function($files) {
    //$files: an array of files selected, each file has name, size, and type.
    for (var i = 0; i < $files.length; i++) {
      var timeStamp = new Date().getTime();
      var $file = $files[i];
      $upload.upload({
        url: '/api/upload',
        method: 'POST',
        data: {path: 'BASE_PATH_IMG'},
        file: $file,
        progress: function(e){}
      }).then(function(data, status, headers, config) {
        // file is uploaded successfully
        if($scope.curIndex === 0){
          $scope.item.image = BASE_PATH_IMG+data.data;
        }else{
          $scope.item.constituents[index-1].image = BASE_PATH_IMG+data.data;
        }  
        });
        console.log($scope.item) 
    }
  }
  }])

  /*
    controller: HomeController
    gets the query string inside the text fields, sets the url and redirect to the correct view
  */
  .controller('EditItemController', ['$scope' ,'ItemService','CommonFunctionsService','$location', '$routeParams', 'BASE_PATH_IMG',
    function($scope, ItemService, CommonFunctionsService, $location, $routeParams, BASE_PATH_IMG) {
      CommonFunctionsService.unset_home_css();

      $scope.item = {};
      $scope.id = $routeParams.id;

      getItem();
      function getItem(){
        ItemService.get({id:$scope.id},function(response) {
          console.log(response)
          $scope.item = response;;
          CommonFunctionsService.set_img($scope.item,BASE_PATH_IMG);
          //Set images constituents
          if($scope.item.constituents && $scope.item.constituents.length >0)
          {
            for(var j=0; j<$scope.item.constituents.length; j++)
            {
              CommonFunctionsService.set_img($scope.item.constituents[j],BASE_PATH_IMG);
            }
          }

        });
      }
      
  }])


;