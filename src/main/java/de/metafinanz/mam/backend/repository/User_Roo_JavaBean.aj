// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;


privileged aspect User_Roo_JavaBean {
    
    public String User.getUsername() {
        return this.username;
    }
    
    public void User.setUsername(String username) {
        this.username = username;
    }
    
    public String User.getEMail() {
        return this.eMail;
    }
    
    public void User.setEMail(String eMail) {
        this.eMail = eMail;
    }
    
    public boolean User.isGetOrCreateUserCreated() {
        return this.getOrCreateUserCreated;
    }
    
    public void User.setGetOrCreateUserCreated(boolean getOrCreateUserCreated) {
        this.getOrCreateUserCreated = getOrCreateUserCreated;
    }
    
    public Boolean User.getEnabled() {
        return this.enabled;
    }
    
    public void User.setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public byte[] User.getPicture() {
        return this.picture;
    }
    
    public void User.setPicture(byte[] picture) {
        this.picture = picture;
    }
    
}
