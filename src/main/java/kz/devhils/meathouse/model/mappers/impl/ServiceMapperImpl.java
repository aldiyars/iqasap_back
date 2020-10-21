package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.request.ServiceRequest;
import kz.devhils.meathouse.model.dtos.response.ServiceResponse;
import kz.devhils.meathouse.model.mappers.ServiceMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public kz.devhils.meathouse.model.entities.Service toEntity(ServiceRequest serviceRequest) {
        kz.devhils.meathouse.model.entities.Service service = new kz.devhils.meathouse.model.entities.Service();
        service.setName(serviceRequest.getName());
        return service;
    }

    @Override
    public ServiceResponse toDto(kz.devhils.meathouse.model.entities.Service service) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setId(service.getId());
        serviceResponse.setName(service.getName());
        return serviceResponse;
    }
}
