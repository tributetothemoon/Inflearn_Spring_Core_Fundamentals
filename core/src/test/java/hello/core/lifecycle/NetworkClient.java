package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출 되어야 하는 메소드
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call " + url + " message + " + message);
    }

    // 서비스 종료시 호출되어야 하는 메소드
    public void disconnect() {
        System.out.println("close " + url);
    }

    // 초기화 콜백
    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메세지");
    }

    // 소멸 콜백
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy()");
        disconnect();
    }
}
