package org.example.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.app.entity.Contact;
import org.example.app.service.impl.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ContactService contactService;


    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "List of Contacts");
        return "home";
    }

    @RequestMapping("/contacts")
    public String fetchAllContacts(Model model) {
        List<Contact> list = contactService.fetchAll();
        model.addAttribute("name", "Contacts");
        model.addAttribute("contacts", list);
        return "contact_list";
    }

    @RequestMapping("/create-contact")
    public String createContact(Model model) {
        model.addAttribute("name", "Add Contact");
        return "contact_add";
    }

    @RequestMapping(value = "/add-contact", method = RequestMethod.POST)
    public RedirectView addContact(@ModelAttribute Contact contact, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/contacts");
        if (contactService.create(contact))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        model.addAttribute("name", "Update Contact");
        Contact contact = contactService.fetchById(id);
        model.addAttribute("contact", contact);
        return "contact_update";
    }

    @RequestMapping(value = "/change-contact", method = RequestMethod.POST)
    public RedirectView changeContact(@ModelAttribute Contact contact, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/contacts");
        if (contactService.update(contact.getId(), contact))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/delete-contact/{id}")
    public RedirectView deleteContact(@PathVariable("id") Long id,
                               HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/contacts");
        if (contactService.delete(id)) return redirectView;
        else return redirectView;
    }

}
