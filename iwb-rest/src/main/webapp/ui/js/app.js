'use strict';

var iwbApp = angular.module('iwbApp', ['ngRoute', 'ngResource', 'google-maps', 'angularFileUpload', 'ui.bootstrap'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {templateUrl: 'partials/home.html' , controller: 'HomeController'});
  $routeProvider.when('/search', {templateUrl: 'partials/q-results_test.html', controller: 'SearchItemController'});
  $routeProvider.when('/items/:id', {templateUrl: 'partials/item-selected.html', controller: 'EditItemController'});
  $routeProvider.when('/items', {templateUrl: 'partials/new-item.html', controller: 'NewItemController'});
  $routeProvider.when('/wastes', {templateUrl: 'partials/wastes.html', controller: 'WasteController'});
  $routeProvider.when('/trashes', {templateUrl: 'partials/trashes.html', controller: 'TrashController'});
  $routeProvider.otherwise({redirectTo: '/home'});
}])
.constant('BASE_PATH_URL','')
.constant('HOST','localhost');
