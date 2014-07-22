// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository.json;

import java.util.Collection;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

privileged aspect JSONAppointment_Roo_Json {
    
    public String JSONAppointment.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String JSONAppointment.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static JSONAppointment JSONAppointment.fromJsonToJSONAppointment(String json) {
        return new JSONDeserializer<JSONAppointment>()
        .use(null, JSONAppointment.class).deserialize(json);
    }
    
    public static String JSONAppointment.toJsonArray(Collection<JSONAppointment> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String JSONAppointment.toJsonArray(Collection<JSONAppointment> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<JSONAppointment> JSONAppointment.fromJsonArrayToJSONAppointments(String json) {
        return new JSONDeserializer<List<JSONAppointment>>()
        .use("values", JSONAppointment.class).deserialize(json);
    }
    
}
