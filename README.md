1. 로컬 실행 방법 : zip파일 압축 해제 후 java -jar ably-0.0.1-SNAPSHOT.jar 실행
2. API 명세 : swagger 참고(http://localhost:8080/swagger-ui/index.html)
3. 사용 기술
   1. spring boot 3.x (jdk 17)
   2. security jwt 인증
   3. springdoc
   4. spring data jpa (querydsl), h2 database
4. 기타 사항
   1. 어플리케이션 실행 시 dummy 데이터가 생성되도록 설정하였습니다. 
   2. 모든 API는 jwt 토큰 발급 후 사용이 가능하도록 하였으며 해당 사용자외의 다른 사용자들에 대한 호출이 불가능하도록 함
   3. 페이징 처리는 slice 방식으로 처리하여 스크롤 형태의 탐색이 가능하도록 하였으며, 옵션으로 페이지네이션 선택이 가능하여, 페이지네이션 형태도 조회가 가능하도록 하였습니다.
   4. 그외에 unique 조건을 통해 동일한 이름의 찜 서랍 생성 및 동일한 상품에 대한 찜이 불가능하도록 설계하였습니다.
