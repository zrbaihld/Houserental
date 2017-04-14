package com.zrb.houserental.Entity;

/**
 * Created by Administrator on 2017/4/10.
 */

public class RoomEntity {

    /**
     * id : 6
     * name : 楼201-房302
     * building_id : 3
     * rental : 200
     * water_rate : 5
     * electric_rate : 1
     * status : 1
     * rend_date_end : 2017-10-09 00:00:00
     * created_at : 2017-04-09 00:00:00
     * updated_at : 2017-04-09 20:14:42
     * lodger : {"id":3,"room_id":6,"name":"kkk01","status":1,"id_card":"132123129312323123","sex":1,"phone":"18850583123","hometown":"test1213","birthday":"2017-08-02 12:00:00","residence_permit_expire":"2017-08-08 12:00:00","rent_date_start":"2017-04-09 00:00:00","rent_date_end":"2017-10-09 00:00:00","deposit":0,"keys":0,"created_at":"2017-04-09 20:06:40","updated_at":"2017-04-09 20:14:42","prev_water":120,"prev_electric":120,"prev_start_date":"2017-08-09 00:00:00","prev_end_date":"2017-10-09 00:00:00"}
     */

    private RoomBean room;

    public RoomBean getRoom() {
        return room;
    }

    public void setRoom(RoomBean room) {
        this.room = room;
    }

    public static class RoomBean {
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
        private String number;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        /**
         * id : 3
         * room_id : 6
         * name : kkk01
         * status : 1
         * id_card : 132123129312323123
         * sex : 1
         * phone : 18850583123
         * hometown : test1213
         * birthday : 2017-08-02 12:00:00
         * residence_permit_expire : 2017-08-08 12:00:00
         * rent_date_start : 2017-04-09 00:00:00
         * rent_date_end : 2017-10-09 00:00:00
         * deposit : 0
         * keys : 0
         * created_at : 2017-04-09 20:06:40
         * updated_at : 2017-04-09 20:14:42
         * prev_water : 120
         * prev_electric : 120
         * prev_start_date : 2017-08-09 00:00:00
         * prev_end_date : 2017-10-09 00:00:00
         */

        private LodgerBean lodger;

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

        public LodgerBean getLodger() {
            return lodger;
        }

        public void setLodger(LodgerBean lodger) {
            this.lodger = lodger;
        }

        public static class LodgerBean {
            private int id;
            private int room_id;
            private String name;
            private int status;
            private String id_card;
            private int sex;
            private String phone;
            private String hometown;
            private String birthday;
            private String residence_permit_expire;
            private String rent_date_start;
            private String rent_date_end;
            private int deposit;
            private int keys;
            private String created_at;
            private String updated_at;
            private int prev_water;
            private int prev_electric;
            private String prev_start_date;
            private String prev_end_date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRoom_id() {
                return room_id;
            }

            public void setRoom_id(int room_id) {
                this.room_id = room_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getHometown() {
                return hometown;
            }

            public void setHometown(String hometown) {
                this.hometown = hometown;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getResidence_permit_expire() {
                return residence_permit_expire;
            }

            public void setResidence_permit_expire(String residence_permit_expire) {
                this.residence_permit_expire = residence_permit_expire;
            }

            public String getRent_date_start() {
                return rent_date_start;
            }

            public void setRent_date_start(String rent_date_start) {
                this.rent_date_start = rent_date_start;
            }

            public String getRent_date_end() {
                return rent_date_end;
            }

            public void setRent_date_end(String rent_date_end) {
                this.rent_date_end = rent_date_end;
            }

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public int getKeys() {
                return keys;
            }

            public void setKeys(int keys) {
                this.keys = keys;
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

            public int getPrev_water() {
                return prev_water;
            }

            public void setPrev_water(int prev_water) {
                this.prev_water = prev_water;
            }

            public int getPrev_electric() {
                return prev_electric;
            }

            public void setPrev_electric(int prev_electric) {
                this.prev_electric = prev_electric;
            }

            public String getPrev_start_date() {
                return prev_start_date;
            }

            public void setPrev_start_date(String prev_start_date) {
                this.prev_start_date = prev_start_date;
            }

            public String getPrev_end_date() {
                return prev_end_date;
            }

            public void setPrev_end_date(String prev_end_date) {
                this.prev_end_date = prev_end_date;
            }
        }
    }
}
