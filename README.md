# 📌 Trello
- 프로젝트명 : Trello 🖥️
- 한 줄 정리 : 업무 프로세스 관리
- 내용 : 업무를 시각적으로 표현하여 팀이 작업과 프로세스를 효율적으로 관리할 수 있게 도와준다.
  
  
# 🎓 Tech Stack
- 언어 : Java
- 버전 : JDK17
- Tools : GitHub, Git
- IDE : IntelliJ IDEA
- DB: MySQL 8.0.37
- Framework : SpringBoot 3.2.5
- 인증/인가 방식 : JWT

[![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=dlalwn&layout=compact)](https://github.com/dlalwn/github-readme-stats)
  
# 👉 팀원 소개
### 팀명 : 감자들 🥔<br>
- 팀장  : ${\textsf{\color{green}정현경}}$
- 팀원 
  - 문수정
  - 이민호
  - 최지연

# 👊 역할 분담
<details>
<summary>
⌨️ 정현경 
</summary>

- 카드 상세 기능(댓글)
  - 댓글 작성
  - 댓글 조회
</details>
<details>
<summary> 
🔍 문수정
</summary>

- 컬럼 관리 기능
  - 컬럼 생성
  - 컬럼 삭제
</details>
<details>
<summary>
📔 이민호
</summary>

- 보드 관리 기능
  - 보드 목록 조회
  - 보드 생성
  - 보드 수정
  - 보드 삭제
  - 보드 초대
</details>
<details>
<summary>
🃏 최지연
</summary>

- 카드 관리 기능
  - 카드 목록 조회
  - 카드 생성
  - 카드 수정
  - 카드 삭제
</details>  

<details>
<summary>
  🙏 공통 구현
</summary>
 - 사용자 기능 : 로그인, 로그 아웃, 회원 가입<br>
 - Entity 설계<br>
 - 컬럼, 카드 순서 이동(수정)<br>
 - 쿼리 최적화<br>
 - 프론트
</details>

# 🎬 프로젝트 소개
#### 기능 소개
<details>
<summary>
🔐 사용자 기능
</summary>

- 로그인
  - 회원 가입된 사용자라면 email과 password를 사용해 로그인 가능
  - header에 토큰을 추가해 로그인에 성공하면 성공 상태코드 메세지 반환
  - 유효하지 않은 사용자 저오로 로그인을 시도한 경우 에러 메세지 반환
  - email과 password가 일치하지 않은 경우 에러 메세지 반환
- 로그 아웃
  - 로그아웃 시, 발생한 토큰 초기화
  - 로그아웃 후 초기화된 Refresh Token 재사용 불가
- 회원가입
  - 중복된 email이 존재하지 않는 경우 성공 메세지 반환
  - 권한
    - Manager : 보드 관계 없이 카드 전체 조회
    - User
      - 보드, 컬럼, 카드 조회 가능
      - 카드 -> 본인이 생성한 항목에 대해서만 권한 존재
      - 보드 -> 초재 받은 모든 보드에서 생성된 카드 전체 조회
    - 중복된 email이 존재하거나 email, password 조건에 맞지 않은 경우 에러 메세지 반환
</details>
<details>
<summary>
📑 보드 관리 기능
</summary>

- 보드 목록 조회
  - 생성된 보드 조회 가능
- 보드 생성
  - 보드 이름, 한 줄 설명 필수 데이터가 있다면 생성 가능
  - 권한에 맞지 않는 사용자가 생성을 시도하거나 필수 데이터가 존재하지 않는 경우 에러 메세지 반환
- 보드 수정
  - 보드 이름, 한 줄 설명 필수 데이터 수정 가능
  - 권한에 맞지 않는 사용자가 생성을 시도하거나 필수 데이터가 존재하지 않는 경우 에러 메세지 반환
- 보드 삭제
  - 보드 삭제 가능
  - 권한에 맞지 않는 사용자가 삭제를 시도하거나 이미 삭제된 보드인 경우 에러 메세지 반환
- 보드 초대
  - 보드에 사용자 초대 가능
  - MANAGER 권한을 가지고 있는 사용자는 본인이 생성한 보드 혹은 초대받은 보드에 대해서 다른 사용자를 초대할 권한을 가질 수 있음
     권한에 맞지 않는 사용자가 초대를 시도하거나, 이미 해당 보드에 초대된 사용자인 경우, 조내하지 않은 사용자인 경우 메세지 반환
</details>
<details>
<summary>
🔖 컬럼 관리 기능
</summary>

- 컬럼 생성
  - 보드에 컬럼 생성 가능
  - 상태 이름 필수 데이터가 존재해야 함
  - 권한에 맞지 않는 사용자가 생성을 시도하거나 이미 존재하는 상태이름으로 생성하는 경우 에러 메세지 반환
- 컬럼 삭제
  - 보드에 생성한 컬럼 삭제 가능
  - 삭제할 때 확인 메세지 출력
  - 권한에 맞지 않는 사용자가 생성을 시도하거나 이미 삭제된 컬림인 경우 에러 메세지 반환
- 컬럼 순서 이동
  - 컬럼 순서를 자유롭게 변경 가능
  - 새로고침 후에도 변경한 순서 유지
  - 권한에 맞지 않는 사용자가 생성을 시도하는 경우 에러 메세지 반환
</details>
<details>
<summary>
💳 카드 관리 기능
</summary>

- 카드 목록 조회
  - 생성된 카드들을 목록에서 조회 가능
  - 조건
    - 전체 조회
    - 작업자별 조회
    - 상태별 조회
- 카드 생성
  - 컬럼에 카드 생성 가능
  - 제목, 카드 상태 필수 데이터가 있다면 생성 가능
  - 내용, 마감일자, 작업자는 필수 데이터 X
  - 필수 데이터가 존재하지 않거나 이미 컬럼이 삭제된 경우 에러 메세지 반환
- 카드 수정
  - 내용, 마감일자, 작업자, 제목 수정 가능
  - 순서 이동을 통해 카드 상태 변경 가능
  - 로그인을 하지 않은 사용자가 순서 이동을 시동하거나 이미 컬럼이 삭제된 경우 에러 메세지 반환
- 카드 삭제
  - 컬럼에 생성한 카드 삭제 가능
  - 삭제할 대 확인 메세지 출력
  - 로그인을 하지 않은 사용자가 순서 이동을 시동하거나 이미 컬럼이 삭제된 경우 에러 메세지 반환
</details>
<details>
<summary>
🖋️ 카드 상세 기능
</summary>

- 댓글 작성
  - 카드 상세에 댓글 작성 가능
  - 내용 필수 데이터가 있다면 생성 가능
  - 로그인을 하지 않은 사용자가 순서 이동을 시동하거나 이미 컬럼이 삭제된 경우 에러 메세지 반환
- 댓글 조회
  - 카드 상세에 작성한 댓글들을 볼 수 있음
  - 작성일자 최신순으로 정렬
  - 로그인을 하지 않은 사용자가 순서 이동을 시동하거나 이미 컬럼이 삭제된 경우 에러 메세지 반환
</details>


# 📢 Github Rules & Code Convention
<details>
<summary>
  ⚖️ Github Rules
</summary>
  
 - 브랜치 이름 규칙
    - dev 브랜치, 각자 개발 기능 구현 feat/(기능이름) 브랜치
    - 두가지 단어라면 ‘ - ’ (하이픈) 사용해서 구분
    - feat/signup, feat/order-create
- 커밋 메시지 규칙

```jsx
[#이슈번호] feat 구현 내용
(한줄 비우기)
- 추가 내용
- 추가 내용
```

- 이슈 작성 규칙
- 이슈 사용
    - title : [Feat] 이슈명
    - content:

```jsx
- [] 기능 구현 내용
- [x] 기능 구현 내용2
```

- PR 작성 규칙
    - 제목
        - [현재날짜] 기능명
        - ex) [2024/07/10] 로그인
    - 내용
        - 간단한 설명, 특이사항 작성 필수
        - ex) 로그인 기능 구현, jwt secret관련 환경변수 추가
- 코드리뷰 적용 (겹치는 부분은 해당 담당자에게 리뷰 받기 필수)
</details>
<details>
<summary>
  🔑 Code Convention
</summary>

# 네이밍 규칙(Naming Conventions)
- 클래스(Class) : 대문자 카멜 케이스 ( 예 : MyClass )
- 메소드(Method) : 소문자 카멜 케이스 ( 예 : myMethod() )
- 변수(Variable) : 소문자 카멜 케이스 ( 예 : myVariable )
- 패키지(Package) : 소문자만 사용하며, 필요시 점(.)으로 계층 구분( 예 : com.example.project)
  
</details>

# 📱 와이어 프레임
<img width="765" alt="image" src="https://github.com/user-attachments/assets/6e21a8cf-7874-4516-aa37-386e6f0f9c50">


# 📑 API 명세서
- 사용자 기능
<img width="914" alt="image" src="https://github.com/user-attachments/assets/09caabf8-0ef7-4506-ad17-883a85aa693b">

- 보드 관리 기능
<img width="690" alt="image" src="https://github.com/user-attachments/assets/dcdf684c-59ba-4e73-afee-f667625bcfcc">
<img width="791" alt="image" src="https://github.com/user-attachments/assets/de178f90-c184-402a-ba23-c93e25b7e338">


- 컬럼 관리 기능
<img width="782" alt="image" src="https://github.com/user-attachments/assets/dc8244cb-3b7f-4c8f-b012-9b5844232d0e">

- 카드 관리 기능
<img width="793" alt="image" src="https://github.com/user-attachments/assets/3d1135fa-7e67-49ee-9359-6b7e933e214b">
<img width="796" alt="image" src="https://github.com/user-attachments/assets/057f14ff-5494-49b3-98a4-1db3c97cd147">

- 카드 상세 기능
<img width="790" alt="image" src="https://github.com/user-attachments/assets/b88af9e8-f41c-428b-b134-d99fbaa575d4">



# 📈 ERD 다이어그램


# 🔊 트러블 슈팅

# 📓 회고
• 좋았던 점 
<details>
<summary>

</summary>
   
</details>

• 아쉬운 점
<details>
<summary>
</summary>
</details>
