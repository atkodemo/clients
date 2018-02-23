package com.atkodemo.clients.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atkodemo.clients.model.Client;
import com.atkodemo.clients.service.ClientService;

@Controller
public class ClientController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(value = {"", "/"})
	public String listClients(Model model) {
		model.addAttribute("clients", clientService.list());
		logger.info("Listing clients");
		return "client/clientlist";
	}
	
	@RequestMapping(value = "/show/{id}")
	public String showClient(@PathVariable String id, Model model) {
		Client client = clientService.getById(new Long(id));
		model.addAttribute("client", client);
		logger.info("Showing client " + client.getId());
		return "client/clientshow";
	}
	
	@RequestMapping(value = "/new")
    public String newClient(Model model){
        model.addAttribute("client", new Client());
        logger.info("Adding new client");
        return "client/clientform";
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveOrUpdateClient(Client client) {
		Client savedClient = clientService.saveOrUpdate(client);
		logger.info("Saving client " + savedClient.getId());
        return "redirect:/show/" + savedClient.getId();
    }
	
	@RequestMapping(value = "/edit/{id}")
	public String editClient(@PathVariable String id, Model model) {
		Client client = clientService.getById(new Long(id));
		model.addAttribute("client", client);
		logger.info("Editing client " + client.getId());
		return "client/clientform";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable String id) {
		clientService.setDeletedFlag(new Long(id), Boolean.TRUE);
		logger.info("Deleting client " + id);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/undelete/{id}")
	public String undelete(@PathVariable String id) {
		clientService.setDeletedFlag(new Long(id), Boolean.FALSE);
		logger.info("Recovering client " + id);
		return "redirect:/";
	}
	
}
