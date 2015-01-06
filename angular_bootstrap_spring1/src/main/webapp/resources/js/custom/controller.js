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
    // Initially get users list:
    $http.get('/rest/users')
        .then(function(response){
            $scope.users = response.data;
        });

    $scope.saveUser = function(user) {
        $http.post('/rest/save', user)
            .then(function(response) {
                console.log("User saved, response: ");
                console.log(response);
            });
    }
});