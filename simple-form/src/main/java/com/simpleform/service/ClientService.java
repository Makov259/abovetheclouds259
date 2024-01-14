package com.simpleform.service;

import com.simpleform.dto.clientDTO;
import com.simpleform.entity.Client;
import com.simpleform.entity.Employee;
import com.simpleform.repository.ClientRepository;
import com.simpleform.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public clientDTO addClientDTO(clientDTO clientDto) {
        Client client = toEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return toDTO(savedClient);
    }



    public void deleteClient(Client client){
        // You might need to fetch the client from the repository before deleting
        Client existingClient = clientRepository.findById(client.getId()).orElse(null);
        if (existingClient != null) {
            clientRepository.delete(existingClient);
        }
    }

    public void updateClient(Client client) {
        // You might want to add validation or error handling logic here

        // Save the updated employee to the database
        clientRepository.save(client);
    }
    public String  updateClientDTO(clientDTO clientDto) {
        Optional<Client> optionalClient = clientRepository.findById(clientDto.getId());
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            // Only update fields that are not null in the DTO
            if (clientDto.getFirstName() != null && !clientDto.getFirstName().isEmpty()) {
                client.setFirstName(clientDto.getFirstName());
            }
            if (clientDto.getLastName() != null && !clientDto.getLastName().isEmpty()) {
                client.setLastName(clientDto.getLastName());
            }
            if (clientDto.getContactNumber() != null) {
                client.setContactNumber(clientDto.getContactNumber());
            }

            clientRepository.save(client);
        } else {
            // Handle scenario where client is not found in the database
            return "error_page.html";
        }
        return "updateClient_page.html";
    }


    public clientDTO getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(this::toDTO).orElse(null);
    }

    // Method to convert Entity to DTO
    public clientDTO toDTO(Client client) {
        clientDTO dto = new clientDTO();
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setContactNumber(client.getContactNumber());
        return dto;
    }

    // Method to convert DTO to Entity
    public Client toEntity(clientDTO dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setContactNumber(dto.getContactNumber());
        return client;
    }

}
