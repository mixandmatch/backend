// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

privileged aspect Canteen_Roo_Jpa_ActiveRecord {
    
    public static final List<String> Canteen.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static long Canteen.countCanteens() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Canteen o", Long.class).getSingleResult();
    }
    
    public static List<Canteen> Canteen.findAllCanteens() {
        return entityManager().createQuery("SELECT o FROM Canteen o", Canteen.class).getResultList();
    }
    
    public static List<Canteen> Canteen.findAllCanteens(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Canteen o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Canteen.class).getResultList();
    }
    
    public static Canteen Canteen.findCanteen(Long id) {
        if (id == null) return null;
        return entityManager().find(Canteen.class, id);
    }
    
    public static List<Canteen> Canteen.findCanteenEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Canteen o", Canteen.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Canteen> Canteen.findCanteenEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Canteen o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Canteen.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Canteen Canteen.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Canteen merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}