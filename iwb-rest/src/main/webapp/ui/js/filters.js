'use strict';

/* Filters */

angular.module('iwbApp.filters', []).
  filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    }
  }])

  .filter('capitalize', function() {
	 return function(input, scope) {
	 if (input!=null)
	 input = input.toLowerCase();
	 return input.substring(0,1).toUpperCase()+input.substring(1);
	 }
   });
