console.log("reply Module.....");
var replyService = (function () {
    function add(reply, callback, error) {
        console.log("add reply......")
        $.ajax({
            type: 'post',
            url: '/reply/write',
            data: JSON.stringify(reply),
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
                ;
            }
        })
    }


    function getList(param, callback, error) {
        var board_id = param.board_id;
        var page = param.page;
        console.log("board_id = "+ board_id);
        console.log("page = " + page);
        $.getJSON("/reply/list/" + board_id + "/" + page,
            function (data) {
                if (callback) {
                    callback(data);
                }
            }).fail(function (xhr, status, err) {
            if (error) {
                error();
            }
        });
    }

    function get(id, callback, error) {
        $.get("/reply/" + id, function (result) {
            if (callback) {
                callback(result);
            }
        }).fail(function (xhr, status, err) {
            if (error) {
                error();
            }
        });
    }

    function update(reply, callback, error) {
        console.log("id: " + reply.id);
        $.ajax({
            type: 'patch',
            url: '/reply/' + reply.id,
            data: JSON.stringify(reply),
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

    function remove(id, callback, error) {
        $.ajax({
            type: 'delete',
            url: '/reply/' + id,
            contentType: "application/json; charset=utf-8",
            success: function (deleteResult, status, xhr) {
                if (callback) {
                    callback(deleteResult);
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

    function getPage(param, callback, error) {
        var board_id = param.board_id;
        var page = param.pageNum;
        console.log("board_id = "+ board_id);
        console.log("page = " + page);
        $.getJSON("/reply/page/" + board_id + "/" + page,
            function (data) {
                if (callback) {
                    callback(data);
                }
            }).fail(function (xhr, status, err) {
            if (error) {
                error();
            }
        });
    }


    return {
        add: add,
        getList: getList,
        get: get,
        update: update,
        remove: remove,
        displayTime: displayTime,
        getPage: getPage
    };

})();