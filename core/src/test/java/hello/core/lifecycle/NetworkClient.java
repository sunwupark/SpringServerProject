package hello.core.lifecycle;

public class NetworkClient {

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
}
