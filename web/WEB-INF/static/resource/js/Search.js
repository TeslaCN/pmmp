var search = new Vue({
    el: "#userSearch",
    data: {
        key: "",
        startDate: "",
        endDate: "",
        message: [],
    },
    methods: {
        simpleSearch: function () {
            // $.ajax({
            //     url: APP_PREFIX + '/basicsearch',
            //     type: 'GET',
            //     contestType: 'application/json',
            //     dataType: 'json',
            //     // data: JSON.stringify({'key': this.key, 'pageNo': 1, 'pageSize': 10}),
            //     data:{'key': this.key, 'pageNo': 1, 'pageSize': 10},
            //     success: function (user) {
            //         this.message = user.users;
            //         for (var i = 0; i < message.length; i++) {
            //             message[i].startDate = this.dateFomed(message[i].startDate);
            //             message[i].endDate = this.dateFomed(message[i].endDate);
            //         }
            //     }
            // })
            if(search.startDate==""&&search.endDate==""){
            $.get(APP_PREFIX + '/basicsearch', {key: this.key, pageNo: 1, pageSize: 10}, function (data) {

                search.message = data.users;

                // var message = this.message
                // for (var i = 0; i < message.length; i++) {
                //     message[i].startDate = this.dateFomed(message[i].startDate);
                //     message[i].endDate = this.dateFomed(message[i].endDate);
                // }
            }, 'json')}
            else{
                $.get(APP_PREFIX + '/advancedsearch', {
                    key: this.key,
                    startDate: this.startDate,
                    endDate: this.endDate
                }, function (data) {
                    console.log(data);
                    search.message = data.users;
                }, 'json')
            }
        },
        /*advanceSearch: function () {
                     debugger
            $.get(APP_PREFIX + '/advancedsearch', {
                key: this.key,
                startDate: this.startDate,
                endDate: this.endDate
            }, function (data) {
                console.log(data);
                search.message = data.users;
            }, 'json')
        },*/
        dateFormed: function (date) {
            var time = new Date(date);
            var year = time.getFullYear();
            var m = time.getMonth();
            var d = time.getDay();
            if (m < 10) {
                m = '0' + m;
            }
            if (d < 10) {
                d = '0' + d;
            }
            return year + '-' + m + '-' + d;
        },
        addFriend: function(id){
            debugger
            console.log(id);
           $.ajax({
              type:"put",
               url:APP_PREFIX+'/friend/'+id,
               success:function(data){
                  console.log(data);
                  if(data.code==0){
                      alert("发送成功");
                  }
                  else{
                      alert("已添加")
                  }
               }
           });
        }
    }

})
$('#startDate').datepicker({
    format: "yyyy-mm-dd",
    startView: 2,
    endDate: new Date(),
    language: "zh-CN",
    autoclose: true
}).on('changeDate', function () {
    search.startDate = $('#startDate').val()
});
$('#endDate').datepicker({
    format: "yyyy-mm-dd",
    startView: 2,
    endDate: new Date(),
    language: "zh-CN",
    autoclose: true
}).on('changeDate', function () {
    search.endDate = $('#endDate').val()
});
