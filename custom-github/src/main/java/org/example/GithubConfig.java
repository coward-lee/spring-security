package org.example;

public class GithubConfig {

    private String client_secret = "Ov23liPqBZgSk374QbCp";
    private String clientId = "7e7a0ddd4014705b8971cb6e063a95b059730958";
    private String code;
    private String redirect_uri;
    private String state;

    public static GithubConfig of(String code,String state){
        GithubConfig githubConfig = new GithubConfig();
        githubConfig.code = code;
        githubConfig.redirect_uri = "http://127.0.0.1:8080/login/oauth2/code/github";
        githubConfig.state = state;
        return githubConfig;
    }


    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }
}
