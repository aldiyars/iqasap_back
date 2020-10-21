package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.request.ServiceRequest;
import kz.devhils.meathouse.model.dtos.response.ServiceResponse;
import kz.devhils.meathouse.model.entities.Service;

public interface ServiceMapper {

    Service toEntity (ServiceRequest serviceRequest);
    ServiceResponse toDto (Service service);
}
