'use strict';

/* Directives */


angular.module('iwbApp.directives', []).

  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }])

  .directive('toto', function() {
  	return {
    restrict: 'A',
    	link: function(scope, element, attrs) {
    		$(element).flexslider({
       		animation: "slide",
	        animationLoop: false,
	        itemWidth: 80,
	        itemMargin: 5
       			});
    		}
  		};
	});
