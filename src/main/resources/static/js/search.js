$(document).ready(function() {
	let lats = document.getElementsByName('lat');
	let lngs = document.getElementsByName('lng');
	let titles = document.getElementsByName('aptName');
	let dongs = document.getElementsByName('dong');
	let jibuns = document.getElementsByName('jibun');
	let buildYears = document.getElementsByName('buildYear');
	let aptCodes = document.getElementsByName('aptCode');

	let positions = [];

	for (let idx = 0; idx < lats.length; idx++) {
		positions.push({
			title : titles[idx].value,
			latlng : new kakao.maps.LatLng(lats[idx].value, lngs[idx].value),
			address : dongs[idx].value + " " + jibuns[idx].value,
			buildYear : buildYears[idx].value,
			lat : lats[idx].value,
			lng : lngs[idx].value,
			aptCode : aptCodes[idx].value
		});

		if (idx == lats.length - 1) {
			makeMarker(positions);
		}
	}
	
	if( window.location.href.indexOf('distance')!=-1){
		$('#sort').val('distance').prop("selected", true);
		let str = window.location.href;
		if(str.indexOf('km')!=-1){
			$('#distance').val(str.substring(str.indexOf("km") + 3));
		}
		
		$('#distanceBlock').css("visibility", 'visible');
	}
	
	$("#distanceSearch").on('click', function(){
		let str = window.location.href;
		if($("#distance").val()<0){
			alert("0 이상으로 입력해주세요.");
			location.href = str;
			return;
		}
		
		if(str.indexOf('km')!=-1){
			str = str.substring(0, str.indexOf("km") - 1);
		}

		location.href = str + "&km="+$("#distance").val();
	});
	

	$("#sort").on('change', function(){
		let str = window.location.href;
		let sortType = this.value;
		if(str.indexOf('aptList')!=-1){
			if(sortType =='distance'){
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(
						function(position){
							location.href = str + "?sort="+sortType + "&lat="+position.coords.latitude+"&lng="+position.coords.longitude;
						});
					}
				}else{
					str = str.substring(0,str.indexOf("sort")-1);
					location.href = str + "?sort="+sortType;
				}
		}else{
			if(sortType =='distance'){
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(
					function(position){
						location.href = str + "&sort="+sortType + "&lat="+position.coords.latitude+"&lng="+position.coords.longitude;
					});
				}
			}
			else	{
				str = str.substring(0,str.indexOf("sort")-1);
				location.href = str + "&sort="+sortType;
			}		
		}
	});

});

function makeMarker(positions) {
	// 마커를 표시할 위치와 title 객체 배열입니다
	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

	for (var i = 0; i < positions.length; i++) {
		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 35);

		// 마커 이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map : map, // 마커를 표시할 지도
			position : positions[i].latlng, // 마커를 표시할 위치
			image : markerImage
		// 마커 이미지
		});

		// var content = '<div class="customoverlay">'
		// + ' <a href="https://map.kakao.com/link/map/11394059"
		// target="_blank">'
		// + ' <span class="title">' + positions[i].title + '</span>'
		// + ' </a>' + '</div>';

		var content = '<div class="wrap">'
			+ '    <div class="info">'
			+ '        <div class="title">'	+ positions[i].title
			+ '            <div class="close" onclick="closeOverlay()" title="닫기"></div>'
			+ '        </div>'
			+ '			<div class="body">'
			+ '            	<div class="desc">'
			+ '                	<div class="ellipsis"> 주소 : ' + positions[i].address 
			+ '					</div>' 				
			+ '                	<div class="ellipsis"> 건축년도 : ' + positions[i].buildYear 
			+ '					</div>' 
			+ '            </div>'
			+ '        </div>' +     
			+ '		</div>' 
			+ '	</div>';
		
		// 인포윈도우를 생성합니다
		var customOverlay = new kakao.maps.CustomOverlay({
			position : marker.getPosition(), // 마커를 표시할 위치
			content : content,
			yAnchor : 1
		});

		kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,
				marker, customOverlay));
		kakao.maps.event.addListener(marker, 'mouseout',
				makeOutListener(customOverlay));
		 kakao.maps.event.addListener(marker, 'click', makeClickListener(map,
		 marker, customOverlay, positions[i].aptCode ,positions[i].lat, positions[i].lng));

		map.setLevel(6);
		map.setCenter(new kakao.maps.LatLng(positions[0].latlng.Ma,
				positions[0].latlng.La));
	}
};

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
function makeClickListener(map, marker, customOverlay, aptCode, lat, lng) {
	return function() {
		location.href = "/apartment/deal?aptCode=" + aptCode + "&lat="
				+ lat + "&lng=" + lng;
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