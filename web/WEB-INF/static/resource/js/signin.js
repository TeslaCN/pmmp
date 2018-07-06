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
                            $.cookie('user', JSON.stringify(d.user))
                        }
                    }, 'json');
                    setInterval(function () {
                        console.log($.cookie('user'))
                        if (!$.cookie('user')) {
                            return
                        }
                        if (data.redirect) {
                            location.href = data.redirect
                        } else {
                            location.href = APP_PREFIX + '/'
                        }
                    }, 500)
                }, 'json'
            )
        }
    }
})

$("#nav").load("navbar.html");