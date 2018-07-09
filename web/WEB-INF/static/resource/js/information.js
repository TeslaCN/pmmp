var information = new Vue({
    el:'#information',
    data:{
        user:{}
    },
    methods: {
        save: function () {
            $.post(
                APP_PREFIX + "/me",
                this.user,
                function (d) {
                },
                'json'
            );
            $.cookie('user', JSON.stringify(this.user));
            alert("保存成功");
        },
        signOut: function () {
            console.log('Sign Out and clear Cookie');
            $.cookie('user', '')
        }
    }
});

$("#birth").datepicker({
    format: "yyyy-mm-dd",
    startView: 2,
    endDate:new Date(),
    language: "zh-CN",
    autoclose: true
}).on('changeDate',function(){
    information.user.birth = $('#birth').val()
});

$.get(APP_PREFIX + '/me', {}, function (data) {
    if (data.user) {
        information.user=data.user;
        delete information.user.signUpTime;
        var date = new Date(parseInt(information.user.birth));
        information.user.birth = date.format('yyyy-MM-dd')
    }
});

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