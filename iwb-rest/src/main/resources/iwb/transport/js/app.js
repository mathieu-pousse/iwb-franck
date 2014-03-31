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
  $routeProvider.when('/home', {templateUrl: 'partials/home.html'});
  $routeProvider.when('/view1', {templateUrl: 'partials/partial1.html', controller: 'MyCtrl1'});
  $routeProvider.otherwise({redirectTo: '/home'});
}]);
