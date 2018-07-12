var message = new Vue({
    el:'#message',
    data:{
        friendId:null,
        nickname:null,
        messages:null,
        content:"",
        pic:null,
    },
    methods:{
        signOut: function () {
            console.log('Sign Out and clear Cookie')
            $.cookie('user', '')
        },
        SendMessage:function(){
            $.post(
                APP_PREFIX+"/message/send/"+this.friendId,
                {
                    content:this.content
                },
                function(data){
                    if(data.code==0){
                        message.content="";
                        refresh();
                        setTimeout(scrollToBottom,500);
                    }
                }, 'json');
        }
    }
});

var fid=window.location.search;
message.friendId=fid.split('=')[1];

//获取好友信息
$.get(
    APP_PREFIX + '/user/'+message.friendId,
    {},
    function (data) {
        if(data.user){
            message.nickname = JSON.parse(JSON.stringify(data.user)).nickname;
            message.pic = JSON.parse(JSON.stringify(data.user)).profileUrl;
        }
    });

//div滚动条(scrollbar)保持在最底部
function scrollToBottom(){
    var div = document.getElementById('words');
    div.scrollTop = div.scrollHeight;
}

function refresh(){
    $.get(
        APP_PREFIX + '/message/with/'+message.friendId,
        {
            "pageNo": 1,
            "pageSize": 500
        },
        function (data) {
            if(data){
                message.messages =JSON.parse(JSON.stringify(data.messages));
                for(var i=0;i<message.messages.length;i++){
                    message.messages[i].sendTime=new Date(parseInt(message.messages[i].sendTime)).format('yyyy-MM-dd hh:mm:ss');
                    if(message.friendId==message.messages[i].fromUserId){
                        message.messages[i].fromUserId='0'
                    }
                }
            }
        });
}
window.setInterval('refresh()',1000);

Date.prototype.format = function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    };
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length==1 ? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}