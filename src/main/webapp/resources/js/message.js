console.log("message Module.....");
var messageService = (function () {

    function getList(mode, user_id, callback, error) {
        console.log("mode = " + mode);
        console.log("user_id = " + user_id);
        $.ajax({
            type: 'get',
            url: '/message/list/' + mode +'/' + user_id,
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

    function read(id, callback, error) {
        $.get("/message/" + id, function (result) {
            if (callback) {
                callback(result);
            }
        }).fail(function (xhr, status, err) {
            if (error) {
                error();
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
        read:read,
        displayTime:displayTime
    };

})();