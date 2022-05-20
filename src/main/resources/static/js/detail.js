$(document).ready(function() {
    let lats = document.getElementsByName('lat');
    let lngs = document.getElementsByName('lng');

    let positions = [];

    for (let idx = 0; idx < lats.length; idx++) {
        positions.push({
            latlng: new kakao.maps.LatLng(lats[idx].value, lngs[idx].value),
        });

        if (idx == lats.length - 1) {
            makeMarker(positions);
        }
    }

});

function makeMarker(positions) {
    // 마커를 표시할 위치와 title 객체 배열입니다
    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    console.log(positions.length);

    for (var i = 0; i < positions.length; i++) {
        console.log(positions[0] + " " + positions[1]);
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            image: markerImage
            // 마커 이미지
        });

        map.setLevel(6);
        map.setCenter(new kakao.maps.LatLng(positions[0].latlng.Ma,
            positions[0].latlng.La));
    }
};