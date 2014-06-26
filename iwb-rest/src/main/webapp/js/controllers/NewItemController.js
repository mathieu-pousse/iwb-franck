'use strict';

angular.module('iwbApp').controller('NewItemController', ['$scope' ,'ItemService','CommonFunctionsService','$location', 'WasteService', '$upload','$route','BASE_PATH_URL',
    function($scope, ItemService, CommonFunctionsService, $location, WasteService, $upload, $route, BASE_PATH_URL) {
      CommonFunctionsService.unset_home_css();

      $scope.item = {'image': ''};
      $scope.wastes = [];
      $scope.waste_selected;
      $scope.query='';
      
      getAllWastes();

      function getAllWastes(){
        WasteService
          .getAllWastes()
          .then( function( result )
          {
            $scope.wastes = result.data;
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
        ItemService
          .createItem($scope.item)
          .then(function(result){
              var itemCreated = result.data;
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

      $scope.goToCreateItem = function(e){
        e.stopPropagation();
        $location.url("/items");
      }

      $scope.goBackHome = function(e){
        e.stopPropagation();
        $location.url("/home");
      }

      $scope.goToWastes = function(e){
        e.stopPropagation();
        $location.url("/wastes");
      }

      $scope.goToTrashes = function(e){
        e.stopPropagation();
        $location.url("/trashes");
      }
      
  }]);