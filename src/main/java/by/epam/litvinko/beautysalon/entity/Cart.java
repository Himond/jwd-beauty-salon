package by.epam.litvinko.beautysalon.entity;

import java.util.HashMap;
import java.util.Map;

public class Cart extends AbstractEntity {

    private int orderId;
    private Map<ProvideService, Master> services = new HashMap<>();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
