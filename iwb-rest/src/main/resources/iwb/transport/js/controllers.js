'use strict';

/* Controllers */

angular.module('iwbApp.controllers', ['iwbApp.services',]).

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
    $scope.query = '';
    $scope.list = [];
    init_css();
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
  .controller('SearchItemController', ['$scope', 'SearchItemService', '$routeParams',  function($scope, SearchItemService, $routeParams) {
    $scope.queryString = $routeParams.query;
    $scope.results = [];
    $scope.queryObject = {q_string:$scope.queryString};
    init_css();
    getItems();
    function getItems(){
      SearchItemService.query($scope.queryObject,function(response) {
      $scope.results = response;
      });
    }
    function init_css(){
      document.getElementById("home_css").setAttribute("href","");
    }

  }])

  .controller('HeaderController', ['$scope', '$location', function($scope, $location) {
      $scope.isActive = function (viewLocation) {
          return viewLocation === $location.path();
      };
  }])

;