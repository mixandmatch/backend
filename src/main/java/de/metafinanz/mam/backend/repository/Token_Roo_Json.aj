// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import java.util.Collection;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

privileged aspect Token_Roo_Json {
    
    public String Token.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Token.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Token Token.fromJsonToToken(String json) {
        return new JSONDeserializer<Token>()
        .use(null, Token.class).deserialize(json);
    }
    
    public static String Token.toJsonArray(Collection<Token> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Token.toJsonArray(Collection<Token> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Token> Token.fromJsonArrayToTokens(String json) {
        return new JSONDeserializer<List<Token>>()
        .use("values", Token.class).deserialize(json);
    }
    
}