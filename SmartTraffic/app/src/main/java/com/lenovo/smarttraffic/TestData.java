package com.lenovo.smarttraffic;

public class TestData {

    /**
     * name : {"username":"admin","nickname":"dachui"}
     * pwd : 123456
     */

    private NameBean name;
    private String pwd;

    public NameBean getName() {
        return name;
    }

    public void setName(NameBean name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static class NameBean {
        /**
         * username : admin
         * nickname : dachui
         */

        private String username;
        private String nickname;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
