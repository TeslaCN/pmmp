$("#birth").datepicker({
        format: "yyyy-mm-dd",
        startView: 2,
        language: "zh-CN",
        autoclose: true
    });

$("#nav").load("navbar.html");

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

