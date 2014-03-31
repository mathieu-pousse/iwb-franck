'use strict';

/* Services */

angular.module('iwbApp.services', ['ngResource']).

   service('AngularIssues', function($resource){
   	return $resource('/api/trashes/531f0f25e4b0f61504f12b1a', {}, {'query': {method: 'GET', isArray: false}})
   })

  .value('version', '0.1');