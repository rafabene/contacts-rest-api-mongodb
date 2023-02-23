package com.rafabene.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.rafabene.data.Contact;

@ApplicationScoped
public class ContactService {

    @Inject
    MongoClient mongoClient;

    public List<Contact> list(){
        List<Contact> list = new ArrayList<>();

        MongoCursor<Document> cursor = getCollection().find().iterator();

        try (cursor){
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Contact contact = new Contact();
                contact.setName(document.getString("name"));
                contact.setPhone(document.getString("phone"));
                list.add(contact);
            }
        }

        return list;
    }

    public void add(Contact contact){
        Document document = new Document()
            .append("name", contact.getName())
            .append("phone", contact.getPhone());
        getCollection().insertOne(document);
    }


    private MongoCollection<Document> getCollection(){
        return mongoClient.getDatabase("contacts").getCollection("contacts");
    }
    
}
