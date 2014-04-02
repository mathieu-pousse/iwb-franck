'use strict';


// Declare app level module which depends on filters, and services
angular.module('iwbApp', [
  'ngRoute',
  'iwbApp.filters',
  'iwbApp.services',
  'iwbApp.directives',
  'iwbApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {templateUrl: 'partials/home.html' , controller: 'HomeController'});
  $routeProvider.when('/view1', {templateUrl: 'partials/partial1.html', controller: 'MyCtrl1'});
  $routeProvider.when('/search', {templateUrl: 'partials/q-results.html', controller: 'SearchItemController'});
  $routeProvider.otherwise({redirectTo: '/home'});
}]);
