## Diet Helper- 클린 식단 추천 웹 애플리케이션

<aside>
💡 DietHelper는 베네딕트 방정식을 통해 사용자의 기초대사량 및 활동량을 검증하여 다이어트에 적합한 칼로리에 맞는 식단을 추천해주는 서비스입니다. 본 프로젝트는 ajax를 통해 비동기방식으로 즉각적인 계산 및 음식 리스트를 가져옵니다. 집주변 공공 체육시설 확인 기능은 오라클 데이터베이스에 저장된 사용자의 주소정보를 REST방식으로 가져옵니다. 서울시 공공체육시설 API에 요청하여 주소에 해당하는 공공 체육시설의 결과값을 다음 지도 API를 통해 웹 애플리케이션 서버와 통신합니다.

</aside>

## 1. 기술스택


### Backend

- maven project
- spring 4.x
- java 11
- OracleDB
- Redis
- MyBatis
- Spring MVC
    - Bootstrap5
    - HTML/CSS
    - JSP
    - Jquery

### ETC

- 공공체육시설 API

## 2. 담당한 기능

- 업무분담 주도 및 발표
- DB구조 설계 생성
- MVC 패턴을 이용한 개인 식단추천 메인기능 구현
