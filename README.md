# Y-Car Project
> 직장인을 위한 안전한 카풀앱, 연차 Ycar - 결제 및 후기 페이지 <br>
> [탑승자페이지 Link](http://13.125.252.85:8080/passenger/)<br>
> [운전자페이지 Link](http://13.125.252.85:8080/driver/) <br>
> [기획서페이지 Link](https://github.com/mand2/y-car-project/blob/master/%EA%B8%B0%ED%9A%8D%EC%84%9C/%EA%B8%B0%ED%9A%8D%EC%84%9C%20ver.1.0.1.pdf) <br>

#### 흐름도
* 전체 : 
[기본적인 흐름도 UI/UX형식으로 보러가기](https://ovenapp.io/view/QFfffnY9rAvOFqIp4uoxwwOHzGEu17o8/uXdsG)

#### 참여자
* 임욱표 @[dladnrvy](https://github.com/dladnrvy)
* 강다은 @[iDaeun](https://github.com/iDaeun)
* 김나연 @[mand2](https://github.com/mand2)
* 노유리 @[yforyuri](https://github.com/yforyuri)
* 손민희 @[Miniminis](https://github.com/Miniminis)



------------



#### 1. 기술/구조

* 웹 표준 
    * `HTML5`
    * `CSS3`
    * `JavaScript`
    * `jQuery`
    * `Bootstrap4`
* TOMCAT 컨테이너 사용
* DBMS - `MySQL`
* Spring Framework
* `JSP`, `EL`
* Spring-Boot
* `MyBatis`
* `JPA`
* `nodeJs` 
* 다양한 Api 사용   
    * `jQuery`, `aJax`, `JSON` 파싱  
    * 카카오 로그인 Api   
    * T-Map Api : 출발지-도착지 경로 검색기능 구현, GPS로 실시간 이동경로 체크기능 구현   
    * 카카오페이 api   
    * Toss Api   
    * 아임포트 api 
* `RESTful Api 구조`  
    * 서버 <--> 클라이언트 통신을 위한 REST 인터페이스 구현
* `AWS` 배포  
    * `EC2`  
    * `RDS`



#### 2. 버전설명

* 자세한 사항은 본 repository의 커밋 내역 참고
* v0
   * 프로젝트 기획, 업무분담
   * DB erd로 테이블 구조 설정
   * `flow-chart` 작성
   * `oven` 사용하여 화면설계 및 흐름 체크 
* v1
   * 각 프로젝트 별 CRUD 포함, 기본 기능 완성 (게시판 페이징 처리 등)
   * `T-Map` Api 이용하여 출발지, 도착지, 경로검색 구현
   * `카카오 페이`, `Toss`, `Import` 등 결제 Api 이용하여 결제 기능 구현 
   * `카카오 로그인` Api 이용 
   * `nodeJs` 서버로 실시간 일대일 채팅기능, 탑승자-운전자간 연결 구현 
   * `Spring-Boot` 및 `JPA` 이용하여 일부 기능 구현
* v2
   * SpringBoot로 구현한 부분 배포
   * 운전자 로그인 세션 구현 (필터 & 인터셉터 사용)
   * 예약한 사람과의 채팅만 가능하도록 변경함
   



#### 3. 구현 중 겪었던 어려움 

* 효율적인 프로젝트 구조 고민

* DB 테이블 관계설정 미숙으로 인한 DB 수정

* 각자 기능 구현한 프로젝트를 합칠 때   
  코드 결합 시 발생하는 세부적인 오류(예- 설정파일 ignore하지 않아 결합시 conflict 발생, 데이터 속성명 불일치) 등
  >>> .ignore file을 생성하여 해결
  
* session 다루는데 어려움을 느낌
  세션의 사용 방법등을 완벽히 파악하지 못해 set, get 등 controller나 jsp 등에서 활용하는데 어려움을 겪음
  >>> google, okky 등 다른 개발자들의 코드등을 찾아보며 활용 방법 이해
  
* 체계적인 설계가 되지 않아 많은 시간 소요

  
  
  



#### 4. 프로젝트 설명 PPT 

* 각 기능 설명   
* 구현 중 어려웠던 점   
* 개선사항   
* PPT 보기 [Link](https://docs.google.com/presentation/d/1X_pm5qu2KIjSAaNNoROyh0tZw7PlQ_8B8T9M9vID6KE/edit?usp=sharing)
