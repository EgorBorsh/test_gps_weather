package com.smollvile.gpsweather.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "STORY")
public class Story {
    @Id(autoincrement = true)
    private Long id;
    private String City;
    private String Coordinates;
    private String Country;
    private String Street;
    private String House;
    private String Temp;
    private String Humidity;
    private String Description;
    private String WindSpeed;
    private String Date;
    private String Time;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1527771594)
    private transient StoryDao myDao;
    @Generated(hash = 1595201472)
    public Story(Long id, String City, String Coordinates, String Country,
            String Street, String House, String Temp, String Humidity,
            String Description, String WindSpeed, String Date, String Time) {
        this.id = id;
        this.City = City;
        this.Coordinates = Coordinates;
        this.Country = Country;
        this.Street = Street;
        this.House = House;
        this.Temp = Temp;
        this.Humidity = Humidity;
        this.Description = Description;
        this.WindSpeed = WindSpeed;
        this.Date = Date;
        this.Time = Time;
    }
    @Generated(hash = 922655990)
    public Story() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCity() {
        return this.City;
    }
    public void setCity(String City) {
        this.City = City;
    }
    public String getCoordinates() {
        return this.Coordinates;
    }
    public void setCoordinates(String Coordinates) {
        this.Coordinates = Coordinates;
    }
    public String getCountry() {
        return this.Country;
    }
    public void setCountry(String Country) {
        this.Country = Country;
    }
    public String getStreet() {
        return this.Street;
    }
    public void setStreet(String Street) {
        this.Street = Street;
    }
    public String getHouse() {
        return this.House;
    }
    public void setHouse(String House) {
        this.House = House;
    }
    public String getTemp() {
        return this.Temp;
    }
    public void setTemp(String Temp) {
        this.Temp = Temp;
    }
    public String getHumidity() {
        return this.Humidity;
    }
    public void setHumidity(String Humidity) {
        this.Humidity = Humidity;
    }
    public String getDescription() {
        return this.Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public String getWindSpeed() {
        return this.WindSpeed;
    }
    public void setWindSpeed(String WindSpeed) {
        this.WindSpeed = WindSpeed;
    }
    public String getDate() {
        return this.Date;
    }
    public void setDate(String Date) {
        this.Date = Date;
    }
    public String getTime() {
        return this.Time;
    }
    public void setTime(String Time) {
        this.Time = Time;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1027372697)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStoryDao() : null;
    }
}
