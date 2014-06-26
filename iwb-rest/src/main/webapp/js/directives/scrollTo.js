'use strict';

angular.module('iwbApp').directive('scrollTo', function ($location, $anchorScroll) {
    return function(scope, element, attrs) {
    element.bind('click', function(event) {
      var location = attrs.scrollTo;
      $location.hash(location);
      $anchorScroll();
    });
  };
});
