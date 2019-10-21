        $(document).ready(function() {

            // 임시비밀번호 발급 회원 로그인 --> 마이페이지에서 비밀번호 수정페이지 띄우기
             getParams();
            
            // 즐겨찾는 장소 출력
             getPlace();
            
            // 선호 탑승 환경 출력
             getEnv();
             
            // 내 예약 목록
             getRsv();

            $('#edit').click(function() {
                // 공백인 상태에서 수정버튼 눌렀을 시 못가게 막음
                var pw1 = $('#pw1').val();
                var pw2 = $('#pw2').val();
                var pw3 = $('#pw3').val();

                if (pw1.length < 1 || pw2.length < 1 || pw3.length < 1) {
                    alert('비밀번호를 입력해주세요!');
                    return false;
                }

                if (!$('#pwCheck').prop('checked')) {
                    alert('[비말번호 불일치] 다시 확인해주세요!');
                    return false;
                }

                $.ajax({
                    url: 'http://13.125.252.85:8080/server/members/mypage',
                    type: 'PUT',
                    data: JSON.stringify({
                        id: $('#id').val(),
                        pw1: $('#pw1').val(),
                        pw2: $('#pw2').val()
                    }),
                    contentType: 'application/json; charset=utf-8',
                    success: function(data) {
                        if (data == 0) {
                            alert('잘못된 비밀번호');
                        }
                        if (data == 1) {
                            alert('내 정보 수정 성공!');
                            // 임시 비밀번호 수정한 경우를 위해 마이페이지 다시 로딩
                            location.href = "http://13.125.252.85:8080/passenger/mypage";
                        }
                        if (data == 2) {
                            alert('*수정오류');
                        }
                    },
                });
                return false;
            });

            // 새로운 비밀번호 일치하는지 확인
            $('#pw3, #pw2').focusout(function() {
                if ($('#pw2').val() == $('#pw3').val() || $('#pw3').val() == $('#pw2').val()) {
                    $('#pwSpan').html('비밀번호 일치');
                    $('#pwSpan').css('color', 'green');
                    $('#pwCheck').prop('checked', true);
                } else {
                    $('#pwSpan').html('비밀번호 불일치');
                    $('#pwSpan').css('color', 'red');
                    $('#pwCheck').prop('checked', false);
                }
            });
        });

        /* 내정보 수정 팝업창 */
        function editForm() {
            $("#popupDiv").css({
                "top": (($(window).height() - $("#popupDiv").outerHeight()) / 2 + $(window).scrollTop()) + "px",
                "left": (($(window).width() - $("#popupDiv").outerWidth()) / 2 + $(window).scrollLeft()) + "px"
                //팝업창을 가운데로 띄우기 위해 현재 화면의 가운데 값과 스크롤 값을 계산하여 팝업창 CSS 설정
            });

            $("#popup_mask").css("display", "block"); //팝업 뒷배경 display block
            $("#popupDiv").css("display", "block"); //팝업창 display block

            //$("body").css("overflow", "hidden"); //body 스크롤바 없애기
            $(".rightDiv").hide();

            $("#popCloseBtn").click(function(event) {
                $('#pw1').val('');
                $('#pw2').val('');
                $('#pw3').val('');
                $("#popup_mask").css("display", "none"); //팝업창 뒷배경 display none
                $("#popupDiv").css("display", "none"); //팝업창 display none
                $(".rightDiv").show();
                //$("body").css("overflow", "auto"); //body 스크롤바 생성 
            });


        }

        function getParams() {
            // 현재 페이지의 url
            var url = decodeURIComponent(location.href);
            // url이 encodeURIComponent로 인코딩 되었을 때 다시 디코딩
            url = decodeURIComponent(url);

            var params;
            // url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
            params = url.substring(url.indexOf('?') + 1, url.length);
            // 파라미터 구분자("&")로 분리
            params = params.split("&");
            //alert('params: ' + params);
            // ("=")로 분리해서 값 받아오기
            var user = params[0].split("=")[1];

            console.log('[임시비밀번호로 로그인 = temp, 일반 로그인 = undefined] 지금 당신은 ==> ' + user);

            if (user == 'temp') {
                // 정보수정 폼 띄우기
                alert('비밀번호를 수정해주세요!');
                $('#myInfo').css('display', 'none');
                editForm();
            }
        }
        
        function getPlace(){
        	
        	var idx = $('#sessionIdx').val();
        	
        	$.ajax({
                url: 'http://13.209.48.59:8090/findRoute/' + idx,
                type: 'GET',
                success: function(data) {
                	
                	for(var i=0; i<data.length; i++){
                		var html = data[i].place+" <a href=\"#\" class=\"btn py-1 px-4 btn-primary\">경로 수정</a>";
                		console.log("(1) 경로 확인:::"+data[i].place);
                		console.log("(1) html내용:::"+html);
                		$("#myPlace>li>span:first").replaceWith(html);
                	}
                },
            });
        }
        
        function getEnv(){
        	
        	var idx = $('#sessionIdx').val();
        	
        	$.ajax({
                url: 'http://13.209.48.59:8090/findEnv/' + idx,
                type: 'GET',
                success: function(data) {
                	
	                	console.log("(2) 환경 확인:::"+data.p_option);
	                	var option = data.p_option;
	                	
	                	if(option.includes('a')){
	                		$('#myEnv').append("<li><span id=\"a\">동성</span></li>");
	                	}
	                	if(option.includes('b')){
	                		$('#myEnv').append("<li><span id=\"b\">조용</span></li>");
	                	}
	                	if(option.includes('c')){
	                		$('#myEnv').append("<li><span id=\"c\">뒷자리 가능</span></li>");
	                	}
	                	if(option.includes('d')){
	                		$('#myEnv').append("<li><span id=\"d\">금연</span></li>");
	                	}
	                	if(option.includes('e')){
	                		$('#myEnv').append("<li><span id=\"e\">천천히</span></li>");
	                	}
	                	if(option.includes('f')){
	                		$('#myEnv').append("<li><span id=\"e\">빠르게</span></li>");
	                	}
                	}
           	 });
        }
        
        function getRsv(){
        	
        	var idx = $('#sessionIdx').val();
        	
        	$.ajax({
                url: 'http://13.209.48.59:8090/rsvList/' + idx,
                type: 'GET',
                success: function(data) {
                	
	                	console.log("리스트 확인:::"+data);
	                	
                	}
           	 });
        	
        }
        
        function deleteMem(){
        	
        	var idx = $('#sessionIdx').val();
        	
        	$.ajax({
                url: 'http://13.125.252.85:8080/passenger/mypage/deleteMem/' + idx,
                type: 'PUT',
                success: function(data) {
                	alert(data);
                    if (data == 'success') {
                        alert('회원탈퇴 성공');
                        location.href = "http://13.125.252.85:8080/passenger";
                    }
                    if (data == 'fail') {
                        alert('회원탈퇴 실패');
                        location.href = "http://13.125.252.85:8080/passenger/mypage";
                    }
                },
            });
        	
        }
        
        function memo(){
        	
        	var idx = $('#sessionIdx').val();
        	
        	$.ajax({
                url: 'http://13.209.48.59:8090/cpList/' + idx,
                type: 'GET',
                success: function(data) {
                	
                	var html = '';
                	
                	for(var i=0; i<data.length; i++){
                		html += '<table id="memoTable"><tr><td colspan="2">\n';
                		
                		if(data[i].r_confirm == 'Y'){
                			html += '<input type="hidden" value=\"'+data[i].dr_idx+'\" id="dr_idx'+i+'"><a style="color: black; font-weight: bold">"'+data[i].dr_idx+' 예약 불가"</a></td></tr>\n';
                		} else if(data[i].r_confirm == 'B'){
                			html += '<input type="hidden" value=\"'+data[i].dr_idx+'\" id="dr_idx'+i+'"><a style="color: red; font-weight: bold">"'+data[i].dr_idx+' 예약 임박"</a></td></tr>\n';
                			html += '<a class="btn py-1 px-4 btn btn-primary" style="float:right" onclick="writeMemo('+i+')">메모하기</a>';
                			html += '<tr><td id="'+i+'" class="memoTd"></td></tr>\n';
                		} else {
                			html += '<input type="hidden" value=\"'+data[i].dr_idx+'\" id="dr_idx'+i+'"><a style="color: green; font-weight: bold">"'+data[i].dr_idx+' 예약 가능"</a></td></tr>\n';
                			html += '<a class="btn py-1 px-4 btn btn-primary" style="float:right" onclick="writeMemo('+i+')">메모하기</a>';
                			html += '<tr><td id="'+i+'" class="memoTd"></td></tr>\n';
                		}
                		
                		if(data[i].memo != null){
                			for(var j=0; j<data[i].memo.length; j++){
                				html += '<tr><td><a class="myMemo">내 메모 ['+(j+1)+'번]</a></td><td><a class="myMemo"><input type="hidden" value=\"'+data[i].memo[j].m_idx+'\" id="m_idx">'
                				+data[i].memo[j].context+'</a> <button class="mmBtn" onclick="editMemo('+data[i].memo[j].m_idx+','+i+')">수정</button> <button class="mmBtn" onclick="delMemo('+data[i].memo[j].m_idx+')">삭제</button> </td></tr>\n';
                			}
                		}
                		html += '<tr><td>날짜</td><td>'+data[i].date+'</td></tr>\n';
                		html += '<tr><td>출발시간</td><td>'+data[i].d_starttime+'</td></tr>\n';
                		html += '<tr><td>도착시간</td><td>'+data[i].d_endtime+'</td></tr>\n';
                		html += '<tr><td>출발지</td><td>'+data[i].d_startpoint+'</td></tr>\n';
                		html += '<tr><td>도착지</td><td>'+data[i].d_endpoint+'</td></tr>\n';
                		html += '<tr><td>출/퇴근</td><td>'+data[i].d_commute+'</td></tr>\n';
                		html += '<tr><td>카풀비용</td><td>'+data[i].d_fee+'</td></tr>\n';
                		html += '<tr><td>거리</td><td>'+data[i].d_distance+'km</td></tr></table><hr><br>\n';
                	}
                	
                	$('#memoDiv').html(html);
                	
                },
            });
        	
        	// 메모 팝업창
        	$("#popupDiv2").css({
                "top": (($(window).height() - $("#popupDiv").outerHeight()) / 2 + $(window).scrollTop()) + "px",
                "left": (($(window).width() - $("#popupDiv").outerWidth()) / 2 + $(window).scrollLeft()) + "px"
                //팝업창을 가운데로 띄우기 위해 현재 화면의 가운데 값과 스크롤 값을 계산하여 팝업창 CSS 설정
            });

            $("#popup_mask2").css("display", "block"); //팝업 뒷배경 display block
            $("#popupDiv2").css("display", "block"); //팝업창 display block

            $(".rightDiv").hide();

            $("#back").click(function(event) {
                $("#popup_mask2").css("display", "none"); //팝업창 뒷배경 display none
                $("#popupDiv2").css("display", "none"); //팝업창 display none
                $(".rightDiv").show();
            });
        	
        }
        
        function writeMemo(index){
        	
        	var html = '';
			html += '<div><textarea placeholder="여기에 메모하기" id="memoContents"></textarea>\n';
			html += '<a class="btn py-1 px-4 btn btn-primary" id="FinMemo" onclick="FinMemo('+index+')">메모등록</a>\n';
			html += '<a class="btn py-1 px-4 btn btn-primary" style="margin-right: 10px;" id="cancelMemo" onclick="cancelMemo('+index+')">취소</a></div>\n';
        	
        	$('#'+index+'').css("display","block");
        	$('#'+index+'').html(html);
        }
        
        // 메모 등록
        function FinMemo(index){
        	var text = $('#memoContents').val();
        	var pidx = $('#sessionIdx').val();
        	var dr_idx = $('#dr_idx'+index+'').val();
        	
        	alert(dr_idx);
        	        	
        	$.ajax({
                url: 'http://13.209.48.59:8090/writeMemo/' + pidx + '/' + dr_idx ,
                type: 'POST',
                data: {memo : text},
                success: function(data) {
                	
                	alert(data);
                	
                	if(data == 'success'){
                		alert("등록완료!");
                		memo();
                	}
                	if(data == 'fail'){
                		alert("다시 작성해주세요.");
               	}
                	
           	}
       });
    }
        
        // 메모 수정
        function editMemo(idx, index){
        	
        	var html = '';
			html += '<div><textarea placeholder="수정할 내용을 적어주세요" id="editMemoContents"></textarea>\n';
			html += '<a class="btn py-1 px-4 btn btn-primary" id="FinMemo" onclick="FinEdit('+idx+')">메모수정</a>\n';
			html += '<a class="btn py-1 px-4 btn btn-primary" style="margin-right: 10px;" id="cancelMemo" onclick="cancelMemo('+index+')">취소</a></div>\n';
        	
        	$('#'+index+'').css("display","block");
        	$('#'+index+'').html(html);
        	
        }
        
        function FinEdit(idx){
        	
        	var context = $('#editMemoContents').val();
        	
        	$.ajax({
                url: 'http://13.209.48.59:8090/writeMemo/' + idx,
                type: 'PUT',
                data : {context : context},
                success: function(data) {
                	
                	if(data == 'success'){
                		alert("수정완료!");
                		memo();
                	}
                	if(data == 'fail'){
                		alert("수정 실패");
               	}
                	
           	}
       });
    		
    	}
        
        // 메모 삭제
        function delMemo(idx){
        	
        	$.ajax({
                url: 'http://13.209.48.59:8090/writeMemo/' + idx,
                type: 'DELETE',
                success: function(data) {
                	
                	alert(data);
                	memo();                	
           	}
       });
        	
        }
        
        //메모 취소 버튼
        function cancelMemo(index){
        	$('#'+index+'').css("display","none");
        }
        
        