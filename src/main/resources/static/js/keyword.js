$(document).ready(function(){
   let string = decodeURI(location.href).split('?')[1].split("&");
   console.log("지역코드 : " + string[0].substring(6));
   console.log("동 : " + string[1].substring(5));
   console.log("검색어 : " + string[2].substring(14));
   console.log("연도 : " + string[3].substring(5));
   console.log("월 : " + string[4].substring(6));

    var ServiceKey = 'd5/yMFcBzVvwDgyGMghgGUfLkVb8RFJdoENpvDFW9XrHwTOgM2VnuFwhaLWxRR79nrA2CxpkmZvXxiwpghC8lw==';
    var pageNo = '1';
    var numOfRows = '100';
    var LAWD_CD = string[0].substring(6, 11);  // 법정동코드: 종로구(11110)
    var DEAL_YMD = string[3].substring(5)+string[4].substring(6);
    if(string[1].substring(5) == "동선택"){
      $("#search-title").text("거래 정보");
    } else {
      $("#apt-name").text(string[1].substring(5));
    }

    $.ajax({
      url: 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev',
      type: 'GET',                    
      data: {
        "ServiceKey": ServiceKey,
        "pageNo": pageNo,
        "numOfRows": numOfRows,
        "LAWD_CD": LAWD_CD,
        "DEAL_YMD": DEAL_YMD
    },

        success: function(response){
            if(string[1].substring(5) == "동선택"){
              findPosition(response);
            }else{
              findPosition(response, string[1].substring(5));
            }
            $(response).find('item').each(function(index){

                
              let dong = $(this).find('법정동').text().trim();
              let code = $(this).find('지역코드').text().trim();
                if((string[1].substring(5) == "동선택" || dong == string[1].substring(5)) && code ==  string[0].substring(6, 11)){
                
                var li = `<li id="${index}">
                <div>
                ${$(this).find('아파트').text()}
                </div>
                <div>
                거래금액 : ${$(this).find('거래금액').text()}
                </div>
                <div>
                전용면적 :  ${$(this).find('전용면적').text()}
                </div>
                <div>
                거래유형 :  ${$(this).find('거래유형').text()}
                </div>
                <div>
                날짜 :  ${$(this).find('년').text()}년 ${$(this).find('월').text()}월 ${$(this).find('일').text()}일
                </div>
                </li>
                <hr>`;

            $(".apartment-list-ul").append(li); 
            let v = encodeURI($(this).find('아파트').text());
            $(`#${index}`).on("click", function(){
              location.href=`detail.html?gugun=${string[0].substring(6)}&dong=${string[1].substring(5)}&searchContent=${string[2].substring(14)}&year=${string[3].substring(5)}&month=${string[4].substring(6)}&name=${v}`;
            });
          }
          })
        }
      })
    
    $(".apartment-list-ul").css("list-style","none");
});

function makeMarker(positions){
  // 마커를 표시할 위치와 title 객체 배열입니다 
  // 마커 이미지의 이미지 주소입니다
  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
      
  for (var i = 0; i < positions.length; i ++) {
      
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(24, 35); 
      
      // 마커 이미지를 생성합니다    
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
      
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image : markerImage // 마커 이미지 
      });

      // 인포윈도우를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
          position: marker.getPosition(), // 마커를 표시할 위치
          content : `<div class="customOverlay alert alert-success"><strong> ${positions[i].title}</strong> </div>`,
      });

      kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, customOverlay));
      kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));
      kakao.maps.event.addListener(marker, 'click', makeClickListener(map, marker, customOverlay));

      map.setLevel(6);
      map.setCenter(new kakao.maps.LatLng(positions[0].latlng.Ma, positions[0].latlng.La));
  }
};

function findPosition(data, string){
    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();
    let positions = [];

    $(data).find("item").each(function(){
      let title  = $(this).find("아파트").text();
      let dong = $(this).find("법정동").text().trim();
      let ji = $(this).find("지번").text().trim();
          // 주소로 좌표를 검색합니다
      geocoder.addressSearch( dong + " " + ji, function(result, status) {
      // 정상적으로 검색이 완료됐으면 
      if (status === kakao.maps.services.Status.OK && string == dong) {
          positions.push({
              title : title,
              latlng :  new kakao.maps.LatLng(result[0].y, result[0].x)
          });
        makeMarker(positions);
      } 
    });    
  });
};

function findPosition(data){
  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();
  let positions = [];

  $(data).find("item").each(function(){
    let title  = $(this).find("아파트").text();
    let dong = $(this).find("법정동").text().trim();
    let ji = $(this).find("지번").text().trim();
        // 주소로 좌표를 검색합니다
    geocoder.addressSearch( dong + " " + ji, function(result, status) {
    // 정상적으로 검색이 완료됐으면 
    if (status === kakao.maps.services.Status.OK) {
        positions.push({
            title : title,
            latlng :  new kakao.maps.LatLng(result[0].y, result[0].x)
        });
      makeMarker(positions);
    } 
  });    
});
};

        // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
        function makeClickListener(map, marker, customOverlay) {
          return function() {
              let pos = marker.getPosition();
              map.setLevel(5);
              map.panTo(pos);

          };
      }

      // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
      function makeOverListener(map, marker, customOverlay) {
          return function() {
            customOverlay.setMap(map);
          };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
      function makeOutListener(customOverlay) {
          return function() {
            customOverlay.setMap(null);     
          };
      }