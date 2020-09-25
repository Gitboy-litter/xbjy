$(function () {
    $(".checked").change(function () {
        var id = $(this).val();
        if ($(this).prop("checked")) {
            $(function () {
                $.ajax({
                    url: "/user/focusUser",
                    data: {id:id},
                    type: "post",
                    dataType: "text",
                    success: function (data) {
                        alert(data)
                    }
                })
            })
        }else {
                $.ajax({
                    url: "/user/deleteFocusById",
                    data: {id:id},
                    type: "post",
                    dataType: "text",
                    success: function (data) {
                        alert("取关成功")
                    }
                })
        }
    })
})