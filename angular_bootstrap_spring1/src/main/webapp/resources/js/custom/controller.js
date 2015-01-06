app.controller('MainController', function ($rootScope, $scope, $location) {
    $scope.logout = function () {
        $scope.$emit('event:logoutRequest');

        $location.path("/main");
    };

    $scope.login = function (credentials) {
        $scope.$emit('event:loginRequest', credentials.email, credentials.password);

        $location.path($rootScope.navigateTo);
    };
});

app.controller('UsersController', function ($rootScope, $scope, $http, CustomerService) {
    $http.get('/rest/users')
        .then(function(response){
            $scope.users = response.data;
        });
});