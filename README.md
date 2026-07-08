# GreenPay BackEnd

친환경 소비 인증과 지역 리워드를 제공하는 그린페이 백엔드입니다.

## 현재 세팅

- Java 17 기준 Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Validation
- H2 인메모리 DB
- MySQL Driver
- Lombok
- 로그인/회원가입 없는 프로토타입 구조
- 모든 사용자 기능은 임시 사용자 `demo@greenpay.local` 기준으로 동작

## DB 보완 반영

기존 ERD에서 먼저 확정해야 하는 핵심 보완 사항을 반영했습니다.

- `ECO_ACTIVITIES`
  - `reward_point` 추가
  - `carbon_reduction_kg` 추가
- `REWARDS` 추가
- `REWARD_EXCHANGES` 추가

## 주요 도메인

- `User`: 임시 주민, 샘플 소상공인, 관리자 데이터
- `Store`: 친환경 매장
- `EcoActivity`: 텀블러 사용, 다회용기 사용 등 친환경 활동
- `StoreActivity`: 매장별 친환경 활동
- `StoreQrCode`: 매장 QR 코드
- `Certification`: QR 인증 기록
- `PointTransaction`: 포인트 적립/사용 내역
- `Reward`: 교환 가능한 리워드
- `RewardExchange`: 리워드 교환 기록

## 로컬 실행

Gradle이 설치되어 있거나 IDE에서 Gradle 프로젝트로 열 수 있으면 실행할 수 있습니다.

```bash
gradle bootRun
```

H2 콘솔:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:greenpay
```

## 프로토타입 API

- `GET /api/me`: 임시 사용자 정보 조회
- `GET /api/stores`: 승인된 친환경 매장 목록 조회
- `GET /api/stores/{storeId}`: 매장 상세 조회
- `POST /api/certifications/qr`: QR 인증 및 포인트 적립
- `GET /api/points`: 포인트 내역 조회
- `GET /api/rewards`: 리워드 목록 조회
- `POST /api/rewards/{rewardId}/exchange`: 리워드 교환
- `GET /api/dashboard`: 마이페이지 대시보드 조회

샘플 QR 토큰:

```json
{
  "qrToken": "GREENPAY-CAFE-DEMO"
}
```

## 다음 구현 순서

1. 프론트 화면과 API 연결
2. 매장 등록/수정 API
3. 친환경 활동 등록/수정 API
4. 관리자 매장 승인 API
5. 캠페인 기능
6. 시간이 남으면 로그인/JWT 추가
