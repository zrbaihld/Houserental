package com.zrb.houserental.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class ResultRoomQuertEntity {

    /**
     * id : 5
     * name : 楼201-房301
     * building_id : 3
     * rental : 200
     * water_rate : 5
     * electric_rate : 1
     * status : 0
     * rend_date_end : 2017-06-09 00:00:00
     * created_at : 2017-04-09 00:00:00
     * updated_at : 2017-04-09 20:23:12
     * days : 60
     */

    private List<RoomsBean> rooms;

    public List<RoomsBean> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomsBean> rooms) {
        this.rooms = rooms;
    }

    public static class RoomsBean {
        private int id;
        private String name;
        private int building_id;
        private int rental;
        private int water_rate;
        private String electric_rate;
        private int status;
        private String rend_date_end;
        private String created_at;
        private String updated_at;
        private int days;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(int building_id) {
            this.building_id = building_id;
        }

        public int getRental() {
            return rental;
        }

        public void setRental(int rental) {
            this.rental = rental;
        }

        public int getWater_rate() {
            return water_rate;
        }

        public void setWater_rate(int water_rate) {
            this.water_rate = water_rate;
        }

        public String getElectric_rate() {
            return electric_rate;
        }

        public void setElectric_rate(String electric_rate) {
            this.electric_rate = electric_rate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRend_date_end() {
            return rend_date_end;
        }

        public void setRend_date_end(String rend_date_end) {
            this.rend_date_end = rend_date_end;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }
    }
}
