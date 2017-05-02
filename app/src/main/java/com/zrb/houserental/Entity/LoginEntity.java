package com.zrb.houserental.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class LoginEntity {

    /**
     * token : 1a79c96495e2d9ab5ec5c3381c1bd234
     * admin : {"id":1,"username":"root","name":"超级管理员","avatar":"image/admin-head.jpg","realname":"superMan","phone":"15688888888","super":1,"email":null,"created_at":"2017-04-08 09:56:52","updated_at":"2017-04-09 23:26:36","buildings":[{"id":1,"admin_id":1,"name":"101","province_id":4,"city_id":53,"area_id":519,"address":"啊啊啊啊","room_count":200,"created_at":"2017-04-24 10:18:34","updated_at":"2017-04-24 10:18:34","rooms":[{"id":1,"name":"201","building_id":1,"rental":"1000.00","water_rate":"5.00","electric_rate":"1.00","status":1,"rent_date_start":"2017-04-24","rent_date_end":"2017-08-24","keys":2,"deposit":1000,"network_num":3,"network_provider":"44455","contract_months":36,"created_at":"2017-04-24 10:18:51","updated_at":"2017-04-24 10:33:33"}]}]}
     */

    private String token;
    /**
     * id : 1
     * username : root
     * name : 超级管理员
     * avatar : image/admin-head.jpg
     * realname : superMan
     * phone : 15688888888
     * super : 1
     * email : null
     * created_at : 2017-04-08 09:56:52
     * updated_at : 2017-04-09 23:26:36
     * buildings : [{"id":1,"admin_id":1,"name":"101","province_id":4,"city_id":53,"area_id":519,"address":"啊啊啊啊","room_count":200,"created_at":"2017-04-24 10:18:34","updated_at":"2017-04-24 10:18:34","rooms":[{"id":1,"name":"201","building_id":1,"rental":"1000.00","water_rate":"5.00","electric_rate":"1.00","status":1,"rent_date_start":"2017-04-24","rent_date_end":"2017-08-24","keys":2,"deposit":1000,"network_num":3,"network_provider":"44455","contract_months":36,"created_at":"2017-04-24 10:18:51","updated_at":"2017-04-24 10:33:33"}]}]
     */

    private AdminBean admin;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AdminBean getAdmin() {
        return admin;
    }

    public void setAdmin(AdminBean admin) {
        this.admin = admin;
    }

    public static class AdminBean {
        private int id;
        private String username;
        private String name;
        private String avatar;
        private String realname;
        private String phone;
        @SerializedName("super")
        private int superX;
        private Object email;
        private String created_at;
        private String updated_at;
        /**
         * id : 1
         * admin_id : 1
         * name : 101
         * province_id : 4
         * city_id : 53
         * area_id : 519
         * address : 啊啊啊啊
         * room_count : 200
         * created_at : 2017-04-24 10:18:34
         * updated_at : 2017-04-24 10:18:34
         * rooms : [{"id":1,"name":"201","building_id":1,"rental":"1000.00","water_rate":"5.00","electric_rate":"1.00","status":1,"rent_date_start":"2017-04-24","rent_date_end":"2017-08-24","keys":2,"deposit":1000,"network_num":3,"network_provider":"44455","contract_months":36,"created_at":"2017-04-24 10:18:51","updated_at":"2017-04-24 10:33:33"}]
         */

        private List<BuildingsBean> buildings;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSuperX() {
            return superX;
        }

        public void setSuperX(int superX) {
            this.superX = superX;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
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

        public List<BuildingsBean> getBuildings() {
            return buildings;
        }

        public void setBuildings(List<BuildingsBean> buildings) {
            this.buildings = buildings;
        }

        public static class BuildingsBean {
            private int id;
            private int admin_id;
            private String name;
            private int province_id;
            private int city_id;
            private int area_id;
            private String address;
            private int room_count;
            private String created_at;
            private String updated_at;
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
             */

            private List<RoomsBean> rooms;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(int admin_id) {
                this.admin_id = admin_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getRoom_count() {
                return room_count;
            }

            public void setRoom_count(int room_count) {
                this.room_count = room_count;
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
                private int days;
                private String rental;
                private String water_rate;
                private String electric_rate;
                private int status;
                private String rent_date_start;
                private String rent_date_end;
                private int keys;
                private int deposit;
                private int network_num;
                private String network_provider;
                private int contract_months;
                private String created_at;
                private String updated_at;

                public int getDays() {
                    return days;
                }

                public void setDays(int days) {
                    this.days = days;
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
            }
        }
    }
}
