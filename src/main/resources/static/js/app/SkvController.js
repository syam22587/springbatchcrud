'use strict';

angular.module('crudAppSkv').controller('SkvController',
    ['SkvService', '$scope',  function( SkvService, $scope) {

        var self = this;
        self.skv = {};
        self.skvs=[];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.skv.id === undefined || self.skv.id === null) {
                console.log('Saving New User', self.skv);
                createUser(self.skv);
            } else {
                updateUser(self.skv, self.skv.id);
                console.log('User updated with id ', self.skv.id);
            }
        }

        function createUser(skv) {
            console.log('About to create skv');
            SkvService.createUser(skv)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.skv={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateUser(skv, id){
            console.log('About to update skv');
            SkvService.updateUser(skv, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        self.successMessage='User updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        self.errorMessage='Error while updating User '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeUser(id){
            console.log('About to remove User with id '+id);
            SkvService.removeUser(id)
                .then(
                    function(){
                        console.log('User '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing skv '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return SkvService.getAllUsers();
        }

        function editUser(id) {
            self.successMessage='';
            self.errorMessage='';
            SkvService.getUser(id).then(
                function (skv) {
                    self.skv = skv;
                },
                function (errResponse) {
                    console.error('Error while removing skv ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.skv={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);