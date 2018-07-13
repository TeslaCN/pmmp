var information = new Vue({
    el:'#information',
    data:{
        user:{},
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
        // change:function(){
        //     var formData = new FormData();
        //     var img = document.getElementById("file");
        //     var file = img.files[0];
        //     formData.append("file",file);
        //     $.ajax({
        //         url:APP_PREFIX+"/head",
        //         type:"post",
        //         enctype: "form-data",
        //         data:{file:formData},
        //         processData : false,
        //         contentType : false,
        //         async:false,
        //         success:function(data){
        //             alert(data.message);
        //         }
        //     })
        // },
        signOut: function () {
            console.log('Sign Out and clear Cookie');
            $.cookie('user', '')
        },
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
        information.user.birth = date.format('yyyy-MM-dd');
        if(information.user.profileUrl!=null&&(information.user.profileUrl.search("jpg") != -1 || information.user.profileUrl.search("png") != -1)){
            information.user.profileUrl=APP_PREFIX +"/images/"+information.user.profileUrl;
        }else{
            information.user.profileUrl=null;
        }
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

var addrShow = document.getElementById('address');
var prov = document.getElementById('prov');
var city = document.getElementById('city');
var country =document.getElementById('country');
/*用于保存当前所选的省市区*/
var current = {
    prov: '',
    city: '',
    country: ''
};

var len = provice.length;
for (var i = 0; i < len; i++) {
    var provOpt = document.createElement('option');
    provOpt.innerText = provice[i]['name'];
    provOpt.value = i;
    prov.appendChild(provOpt);
}

/*根据所选的省份来显示城市列表*/
function showCity(obj) {
    var val = obj.options[obj.selectedIndex].value;
    if (val != current.prov) {
        current.prov = val;
        addrShow.value = provice[current.prov].name ;
    }

    if (val != null) {
        city.length = 1;
        var cityLen = provice[val]["city"].length;
        for (var j = 0; j < cityLen; j++) {
            var cityOpt = document.createElement('option');
            cityOpt.innerText = provice[val]["city"][j].name;
            cityOpt.value = j;
            city.appendChild(cityOpt);
        }
    }
}

/*根据所选的城市来显示县区列表*/
function showCountry(obj) {
    var val = obj.options[obj.selectedIndex].value;
    current.city = val;
    if (val != null) {
        addrShow.value = provice[current.prov].name + '-' + provice[current.prov]["city"][current.city].name;
        country.length = 1; //清空之前的内容只留第一个默认选项
        var countryLen = provice[current.prov]["city"][val].districtAndCounty.length;
        if(countryLen == 0){
            addrShow.value = provice[current.prov].name + '-' + provice[current.prov]["city"][current.city].name;
            return;
        }
        for (var n = 0; n < countryLen; n++) {
            var countryOpt = document.createElement('option');
            countryOpt.innerText = provice[current.prov]["city"][val].districtAndCounty[n];
            countryOpt.value = n;
            country.appendChild(countryOpt);
        }
    }
}

/*选择县区之后的处理函数*/
function selecCountry(obj) {
    current.country = obj.options[obj.selectedIndex].value;
    information.user.address=provice[current.prov].name +
        '-' + provice[current.prov]["city"][current.city].name +
        '-' + provice[current.prov]["city"][current.city].districtAndCounty[current.country];
}



