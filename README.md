# 공공기관 API 연동 앱

## 개요
이 프로젝트는 대한민국 공공데이터 포털에서 제공하는 [오픈 API](https://www.data.go.kr/data/15050017/fileData.do)(연령별 권장 영양소 및 영양소)를 활용하여 Android 애플리케이션입니다.

## 기술 스택
- **언어**: Kotlin
- **프레임워크**: Jetpack Compose
- **네트워크 처리**: Retrofit, Kotlin Coroutines
- **데이터베이스**: Room
- **비동기 작업**: Coroutines, Flow
- **모니터링**: Firebase Crashlytics, Leak canary
- **아키텍쳐**: Clean architecture, MVI Pattern

## 기능
1. **공공 API 연동 및 고급 네트워크 처리**
   - Retrofit과 Kotlin Coroutines를 통한 비동기 네트워크 처리
   - HTTP 상태 코드 및 응답 메시지를 기반으로 한 에러 처리
   - 지수 백오프 알고리즘을 적용한 재시도 로직

2. **동시성 처리 및 반응형 프로그래밍**
   - Flow와 LiveData를 사용한 데이터 스트림 관리
   - ViewModel과 함께 UI와 데이터의 생명주기 관리
   - 코루틴 스코프를 활용한 멀티스레딩 및 동시 작업 관리

3. **고급 데이터 관리 및 로컬 저장소**
   - Room 데이터베이스와 DAO를 활용한 데이터 영속화
   - 서버 데이터를 주기적으로 가져와 로컬 데이터베이스 업데이트
   - network 미연결시 최초에 내부 데이터 표시
   - data model, entity model 분리 및 mapper를 이용한 변환

4. **UI/UX 구현 및 접근성 향상**
   - Jetpack Compose를 사용한 선언형 UI 구현
   - 커스텀 UI 컴포넌트 및 애니메이션 제작
   - 반응형 디자인 구현

5. **보안 및 데이터 보호**
   - AES 암호화를 통한 민감한 데이터 보호
   - 모든 네트워크 통신에 HTTPS 사용

6. **성능 최적화 및 모니터링**
   - 메모리 누수, CPU 사용량, 렌더링 시간 프로파일링
   - Firebase Crashlytics를 활용한 크래시 리포트 수집
   - LeakCanary를 사용하여 메모리 누수 리포트 수집

## 보고된 이슈 및 해결 방안
1. **네트워크 연결 불안정 시 앱의 비정상 종료**
   - 네트워크 오류 발생 시 적절한 에러 메시지를 사용자에게 제공하고, 앱이 비정상 종료되지 않도록 수정하였습니다.

2. **UI에서 대량의 데이터 표시 시 성능 저하**
   - LazyColumn을 이용하여 대량 데이터 표시시 메모리 효율적 사용
   - Paging 3 라이브러리를 사용하여 대량의 데이터를 효율적으로 표시하도록 개선

3. **앱 내 이미지 로딩이 느리고 때때로 실패함**
   - Coil 라이브러리를 사용하여 이미지 로딩 성능을 개선하고, 실패 시 재시도 로직을 추가하였습니다.
   - AsyncImage 사용으로 이미지 로딩, 실패, 성공을 표시함으로 이미지 로딩을 기다리는 시간을 확인시키고 실패 후 재시도가 가능하게 설정

4. **사용자 입력 폼에서 키보드가 화면을 가림**
   - manifest에 android:windowSoftInputMode="adjustResize" 를 추가하여 하단에 있는 검색칸을 가리지 않게 수정
   - 내부 데이터로 검색기능 추가
   - 검색어 삭제시 내부 데이터 호출하게 변경

## 설치 및 실행 방법
1. 이 레포지토리를 클론합니다.
   ```bash
   git clone <repository-url>
   ```
2. Android Studio에서 프로젝트를 엽니다.
3. google-service.json 파일은 누락되어 있기에 요청하여 받거나 Firebase에서 프로젝트를 추가해 사용한다.
4. Gradle 동기화를 수행합니다.
5. Android 기기 또는 에뮬레이터에서 앱을 실행합니다.
