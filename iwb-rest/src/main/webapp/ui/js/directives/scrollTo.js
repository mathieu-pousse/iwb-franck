'use strict';

angular.module('iwbApp').directive('scrollTo', function ($location, $anchorScroll) {
    return function(scope, element, attrs) {
    element.bind('click', function(event) {
      event.stopPropagation();
      scope.$on('$locationChangeStart', function(ev) {
        ev.preventDefault();
      });
      var location = attrs.scrollTo;
      $location.hash(location);
      $anchorScroll();
    });
  };
});
