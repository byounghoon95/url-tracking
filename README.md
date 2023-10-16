# url-tracking

url을 트래킹하는 개인 프로젝트입니다<br/>

## 기술 스택
1. Java 11
2. Mysql 8.0
3. Swagger
4. Gradle
5. Github Action
6. Spring Boot
7. Spring Data Jpa

## 주요 기능
### url 생성
- Url 입력 시 추적가능한 Url 반환
- 중복 처리를 위해 prefix + UUID 형태로 생성
### 조회수 증가
- url 입력 시 일간/누적 조회수 증가
- 데이터 신뢰성을 위해 조회 로직 비관적 락 적용
### 조회수 응답
- 조회한 날의 일간/누적 조회수를 응답
### 조회수 통계 데이터 응답
- 조회를 원하는 날부터 이전 7일간의 조회수를 조회
