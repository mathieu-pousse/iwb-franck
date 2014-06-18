'use strict';

angular.module('iwbApp').controller('ModalInstanceTrashController', ['$scope', '$modalInstance', 'selectedTrash', 'acronyms', 'update', 'TrashService',
	function ($scope, $modalInstance, selectedTrash, acronyms, update, TrashService){

		$scope.selectedTrash = selectedTrash;
		$scope.update = update;
		$scope.acronyms = acronyms;

		$scope.save = function () {
			if($scope.update){
				//code to update
		      }else{
		      	TrashService
		      	.createTrash
		      	TrashService
				.createTrash($scope.selectedTrash)
				.then( function( result )
				{
					$modalInstance.close(result.data);
				});
		      }
    	};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};

}]);