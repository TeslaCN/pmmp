var re
$(document).ready(function () {
        re = function refresh() {
            $("#verifyImg").remove()
            $("#inputVerify").after("<img src=\"util/code\" id=\"verifyImg\" onclick='re()' title=\"看不清可单击图片刷新\">")
        }
    }
);

var userRegister = new Vue({
    el: "#register",
    data: {
        user: {
            account:'',
            password:'',
            realname:'',
            nickname:'',
            birth:'',
            gender:'0',
            verify:'',
            email:'',
            salary:'',
            education: '',
            address:''
        },
    },
    methods: {
        postRegisterMessage: function () {
            if(this.user.password!=$('#repassword').val()){
                alert("两个密码不一致")
            }else{
                $.post(
                    APP_PREFIX + "/signup",
                    {
                        'realname':this.user.realname,
                        'account':this.user.account,
                        'password':this.user.password,
                        'nickname':this.user.nickname,
                        'gender':this.user.gender,
                        'birth':this.user.birth,
                        'email':this.user.email,
                        'salary':this.user.salary,
                        'education':this.user.education,
                        'address':this.user.address,
                        'verify':this.user.verify
                    },
                    function (data) {
                        if (data.code) {
                            re();
                            alert(data.message);
                            return;
                        }else{
                            alert("注册成功");
                            location.href="signin.html";
                        }
                    }, 'json'
                );
            }
        }
    }
})

$('#birth').datepicker({
    format: "yyyy-mm-dd",
    startView: 2,
    endDate:new Date(),
    language: "zh-CN",
    autoclose: true
}).on('changeDate',function(){
    userRegister.user.birth = $('#birth').val()
});