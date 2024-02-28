![](https://img.shields.io/badge/내일배움캠프-Spring팀과제-white.svg)
:데스크톱_컴퓨터: 프로젝트 소개
---
+ 프로젝트 명 : LATTE는 말야
+ 소개
    + 한 줄 정리 : "LATTE는 말야" 카페의 주문 어플입니다.
    + 내용 : 카페 앱을 개발해 주문을 할 수 있도록 기능을 구현합니다.
---
# Development
## 디렉토리 구조
```bash
├── src                         # 소스 코드가 위치하는 디렉토리
│   ├── main                    # 메인 소스 코드가 위치하는 디렉토리
│   │   ├── java                # 자바 소스 코드가 위치하는 디렉토리
│   │   │   ├── comm.sparta.project/icylattei  # 기본 패키지 이름
│   │   │   │   ├── config      # 스프링 설정 클래스들이 위치하는 디렉토리
│   │   │   │   ├── global      # 예외처리하는 ControllerAdvicer가 위치하는 디렉토리
│   │   │   │   ├── user        # 유저 관련 클래스(Controller, Service, Repository 등..)가 위치하는 디렉토리
│   │   │   │   ├── order       # 주문(order) 관련 클래스(Controller, Service, Repository 등..)가 위치하는 디렉토리
│   │   │   │   ├── cart        # 장바구니(cart) 관련 클래스(Controller, Service, Repository 등..)가 위치하는 디렉토리
│   │   │   │   ├── product     # 상품(product) 관련 클래스(Entity, Repository)가 위치하는 디렉토리
│   │   │   │   ├── review      # 리뷰 관련 클래스(Controller, Service, Repository 등..)가 위치하는 디렉토리
│   │   │   │   ├── jwt         # JWT(Json Web Token) 관련 클래스들이 위치하는 디렉토리
│   │   │   │   └── userDetails # user관련 정보들을 가져오는 클래스가 위치하는 디렉토리
│   │   └── resources           # 리소스 파일들이 위치하는 디렉토리
│   └── test                    # 테스트 소스 코드가 위치하는 디렉토리
├── build.gradle               # Gradle 빌드 스크립트 파일
├── settings.gradle            # Gradle 설정 파일
└── README.md                  # 프로젝트에 대한 설명이 담긴 마크다운 파일
```
## :톱니바퀴: 개발 환경
+ JDK amazoncorretto 17.0.9
+ Spring Boot 3.2.3
+ Gradle 8.5
+ MySQL 8.0.35
## Code Convension
+ [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
# API
<img width="1069" alt="스크린샷 2024-02-28 오전 10 27 43" src="https://github.com/ChiliChili-97/Icy-Lattei/assets/120374640/4201e4bc-9435-44d1-8d1b-2694ac77b3ce">
# ERD
<img width="1069" alt="스크린샷 2024-02-28 175721" src="https://github.com/ChiliChili-97/Icy-Lattei/assets/71073365/57215546-1434-4bc8-abad-348e63a83f92">
