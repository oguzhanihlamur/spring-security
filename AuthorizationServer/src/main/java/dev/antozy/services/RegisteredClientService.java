package dev.antozy.services;

import dev.antozy.dto.RegisteredClientRequestDTO;
import dev.antozy.entities.RegisteredClientRecord;

public interface RegisteredClientService {

    RegisteredClientRecord createRegisteredClient(RegisteredClientRequestDTO registeredClientRequestDTO);

}
