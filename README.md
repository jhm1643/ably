1. github : https://github.com/jhm1643/ably
2. 로컬 실행 방법
   1. zip파일 압축 해제 후 java -jar ably-0.0.1-SNAPSHOT.jar 실행 (jdk 17)
   2. 소스는 ably.zip 압축 해제후 intellij에서 open or github에서 checkout
3. API 명세 : http://localhost:8080/swagger-ui/index.html
4. db client : http://127.0.0.1:8080/h2-console
5. 사용 기술
   1. spring boot 3.x (jdk 17)
   2. security jwt 인증
   3. springdoc
   4. spring data jpa (querydsl), h2 database
6. 테스트 방법
   1. 회원가입을 통해 계정을 생성한다.
   2. 생성한 계정정보를 통해 로그인(jwt발급)을 진행하여 jwt토큰을 셋팅한다.(swagger는 API 우측에 자물쇠 모양을 누르고 토큰 입력)
   3. 이후 찜 서랍 및 찜 API을 호출한다.
   4. 많은 데이터를 확인하고 싶은 경우 h2-consle에서 많은 데이터가 들어가 있는 값으로 호출하여 정상적으로 페이징처리가 되는지 확인한다.
7. 기타 사항
   1. 어플리케이션 실행 시 dummy 데이터가 생성
   2. 모든 API는 jwt 토큰 발급 후 사용이 가능하도록 하였으며 해당 사용자에 대한 API 요청만 가능
   3. 페이징 처리는 페이지네이션 옵션을 통해 스크롤 또는 페이지 형태로 요청 및 응답 가능
   4. 그외에 unique 조건을 통해 동일한 이름의 찜 서랍 생성 및 동일한 상품에 대한 찜이 불가능하도록 설계
