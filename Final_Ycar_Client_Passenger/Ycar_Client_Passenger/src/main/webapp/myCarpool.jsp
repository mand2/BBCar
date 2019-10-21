<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YCAR</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- datepicker -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
<!-- tmap -->
<script src="https://apis.openapi.sk.com/tmap/js?version=1&format=javascript&appKey=5beda631-7db0-4be9-b0bd-b6b5a7f41945"></script>
<!-- bootstrap -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/static/style.css'/>">
<link rel="stylesheet" href="<c:url value='/static/css/animate.css'/>">
<script src="<c:url value='/static/js/jquery.waypoints.min.js'/>"></script>
<!-- font -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<style>
body {
   background-color: #FFFEF4;
   font-family: 'Noto Sans KR', sans-serif;
   text-align: center;
}
.container {
   margin-top: 100px;
   margin-bottom: 100px;
   min-width: 100px;
   width: 500px;
}
.ftco-navbar-light.scrolled {
   background-color: #FFFEF4 !important;
}
.boxes {
   text-align: center;
   margin: 0 auto;
   width: 80%
}
.modal-body {
   text-align: left;
}
.inputborder {
   border: 0px;
}
.rsvsbtn {
   display: inline-block !important;
   width: 100px !important;
   height: 40px;
}
.rsv {
   text-align: left;
   color: black;
   background-color: #FEFFFB;
   margin-bottom:10px;
}
#prsv {
   text-align: left;
   color: black;
   margin-bottom:10px;
   background-color: #F7F7F7;
}
#matchimg{
	float: left;
	width: 130px;
	display:inline-block;
}
.fitem{
	color:#999999;
	font-size:15px;
}
#frdate{
	float:right;
	color:#999999;
	font-size:10px;
}
#Tmap_Map_7_Tmap_ViewPort{
	display:none;
}
</style>
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300"> 
    
    <!--navbar 시작-->
	<%@ include file="frame/passengerNavbar.jsp"%>
	<!--navbar 끝 -->
	
<div class="container">
   <div id="title">
      <h2 class="mb-4">나의 카풀</h2>
   </div>
   <div class="row">
      <div class="col-md-12">
         <div class="search-wrap-1 ftco-animate p-4 boxes">
            <button id="confirmRsv" onclick="confirmRsv();" class="btn btn-primary rsvsbtn">확정된예약</button>
            <button id="waitingRsv" onclick="waitingRsv();" class="btn btn-primary rsvsbtn">대기예약</button>
            <button id="waitingRsv" onclick="pastRsv();" class="btn btn-primary rsvsbtn">지난예약</button>
         </div>
         <div class="boxes">
            <div id="confirmList"></div>
            <div id="waitingList"></div>
            <div id="pastList"></div>
         </div>
      </div>
   </div>
</div>
<!-- 카풀 경로 모달 --> 
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="selectModalLabel">선택하신 카풀의 경로입니다.</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <div id="map_div"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary rsvsbtn" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>
<script>
var p_idx = ${sessionScope.login.idx};
var socket = io('http://54.180.123.46:3000/');

function drivingStart (r_idx) {
//소켓 연결 
console.log('소켓 연결을 위한 r_idx '+r_idx);
socket.emit('join start room', r_idx); 
socket.on('startroom join result', function(msg){
console.log(msg);
	});
	alert('운전자가 운행 시작 버튼을 누르면 자동으로 운행이 시작됩니다. 조금만 기다려주세요!');
}
      
//운행중 page 로 redirect 
socket.on('go driving page', function(r_idx){
	console.log('탑승자님, 운행 중 페이지로 이동하실게요 '+r_idx);
	setTimeout(function(){
		window.location.href="http://13.125.252.85:8080/passenger/passenger-driving.jsp?r_idx="+r_idx;
		}, 3000);
});  
      
$(document).ready(function() {
	viewRoute();
	$('#viewModal').on('hide.bs.modal', function(e) {
		});
});



function confirmRsv(){
	$('#waitingList').css('display', 'none');
    $('#pastList').css('display', 'none');
    $('#confirmList').css('display', 'block');
    $.ajax({
    	url : 'http://13.125.252.85:8080/server/rsv/mycarpool/' + p_idx,
        type : 'GET',
        success : function(data){
        	var html = '';
            for (var i = 0; i < data.length; i++) {
                  
            	html += '<div class="rsv">\n';
				html += '<img src="<c:url value='/static/images/logo_yeoncha.png'/>" id="matchimg">';
				html += '<span class="fitem">운전자</span>\t'+ data[i].nickname + '<br>\n';
				html += '<span class="fitem">카풀일시</span>\t'+ data[i].d_date + '\t'+ data[i].d_starttime + '\t -\t '+ data[i].d_endtime + '<br>\n';
				html += '<span class="fitem">출발지</span>\t'+ data[i].d_startpoint + '<br>\n';
				html += '<span class="fitem">도착지</span>\t'+ data[i].d_endpoint + '<br>\n';
				html += '<span class="fitem">요금</span>\t'+ data[i].d_fee + '원 <br>\n';
				html += '<span id="frdate">예약일자 ' + data[i].r_date+ '</span><br>\n';
				html += '<button id="view" onclick="viewRoute('+ data[i].d_startlon + ', ' + data[i].d_startlat + ', ' + data[i].d_endlon + ', ' + data[i].d_endlat + ')" class="btn btn-primary rsvsbtn" data-toggle="modal" data-target="#viewModal">경로보기</button>\t';
				html += '<button class="btn btn-primary rsvsbtn" onclick="drivingStart('+data[i].r_idx+')" >탑승대기</button>\t';
				html += '<button id="delete" onclick="deleteRsv(' + p_idx + ',' + data[i].r_idx + ')" class="btn btn-primary rsvsbtn">카풀취소</button>';
				html += '</div>'
				}
               $('#confirmList').html(html);
          }
    });
}
      
function deleteRsv(p_idx, r_idx){
    	  if (confirm('예약을 취소하시겠습니까?')) {
    		  $.ajax({
    		  url : 'http://13.125.252.85:8080/server/rsv/mycarpool/'+p_idx+'/'+r_idx,
    		  type : 'DELETE',
    		  success : function(data) {
    			  if (data == 'success') {
						alert('취소되었습니다!');
					} else {
						alert('취소실패');
					}
    			}
    		  });
    	}
}
      

function waitingRsv(){
         $('#confirmList').css('display', 'none');
         $('#pastList').css('display', 'none');
         $('#waitingList').css('display', 'block');
         $.ajax({
            url : 'http://13.125.252.85:8080/server/rsv/waitcarpool/' + p_idx,
            type : 'GET',
            success : function(data){
               var html = '';
               for (var i = 0; i < data.length; i++) {
            	   html += '<div class="rsv">\n';
					html += '<span class="fitem">카풀일시</span>\t' + data[i].d_date + '\t' + data[i].d_starttime + '\t -\t ' + data[i].d_endtime + '<br>\n';
					html += '<span class="fitem">출발지</span>\t' + data[i].d_startpoint + '<br>\n';
					html += '<span class="fitem">도착지 </span>\t ' + data[i].d_endpoint + '<br>\n';
					html += '<span class="fitem">요금</span>\t' + data[i].d_fee + '원 <br>\n';
					html += '<span id="frdate">예약요청일자 '+ data[i].r_date + '</span><br>\n';
					html += '<button id="view" onclick="viewRoute('+ data[i].d_startlon + ', ' + data[i].d_startlat + ', ' + data[i].d_endlon + ', ' + data[i].d_endlat + ')" class="btn btn-primary rsvsbtn" data-toggle="modal" data-target="#viewModal">경로보기</button>\t';
					html += '<button id="delete" onclick="deleteReq('+ p_idx + ',' + data[i].r_idx + ')" class="btn btn-primary rsvsbtn">요청취소</button>';
					html += '</div>'
					}
               $('#waitingList').html(html);
            }
         });  
      }
     

function deleteReq(p_idx, r_idx){
    	 if (confirm('예약을 취소하시겠습니까?')) {
    		 $.ajax({
    		  url : 'http://13.125.252.85:8080/server/rsv/waitcarpool/'+p_idx+'/'+r_idx,
    		  type : 'DELETE',
    		  uccess : function(data) {
					if (data == 'success') {
						alert('취소되었습니다!');
					} else {
						alert('취소실패');
					}
					}
    		 });
    	}
}

      
function pastRsv(){
    	  
         $('#waitingList').css('display', 'none');
         $('#confirmList').css('display', 'none');
         $('#pastList').css('display', 'block');
         $.ajax({
            url : 'http://13.125.252.85:8080/server/rsv/pastcarpool/'+ p_idx,
            type : 'GET',
            success : function(data){
               var html = '';
               for (var i = 0; i < data.length; i++) {
					html += '<div id="prsv">\n';
					html += '<span class="fitem">카풀일시</span>\t' + data[i].d_date + '\t' + data[i].d_starttime + '\t -\t ' + data[i].d_endtime + '<br>\n';
					html += '<span class="fitem">출도착지</span>\t' + data[i].d_startpoint + '\t <span class="fitem"> ~ </span>' + data[i].d_endpoint + '<br>\n';
					html += '<span class="fitem">요금</span>\t' + data[i].d_fee + '<span class="fitem">원 </span><br>\n';
					html += '</div>'
				}
               $('#pastList').html(html);
            }
      });
}



/* -------------------------------카풀리스트 tmap------------------------------- */
var map;
function viewRoute(d_startlon, d_startlat, d_endlon, d_endlat) {
		// 페이지가 로딩이 된 후 호출하는 함수입니다.
		// map 생성
		// Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
		map = new Tmap.Map({
			div : 'map_div',
			width : '460px',
			height : '450px'
		});
		var tData = new Tmap.TData(); //REST API 에서 제공되는 경로, 교통정보, POI 데이터를 쉽게 처리할 수 있는 클래스입니다.
		var s_lonLat = new Tmap.LonLat(d_startlon, d_startlat); //시작 좌표입니다.   
		var e_lonLat = new Tmap.LonLat(d_endlon, d_endlat); //도착 좌표입니다.
		var optionObj = {
			reqCoordType : "WGS84GEO", //요청 좌표계 옵셥 설정입니다.
			resCoordType : "EPSG3857" //응답 좌표계 옵셥 설정입니다.
		}
		tData.getRoutePlan(s_lonLat, e_lonLat, optionObj); //경로 탐색 데이터를 콜백 함수를 통해 XML로 리턴합니다.
		tData.events.register("onComplete", tData, onComplete); //데이터 로드가 성공적으로 완료되었을 때 발생하는 이벤트를 등록합니다.
		tData.events.register("onPrnError", tData, onError); //데이터 로드가 실패했을 떄 발생하는 이벤트를 등록합니다.
		//데이터 로드가 성공적으로 완료되었을 때 발생하는 이벤트 함수 입니다. 
		function onComplete() {
			console.log(this.responseXML); //xml로 데이터를 받은 정보들을 콘솔창에서 확인할 수 있습니다.
			var kmlForm = new Tmap.Format.KML({
				extractStyles : true
			}).read(this.responseXML);
			var vectorLayer = new Tmap.Layer.Vector("vectorLayerID");
			vectorLayer.addFeatures(kmlForm);
			map.addLayer(vectorLayer);
			//경로 그리기 후 해당영역으로 줌  
			map.zoomToExtent(vectorLayer.getDataExtent());
		}
		//데이터 로드중 발생하는 이벤트 함수입니다.
		function onProgress() {
			//alert("onComplete");
		}
		//데이터 로드시 에러가 발생시 발생하는 이벤트 함수입니다.
		function onError() {
			alert("오류가 발생했습니다. 죄송합니다.");
		}
}
</script>
<script src="<c:url value='/static/js/jquery.stellar.min.js'/>"></script>
<script src="<c:url value='/static/js/owl.carousel.min.js'/>"></script>   
<script src="<c:url value='/static/js/aos.js'/>"></script>   
<script src="<c:url value='/static/js/scrollax.min.js'/>"></script>
<script src="<c:url value='/static/js/main.js'/>"></script>
</body>
</html>