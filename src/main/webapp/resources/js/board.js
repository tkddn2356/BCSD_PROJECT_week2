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
        displayTime:displayTime
    };

})();