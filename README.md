# CareIt_BE
요양 보호사와 어르신 매칭 플랫폼

## 기술 스택
- springboot
- mysql
- redis
- docker

## 작업 규칙
- 브랜치 파서 작업 후,
- `main` 브랜치로 PR 날려서 머지
  <br/><br/>

## 기타 세팅 내용
### Docker로 CI/CD 배포룰 세팅
- main 브랜치에서 CI/CD Actions 동작합니다.
  - `Dockerfile .jar` 파일 복사 및 실행
  - `.github/workflows/deploy.yml` main 브랜치에 푸시 이벤트 발생 시, 서버에 배포