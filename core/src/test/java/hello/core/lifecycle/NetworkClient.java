package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient{

    private String url;
    public NetworkClient(){
        System.out.println("url = " + url);
    }

    public void  setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("connect  = " + url);
    }

    public void call(String message){
        System.out.println("call  = " + url + " message = "  + message);
    }

    public void disconnect(){
        System.out.println("close:  = " + url);
    }

    //의존 관계 주입이 끝나고
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
