package boot.http.bean;

public class Data {
    private String login_token;
    private String gateway_token;

    @Override
    public String toString() {
        return "Data{" +
                "login_token='" + login_token + '\'' +
                ", gateway_token='" + gateway_token + '\'' +
                '}';
    }

    public Data(String login_token, String gateway_token) {
        this.login_token = login_token;
        this.gateway_token = gateway_token;
    }

    public String getLogin_token() {
        return login_token;
    }

    public void setLogin_token(String login_token) {
        this.login_token = login_token;
    }

    public String getGateway_token() {
        return gateway_token;
    }

    public void setGateway_token(String gateway_token) {
        this.gateway_token = gateway_token;
    }
}
