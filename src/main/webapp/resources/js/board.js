console.log("reply Module.....");
var boardService = (function () {

    function getHit(id, callback, error) {
        console.log("id: " + id);
        $.ajax({
            type: 'get',
            url: '/board/hit/' + id,
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }

    function getHit_not(id, callback, error) {
        console.log("id: " + id);
        $.ajax({
            type: 'get',
            url: '/board/hit_not/' + id,
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }


    function updateHit(id, callback, error) {
        console.log("id: " + id);
        $.ajax({
            type: 'patch',
            url: '/board/hit/' + id,
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }

    function updateHit_not(id, callback, error) {
        console.log("id: " + id);
        $.ajax({
            type: 'patch',
            url: '/board/hit_not/' + id,
            async: false,
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }

    return {
        getHit: getHit,
        getHit_not: getHit_not,
        updateHit: updateHit,
        updateHit_not:updateHit_not
    };

})();