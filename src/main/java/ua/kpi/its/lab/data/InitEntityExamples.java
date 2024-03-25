package ua.kpi.its.lab.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kpi.its.lab.data.entity.Route;
import ua.kpi.its.lab.data.entity.Train;
import ua.kpi.its.lab.data.repo.RouteRepository;
import ua.kpi.its.lab.data.repo.TrainRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InitEntityExamples {

    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public InitEntityExamples(TrainRepository trainRepository, RouteRepository routeRepository) {
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
    }

    public List<Train> initTrains() {
        List<Route> routes = new ArrayList<>();
        List<Train> trains = new ArrayList<>();
        for (int i =0; i < 5; i++ ) {

            Route route = new Route();
            route.setDeparturePoint("Departure point");
            route.setDestination("Destination");
            route.setDateOfShipment(LocalDateTime.of(2024, 1 + i, 24 + i , 1 + i, i));
            route.setMileage(100 + i);
            route.setPriceOfTicket(100.0 + i);
            route.setIsCircular(false);

            // Збереження маршруту в БД
            route = routeRepository.save(route);

            routes.add(route);

            Train train = new Train();

            train.setModel("model " + i);
            train.setWeight(231.1 + i);
            train.setProducer("Producer " + i);
            train.setNumberOfSeats(220 + i);
            train.setType(TrainTypes.PASSAGER);
            train.setDateOfCommissioning(LocalDateTime.of(2021, 1 + i, 2 + i, 10 + i, 12 + i));
            train.setHasConditioner(true);

            train.setRoutes(routes);

            train = trainRepository.save(train);

            trains.add(train);
        }
        return trains;
    }

}

