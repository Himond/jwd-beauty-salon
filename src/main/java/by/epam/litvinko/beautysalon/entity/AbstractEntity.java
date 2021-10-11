package by.epam.litvinko.beautysalon.entity;

import java.io.Serializable;

/**
 * The type Abstract entity.
 */
public abstract class AbstractEntity implements Serializable, Cloneable {
    private int id;

    /**
     * Instantiates a new Abstract entity.
     */
    public AbstractEntity() {
    }

    /**
     * Instantiates a new Abstract entity.
     *
     * @param id the id
     */
    public AbstractEntity(int id) {
        this.id = id;
    }

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity entity = (AbstractEntity) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
