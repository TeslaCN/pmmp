var userSignIn = new Vue({
    el: "#userSignIn",
    data: {
        username: "",
        password: "",
        verify: ""
    },
    methods: {
        signIn: function () {
            $.post(
                APP_PREFIX + "/signin",
                {
                    'username': this.username,
                    'password': this.password,
                    'verify': this.verify
                },
                function (data) {
                    if (data.code) {
                        alert(data.message);
                        return
                    }
                    $.get(APP_PREFIX + '/me', {}, function (d) {
                        if (d.user) {
                            alert(d.user.id)
                            $.cookie('user', JSON.stringify(d.user))
                        }
                    }, 'json');
                    while ($.cookie('user') == null){
                        alert("???")
                    }
                    if (data.redirect) {
                        location.href = data.redirect
                    } else {
                        location.href = APP_PREFIX + '/'
                    }
                }, 'json'
            )
        }
    }
})

$("#nav").load("navbar.html");