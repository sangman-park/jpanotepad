# JPA NOTEPAD

JPA를 StandAlone으로 간편하게 연습할수 있으며
JPA의 삽질을 빠르고 짧은시간에 많이 할수 있게하기위해 구성되었습니다.

## 셋팅및 구동
- InteliJ에서 Open (메이븐)
- 라이브러리가 다 받을때까지 기다렸다가
- 그냥 RUN (SPRING BOOT을 감지하여 자동 활성화됨)
- http://localhost:8080/ 에 기본구동

## API 문서자동화

controler에 API를 만들면 문서화되어 API 테스트 가능
url : http://localhost:8080/swagger-ui.html

## H2 
SQL문을 통해 값을 확인하거나, 추가적, 네이티브 쿼리 연습이 가능합니다.

- url : http://localhost:8080/h2
- jdbc : jdbc:h2:~/spring-boot-h2-db

## 주요파일
- JPATestService.java : EntityManager 를 시험할수 있습니다.
- JPATestController.java : Rest화 시켜 Swagger에서 테스트가 되게 하십시오
- entity : 연습하고 싶은 Entity를 추가 정의합니다. 다음 구동시 테이블은 자동 생성됩니다.

## 주요 옵션: application.properties

    # entity에 정의된 자바 파일기반으로, 어플리케이션 구동시마다 테이블 자동 생성됩니다.(초기화)
    spring.jpa.hibernate.ddl-auto=create
    # 디버그 로그창에 ORM이 호출하는 sql문을 볼수가 있습니다.
    spring.jpa.show-sql=true
    # SQL문을 실행하는 SQL 웹유틸을 실행할수 있습니다. 
    spring.h2.console.path=/h2
    spring.h2.console.enabled=true
    