//监听回车
$(function(){
    $('#wxId').bind('keypress',function(event){
        if(event.keyCode == "13"){
            getCard();
        }
    });
});

function stopWallpaper(){
    $(".bg").css("display","none");
    $(".bgButton").css("display","none");
    $(".right-button").css("display","none");
    $(".right-card").css("display","none");
    $(".right-say").css("display","none");
    $(".right").css("display","block");
    $("#wxId").focus();
}


function beginWallpaper(){
    $(".right-tips").html('支付结束，3秒后返回主界面');
    setInterval(function(){
        $(".bg").css("display","block");
        $(".bgButton").css("display","block");
        $(".right-button").css("display","none");
        $(".right-card").css("display","none");
    },3000);
}

//获取卡数据
function getCard(){
    $(".right-qrcode").css("display","none");
    $(".right-tips").text('请选择充值卡类型');
    $(".right-button").css("display","block");
    $.ajax({
        url:"../keepRecord/add",
        type : "post",
        data:{"wxId":$('#wxId').val()},
        dataType : "json",
    });


    $.ajax({
        url:"../card/all",
        type : "post",
        dataType : "json",
        success : function(data){
            $(".right-card").css("display","block");
            getCard = data.cardNum;
            var i = 1,html = "",hideHtml = "";
            for(i=1;i<=getCard;i++){
                var cardHtml = '<div class="card" id="card' + i + '" onclick="cardChoose(' + i + ');"><div class="card-name" id="card-name' + i + '">' + data.card[i-1].name + '</div><div class="card-expireDay">' + data.card[i-1].expireDay + '天</div><div class="card-price">' + data.card[i-1].price + '元</div></div>';

                if(data.card[i-1].see == 1){
                    html = html + cardHtml;
                }else{
                    hideHtml = hideHtml + cardHtml;
                }
            }
            var divshow = $(".card-show");
            divshow.text("");
            divshow.append(html);
            var divshow = $(".card-hide");
            divshow.text("");
            divshow.append(hideHtml);
            $("#cardNum").val(getCard);
        }
    });
}

//卡被选中
function cardChoose(id){
    var cardNum = parseInt($("#cardNum").val());
    var i = 1;
    for(i = 1;i <= cardNum;i++){
        $("#card" + i).css("background-color","#FFF");
    }
    $("#card" + id).css("background-color","#111");
}

//更多类型
function cardMore(){
    $(".card-more").css("display","none");
    $(".card-hide").css("display","block");
}

//开始支付
function cardPay(){
    /*
    支付流程：
    1、 前端页面和后端 发送支付请求数据（用户wxid、卡id）
    2、 后端生成订单号并加上官方秘钥后再次发送给第四方支付服务器，索要支付二维码数据
        （文档： https://www.xddpay.com/doc/pay.htm）
    3、 后端返回支付二维码和订单号数据返回前端（返回 qr 和 xddpay_order 内容）
    4、 后端回调页面等待接收异步通知（服务器需返回“success”，就表示回调已收到）
    5、 前端显示支付二维码页面，每隔3s向后端请求是否支付成功数据，同一订单最多请求5分钟。成功返回1，失败返回0 
    
    注意！！前端支付有效时间设置为5分钟，超时无法扫码，自动返回首页。
    五分钟扫码五分钟后完成付款订单依旧需要给用户增加时长信息！但前端不会显示信息。
    */

    var cardNum = parseInt($("#cardNum").val());
    var payCard = '';
    var i = 1;
    for(i = 1;i <= cardNum;i++){
        if($("#card" + i).css("background-color") == "rgb(17, 17, 17)" || $("#card" + i).css("background-color") == "#111"){
            payCard = $("#card-name" + i).text();
            break;
        }
    }

    $(".right-say").html("支付商品：" + payCard + "<br/>等待拉取支付二维码...")
    $(".right-card").css("display","none");
    $("#wxId").css("display","none");
    $(".right-button").css("display","none");
    $(".right-qrcode").css("display","block");
    $(".right-qrcode").css("height","300px");
    $(".right-qrcode").css("width","300px");
    $(".right-tips").text('支付宝扫码支付');
    $(".qrcode-show").css("background-image","");
    $(".right-say").css("display","block");

    $.ajax({
        url:"/admin/api/getpay.api",
        type : "post",
        dataType : "json",
        data:{"payCard" : payCard},
        success : function(data){
            if(data.fail == 1){
                $(".qrcode-show").css("background-image",'url(http://qr.topscan.com/api.php?text=' + data.qr + ')');
                payReturn(data.xddpay_order,payCard)
            }
        }
    });
}

//异步监听
function payReturn(order,payCard){
    var wrongTime = 0;
    var getTime = 0;
    var payReturn = setInterval(function(){
        getTime++;
        $(".right-say").html("支付商品：" + payCard + "<br/>第" + getTime + "次拉取数据")

        if(getTime == 60){
            //支付超时
            $(".right-tips").html('支付超时<br/>未付款请停止支付重新发起');
            $(".qrcode-show").css("background-image","url(img/success.png)");
            beginWallpaper()
            clearInterval(payReturn);
        }
        $.ajax({
            url:"/admin/api/getpayreturn.api",
            type : "post",
            dataType : "json",
            data:{"xddpay_order" : order},
            success : function(data){
                if(data.fail == 1 && data.payReturn == 1 ){
                    //支付成功
                    $(".right-tips").text('支付成功');
                    $(".qrcode-show").css("background-image","url(img/success.png)");
                    beginWallpaper();
                    clearInterval(payReturn);
                }
                if(data.fail == -1){
                    wrongTime++;
                    if(wrongTime > 20){  //后端调用频繁出错
                        //支付错误
                        $(".right-tips").html('支付回调错误，请停止支付<br/>已支付请联系管理员！');
                        $(".qrcode-show").css("background-image","img/unknow.png");
                        beginWallpaper();
                        clearInterval(payReturn);
                    }
                }
            }
        });
    },3000);
}
