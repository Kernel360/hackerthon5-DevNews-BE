# DevNews Backend

DevNews는 개발자들을 위한 뉴스 플랫폼의 백엔드 서비스입니다.

## 기술 스택

- Java 17
- Spring Boot 3.4.5
- Spring Security
- Spring Data JPA
- MySQL
- JWT Authentication
- Spring Mail (SMTP)

## 주요 기능

- 사용자 인증 (회원가입, 로그인)
- 뉴스 기사 관리
- 좋아요 기능
- 이메일 인증

## 프로젝트 구조

```
src/main/java/org/example/devnews/
├── config/         # 설정 클래스
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
│
├── domain/         # 엔티티 클래스
│   ├── article/    # 뉴스 기사 관련 엔티티
│   ├── category/   # 카테고리 엔티티
│   ├── company/    # 회사 정보 엔티티
│   ├── interestcategory/  # 관심 카테고리 엔티티
│   ├── interestcompany/   # 관심 회사 엔티티
│   ├── like/       # 좋아요 엔티티
│   └── user/       # 사용자 엔티티
│
├── dto/            # 데이터 전송 객체
│   ├── request/    # 요청 DTO
│   └── response/   # 응답 DTO
│
├── ex/             # 예외 처리
│   ├── GlobalExceptionHandler.java
│   └── CustomException.java
│
├── service/        # 비즈니스 로직
│   ├── ArticleService.java
│   ├── UserService.java
│   ├── LikeService.java
│   └── SmtpService.java
│
├── util/           # 유틸리티 클래스
│   ├── JwtUtil.java
│   └── SecurityUtil.java
│
├── validate/       # 유효성 검사
│   └── Validator.java
│
└── web/            # 컨트롤러
    ├── ArticleController.java
    ├── UserController.java
    └── LikeController.java
```

### 주요 컴포넌트 설명

#### 도메인 (Domain)
- `article`: 뉴스 기사 정보를 관리하는 엔티티
- `category`: 뉴스 카테고리 정보를 관리하는 엔티티
- `company`: 회사 정보를 관리하는 엔티티
- `interestcategory`: 사용자의 관심 카테고리를 관리하는 엔티티
- `interestcompany`: 사용자의 관심 회사를 관리하는 엔티티
- `like`: 사용자의 좋아요 정보를 관리하는 엔티티
- `user`: 사용자 정보를 관리하는 엔티티

#### 서비스 (Service)
- `ArticleService`: 뉴스 기사 관련 비즈니스 로직 처리
- `UserService`: 사용자 관련 비즈니스 로직 처리
- `LikeService`: 좋아요 관련 비즈니스 로직 처리
- `SmtpService`: 이메일 발송 관련 비즈니스 로직 처리

#### 컨트롤러 (Controller)
- `ArticleController`: 뉴스 기사 관련 API 엔드포인트 제공
- `UserController`: 사용자 관련 API 엔드포인트 제공
- `LikeController`: 좋아요 관련 API 엔드포인트 제공

## 시작하기

### 필수 조건

- Java 17
- MySQL
- Gradle

## API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:
```
http://localhost:8080/swagger-ui.html
```

## 개발 환경 설정

1. MySQL 데이터베이스 설정
2. SMTP 서버 설정 (이메일 인증용)
3. JWT 시크릿 키 설정
