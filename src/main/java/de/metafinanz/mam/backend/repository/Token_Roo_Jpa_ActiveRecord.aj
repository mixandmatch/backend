// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

privileged aspect Token_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Token.entityManager;
    
    public static final List<String> Token.fieldNames4OrderClauseFilter = java.util.Arrays.asList("DEFAULT_HOURS_FOR_VALID_UNTIL", "logger", "tokenString", "user", "enabled", "validUntil", "usageTime");
    
    public static final EntityManager Token.entityManager() {
        EntityManager em = new Token().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Token.countTokens() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Token o", Long.class).getSingleResult();
    }
    
    public static List<Token> Token.findAllTokens() {
        return entityManager().createQuery("SELECT o FROM Token o", Token.class).getResultList();
    }
    
    public static List<Token> Token.findAllTokens(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Token o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Token.class).getResultList();
    }
    
    public static Token Token.findToken(Long id) {
        if (id == null) return null;
        return entityManager().find(Token.class, id);
    }
    
    public static List<Token> Token.findTokenEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Token o", Token.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Token> Token.findTokenEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Token o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Token.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Token.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Token.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Token attached = Token.findToken(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Token.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Token.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Token Token.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Token merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}