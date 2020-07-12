console.log("reply Module.....");
var replyService = (function () {
    function add(reply, callback, error){
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
                if(error){
                    error(er)
                };
            }
        })
    }

    // function getList(param, callback,  error){
    //     var board_id = param.board_id;
    //     var page = param.page;
    //     $.getJSON("/reply/list/"+ board_id +"/" +page,
    //         function (data) {
    //         if(callback){
    //             callback(data);
    //         }
    //     }).fail(function (xhr, status, err) {
    //         if(error){
    //             error();
    //         }
    //     });
    // }

    function getList(param, callback,  error){
        var board_id = param.board_id;
        var page = param.page;
        $.getJSON("/reply/list/"+ board_id +"/" +page,
            function (data) {
                if(callback){
                    callback(data.totalReply, data.replyList);
                }
            }).fail(function (xhr, status, err) {
            if(error){
                error();
            }
        });
    }

    function get(id, callback, error) {
        $.get("/reply/" + id, function(result) {
            if (callback) {
                callback(result);
            }
        }).fail(function(xhr, status, err) {
            if (error) {
                error();
            }
        });
    }

    function update(reply, callback, error) {
        console.log("id: " + reply.id);
        $.ajax({
            type : 'put',
            url : '/reply/' + reply.id,
            data : JSON.stringify(reply),
            contentType : "application/json; charset=utf-8",
            success : function(result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error : function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }

    function remove(id, callback, error) {
        $.ajax({
            type : 'delete',
            url : '/reply/' + id,
            contentType:"application/json; charset=utf-8",
            success : function(deleteResult, status, xhr) {
                if (callback) {
                    callback(deleteResult);
                }
            },
            error : function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }



    return {
        add:add,
        getList:getList,
        get:get,
        update:update,
        remove:remove
    };

})();