'use strict';

angular.module('iwbApp').controller('ModalInstanceController', ['$scope', '$modalInstance', 'selectedWaste', 'acronyms', 'update', 'WasteService', '$location',
	function ($scope, $modalInstance, selectedWaste, acronyms, update, WasteService, $location){

		$scope.selectedWaste = selectedWaste;
		$scope.acronyms = acronyms;
		$scope.update = update;

		$scope.save = function () {
			if($scope.update){
				WasteService
		          .saveWaste($scope.selectedWaste._id, $scope.selectedWaste)
		          .then( function( result )
		          {
		            $modalInstance.close();
		            //$location.url("/wastes");
		        });
		      }else{
		      	WasteService
		          .createWaste($scope.selectedWaste)
		          .then( function( result )
		          {
		            $modalInstance.close(result.data);
		        });
		      }
    		
    	};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};

		$scope.updateAcronym = function () {
		}

}]);