'use strict';

angular.module('iwbApp').controller('TrashController', ['$scope', '$location', 'TrashService', '$modal',
	function($scope, $location, TrashService, $modal){

		$scope.query = '';
		$scope.maxSize = 10;
		$scope.totalItems = 300;
		$scope.currentPage = 1;
		$scope.numberItemPerPage = 10;
		$scope.trashes = [];
		$scope.selectedTrash = {};
		$scope.acronyms = [];

		getTrashes();


		function getTrashes(){
			TrashService
				.getTrashesPagination($scope.numberItemPerPage, $scope.currentPage)
				.then( function( result )
				{
					$scope.trashes = result.data;
					TrashService
			            .getAllAcronyms()
			              .then( function( result )
			              {
			                $scope.acronyms = result.data;
			              });
				});
	    }
		

		$scope.goToCreateItem = function(e){
			e.stopPropagation();
			$location.url("/items");
		}

		$scope.goToWastes = function(e){
			e.stopPropagation();
			$location.url("/wastes");
		}

		$scope.goToTrashes = function(e){
			e.stopPropagation();
			$location.url("/trashes");
		}

		$scope.goBackHome = function(e){
			e.stopPropagation();
			$location.url("/home");
		}

		$scope.submitForm = function (e){
			e.stopPropagation();
			$location.search('query', $scope.query);
			$location.path("/search");
		}

		$scope.pageChanged = function() {
			//console.log('Page changed to: ' + $scope.currentPage);
			getTrashes();
		};

		$scope.editTrash = function (index) {
        $scope.selectTrash(index);
        var editTrashModal = $modal.open({
            templateUrl:"partials/modals/modalContentTrash.html",
            controller:"ModalInstanceTrashController",
            size:'lg',
            resolve: {
            selectedTrash: function () {
              return $scope.selectedTrash;
            },
            acronyms: function () {
              return $scope.acronyms;
            },
            update: function () {
              return true;
            }
          }
        });

        editTrashModal.result.then(function () {

        });
      }

      $scope.createTrash = function (index) {
        $scope.selectTrash(index);
        var editTrashModal = $modal.open({
            templateUrl:"partials/modals/modalContentTrash.html",
            controller:"ModalInstanceTrashController",
            size:'lg',
            resolve: {
            selectedTrash: function () {
              return {};
            },
            acronyms: function () {
              return $scope.acronyms;
            },
            update: function () {
              return false;
            }
          }
        });

        editTrashModal.result.then(function () {

        });
      }

      $scope.selectTrash =  function(index){
        $scope.selectedTrash = $scope.trashes[index];
      }

      $scope.isGarbage = function(index){
      	return ($scope.trashes[index].type === 'GARBAGE') ? true : false;
      }

      $scope.isLocalizable = function(index){
      	return ($scope.trashes[index].location) ? true : false;
      }
		

}]);