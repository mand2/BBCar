<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>YeonCha</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/aos.css">
<link rel="stylesheet" href="css/ionicons.min.css">
<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<style>
body {
	background-color: #FFFEF4;
	font-family: 'Noto Sans KR', sans-serif;
	text-align: center; 
}

.container {
	margin-top: 100px;
	min-width:100px;
	width:500px;
}

.ftco-navbar-light.scrolled {
	background-color: #FFFEF4 !important;
}
.boxes{
text-align:center;
margin:0 auto;
width: 70%
}

.modal-body{
text-align: left; 
}
.inputborder{
border : 0px;
}
.rsvsbtn{
display : inline-block !important;
width : 100px !important;
height:40px;
}
.rsv{
text-align:left;
color:black;
}
</style>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300"> 
      <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light site-navbar-target" id="ftco-navbar">
          <div class="container">
              <a class="navbar-brand" href="index.html">YCAR</a>
              <button class="navbar-toggler js-fh5co-nav-toggle fh5co-nav-toggle" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="oi oi-menu"></span> Menu
            </button>
              <div class="collapse navbar-collapse" id="ftco-nav">
                  <ul class="navbar-nav nav ml-auto">
                      <li class="nav-item"><a href="index.jsp" class="nav-link"><span>카풀찾기</span></a></li>
                      <li class="nav-item"><a href="#" class="nav-link"><span>나의카풀</span></a></li>
                      <li class="nav-item"><a href="#about-section" class="nav-link"><span>결제</span></a></li>
                      <li class="nav-item"><a href="#destination-section" class="nav-link"><span>후기</span></a></li>
                      <li class="nav-item"><a href="#hotel-section" class="nav-link"><span>마이페이지</span></a></li>
                      <li class="nav-item"><a href="#contact-section" class="nav-link"><span>로그아웃</span></a></li>
                  </ul>
              </div>
          </div>
      </nav>
<div class="container">
	<div id="title">
		<h2 class="mb-4">나의 카풀</h2>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="search-wrap-1 ftco-animate p-4 boxes">
				<button id="confirmRsv" onclick="confirmRsv();" class="btn btn-primary rsvsbtn">확정된예약</button>
				<button id="waitingRsv" onclick="waitingRsv(p_idx);" class="btn btn-primary rsvsbtn">대기예약</button>
			</div>
			<div class="boxes">
				<div id="confirmList"></div>
				<div id="waitingList"></div>
			</div>
		</div>
	</div>
</div>

<!-- 카풀 삭제 모달 --> 
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="selectModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteModalLabel">카풀 예약을 취소하시겠습니까?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form id="deleteRsv">
      <input type="hidden" id="r_idx" name="r_idx">
      <input type="hidden" id="dr_idx" name="dr_idx">
      취소하면 예약이 삭제됩니다.<br>
      취소하시겠습니까?
      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary rsvsbtn" data-dismiss="modal">아니오</button>
        <button type="button" class="btn btn-primary rsvsbtn" id="requestReserve" onclick="requestReserve();">예약취소</button>
      </div>
    </div>
  </div>
</div>

      <script>
      $(document).ready(function() {
    	  confirmList();
    	  waitingList();
    	  
    	  
    	  
    	  
		$('#deleteModal').on('hide.bs.modal', function (e) {
		      $(this).find('.modal-body form')[0].reset(); 
		         //폼 초기화 : 이후 다시 열어도 폼이 비워져 있도록!
		   });
  		
      });
      
      var p_idx = 11; //나중에 세션으로 받아서 넣어야함
      
      function confirmRsv(p_idx){
    	  var p_idx=11; 
    	  $('#waitingList').css('display', 'none');
    	  $('#confirmList').css('display', 'block');
    	  $.ajax({
    		  url : 'http://localhost:8080/reservation/mycarpool/' + p_idx,
    		  type : 'GET',
    		  success : function(data){
    			  var html = '';
    			  for (var i = 0; i < data.length; i++) {
    				  
    				  html += '<div class="rsv">\n';
    				  html += '예약일자 '+ data[i].r_date + '<br>\n';
    				  html += '운전자 닉네임 ' + data[i].nickname + '<br>\n';
    				  html += '카풀일시 '+ data[i].d_date + '\t' + data[i].d_starttime + '\t -\t ' + data[i].d_endtime + '<br>\n';
    				  html += '출발지 '+ data[i].d_startpoint + '<br>\n';
    				  html += '도착지 ' + data[i].d_endpoint + '<br>\n';
    				  html += '요금 '+ data[i].d_fee +'원 <br>\n';
    				  html += '<button id="delete" onclick="deleteCarpool('+p_idx+',' + data[i].r_idx + ')" class="btn btn-primary" data-toggle="modal" data-target="#deleteModal">카풀취소</button>';
    				  html += '</div>' 
    			  }
    			  $('#confirmList').html(html);
    		  }
    	  });
    	  
      }
      
      function deleteCarpool(p_idx, r_idx){
    	  $.ajax({
    		  url : 'http://localhost:8080/reservation/mycarpool/'+p_idx+'/'+r_idx,
    		  type : 'DELETE',
    		  success : function(data) {
    			  $('#deleteModal').modal('hide');
    			  alert('삭제되었습니다!');
				}
    	  });
		}
      
      function waitingRsv(p_idx){
    	  var p_idx=11;
    	  $('#confirmList').css('display', 'none');
    	  $('#waitingList').css('display', 'block');
    	  $.ajax({
    		  url : 'http://localhost:8080/reservation/waitcarpool/' + p_idx,
    		  type : 'GET',
    		  success : function(data){
    			  var html = '';
    			  for (var i = 0; i < data.length; i++) {
    				  
    				  html += '<div class="rsv">\n';
    				  html += '예약요청일자 '+ data[i].r_date + '<br>\n';
    				  html += '카풀일시 '+ data[i].d_date + '\t' + data[i].d_starttime + '\t -\t ' + data[i].d_endtime + '<br>\n';
    				  html += '출발지 '+ data[i].d_startpoint + '\t 도착지 \t ' + data[i].d_endpoint + '<br>\n';
    				  html += '요금 '+ data[i].d_fee +'원 <br>\n';
    				  html += '</div>' 
    			  }
    			  $('#waitingList').html(html);
    		  }
    	  });  
      }      
      </script>

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-migrate-3.0.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/aos.js"></script>
	<script src="js/jquery.animateNumber.min.js"></script>
	<script src="js/scrollax.min.js"></script>
	<script src="https://apis.openapi.sk.com/tmap/js?version=1&format=javascript&appKey=5beda631-7db0-4be9-b0bd-b6b5a7f41945"></script>
	<script src="js/main.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</body>
</html>