'use strict';

/* Controllers */

angular.module('iwbApp.controllers', ['iwbApp.services']).

  controller('MyCtrl1', ['$scope', 'AngularIssues', function($scope, AngularIssues) {
    $scope.data = {};
    AngularIssues.query(function(response) {
      $scope.data.issues = response;
    });
  }])
  .controller('HeaderController', ['$scope', '$location', function($scope, $location) {
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };
  }])
;