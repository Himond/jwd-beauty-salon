package by.epam.litvinko.beautysalon.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The type Provide service.
 */
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

    /**
     * Instantiates a new Provide service.
     */
    public ProvideService() {
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets category id.
     *
     * @param categoryId the category id
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets service time.
     *
     * @return the service time
     */
    public int getServiceTime() {
        return serviceTime;
    }

    /**
     * Sets service time.
     *
     * @param serviceTime the service time
     */
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public LocalDate getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(LocalDate created) {
        this.created = created;
    }

    /**
     * Gets updated.
     *
     * @return the updated
     */
    public LocalDate getUpdated() {
        return updated;
    }

    /**
     * Sets updated.
     *
     * @param updated the updated
     */
    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
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

    /**
     * New builder provide service . builder.
     *
     * @return the provide service . builder
     */
    public static ProvideService.Builder newBuilder(){
        return new ProvideService().new Builder();
    }

    /**
     * The type Builder.
     */
    public class Builder{

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Set id provide service . builder.
         *
         * @param id the id
         * @return the provide service . builder
         */
        public ProvideService.Builder setId(int id){
            ProvideService.this.setId(id);
            return this;
        }

        /**
         * Set category id provide service . builder.
         *
         * @param categoryId the category id
         * @return the provide service . builder
         */
        public ProvideService.Builder setCategoryId(int categoryId){
            ProvideService.this.categoryId = categoryId;
            return this;
        }

        /**
         * Set name provide service . builder.
         *
         * @param name the name
         * @return the provide service . builder
         */
        public ProvideService.Builder setName(String name){
            ProvideService.this.name = name;
            return this;
        }

        /**
         * Set description provide service . builder.
         *
         * @param description the description
         * @return the provide service . builder
         */
        public ProvideService.Builder setDescription(String description){
            ProvideService.this.description = description;
            return this;
        }

        /**
         * Set price provide service . builder.
         *
         * @param price the price
         * @return the provide service . builder
         */
        public ProvideService.Builder setPrice(BigDecimal price){
            ProvideService.this.price = price;
            return this;
        }

        /**
         * Set service time provide service . builder.
         *
         * @param serviceTime the service time
         * @return the provide service . builder
         */
        public ProvideService.Builder setServiceTime(int serviceTime){
            ProvideService.this.serviceTime = serviceTime;
            return this;
        }

        /**
         * Set available provide service . builder.
         *
         * @param available the available
         * @return the provide service . builder
         */
        public ProvideService.Builder setAvailable(boolean available){
            ProvideService.this.available = available;
            return this;
        }

        /**
         * Set created provide service . builder.
         *
         * @param created the created
         * @return the provide service . builder
         */
        public ProvideService.Builder setCreated(LocalDate created){
            ProvideService.this.created = created;
            return this;
        }

        /**
         * Set updated provide service . builder.
         *
         * @param updated the updated
         * @return the provide service . builder
         */
        public ProvideService.Builder setUpdated(LocalDate updated){
            ProvideService.this.updated = updated;
            return this;
        }

        /**
         * Set image provide service . builder.
         *
         * @param image the image
         * @return the provide service . builder
         */
        public ProvideService.Builder setImage(String image){
            ProvideService.this.image = image;
            return this;
        }

        /**
         * Build provide service.
         *
         * @return the provide service
         */
        public ProvideService build(){
            return ProvideService.this;
        }

    }
}
