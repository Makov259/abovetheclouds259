package com.simpleform.controller;
import com.simpleform.dto.clientDTO;
import com.simpleform.entity.Client;
import com.simpleform.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    public String addClient(@ModelAttribute clientDTO clientDto) {
        clientService.addClientDTO(clientDto);
        return "addClient_page.html";
    }




    @PostMapping("/delete")
    public String deleteClient(@RequestParam Long id) {
            Client client = new Client();
        client.setId(id);
        clientService.deleteClient(client);
        return "deleteClient_page.html";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute clientDTO clientDto) {
        clientService.updateClientDTO(clientDto);
        return "updateClient_page.html";
    }

// You will need to modify the update logic in the service layer to handle DTO.

}
