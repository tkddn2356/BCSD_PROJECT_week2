console.log("user Module.....");
var userService = (function () {

    function join(user, callback, error) {
        console.log("user = " + user);
        $.ajax({
            type: 'post',
            url: '/user/join',
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(er);
                }
            }
        });
    }

    function login(userInfo, callback, error) {
        console.log("userInfo = " + userInfo);
        $.ajax({
            type: 'post',
            url: '/user/login',
            data: JSON.stringify(userInfo),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(er);
                }
            }
        });
    }

    function checkUserExist(id, callback, error) {
        $.ajax({
            url: '/user/checkUserExist/' + id,
            type: 'get',
            dataType: 'text',
            success: function (result) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(er);
                }
            }
        });
    }

    function logout(callback, error) {
        $.ajax({
            url: '/user/logout',
            type: 'get',
            dataType: 'text',
            success: function (result, status, xhr) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(er);
                }
            }
        });
    }




    return {
        join: join,
        login: login,
        checkUserExist: checkUserExist,
        logout, logout
    };

})();