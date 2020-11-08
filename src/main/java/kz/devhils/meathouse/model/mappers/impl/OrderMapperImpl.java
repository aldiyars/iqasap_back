package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.request.CreateOrderRequest;
import kz.devhils.meathouse.model.dtos.response.*;
import kz.devhils.meathouse.model.entities.*;
import kz.devhils.meathouse.model.mappers.AnimalMapper;
import kz.devhils.meathouse.model.mappers.AnimalServiceMapper;
import kz.devhils.meathouse.model.mappers.OrderMapper;
import kz.devhils.meathouse.repositories.AnimalProfileRepo;
import kz.devhils.meathouse.service.AnimalServiceService;
import kz.devhils.meathouse.service.OrderAnimalService;
import kz.devhils.meathouse.service.PaymentTypeService;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private PaymentTypeService paymentTypeService;
    @Autowired
    private AnimalProfileRepo animalProfileRepo;
    @Autowired
    private AnimalServiceService animalServiceService;
    @Autowired
    private OrderAnimalService orderAnimalService;
    @Autowired
    private AnimalMapper animalMapper;
    @Autowired
    private AnimalServiceMapper animalServiceMapper;

    @Override
    public Order toEntity(CreateOrderRequest createOrderRequest) {
        Order result = new Order();

        User client = userService.findById(createOrderRequest.getClientId());
        PaymentType paymentType = paymentTypeService.findById(createOrderRequest.getPaymentTypeId());
        double sum = 0;
        List<OrderAnimal> orderAnimals = new ArrayList<>();
        for (int i = 0; i < createOrderRequest.getCreateAnimalOrders().size(); i++) {
            OrderAnimal orderAnimal = new OrderAnimal();
            double total = 0;
            AnimalProfile animalProfile = animalProfileRepo.getOne(createOrderRequest.getCreateAnimalOrders().get(i).getAnimalProfileId());
            total += animalProfile.getCost();
            orderAnimal.setAnimalProfile(animalProfile);
            if (createOrderRequest.getCreateAnimalOrders().get(i).getAnimalServiceIds().size() >= 1) {
                List<AnimalService> animalServices = new ArrayList<>();
                for (int j = 0; j < createOrderRequest.getCreateAnimalOrders().get(i).getAnimalServiceIds().size(); j++) {
                    Long id = createOrderRequest.getCreateAnimalOrders().get(i).getAnimalServiceIds().get(j);
                    AnimalService animalService = animalServiceService.findById(id);
                    total += animalService.getCost();
                    animalServices.add(animalService);
                }
                Set<AnimalService> serviceSet = new HashSet<>(animalServices);
                orderAnimal.setAnimalServices(serviceSet);
            }
            orderAnimal.setTotal(total);
            orderAnimalService.save(orderAnimal);
            sum += orderAnimal.getTotal();
            orderAnimals.add(orderAnimal);
        }

        result.setClient(client);
        result.setTotal(sum);
        Set<OrderAnimal> serviceSet = new HashSet<>(orderAnimals);
        result.setOrderAnimals(serviceSet);
        result.setPaymentType(paymentType);
        result.setIsPayed(createOrderRequest.getIsPayed());

        return result;
    }

    @Override
    public OrderResponse toDto(Order order) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(order.getClient().getId());
        clientResponse.setLastName(order.getClient().getLastName());
        clientResponse.setFirstName(order.getClient().getFirstName());
        clientResponse.setTel(order.getClient().getTel());
        clientResponse.setAddress(order.getClient().getUserProfile().getAddress());

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String createdAt = dateFormat.format(order.getCreatedAt());

        List<OrderAnimalResponse> orderAnimalResponses = new ArrayList<>();
        for (OrderAnimal orderAnimal : order.getOrderAnimals()) {
            OrderAnimalResponse orderAnimalResponse = new OrderAnimalResponse();

            AnimalProfileResponse animalProfileResponse = new AnimalProfileResponse();
            AnimalProfile animalProfile = orderAnimal.getAnimalProfile();
            animalProfileResponse.setId(animalProfile.getId());
            animalProfileResponse.setAnimal(animalMapper.toDto(animalProfile.getAnimal()));
            animalProfileResponse.setAge(animalProfile.getAge());
            animalProfileResponse.setColor(animalProfile.getColor());
            animalProfileResponse.setWeight(animalProfile.getWeight());
            animalProfileResponse.setBreed(animalProfile.getBreed());
            animalProfileResponse.setGender(animalProfile.getGender());

            List<String> photos = new ArrayList<>();
            for (Photo photo : animalProfile.getPhotos()) {
                photos.add(photo.getFileUrl());
            }

            animalProfileResponse.setPhotos(photos);

            List<AnimalServiceResponse> animalServiceResponse =
                    animalProfile.getAnimalServices().stream().map(e -> animalServiceMapper.toDto(e)).collect(Collectors.toList());

            animalProfileResponse.setAnimalServiceResponses(animalServiceResponse);
            animalProfileResponse.setCost(animalProfile.getCost());
//
            List<AnimalServiceResponse> animalServiceResponses = new ArrayList<>();
            if (orderAnimal.getAnimalServices() != null) {
                if (orderAnimal.getAnimalServices().size() > 0) {
                    for (AnimalService animalService : orderAnimal.getAnimalServices()) {
                        AnimalServiceResponse as = new AnimalServiceResponse();
                        as.setId(animalService.getId());
                        as.setAnimalName(animalService.getAnimal().getName());
                        as.setServiceName(animalService.getService().getName());
                        as.setCost(animalService.getCost());

                        animalServiceResponses.add(as);
                    }
                }
            }

            orderAnimalResponse.setId(orderAnimal.getId());
            orderAnimalResponse.setAnimalProfile(animalProfileResponse);
            orderAnimalResponse.setAnimalServices(animalServiceResponses);

            orderAnimalResponses.add(orderAnimalResponse);
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setClient(clientResponse);
        orderResponse.setPaymentTypeName(order.getPaymentType().getName());
        orderResponse.setStatusName(order.getStatus().getName());
        orderResponse.setOrderAnimals(orderAnimalResponses);
        orderResponse.setTotal(order.getTotal());
        orderResponse.setCreatedAt(createdAt);

        return orderResponse;
    }


    @Override
    public List<OrderResponse> toDtoList(List<Order> orders) {
        List<OrderResponse> responses = orders.stream().map(e -> toDto(e)).collect(Collectors.toList());
        return responses;
    }


    public void saveOrderAnimals(List<OrderAnimal> orderAnimals) {
        for (int i = 0; i < orderAnimals.size(); i++) {
            orderAnimalService.save(orderAnimals.get(i));
        }
    }
}
