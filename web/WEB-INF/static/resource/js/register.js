// $("#birth").datepicker({
//     format: "yyyy-mm-dd",
//     startView: 2,
//     endDate:new Date(),
//     language: "zh-CN",
//     autoclose: true
// }).on('changeDate',function(){
//     userRegister.birth = $('#birth').val()
// });

var re
$(document).ready(function () {
        re = function refresh() {
            $("#verifyImg").remove()
            $("#inputVerify").after("<img src=\"util/code\" id=\"verifyImg\" onclick='re()' title=\"看不清可单击图片刷新\">")
        }
    }
);

var userRegister = new Vue({
    el: "#userRegister",
    data: {
        account: "",
        password: "",
        realname:"",
        nickname:"",
        birth:"",
        gender : "",
        verify: ""
    },
    methods: {
        postRegisterMessage: function () {
            $.post(
                APP_PREFIX + "/signup",
                {
                    'realname':this.realname,
                    'account': this.account,
                    'password': this.password,
                    'nickname':this.nickname,
                    'gender':this.gender,
                    'birth':this.birth,
                    "email": "",
                    "salary": "",
                    "education": "",
                    "address": "",
                    'verify': this.verify
                },
                function (data) {
                    if (data.code) {
                        alert(data.message);
                        return
                    }
                    else{
                        alert("注册成功，跳转到登录界面")
                        location.href = "signin.html"
                    }
                }, 'json'
            )
        }
    }
})

