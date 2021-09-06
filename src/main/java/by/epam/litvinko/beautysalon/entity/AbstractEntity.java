package by.epam.litvinko.beautysalon.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable, Cloneable {
    private int id;

    public AbstractEntity() {
    }

    public AbstractEntity(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

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
