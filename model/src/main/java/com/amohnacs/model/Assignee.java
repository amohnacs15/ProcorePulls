package com.amohnacs.model;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

class Assignee {

    private String login;
    private long id;
    private String avatarUrl;
    private String type;
    private boolean isSiteAdmin;

    public Assignee() {
    }

    public Assignee(String login, long id, String avatarUrl, String type, boolean isSiteAdmin) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.type = type;
        this.isSiteAdmin = isSiteAdmin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSiteAdmin() {
        return isSiteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        isSiteAdmin = siteAdmin;
    }
}
