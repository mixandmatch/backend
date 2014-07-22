// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Office;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Office_Roo_Json {
    
    public String Office.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Office.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Office Office.fromJsonToOffice(String json) {
        return new JSONDeserializer<Office>()
        .use(null, Office.class).deserialize(json);
    }
    
    public static String Office.toJsonArray(Collection<Office> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Office.toJsonArray(Collection<Office> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Office> Office.fromJsonArrayToOffices(String json) {
        return new JSONDeserializer<List<Office>>()
        .use("values", Office.class).deserialize(json);
    }
    
}
