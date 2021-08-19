package by.epam.litvinko.beautysalon.entity;

import java.util.HashMap;
import java.util.Map;

public class Cart extends Entity {

    private int orderID;
    private Map<ProvideService, Master> services = new HashMap<>();

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Map<ProvideService, Master> getService() {
        return services;
    }

    public void addProvideService(ProvideService service, Master master){
        services.put(service, master);
    }

    public void removeProvideService(ProvideService service){
        services.remove(service);
    }

    public void clearCart(){
        services.clear();
    }
}
