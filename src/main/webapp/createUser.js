/**
 * 
 */

function createUser($scope, $http) {
    $http.get('http://localhost:8080/ComS309Project/309/T11/createNewUser/TestUser/myPassword/testUser@iastate.edu').
    success(function(data) {
        $scope.confirmation = data;
    });
}