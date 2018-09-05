= Test purpose project for Intelli J (At HOME) =

Intelli J 2017.1.6, JDK 1.8.0.161 64bit, Spring Boot 2.0.1 Release, Spring 5.0.5 Release
JMS Apache Active MQ, JPA, QueryDSL, React, Element UI

•Make root directory's name would be 'test'

    
       설치방법
       (testhome repository와 거의 비슷한 내용인데 정리가 안되있어서
        그냥 연습용임)
       
       (git으로 받은게 아닐경우)
       C:\에 그대로 압축해제 IntelliJ로 C:\MYTEST\demo 디렉토리 오픈
       (디렉토리 명은 변경해도 상관없긴함)
        
    
       Interface 테이블에 들어온 데이터를 Polling 해서 설정 Controller에
       메시지 기반으로 데이터를 보낸다
    
       (
        PollingTaskService.poll()에 스케쥴링이 걸려있음, 계속 돌리는건 PC가 힘들어해서
        ExApplcation.java에서 @EnableScheduling 막아놓음 스케쥴러 폴링으로 테스트 해보고
        싶으면 이 부분만 주석해제하면 됨, 테스트는 http://localhost:50000 으로 접속해서
        'JMS호출' 테스트 버튼 눌러보면 됨
       )
       
       FrontEnd는 demo/ex/frontend/my-app 디렉토리로 CMD등에서 이동 후 npm install
       후에 npm run dev, npm start 등으로 구동시켜 http://localhost:3000 으로 접속해보면됨
       
       bootRun후 log로 메시지 서비스를 통한 데이터 전달이 어떻게 이뤄지는지를 보고 이해하는걸로 보면됨
    
       중요한건 JmsConfiguration에 설정된 리스너와 ActiveMQ 이름 정보 등이고 
       Adaptor, Router, Connector등은 Application설계에 대한 내부적인 내용이므로 메시지 정보 전달이 잘 되는지만 
       확인 가능하면 JMS 메시지 전송이 성공적으로 이뤄짐을 확인할 수 있다.
