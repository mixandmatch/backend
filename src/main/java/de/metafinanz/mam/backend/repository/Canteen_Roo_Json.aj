// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Canteen;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Canteen_Roo_Json {
    
    public String Canteen.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Canteen.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Canteen Canteen.fromJsonToCanteen(String json) {
        return new JSONDeserializer<Canteen>()
        .use(null, Canteen.class).deserialize(json);
    }
    
    public static String Canteen.toJsonArray(Collection<Canteen> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Canteen.toJsonArray(Collection<Canteen> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Canteen> Canteen.fromJsonArrayToCanteens(String json) {
        return new JSONDeserializer<List<Canteen>>()
        .use("values", Canteen.class).deserialize(json);
    }
    
}
