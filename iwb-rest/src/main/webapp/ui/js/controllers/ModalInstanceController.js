'use strict';

angular.module('iwbApp').controller('ModalInstanceController', ['$scope', '$modalInstance',
	function ($scope, $modalInstance){

		$scope.save = function () {
    	$modalInstance.close();
    	};

		$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
		};

}]);