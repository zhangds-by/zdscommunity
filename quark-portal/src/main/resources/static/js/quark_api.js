/*链接*/
var quark_api_base_url = "http://127.0.0.1:8081";
var quark_login_api = quark_api_base_url + "/user/login";
var quark_getUser_api = quark_api_base_url + "/user/message/";
var quark_logout_api = quark_api_base_url + "/user/logout";
var quark_register_api = quark_api_base_url + "/user";
var quark_upload_api = quark_api_base_url + "/upload/image";
var quark_upload_icon_api = quark_api_base_url + "/upload/usericon/";
var quark_posts_add_api = quark_api_base_url + "/posts";
var quark_posts_get_api = quark_api_base_url + "/posts";
var quark_posts_detail_api = quark_api_base_url + "/posts/detail/";
var quark_reply_add_api = quark_api_base_url + "/reply";
var quark_label_getall_api = quark_api_base_url + "/label";
var quark_rank_posts_api= quark_api_base_url + "/rank/topPosts";
var quark_rank_users_api = quark_api_base_url + "/rank/newUsers";
var quark_user_detail_api = quark_api_base_url + "/user/detail/";
var quark_user_update_api = quark_api_base_url + "/user/";
var quark_user_update_psd_api = quark_api_base_url + "/user/password/";
var quark_label_posts_api = quark_api_base_url + "/posts/label/";
var quark_webSocket_api = quark_api_base_url + "/quarkServer";
var quark_notification_api= quark_api_base_url + "/notification/";
var quark_chat_webSocket_api = "ws://127.0.0.1:8083/websocket";
function setCookie(data) {
    var expiresDate= new Date();
    expiresDate.setTime(expiresDate.getTime() + (60 * 60 * 1000));
    $.cookie("QUARK_TOKEN",data,{
        path: '/',
        expires : 1
    });
}

function getCookie() {
    return $.cookie('QUARK_TOKEN');
}

function deleteCookie() {
    $.cookie("QUARK_TOKEN",null,{
        path: '/'
    });
}

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

function applyHeader() {
    var htm;
    $.get(quark_getUser_api+ getCookie(),function (data) {
        if (data.status == 200){
            htm =  "<a class='avatar' href='/user/setting'>"+
                "<img src='"+data.data.icon+"'>"+
                "<cite>"+data.data.username+"</cite>"+
                "</a>"+
                "<div class='nav'>"+
                "<a href='/user/setting'><i class='iconfont icon-shezhi'></i>设置</a>"+
                "<a href='' onclick='logout()'><i class='iconfont icon-tuichu' style='top: 0; font-size: 22px;'></i>退了</a>"+
                "</div>";
        }else{
            htm ="<a class='unlogin' href='#'><i class='iconfont icon-touxiang'></i></a>"+
                "<span><a href='/user/login'>登入</a><a href='/user/register'>注册</a></span>";
        }

        $(".nav-user").append(htm);
    });
}

function logout() {

    $.post(quark_logout_api,{
        token: getCookie()
    },function (obj) {
        if (obj.status == 200){
            deleteCookie();
            location.href = "/index";
        }else{
            layer.msg(obj.error,{icon:5});
        }
    });

}