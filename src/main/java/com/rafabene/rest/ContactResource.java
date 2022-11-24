package com.rafabene.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.rafabene.data.Contact;
import com.rafabene.service.ContactService;

@Path("/contacts")
public class ContactResource {
    

    @Inject
    private ContactService contactService;

    @GET
    public List<Contact> list(){
        return contactService.list();
    }

    @POST
    public List<Contact> addd(Contact contact){
        contactService.add(contact);
        return list();
    }

}
