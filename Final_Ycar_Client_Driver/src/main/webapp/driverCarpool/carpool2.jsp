<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>연차 :채팅:</title>

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    

	<link rel="stylesheet" href="<c:url value='/staticD/css/open-iconic-bootstrap.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/animate.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/owl.carousel.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/owl.theme.default.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/magnific-popup.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/aos.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/ionicons.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/flaticon.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/icomoon.css'/>">
	<link rel="stylesheet" href="<c:url value='/staticD/css/style.css'/>">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--<link rel="stylesheet" href="/resources/demos/style.css">-->

    <!-- 한글 폰트 -->
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<!-- chat socket -->
<script src="http://13.125.134.106:5000/socket.io/socket.io.js"></script>
<!-- <script src="http://localhost:5000/socket.io/socket.io.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<style>
    #content {
        margin: 0 auto;
        /*max-width: 100%;*/
        text-align: center;
        /*height: auto;*/
        font-family: 'Noto Sans KR', sans-serif;
    }

    /*팝업창 css*/
    #popupDiv {
        top: 0px;
        position: fixed;
        background: white;
        width: 510px;
        height: 540px;
        display: none;
        padding-left: 10px;
    }

    /* 팝업 배경 css*/
    #popup_mask {
        position: fixed;
        width: 100%;
        height: 1000px;
        top: 0px;
        left: 0px;
        display: none;
        background-color: #000;
        -moz-opacity: 0.8;
        filter: alpha(opacity=80);
        opacity: 0.8;
    }

    #popCloseBtn {
        margin-left: 330px;
        margin-top: 10px;
        margin-bottom: 10px;
        width: 100px;
        height: 40px;
    }

    #year {
        text-align: center;
    }

    /*예약된 카풀*/
    #yearalllist {
        text-align: center;
    }

    #listdiv {
        margin: 0 auto;
        width: 100%;
    }

    #list {
        display: inline-block;
        border: 1px solid #DDD;
        margin-top: 20px;
        overflow: scroll;
        overflow-x: hidden;
        width: 500px;
        height: 500px;
    }

    /*예약된 카풀*/
    #allListdiv {
        margin: 0 auto;
        width: 100%;
    }

    /*예약된 카풀*/
    #allList {
        display: inline-block;
        border: 1px solid #DDD;
        margin-top: 20px;
        overflow: scroll;
        overflow-x: hidden;
        width: 500px;
        height: 500px;
    }


    .pastDiv {
        text-align: left;
        border-radius: 10px;
        /*border: 1px solid #DDD;*/
        width: 400px;
        padding-left: 10px;
        line-height: 40px;
        display: inline-block;
        background-color: white;
        margin-bottom: 25px;


    }

    #footer {
        height: 100px;
    }

    /*부트시작*/
    body {
        background-color: #FFFEF4;
        height: 100%;
    }

    #ftco-nav {
        font-family: 'Noto Sans KR', sans-serif;
    }

    #past {
        width: 100px;
        height: 40px;
    }

    #today {
        width: 100px;
        height: 40px;
    }

    #future {
        width: 100px;
        height: 40px;
    }

    /*예약된 카풀*/
    #pastAllButton {
        width: 100px;
        height: 40px;
    }

    /*예약된 카풀*/
    #todayAllButton {
        width: 100px;
        height: 40px;
    }

    /*예약된 카풀*/
    #futureAllButton {
        width: 100px;
        height: 40px;
    }


   /*  span {
        color: black;
        font-weight: bolder;
    } */

    .CommuteSPAN {
        display: inline-block;
        font-weight: 100;
        background-color: #cd3184;
        color: white;
        width: 50px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        border-radius: 5px;
        margin-top: 20px;
    }

    #pastmapBTN {
        width: 100px;
        height: 35px;
        line-height: 20px;

    }

    #home-section {
        margin-top: 120px;
    }

    #pastdelBTN {
        width: 300px;
        height: 40px;
        margin-left: 35px;
        margin-top: 10px;
    }
    #driving{
   		width: 300px;
        height: 40px;
        margin-left: 35px;
        margin-top: 10px;
    }

    .ftco-navbar-light.scrolled {
        background-color: #FFFEF4 !important;
    }

    .mb-4 {
        margin-top: 50px;
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: bolder;
        font-size: 35px;
    }

    #todaymapBTN {
        width: 100px;
        height: 35px;
        line-height: 20px;

    }

    #todaydelBTN {
        width: 300px;
        height: 40px;
        margin-left: 35px;
        margin-top: 10px;
    }

    #futuremapBTN {
        width: 100px;
        height: 35px;
        line-height: 20px;

    }

    #futuredelBTN {
        width: 300px;
        height: 40px;
        margin-left: 35px;
        margin-top: 10px;
    }

/* --------------------------------- */
/*             채 팅 c s s           */
#container {
	width: 400px;
	height: 600px;
	border: 1px solid black;
	background: #FFFEF4;
	margin: 50px auto;
	font-family: 'Noto Sans KR', sans-serif;
}

#chatView {
	height: 90%;
	overflow-y: scroll;
}

#chatForm {
	height: 10%;
	border-top: 1px solid black;
	text-align: center;
}

#msg {
	width: 70%;
	height: 63%;
	line-height: 32px;
	border-radius: 8px;
	margin: 10px 0;
	padding: 5px;
	font-family: 'Noto Sans KR', sans-serif;
}

#send {
	width: 16%;
	height: 34px;
	border-radius: 50px;
	background: #413A6D;
	color: white;
	font-family: 'Noto Sans KR', sans-serif;
	margin-left: 5px;
}

.msgLine {
	margin: 15px;
	clear: both;
}

.msgBox {
	border: 1px solid black;
	background: skyblue;
	padding: 2px 5px;
	border-radius: 10px;
	font-size: 14px;
}

.me {
	color: #fff;
}

.btn_chat {
	background-color: #413A6D;
	display: inline-block;
	color: #fff;
	padding: 2px 10px;
	margin-top: 10px;
}

#roomTitle {
	background-color: #ccc;
	text-align: center;
	padding: 10px 0;
	color: #fff;
}

.roomactive {
	background-color: #6258A4 !important;
}

.infoTxt {
	font-size: 10px;
	text-align: left;
	clear: both;
}

.bold {
	font-weight: bold;
	color: #000;
}

/*             채 팅 c s s           */
/* --------------------------------- */
</style>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

	<!--navbar 시작-->
	<%@ include file="../paymentAndReview/frame/driverNavbar.jsp"%><!--navbar 끝 -->
	

	<section id="home-section" class="ftco-section ftco-no-pb ftco-no-pt">
		<div id="content">
			
            <div class="row justify-content-center pb-0 pb-mb-5 pt-5 pt-md-0"></div>
            <h1 class="mb-4">예약 된 카풀</h1>

            <div id="left">
				<div id="year">
                    <button id="today" class="btn btn-primary"></button>
                    <button id="future" class="btn btn-primary"></button>
                </div>
				<div id="listdiv">
					<div id="list">
						<br>
						<div id="todayList"></div>
						<div id="futureList"></div>
					</div>
				</div>
				<div id="listdiv">
	                    <div id="list">
	                        <br>
	                        <div id="pastList"></div>
	                        <div id="todayList"></div>
	                        <div id="futureList"></div>
	                    </div>
	
	                    <div id="popup_mask"></div> <!-- 팝업 배경 DIV -->
	
	                    <div id="popupDiv">
	                        <!-- 팝업창 -->
	                        <button id="popCloseBtn" class="btn btn-primary">닫기</button>
	                        <div id="map_div"></div>
	                    </div>
	             </div>
             </div>


			<!-- chat room -->
			<div class="row justify-content-center pb-0 pb-mb-5 pt-5 pt-md-0">
				<input type="hidden" value="${sessionScope.loginInfo.nickname}"
					id="chatNickName">
			</div>
			<h1 class="mb-4">채팅</h1>

			<div id="container">
				<div id="chatView">
					<div id="roomTitle">채팅방준비중</div>
				</div>
				<form id="chatForm" onsubmit="return false">
					<input type="text" id="msg"> <input type="submit" id="send"
						value="전송">
				</form>
			</div>

		</div>

	</section>
	<div id="footer"></div>

	<script src="<c:url value='/staticD/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/jquery-migrate-3.0.1.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/popper.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/jquery.easing.1.3.js'/>"></script>
	<script src="<c:url value='/staticD/js/jquery.waypoints.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/jquery.stellar.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/owl.carousel.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/jquery.magnific-popup.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/aos.js'/>"></script>
	<script src="<c:url value='/staticD/js/jquery.animateNumber.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/scrollax.min.js'/>"></script>
	<script src="<c:url value='/staticD/js/bootstrap.min.js'/>"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://apis.openapi.sk.com/tmap/js?version=1&format=javascript&appKey=61f9f7ec-2010-4d26-97e1-806dc10dce63"></script>
	
	
<script>
	var count = 0;
	
	var d = new Date();
	
	var thisyear = d.getFullYear() + "년";
	var today;
	var past;
	var future;
	var thisday;
	
	//날짜 비교 변수
	//10월 미만, 10일 미만 은 0붙여주기
	if(d.getMonth()+1 < 10 && d.getDate() < 10){
	   thisday = d.getFullYear() + "-" + "0" + (d.getMonth() + 1) + "-" +"0"+ d.getDate();   
	   //그냥 10월 미만
	}else if(d.getMonth()+1 < 10){
	   thisday = d.getFullYear() + "-" + "0" + (d.getMonth() + 1) + "-" + d.getDate();   
	   //10일 미만
	}else if(d.getDate() < 10){
	   thisday = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + "0" + d.getDate();   
	}else if(d.getMonth() + 1 == 13){
	   thisday = d.getFullYear() + "-" + "01" + "-" + d.getDate();
	}else{
	   thisday = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
	}
   

	
	//과거
	//0일일경우..
	if(d.getDate()-1 < 1){
	   //3일일경우..
	   if(d.getMonth()+1 == '3'){
	      past = "~" + (d.getMonth()) + "/28";
	      //홀수달일경우.. 
	   }if(d.getMonth()+1 == '1' || '5' || '7' || '9' || '11'){
	      past = "~" + (d.getMonth()) + "/30";
	      //짝수달일경우..
	   }if(d.getMonth()+1 == '2' || '4' || '6' || '8' || '10' || '12'){
	      past = "~" + (d.getMonth()) + "/31";
	   }  
	}else{
	   past = "~" + (d.getMonth() + 1) + "/" + (d.getDate() - 1);   
	};
	
	//과거
	//1일 일경우
		   
	//오늘
	today = (d.getMonth() + 1) + "/" + d.getDate();
	   
		   
	
	//미래
	//32일이 되어버린다면..
	if(d.getDate() + 1 == 32){
	   future = (d.getMonth() + 2) + "/1" + "~";   
	}else{
	   future = (d.getMonth() + 1) + "/" + (d.getDate() + 1) + "~";
	}



    $(document).ready(function() {
        
            $('#thisyear').append(thisyear);
            $('#today').append(today);
            $('#future').append(future);
        
        list();


        //리스트 none
        $('#futureList').css('display', 'none');
        $('#pastList').css('display', 'none');

        //날짜 버튼 클릭시 디스플레이
        $('#past').click(function() {
            $('#pastList').css('display', 'inline');
            $('#todayList').css('display', 'none');
            $('#futureList').css('display', 'none');

        });

        $('#today').click(function() {
            $('#todayList').css('display', 'inline');
            $('#pastList').css('display', 'none');
            $('#futureList').css('display', 'none');
        });

        $('#future').click(function() {
            $('#futureList').css('display', 'inline');
            $('#pastList').css('display', 'none');
            $('#todayList').css('display', 'none');
        });

        
         });


        function list(){
			
        	console.log('버튼 날짜 :' + today);
        	console.log('리스트 날짜 :' + thisday);
        	
        	
            $.ajax({
                url: 'http://13.125.252.85:8080/server/carpool/Y/' + ${sessionScope.loginInfo.d_idx},
                type: 'GET',
                success: function(data) {

                    var pasthtml = '';
                    var todayhtml = '';
                    var futurehtml = '';
                    var pastlist = data.past;
                    var todaylist = data.today;
                    var futurelist = data.future;

                    //과거
                    for (var i = 0; i < data.length; i++) {

                        var fee = data[i].d_fee.toString();
                        var feeCut = fee.substring(fee.length, fee.length - 3);
                        var split = fee.split(feeCut);
                        var feeSplit = split[0] + ',' + feeCut;
                        
           	/* ------ 수정부분------ */
			 		   //오늘
						if (data[i].d_date == thisday) {
						
							todayhtml += '<div class="pastDiv">';
							todayhtml += '<span class="CommuteSPAN">' + data[i].d_commute + '</span><br>\n';
							todayhtml += '<span>예약자 닉네임 : ' + data[i].nickname + '</span><br>\n';
							todayhtml += '<span class="DateSPAN">날짜 : ' + '</span>' + data[i].d_date + '<br>\n';
							todayhtml += '<span class="STimeSPAN">출발시간 : ' + '</span>' + data[i].d_startTime + ' <br>\n';
							todayhtml += '<span class="ETimeSPAN">도착시간 : </span>' + data[i].d_endTime + '<br>\n';
							todayhtml += '<span class="SpointSPAN">출발장소 : </span>' + data[i].d_startPoint + '<br>\n';
							todayhtml += '<span class="EpointSPAN">도착장소 : </span>' + data[i].d_endPoint + '<br>\n';
							todayhtml += '<button class="btn btn-primary" id="driving'+data[i].r_idx+'" onclick="fn_chat('+data[i].r_idx+')" >채팅하기</button><br><br>\n';
						/* todayhtml += '<input type="hidden" id="hiddenRidx" value="'+data[i].r_idx+'">'; */
						     todayhtml += '</div>';
							
					    //미래
						} else if (data[i].d_date > thisday) {
						
							futurehtml += '<div class="pastDiv">';
							futurehtml += '<span class="CommuteSPAN">' + data[i].d_commute + '</span><br>\n';
							futurehtml += '<span>예약자 닉네임 : ' + data[i].nickname + '</span><br>\n';
							futurehtml += '<span class="DateSPAN">날짜 : ' + '</span>' + data[i].d_date + '<br>\n';
						    futurehtml += '<span class="STimeSPAN">출발시간 : ' + '</span>' + data[i].d_startTime + ' <br>\n';
						    futurehtml += '<span class="ETimeSPAN">도착시간 : </span>' + data[i].d_endTime + '<br>\n';
						    futurehtml += '<span class="SpointSPAN">출발장소 : </span>' + data[i].d_startPoint + '<br>\n';
						    futurehtml += '<span class="EpointSPAN">도착장소 : </span>' + data[i].d_endPoint + '<br>\n';
						    futurehtml += '<button class="btn btn-primary" id="driving'+data[i].r_idx+'" onclick="fn_chat('+data[i].r_idx+')" >채팅하기</button><br><br>\n';
						    futurehtml += '</div>';
						}
					}
								
				    $('#pastList').html(pasthtml);
				    $('#todayList').html(todayhtml);
				    $('#futureList').html(futurehtml);
			
				}
			});            
            return false;
        };

</script>
	
	
	
					/**********************
					    	  채팅용
					**********************/
	<script>

	const socketChat = io('http://13.125.134.106:5000');
	/* const socketChat = io('http://localhost:5000'); */
	var chat_nickname = $('#chatNickName').val();
	console.log('nickname type? \n' + typeof(chat_nickname));
	
	function fn_chat(r_idx){
		socketChat.emit('set room', {room: r_idx, nickname: chat_nickname});
	      //title 재정의
		document.getElementById('roomTitle').innerHTML = r_idx + '번 방 개설!' + '<input type="hidden" id="chatIdx" value = "'+r_idx+'">';
	    $('#roomTitle').addClass('roomactive');
	}
  
	  let chatView = document.getElementById('chatView');
	  let chatForm = document.getElementById('chatForm');

	chatForm.addEventListener('submit', function() {
      let msg = $('#msg');

      if (msg.val() == '') {
          return;

      } else {
      	socketChat.emit('SEND', 
                      {room: $('#chatIdx').val(),
                      nickname: chat_nickname,
                      msg: msg.val()
                      }
		);

          let msgLine = $('<div class="msgLine">');
          let msgBox = $('<div class="me msgBox">');

          msgBox.append(msg.val());
          msgBox.css('display', 'inline-block');
          msgLine.css('text-align', 'right');
          msgLine.append(msgBox);

          $('#chatView').append(msgLine);
          
          msg.val('');
          chatView.scrollTop = chatView.scrollHeight;
      }
  });
  
	  socketChat.on('SEND', function(nickname, msg, date) {
		  if(nickname != chat_nickname){
			//msgData: room, nickname, parsedate, msg
		      let msgLine =  $('<div class="msgLine">');
		      let msgBox = $('<div class="msgBox">');
		      
		      msgLine.append('<p class="infoTxt bold">'+nickname+'</p>');
		      
		      msgBox.append(msg);
		      msgBox.css('display', 'inline-block');
		      msgBox.css('float', 'left');
		      msgLine.append(msgBox);
		      msgLine.append('<p class="infoTxt">'+date+'</p>');
		      
		      $('#chatView').append(msgLine);
		      chatView.scrollTop = chatView.scrollHeight;
		  }
	      
	  });
</script>


</body>
</html>
