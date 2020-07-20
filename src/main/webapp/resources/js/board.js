console.log("reply Module.....");
var boardService = (function () {

    function getList(category, criteria, callback, error) {
        console.log("category = " + category);
        console.log("criteria = " + criteria);
        $.ajax({
            type: 'post',
            url: '/board/list/' + category,
            data: JSON.stringify(criteria),
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

    function getPaging(category, criteria, callback, error) {
        console.log("category = " + category);
        console.log("criteria = " + criteria);
        $.ajax({
            type: 'post',
            url: '/board/paging/' + category,
            data: JSON.stringify(criteria),
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

    function write(board, callback, error) {
        console.log("add reply......")
        $.ajax({
            type: 'post',
            url: '/board/write',
            data: JSON.stringify(board),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er)
                }
            }
        });
    }

    function read(id, callback, error) {
        $.get("/board/" + id, function (result) {
            if (callback) {
                callback(result);
            }
        }).fail(function (xhr, status, err) {
            if (error) {
                error();
            }
        });
    }

    function modify(id ,board, callback, error) {
        console.log("id: " + id);
        $.ajax({
            type: 'patch',
            url: '/board/' + id,
            data: JSON.stringify(board),
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


    function displayTime(timeValue) {
        var dateObj = new Date(timeValue),
            month = '' + (dateObj.getMonth() + 1),
            day = '' + dateObj.getDate(),
            year = dateObj.getFullYear();
        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;
        return [year, month, day].join('-');
    }

    return {
        getList:getList,
        getPaging:getPaging,
        write:write,
        read:read,
        modify:modify,
        displayTime:displayTime
    };

})();