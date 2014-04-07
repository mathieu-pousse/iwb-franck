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
    $scope.updateDesc = function(event){
      event.stopPropagation();
      var id = $(event.target).attr('class');
      $scope.objectDetail = $scope.results[id];
    }

  }])

  .controller('HeaderController', ['$scope', '$location', function($scope, $location) {
      $scope.isActive = function (viewLocation) {
          return viewLocation === $location.path();
      };
  }])

;