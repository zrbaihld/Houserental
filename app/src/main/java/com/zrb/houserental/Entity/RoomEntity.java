package com.zrb.houserental.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class RoomEntity {

    /**
     * id : 1
     * name : 201
     * building_id : 1
     * rental : 1000.00
     * water_rate : 5.00
     * electric_rate : 1.00
     * status : 1
     * rent_date_start : 2017-04-24
     * rent_date_end : 2017-08-24
     * keys : 2
     * deposit : 1000
     * network_num : 3
     * network_provider : 44455
     * contract_months : 36
     * created_at : 2017-04-24 10:18:51
     * updated_at : 2017-04-24 10:33:33
     * number : 20170424
     * lodger : {"id":1,"room_id":1,"building_id":1,"name":"gvbb","status":1,"id_card":"123456789123456789","sex":1,"phone":"15659810042","province_id":0,"city_id":0,"area_id":0,"hometown":"gbbvv","birthday":"2017-04-24 00:00:00","residence_permit_expire":"2017-04-24 00:00:00","rent_date_start":"2017-04-24 00:00:00","rent_date_end":"2017-08-24 00:00:00","deposit":1000,"created_at":"2017-04-24 10:37:46","updated_at":"2017-04-24 10:37:46","prev_water":0,"prev_electric":0,"prev_start_date":"2017-04-24","prev_end_date":"2017-08-24"}
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
        private String rental;
        private String water_rate;
        private String electric_rate;
        private int status;
        private String rent_date_start;
        private String rent_date_end;
        private int water_init;
        private int electric_init;
        private int keys;
        private int days;
        private int deposit;
        private int network_num;
        private String network_provider;
        private int contract_months;
        private String created_at;
        private String updated_at;
        private String number;
        private List<String> phone;

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getWater_init() {
            return water_init;
        }

        public void setWater_init(int water_init) {
            this.water_init = water_init;
        }

        public int getElectric_init() {
            return electric_init;
        }

        public void setElectric_init(int electric_init) {
            this.electric_init = electric_init;
        }

        public List<String> getPhone() {
            return phone;
        }

        /**
         * id : 1
         * room_id : 1
         * building_id : 1
         * name : gvbb
         * status : 1
         * id_card : 123456789123456789
         * sex : 1
         * phone : 15659810042
         * province_id : 0
         * city_id : 0
         * area_id : 0
         * hometown : gbbvv
         * birthday : 2017-04-24 00:00:00
         * residence_permit_expire : 2017-04-24 00:00:00
         * rent_date_start : 2017-04-24 00:00:00
         * rent_date_end : 2017-08-24 00:00:00
         * deposit : 1000
         * created_at : 2017-04-24 10:37:46
         * updated_at : 2017-04-24 10:37:46
         * prev_water : 0
         * prev_electric : 0
         * prev_start_date : 2017-04-24
         * prev_end_date : 2017-08-24
         */

        private LodgerBean lodger;

        public void setPhone(List<String>  phone) {
            this.phone = phone;
        }
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

        public String getRental() {
            return rental;
        }

        public void setRental(String rental) {
            this.rental = rental;
        }

        public String getWater_rate() {
            return water_rate;
        }

        public void setWater_rate(String water_rate) {
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

        public int getKeys() {
            return keys;
        }

        public void setKeys(int keys) {
            this.keys = keys;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getNetwork_num() {
            return network_num;
        }

        public void setNetwork_num(int network_num) {
            this.network_num = network_num;
        }

        public String getNetwork_provider() {
            return network_provider;
        }

        public void setNetwork_provider(String network_provider) {
            this.network_provider = network_provider;
        }

        public int getContract_months() {
            return contract_months;
        }

        public void setContract_months(int contract_months) {
            this.contract_months = contract_months;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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
            private int building_id;
            private String name;
            private int status;
            private String id_card;
            private int sex;
            private String phone;
            private int province_id;
            private int city_id;
            private int area_id;
            private String hometown;
            private String birthday;
            private String residence_permit_expire;
            private String rent_date_start;
            private String rent_date_end;
            private int deposit;
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

            public int getBuilding_id() {
                return building_id;
            }

            public void setBuilding_id(int building_id) {
                this.building_id = building_id;
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

            public int getProvince_id() {
                return province_id;
            }

            public void setProvince_id(int province_id) {
                this.province_id = province_id;
            }

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public int getArea_id() {
                return area_id;
            }

            public void setArea_id(int area_id) {
                this.area_id = area_id;
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
