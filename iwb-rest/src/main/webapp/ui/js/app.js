'use strict';

var iwbApp = angular.module('iwbApp', ['ngRoute', 'ngResource', 'google-maps', 'angularFileUpload'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {templateUrl: 'partials/home.html' , controller: 'HomeController'});
  $routeProvider.when('/search', {templateUrl: 'partials/q-results_test.html', controller: 'SearchItemController'});
  $routeProvider.when('/items/:id', {templateUrl: 'partials/item-selected.html', controller: 'EditItemController'});
  $routeProvider.when('/items', {templateUrl: 'partials/new-item.html', controller: 'NewItemController'});
  $routeProvider.otherwise({redirectTo: '/home'});
}])
.constant('BASE_PATH_URL','/iwb-rest')
.constant('HOST','localhost');
