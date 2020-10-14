package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.request.CreateOrderReq;
import kz.devhils.meathouse.model.entities.*;
import kz.devhils.meathouse.model.mappers.OrderMapper;
import kz.devhils.meathouse.repositories.AnimalProfileRepo;
import kz.devhils.meathouse.service.AnimalServiceService;
import kz.devhils.meathouse.service.OrderAnimalService;
import kz.devhils.meathouse.service.PaymentTypeService;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public Order toEntity(CreateOrderReq createOrderReq) {
        Order result = new Order();

        User client = userService.findById(createOrderReq.getClientId());
        PaymentType paymentType = paymentTypeService.findById(createOrderReq.getPaymentTypeId());
        double sum = 0;
        List<OrderAnimal> orderAnimals = new ArrayList<>();
        for (int i=0; i < createOrderReq.getCreateAnimalOrders().size(); i++){
            OrderAnimal orderAnimal = new OrderAnimal();
            double total = 0;
            AnimalProfile animalProfile = animalProfileRepo.getOne(createOrderReq.getCreateAnimalOrders().get(i).getAnimalProfileId());
            total += animalProfile.getCost();
            orderAnimal.setAnimalProfile(animalProfile);
            if (createOrderReq.getCreateAnimalOrders().get(i).getAnimalServiceIds().size() >= 1 ){
                List<AnimalService> animalServices = new ArrayList<>();
                for (int j = 0; j < createOrderReq.getCreateAnimalOrders().get(i).getAnimalServiceIds().size(); j++){
                    Long id = createOrderReq.getCreateAnimalOrders().get(i).getAnimalServiceIds().get(j);
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
        result.setIsPayed(createOrderReq.getIsPayed());

        return result;
    }

    public void saveOrderAnimals(List<OrderAnimal> orderAnimals){
        for (int i=0; i < orderAnimals.size(); i++){
            orderAnimalService.save(orderAnimals.get(i));
        }
    }
}
