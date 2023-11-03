import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Vehicle {
    private int vehicleId;
    private double speed; // in meters per second
    private double position; // in meters
    private double communicationRange; // in meters

    public Vehicle(int vehicleId) {
        this.vehicleId = vehicleId;
        this.speed = new Random().nextInt(30) + 30; // Initial speed between 30 and 60 m/s
        this.position = 0; // Initial position on the road
        this.communicationRange = 100; // Communication range in meters
    }

    public void updatePosition(double timeInterval) {
        position += speed * timeInterval; // Update position based on speed
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public double getPosition() {
        return position;
    }

    public double getSpeed() {
        return speed;
    }

    public double getCommunicationRange() {
        return communicationRange;
    }

    public String broadcastMessage() {
        return String.format("Vehicle %d at position %.2f meters with speed %.2f m/s", vehicleId, position, speed);
    }
}

class TrafficInfrastructure {
    public void manageTraffic(List<Vehicle> vehicles) {
        // Simulate traffic management actions (e.g., traffic light control) based on V2I communication
        System.out.println("Traffic infrastructure managing traffic...");
    }
}

class PedestrianApp {
    public void interactWithVehicle(Vehicle vehicle) {
        // Simulate V2P communication where a pedestrian app interacts with nearby vehicles
        System.out.printf("Pedestrian app interacts with Vehicle %d%n", vehicle.getVehicleId());
    }
}

class CloudServices {
    public void collectAndAnalyzeData(List<Vehicle> vehicles) {
        // Simulate collecting and analyzing data for traffic management, predictive maintenance, and urban planning
        System.out.println("Cloud services collecting and analyzing data...");

        // Print current positions of vehicles for demonstration purposes
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.broadcastMessage());
        }
    }
}

public class V2VCommunicationSimulation {
    public static void main(String[] args) {
        int numVehicles = 5;
        List<Vehicle> vehicles = new ArrayList<>();

        // Create a fleet of vehicles
        for (int i = 0; i < numVehicles; i++) {
            vehicles.add(new Vehicle(i));
        }

        TrafficInfrastructure trafficInfrastructure = new TrafficInfrastructure();
        PedestrianApp pedestrianApp = new PedestrianApp();
        CloudServices cloudServices = new CloudServices();

        // Start the simulation
        while (true) {
            try {
                Thread.sleep(1000); // Simulate a time interval of 1 second

                // Update positions and broadcast messages
                for (Vehicle vehicle : vehicles) {
                    vehicle.updatePosition(1);

                    // Simulate V2P communication
                    pedestrianApp.interactWithVehicle(vehicle);
                }

                // Simulate V2V communication
                for (Vehicle vehicle : vehicles) {
                    System.out.println(vehicle.broadcastMessage());

                    // Simulate V2V communication - check for nearby vehicles within communication range
                    for (Vehicle otherVehicle : vehicles) {
                        double distance = Math.abs(vehicle.getPosition() - otherVehicle.getPosition());
                        if (distance <= vehicle.getCommunicationRange() && vehicle != otherVehicle) {
                            System.out.printf("Vehicle %d receives message from Vehicle %d%n",
                                    vehicle.getVehicleId(), otherVehicle.getVehicleId());
                        }
                    }
                }

                // Simulate V2I communication
                trafficInfrastructure.manageTraffic(vehicles);

                // Simulate V2C cloud services
                cloudServices.collectAndAnalyzeData(vehicles);

                System.out.println("------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
