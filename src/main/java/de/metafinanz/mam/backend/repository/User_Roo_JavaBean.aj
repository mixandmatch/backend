// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.User;

privileged aspect User_Roo_JavaBean {
    
    public String User.getUsername() {
        return this.username;
    }
    
    public void User.setUsername(String username) {
        this.username = username;
    }
    
}
