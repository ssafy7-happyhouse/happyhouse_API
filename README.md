# 요구사항
* 기존 HappyHouse 프로젝트 구현시 알고리즘이 필요한 곳을 찾고, 알맞은 알고리즘을 구현하여 추가한다.
* 알고리즘은 2개 이상 적용한다.
    * 예시: 관심매물과 특정 위치(역, 상가 등) 최단경로
* 적용 알고리즘에 대해 별도의 기획서를 작성하여 제출한다. 
    * 기획서는 6. 기획서 예시를 참고한다.

# 이미지

## 회원 관리
<img src="/uploads/6f21dd16ddaf8c7a52865e4ee93ae8af/image.png" width="500">    

	* 회원 가입 페이지 입니다. input에 가입할 정보들을 입력한 후 회원가입 버튼을 클릭합니다.  
    
<img src="/uploads/a77f6be0d5d55938238bfa1e17be451c/image.png" width="500">    

	* 회원 가입 시, 아이디에서 연속된 3개의 문자로 이루어진 문자열이 비밀번호에 포함되어있는 경우, 회원가입이 진행되지 않고 사진과 같은 경고 창이 발생한 후 회원가입 페이지로 이동합니다.
    * 그렇지 않은 경우, 회원가입이 정상적으로 진행됩니다.

## 아파트 검색
<img src="/uploads/2cf99ab7db820dc52c684dada1a801b2/캡처1.PNG" width="500">    

    * 검색결과를 이름순으로 정렬합니다.

<img src="/uploads/6c035e16491aac9a27d32152ed30cf19/캡처.PNG" width="500">    

    * 검색결과를 이름순으로 정렬합니다.
    * 검색결과중 263KM 이내의 결과들만 출력합니다.
    * 이분 탐색을 이용해서 결과 반환

<img src="/uploads/812d773c5214fc9197523102ad9be7bd/캡처2.PNG" width="500">    

    * 거리는 0 이상으로 입력가능 합니다.


