package com.zrb.houserental.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class LoginEntity {

    /**
     * token : 328a4a96ebfe747b99ba7ae8deb1a975
     * admin : {"id":2,"username":"pangzhi","realname":"pangzhi","email":"123456@qq.com","phone":"123456789","super":0,"created_at":"2017-04-09 19:36:51","updated_at":"2017-04-09 19:36:51","buildings":[{"id":3,"admin_id":2,"name":"楼201","address":"说的房间","room_count":3,"created_at":null,"updated_at":null,"rooms":[{"id":5,"name":"楼201-房301","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":"2017-06-09 00:00:00","created_at":"2017-04-09 00:00:00","updated_at":"2017-04-09 20:23:12"},{"id":6,"name":"楼201-房302","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":1,"rend_date_end":"2017-10-09 00:00:00","created_at":"2017-04-09 00:00:00","updated_at":"2017-04-09 20:14:42"},{"id":7,"name":"楼201-房303","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":null,"created_at":"2017-04-08 00:00:00","updated_at":null}]},{"id":4,"admin_id":2,"name":"楼202","address":"是短发了","room_count":2,"created_at":null,"updated_at":null,"rooms":[{"id":10,"name":"楼301-房403","building_id":4,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":null,"created_at":"2017-04-09 00:00:00","updated_at":null}]}]}
     */

    private String token;
    /**
     * id : 2
     * username : pangzhi
     * realname : pangzhi
     * email : 123456@qq.com
     * phone : 123456789
     * super : 0
     * created_at : 2017-04-09 19:36:51
     * updated_at : 2017-04-09 19:36:51
     * buildings : [{"id":3,"admin_id":2,"name":"楼201","address":"说的房间","room_count":3,"created_at":null,"updated_at":null,"rooms":[{"id":5,"name":"楼201-房301","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":"2017-06-09 00:00:00","created_at":"2017-04-09 00:00:00","updated_at":"2017-04-09 20:23:12"},{"id":6,"name":"楼201-房302","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":1,"rend_date_end":"2017-10-09 00:00:00","created_at":"2017-04-09 00:00:00","updated_at":"2017-04-09 20:14:42"},{"id":7,"name":"楼201-房303","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":null,"created_at":"2017-04-08 00:00:00","updated_at":null}]},{"id":4,"admin_id":2,"name":"楼202","address":"是短发了","room_count":2,"created_at":null,"updated_at":null,"rooms":[{"id":10,"name":"楼301-房403","building_id":4,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":null,"created_at":"2017-04-09 00:00:00","updated_at":null}]}]
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
        private String id;
        private String username;
        private String realname;
        private String email;
        private String phone;
        @SerializedName("super")
        private int superX;
        private String created_at;
        private String updated_at;
        /**
         * id : 3
         * admin_id : 2
         * name : 楼201
         * address : 说的房间
         * room_count : 3
         * created_at : null
         * updated_at : null
         * rooms : [{"id":5,"name":"楼201-房301","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":"2017-06-09 00:00:00","created_at":"2017-04-09 00:00:00","updated_at":"2017-04-09 20:23:12"},{"id":6,"name":"楼201-房302","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":1,"rend_date_end":"2017-10-09 00:00:00","created_at":"2017-04-09 00:00:00","updated_at":"2017-04-09 20:14:42"},{"id":7,"name":"楼201-房303","building_id":3,"rental":200,"water_rate":5,"electric_rate":"1","status":0,"rend_date_end":null,"created_at":"2017-04-08 00:00:00","updated_at":null}]
         */

        private List<BuildingsBean> buildings;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
            private String id;
            private int admin_id;
            private String name;
            private String address;
            private int room_count;
            private String created_at;
            private String updated_at;
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
             */

            private List<RoomsBean> rooms;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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
                private String id;
                private String name;
                private int building_id;
                private int rental;
                private int water_rate;
                private String electric_rate;
                private int status;
                private String rend_date_end;
                private String created_at;
                private String updated_at;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
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
            }
        }
    }
}
