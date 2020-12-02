<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poly.dto.MapDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="java.util.List"%>
<%   List<MapDTO> rList = (List<MapDTO>) request.getAttribute("rList"); %>

<!DOCTYPE html>
<html>
<head>
<!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>집 주변 체육시설 추천</title>
</head>
<body>
<p style="margin-top:-12px">
    <em class="link">
       <!-- <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
        </a>  --> 
    </em>
</p>
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e79d5d26187c22c88032f2fea023427"></script>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(37.552295,126.842843), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  


var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 

// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
var positions = [
<%for (int i=0;i<rList.size();i++) {
	MapDTO eDTO = rList.get(i);
	if (eDTO == null) {
		eDTO = new MapDTO();
	}
	else if(i !=rList.size()-1 ) {%>
	
    {
        content: '<div><%=eDTO.getMap_name()%></div>', 
        latlng: new kakao.maps.LatLng(<%=eDTO.getMap_pointy()%>, <%=eDTO.getMap_pointx()%>)
    },
	

<%}
else{%>
{
    content: '<div><%=eDTO.getMap_name()%></div>', 
    latlng: new kakao.maps.LatLng(<%=eDTO.getMap_pointy()%>, <%=eDTO.getMap_pointx()%>)
}];
var coords = new kakao.maps.LatLng(<%=eDTO.getMap_pointy()%>, <%=eDTO.getMap_pointx()%>);
<%}
}%>
//지도의 중심을 결과값으로 받은 위치로 이동시킵니다
map.setCenter(coords);

for (var i = 0; i < positions.length; i ++) {
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng // 마커의 위치
    });

    // 마커에 표시할 인포윈도우를 생성합니다 
    var infowindow = new kakao.maps.InfoWindow({
        content: positions[i].content // 인포윈도우에 표시할 내용
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}
</script>
<!-- timecheck js 사용 -->
	<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
</body>
</html>