function login() {
    $.ajax({
        // url:"http://localhost:85/admin/api/login.api",
        url: "admin/login/",
        type: "post",
        dataType: "json",
        data: {'username': $("#name").val(), 'password': $("#password").val()},
        success: function (data) {
            var failNum = data.fail;
            if (failNum == 1) {
                var token = data.token;
                $.cookie("codekeep", token, {
                    expires: 7,
                    path: '/'
                });
                $(location).attr('href', 'index.html');
            } else {
                $(".wrongShow").css({"height": "40px"});
            }
        }
    });
}

function getLoginHome() {
    var token = getToken();
    $.ajax({
        url: "admin/getInfo",
        type: "post",
        dataType: "json",
        data: {'token': token},
        success: function (data) {
            var failNum = data.fail;
            if (failNum == 1) {
                $("#userName").text(data.userName);
                $("#allUser").text(data.allUser);
                $("#dayCome").text(data.dayCome);
                $("#monthPrice").text(data.monthPrice);
                $("#monthUser").text(data.monthUser);

                var sexMan = data.sex1;
                var sexWoman = data.sex2;
                var sexUnknow = data.sex0;
                sex(sexWoman, sexMan, sexUnknow);

                $('.right-goCardSet').tooltipster({theme: 'tooltipster-borderless'});
                $('.right-adminAdd').tooltipster({theme: 'tooltipster-borderless'});
                $('.card-seeTips').tooltipster({theme: 'tooltipster-borderless', 'maxWidth': 300});

                $('#card-ListBox').DDSort({
                    target: '.card-list',
                    floatStyle: {
                        'margin-top': '-300px',
                        'margin-left': '-400px',
                        'box-shadow': '0px 0px 20px #417BFC'
                    }
                });

                if (data.level == 'super') {
                    $("#level").text('顶级管理');
                } else {
                    $("#level").text('普通管理');
                    $("#right-adminAdd").css("display", "none");
                }
                getUser();
                getAdmin();
                getPay();
                getCard();

                $("#middle-seachList").css("display", "none");
                $("#middle-chooseAdmin").css("display", "none");
                $("#middle-userShow").css("display", "none");
                $("#middle-cardSet").css("display", "block");

                $("#chooseMyPass").attr("onclick", "goChooseAdmin(" + data.id + ",'" + data.userName + "');");
            } else {
                $(location).attr('href', 'login.html');
            }
        }
    });
}


//监听用户列表滚轮滑底
document.getElementById("user").onscroll = function () {
    var scrollHeight = document.getElementById("user").scrollHeight;
    var scrollTop = document.getElementById("user").scrollTop;
    var clientHeight = document.getElementById("user").clientHeight;
    if (scrollHeight - clientHeight == scrollTop) {
        if ($("#user-lastUser").text() == '-1') {
            getUser();
        }
    }
};

//监听付款列表滚轮滑底
document.getElementById("pay").onscroll = function () {
    var scrollHeight = document.getElementById("pay").scrollHeight;
    var scrollTop = document.getElementById("pay").scrollTop;
    var clientHeight = document.getElementById("pay").clientHeight;
    if (scrollHeight - clientHeight == scrollTop) {
        if ($("#pay-lastUser").text() == '-1') {
            getPay();
        }
    }
};

//获取付款信息列表
function getPay() {
    var token = getToken();
    var nextNum = $("#pay-allNum").text();

    $.ajax({
        url: "pay/part",
        type: "post",
        dataType: "json",
        data: {'token': token, 'nextNum': nextNum},
        success: function (data) {
            // console.log(data);
            var getPay = data.getPay;
			var num = getPay;
            var lastPay = data.lastPay;
            var html = "";
            var i = 0;
            for (i = 0; i < getPay; i++) {
                html = html + '<div class="pay-data"><div class="pay-img" style="background-image: url(' + data.userList[i].avatar + ');"></div><div class="pay-nickname">' + data.userList[i].nickName + '</div><div class="pay-card">' + data.userList[i].payCard + '</div><div class="pay-day">' + data.userList[i].payTime + '</div><div class="pay-price">￥' + data.userList[i].payPrice + '</div></div>';
            }
            var divshow = $("#pay");
            divshow.append(html);

            if (lastPay == 1) {
                $("#pay-lastUser").text("1");
            }
            var payNum = +$("#pay-allNum").text() + num;
            $("#pay-allNum").text(payNum);
        }
    });
}

//获取卡数据
function getCard() {
    var token = getToken();
    $.ajax({
        url: "card/all",
        type: "post",
        dataType: "json",
        data: {'token': token},
        success: function (data) {
            //回调有序json，顺序即权重
            var getCard = data.cardNum;
            var html = "";
            var i = 0;
            for (i = 0; i < getCard; i++) {
                var see = 'see2.png'
                if (data.card[i].see == 0) {
                    see = 'unsee2.png'
                }
                html = html + '<li class="card-list" title="单击文字即可修改，按住拖动排序~" id="card-list' + i + '"><input class="card-title" value="' + data.card[i].name + '" id = "card-title' + i + '"><input class="card-day" value="' + data.card[i].expireDay + '" id = "card-day' + i + '"><input class="card-price" value="' + data.card[i].price + '" id = "card-price' + i + '"><div class="card-see" id="card-see' + i + '" onclick="cardSee(' + i + ')" style="background-image:url(images/' + see + ')"></div><div class="card-del" onclick="cardDel(' + i + ')">×</div></li>';
            }
            var divshow = $("#card-ListBox");
            divshow.text("");
            divshow.append(html);
            $(".card-allNum").val(getCard);
            $('.card-list').tooltipster({theme: 'tooltipster-borderless'});
        }
    });
}

//拉取管理员（所有结果一起展示）
function getAdmin() {
    var token = getToken();

    $.ajax({
        url: "admin/all",
        type: "post",
        dataType: "json",
        data: {'token': token},
        success: function (data) {
            var getAdmin = data.adminNum;
            var html = "";
            var i = 0;
            for (i = 0; i < getAdmin; i++) {
                var adminDo = "";
                if (data.admin[i].level != 'super') {
                    adminDo = '<div class="admin-choose" onclick="goChooseAdmin(' + data.admin[i].id + ',\'' + data.admin[i].username + '\')"></div><div class="admin-delect" onclick="delAdmin(' + data.admin[i].id + ')"></div>'
                }
                html = html + '<div class="admin"><div class="admin-name">' + data.admin[i].username + '</div>' + adminDo + '</div>';
            }
            var divshow = $("#admin");
            divshow.text("");
            divshow.append(html);
        }
    });
}

//拉取会员数据
function getUser() {
    var token = getToken();
    var nextNum = $("#user-allNum").text();

    $.ajax({
        url: "member/part",
        type: "post",
        dataType: "json",
        data: {'token': token, 'nextNum': nextNum},
        success: function (data) {
            var getUser = data.getUser;
			var num = getUser;
            var lastUser = data.lastUser;
            var html = "";
            var i = 0;

            for (i = 0; i < getUser; i++) {
                html = html + '<div class="left-userShow"><div class="user-img"><image src="' + data.userList[i].avatar + '" class="user-imgShow"></div><div class="user-main"><div class="user-nickName">' + data.userList[i].nickName + '</div><div class="user-date">' + data.userList[i].expireTime + '</div></div><div class="user-do"><div class="user-see" onclick="seeUser(' + data.userList[i].id + ')"></div><div class="user-del" onclick="delUser(' + data.userList[i].id + ')"></div></div></div>';
            }

            if (lastUser == 1) {
                $("#user-lastUser").text("1");
            }
            var userNum = +$("#user-allNum").text() + num;
            $("#user-allNum").text(userNum);

            var divshow = $("#user");
            divshow.append(html);

        },
    });
}


//性别echatrts图
function sex(man, woman, noknow) {
    var dom = document.getElementById("sexContainer");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        color: ['#FE519B', '#15DCFC', '#CC6600'],
        title: {
            show: false
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            show: false,
            orient: 'vertical',
            left: 'left',
            data: ['男', '女', '未知'],
        },
        series: [
            {
                name: '性别',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: man, name: '男'},
                    {value: woman, name: '女'},
                    {value: noknow, name: '未知'},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

//搜索（所有结果一起展示）
function seach() {
    var seachWord = $("#userSeach").val();
    var token = getToken();
    $("#userSeach").val("");

    $.ajax({
        url: "http://localhost:85/admin/api/alluser.api",
        type: "post",
        dataType: "json",
        data: {'token': token, 'word': seachWord},
        success: function (data) {
            var getUser = data.getUser;
            var html = "";
            var i = 0;

            for (i = 0; i < getUser; i++) {
                html = html + '<div class="seach-Show"><div class="seach-img"><image src="' + data.userList[i].avatar + '" class="seach-imgShow"></image></div><div class="seach-nickName">' + data.userList[i].nickName + '</div><div class="seach-time">' + data.userList[i].expireTime + '</div><div class="seach-more"  onclick="seeUser(' + data.userList[i].id + ')"></div><div class="seach-del"  onclick="delUser(' + data.userList[i].id + ')"></div></div>';
            }

            var divshow = $("#seachList");

            $("#middle-seachList").css("display", "none");
            $("#middle-chooseAdmin").css("display", "none");
            $("#middle-userShow").css("display", "none");
            $("#middle-cardSet").css("display", "block");

            divshow.text("");
            divshow.append(html);

        },
    });
}

//查看用户
function seeUser(userId) {
    var token = getToken();

    $("#middle-seachList").css("display", "none");
    $("#middle-chooseAdmin").css("display", "none");
    $("#middle-userShow").css("display", "block");
    $("#middle-cardSet").css("display", "none");

    $.ajax({
        url: "member/getById",
        type: "post",
        dataType: "json",
        data: {'token': token, 'userId': userId},
        success: function (data) {
            $("#wx-name").text(data.user.nickName);
            $("#wx-wxTime").text('到期时间：' + data.user.expireTime);
            $("#wx-imgShow").attr('src', data.user.avatar);

            if (data.user.gender == 1) {
                $("#wx-man").css("display", "block");
                $("#wx-woman").css("display", "none");
            }
            if (data.user.gender == 2) {
                $("#wx-man").css("display", "none");
                $("#wx-woman").css("display", "block");
            }

        },
    });

    $.ajax({
        url: "keep/getById",
        type: "post",
        dataType: "json",
        data: {'token': token, 'userId': userId, 'allShow': 1},   //电脑一次拉取所有数据，小程序是分段展示
        success: function (data) {
            var getData = data.getData;
            var html = "";
            var i = 0;
            for (i = 0; i < getData; i++) {
                var keepTime = data.keepData[i].keepTime;
                keepTime = keepTime.replace(' ', data.keepData[i].keepDay + ' ')
                html = html + '<div class="wx-keep">' + keepTime + '</div>';
            }
            var divshow = $("#wx-keeplist");
            divshow.text("");
            divshow.append(html);
        },
    });

    $.ajax({
        url: "pay/getById",
        type: "post",
        dataType: "json",
        data: {'token': token, 'userId': userId, 'allShow': 1},   //电脑一次拉取所有数据，小程序是分段展示
        success: function (data) {
            console.log(data);
            var getData = data.getData;
            var html = "";
            var i = 0;
            for (i = 0; i < getData; i++) {
                html = html + '<div class="wx-list"><div class="wx-title">' + data.payData[i].payCard + '</div><div class="wx-text">' + data.payData[i].payTime + '</div></div>';
            }
            var divshow = $("#wx-paylist");
            divshow.text("");
            divshow.append(html);
        },
    });
}

//开始添加用户
function goAddAdmin() {
    $("#middle-seachList").css("display", "none");
    $("#middle-chooseAdmin").css("display", "block");
    $("#middle-userShow").css("display", "none");
    $("#middle-cardSet").css("display", "none");

    $("#cadmin-name").css("display", "none");
    $("#cadmin-addname").css("display", "block");
    $("#cadmin-addname").css("margin-top", "50px");
    $("#cadmin-level").css("display", "block");

    $("#chooseAdminTitle").text("添加管理员");
    $("#cadmin-button").text("确定添加");
}

//修改密码
function goChooseAdmin(id, username) {
    $("#middle-seachList").css("display", "none");
    $("#middle-chooseAdmin").css("display", "block");
    $("#middle-userShow").css("display", "none");
    $("#middle-cardSet").css("display", "none");

    $("#cadmin-name").css("display", "block");
    $("#cadmin-name").text(username);
    $("#cadmin-addname").css("display", "none");
    $("#cadmin-level").css("display", "none");
    $("#cadmin-addname").css("margin-top", "50px");

    $("#chooseAdminTitle").text("修改用户密码");
    $("#cadmin-button").text("确定修改");
    $("#cadmin-id").val(id);
}

//开始管理卡数据
function goCardSet() {
    $("#middle-seachList").css("display", "none");
    $("#middle-chooseAdmin").css("display", "none");
    $("#middle-userShow").css("display", "none");
    $("#middle-cardSet").css("display", "block");
}

//管理员管理面板确定被点击
function adminSure() {
    var token = getToken();
    var password = $("#cadmin-password").val();

    if ($("#cadmin-button").text() == "确定修改") {
        var adminId = $("#cadmin-id").val();
        $.ajax({
            url: "admin.update",  //修改密码
            type: "post",
            dataType: "json",
            data: {'token': token, 'userId': adminId, 'password': password},
            success: function (data) {
                if (data.fail == 1) {
                    alert("修改成功");
                } else {
                    alert("修改失败");
                }
            },
        });
        $("#cadmin-password").val("");
    } else {
        var userName = $("#cadmin-nickName").val();
        var password = $("#cadmin-password").val();
        var level = $("#cadmin-type").val();

        $.ajax({
            url: "admin/add",   //添加用户
            type: "post",
            dataType: "json",
            data: {'token': token, 'username': userName, 'password': password, 'level': level},
            success: function (data) {
                if (data.fail == 1) {
                    alert("添加成功");
                } else {
                    alert("添加失败");
                }
            },
        });

        $('#cadmin-nickName').val("");
        $("#cadmin-password").val("");
        $("#cadmin-type").val("");
    }
}

//删除用户
function delUser(id) {
    var token = getToken();
    if (window.confirm('你确定要删除吗？删除后将无法更改！')) {
        $.ajax({
            url: "member/delete",
            type: "post",
            dataType: "json",
            data: {'token': token, 'userId': id},
            success: function (data) {
                alert("删除成功");
            },
        });
    }
}

//删除管理员
function delAdmin(id) {
    var token = getToken();
    if (window.confirm('你确定要删除吗？删除后将无法更改！')) {
        $.ajax({
            url: "admin/delete",
            type: "post",
            dataType: "json",
            data: {'token': token, 'adminId': id},
            success: function (data) {
                alert("删除成功");
            },
        });
    }
}

//超级管理按钮点击
function cadminTypeChoose() {
    if ($("#cadmin-type").val() == '0') {
        $("#cadmin-typeImg").css("background-image", "url(images/open.png)");
        $("#cadmin-type").val('1');
    } else {
        $("#cadmin-typeImg").css("background-image", "url(images/close.png)");
        $("#cadmin-type").val('0');
    }
}

//修改卡推荐
function cardSee(cardId) {
    var unSee = 'unsee2';
    var getSee = $("#card-see" + cardId).css("background-image");
    if (getSee.indexOf(unSee) >= 0) {
        $("#card-see" + cardId).css("background-image", "url(images/see2.png)");
    } else {
        $("#card-see" + cardId).css("background-image", "url(images/unsee2.png)");
    }
}

//删除一种卡
function cardDel(cardId) {
    var cardNum = parseInt($(".card-allNum").val()) - 1;
    $(".card-allNum").val(cardNum);
    $("#card-list" + cardId).remove();
    var cardListHtml = $("#card-ListBox").html();
    var cardListArr = cardListHtml.split('</li>');
    var cardList = new Array();
    var i = 0;
    for (i = 0; i < cardNum; i++) {
        if (i < cardId - 1) {
            //删前加尾值不变
            cardList[i] = cardListArr[i] + '</li>';
        } else {
            //删后加尾值减一
            var oldId = i + 2;
            var newId = i + 1;
            var getSee = $("#card-see" + oldId).css("background-image");
            if (getSee.indexOf('unsee2') >= 0) {
                seeImg = "url(images/unsee2.png)";
            } else {
                seeImg = "url(images/see2.png)";
            }
            cardList[i] = '<li class="card-list" title="单击文字即可修改，按住拖动排序~" id="card-list' + newId + '"><input class="card-title" value="' + $("#card-title" + oldId).val() + '" id = "card-title' + newId + '"><input class="card-day" value="' + $("#card-day" + oldId).val() + '" id = "card-day' + newId + '"><input class="card-price" value="' + $("#card-price" + oldId).val() + '" id = "card-price' + newId + '"><div class="card-see" id="card-see' + newId + '" onclick="cardSee(' + newId + ')" style="background-image:' + seeImg + '"></div><div class="card-del" onclick="cardDel(' + newId + ')">×</div></li>';
        }
    }
    var newCardListHtml = "";
    for (i = 0; i < cardList.length; i++) {
        newCardListHtml = newCardListHtml + cardList[i];
    }
    var divshow = $("#card-ListBox");
    divshow.text("");
    divshow.append(newCardListHtml);
    //刷新提示功能
    $('.card-list').tooltipster({theme: 'tooltipster-borderless'});
}

//上传卡数据
function cardSure() {
    var cardNum = parseInt($(".card-allNum").val());
    var cardList = [];
    var i = 0, j = 0, safe = 0;
    for (i = 1; i <= cardNum; i++) {
        $("#card-list" + i).css("background-image", "linear-gradient(to right, #074FF2 , #407AFC)");
    }
    //查空 查重
    for (i = 1; i <= cardNum; i++) {
        if ($("#card-title" + i).val() == "" || $("#card-day" + i).val() == "" || $("#card-price" + i).val() == "") {
            $("#card-list" + i).css("background-image", "linear-gradient(to right, #E9220D , #F77062)");
            $("#card-list" + i).css("animation", "shake 1s");
            cardReset(i);
            var x = document.getElementById("card-list" + i);
            safe = -1;
        }
        for (j = 1; j < i; j++) {
            if ($("#card-title" + i).val() == $("#card-title" + j).val()) {
                $("#card-list" + i).css("background-image", "linear-gradient(to right, #E9220D , #F77062)");
                $("#card-list" + j).css("background-image", "linear-gradient(to right, #E9220D , #F77062)");
                $("#card-list" + i).css("animation", "shake 1s");
                $("#card-list" + j).css("animation", "shake 1s");
                cardReset(i);
                cardReset(j);
                safe = -1;
            }
        }
    }
    //上传数据
    if (safe != -1) {
        var cardPost = '[';
        for (i = 0; i < cardNum; i++) {
            var getCard = [];
            cardPost = cardPost + '{';
            getCard['name'] = $("#card-title" + i).val();
            cardPost = cardPost + '"name":"' + getCard['name'] + '",';

            getCard['price'] = parseFloat($("#card-price" + i).val());
            getCard['price'] = getCard['price'].toFixed(2);
            cardPost = cardPost + '"price":"' + getCard['price'] + '",';

            getCard['expireDay'] = parseInt($("#card-day" + i).val());
            cardPost = cardPost + '"expireDay":"' + getCard['expireDay'] + '",';

            var getSee = $("#card-see" + i).css("background-image");
            if (getSee.indexOf('unsee2') >= 0) {
                getCard['see'] = 0;  //隐藏
            } else {
                getCard['see'] = 1;  //显示
            }
            cardPost = cardPost + '"see":"' + getCard['see'] + '",';

            getCard['rank'] = cardNum - i;
            cardPost = cardPost + '"card_rank":"' + getCard['rank'] + '"}';

            if (i != cardNum - 1) {
                cardPost = cardPost + ',';
            } else {
                cardPost = cardPost + ']';
            }
            cardList[i] = getCard;
        }
        console.log(cardPost);

        token = getToken();
        $.ajax({
            url: "card/add",
            type: "post",
            dataType: "json",
            data: {'token': token, 'card': cardPost},
            success: function (data) {
                if (data.fail == 1) {
                    alert("上传成功")
                }
            },
        });
    }

}

//清除动画
function cardReset(i) {
    setTimeout(function () {
        $("#card-list" + i).css("animation", "");
    }, 1100);
}

//添加一种卡
function addCardList() {
    var cardNum = parseInt($(".card-allNum").val()) + 1;
    $(".card-allNum").val(cardNum);
    html = '<li class="card-list" title="单击文字即可修改，按住拖动排序~" id="card-list' + cardNum + '"><input class="card-title" value="' + cardNum + '卡" id = "card-title' + cardNum + '"><input class="card-day" value="31" id = "card-day' + cardNum + '"><input class="card-price" value="88.8" id = "card-price' + cardNum + '"><div class="card-see" id="card-see' + cardNum + '" onclick="cardSee(' + cardNum + ')" style="background-image:url(images/see2.png)"></div><div class="card-del" onclick="cardDel(' + cardNum + ')">×</div></li>';
    $("#card-ListBox").append(html);
    //刷新提示功能
    $('.card-list').tooltipster({theme: 'tooltipster-borderless'});
}

//获取本地令牌
function getToken() {
    if ($.cookie("codekeep") != null) {
        var cookie = $.cookie("codekeep");
    } else {
        $(location).attr('href', 'login.html');
    }
    return cookie;
}

//退出
function logout() {
    $.cookie('codekeep', '', {expires: -1});
    $(location).attr('href', 'login.html');
}

//判定浏览器
function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if (isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if (fIEVersion == 7) {
            return 7;
        } else if (fIEVersion == 8) {
            return 8;
        } else if (fIEVersion == 9) {
            return 9;
        } else if (fIEVersion == 10) {
            return 10;
        } else {
            return 6;//IE版本<=7
        }
    } else if (isEdge) {
        return 1;//edge
    } else if (isIE11) {
        return 11; //IE11  
    } else {
        return -1;//不是ie浏览器
    }
}

//判定是否禁止加载
function IEShow() {
    if (IEVersion() > 1) {
        $(".ie-box").css("display", "block");
        $(".title").css("display", "none");
        $(".numShow").css("display", "none");
        $(".main").css("display", "none");
    }
}



