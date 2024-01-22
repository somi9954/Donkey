
# 📚 프로젝트 소개 : 임금님 귀는 당나귀 귀
**자신의 얘기를 하고 싶거나 자신을 가리고(익명) 얘기를 하고 싶은 커뮤니티**

## 1. 개요
- 프로젝트명 : 임금님 귀는 당나귀 귀
- 개발 기간 : 2024.01
- 개발 인원 : 2명

## 2. ⚙️기술 스택
### ✔️프론트엔드
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=black"><img src="https://img.shields.io/badge/Css-1572B6?style=for-the-badge&logo=Css&logoColor=white"><img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white"><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
### ✔️Back-end
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=green"><img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=yellow"><img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">

## 🦹‍ Team
|목진혁|조소미|
|:---:|:---:|
|[MJinHyuk](https://github.com/MJinHyuk)|[somi9954](https://github.com/somi9954)|
|![](https://avatars.githubusercontent.com/u/147026593?v=4)|![](https://avatars.githubusercontent.com/u/137499604?v=4)|
# 📜 UI설계도 & ERDcloud

### UI 설계
[UI설계도](https://ovenapp.io/view/KhSbBScGvstrv5EjFvRovhfeEKN5ROvK/L2kPB) - 카카오 오븐을 사용하였습니다.

### ERD 다이어그램 
![ERD그램](/images/ERD.png)

### 플로우 차트
![플로우차트](/images/플로우%20차트.png)

# 📋 기능 명세서
## 메인 페이지


## 1. 관리자 페이지
### 기본 설정
- 사이트 설정

### 회원관리
- 회원 전체 조회
- 아이디 찾기
- 비밀번호 찾기

### 대나무 숲 관리
- 대나무숲 제보 승인 / 거절(어드민)

### 고객센터 관리
- 고객센터 전체 조회 (어드민)
- 고객센터 답변 처리 되지 않은 질문 조회 (어드민)
- 고객센터 답변 작성/수정/삭제 (어드민)

## 2. 회원
### 로그인
- 로그인
### 회원가입
- 회원가입 시 암호화(hashing)화 되어 DB에 저장.
- Id(email) : email 형식의 아이디. 필수 항목.
- Pw : 최대 길이 40. 필수 항목.
- 회원명 : 최대 길이 40. 필수 항목.

### 아이디(email) 찾기
- 아이디(email), 회원명으로 조회
- 성공시 정보 출력 후 로그인 페이지로 이동.
- 실패시 재입력 요구.

### 비밀번호 찾기
- 아이디(email), 회원명으로 조회
- 성공시 정보 출력 후 로그인 페이지로 이동.
- 실패시 재입력 요구.

### 마이페이지
- 개인정보 수정, 회원탈퇴

## 3. 대나무숲
- 대나무숲 제보(작성)
- 대나무 숲 승인 후 수정/ 삭제
- 대나무 숲 익명/ 실명 지원
- 대나무 숲 피드 조회

## 4. 고객센터
- 질문 작성/ 수정/ 삭제
- 자신이 작성한 질문 조회, 답변 처리 된 질문 조회, 질문 상세 조회
- 고객센터 리스트

# 💡 담당 파트
목진혁 : 회원 관리, 대나무 숲 관리, 회원가입 <br>
조소미 : 사이트 설정, 고객센터 관리, 고객센터

나머지 기능은 함께 구현하였습니다.