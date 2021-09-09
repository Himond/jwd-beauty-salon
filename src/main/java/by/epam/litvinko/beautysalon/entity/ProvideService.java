package by.epam.litvinko.beautysalon.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class ProvideService extends AbstractEntity {

    private int categoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private int serviceTime;
    private boolean available;
    private LocalDate created;
    private LocalDate updated;
    private String image;

    public ProvideService() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ProvideService service = (ProvideService) o;

        if (categoryId != service.categoryId) return false;
        if (serviceTime != service.serviceTime) return false;
        if (available != service.available) return false;
        if (name != null ? !name.equals(service.name) : service.name != null) return false;
        if (description != null ? !description.equals(service.description) : service.description != null) return false;
        if (price != null ? !price.equals(service.price) : service.price != null) return false;
        if (created != null ? !created.equals(service.created) : service.created != null) return false;
        if (updated != null ? !updated.equals(service.updated) : service.updated != null) return false;
        return image != null ? image.equals(service.image) : service.image == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + categoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + serviceTime;
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProvideService{");
        sb.append("categoryId=").append(categoryId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", serviceTime=").append(serviceTime);
        sb.append(", available=").append(available);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static ProvideService.Builder newBuilder(){
        return new ProvideService().new Builder();
    }

    public class Builder{

        public Builder() {
        }

        public ProvideService.Builder setId(int id){
            ProvideService.this.setId(id);
            return this;
        }

        public ProvideService.Builder setCategoryId(int categoryId){
            ProvideService.this.categoryId = categoryId;
            return this;
        }

        public ProvideService.Builder setName(String name){
            ProvideService.this.name = name;
            return this;
        }

        public ProvideService.Builder setDescription(String description){
            ProvideService.this.description = description;
            return this;
        }

        public ProvideService.Builder setPrice(BigDecimal price){
            ProvideService.this.price = price;
            return this;
        }

        public ProvideService.Builder setServiceTime(int serviceTime){
            ProvideService.this.serviceTime = serviceTime;
            return this;
        }

        public ProvideService.Builder setAvailable(boolean available){
            ProvideService.this.available = available;
            return this;
        }

        public ProvideService.Builder setCreated(LocalDate created){
            ProvideService.this.created = created;
            return this;
        }

        public ProvideService.Builder setUpdated(LocalDate updated){
            ProvideService.this.updated = updated;
            return this;
        }

        public ProvideService.Builder setImage(String image){
            ProvideService.this.image = image;
            return this;
        }

        public ProvideService build(){
            return ProvideService.this;
        }

    }
}
