package com.example.inyoung.teamapp.dto;

/**
 * Created by MYpc on 2017-05-14.
 */

public class LoginDTO {

    //@SerializedName("id")
    private String id;
    private  String password;
    /*@SerializedName("sess")
    private String sess;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }
*/
}
