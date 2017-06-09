package com.zrb.houserental.Entity;

import java.util.List;

/**
 * Created by zrb on 2017/4/15.
 */

public class ListSmsEntity {

    /**
     * room : {"id":1,"name":"101","building_id":1,"rental":280,"water_rate":23,"electric_rate":235,"status":1,"rend_date_end":"2017-08-14 00:00:00","created_at":"2017-04-14 18:31:13","updated_at":"2017-04-15 10:08:51","rent_records":[{"id":1,"number":"20170414","user_id":1,"room_id":"1","phone":"15633639393","type":1,"days":0,"prev_water":0,"prev_electric":0,"water":25,"electric":36,"payable":0,"receivable":0,"rent_fee":0,"total_fee":600,"start_date":"2017-04-14 00:00:00","end_date":"2017-05-14 00:00:00","remark":"新租","addition":"[1]","created_at":"2017-04-14 20:29:19","updated_at":"2017-04-14 20:29:19"},{"id":2,"number":"20170415","user_id":1,"room_id":"1","phone":"15864964619","type":2,"days":0,"prev_water":25,"prev_electric":36,"water":258,"electric":368,"payable":36,"receivable":89,"rent_fee":84219,"total_fee":84272,"start_date":"2017-05-14 00:00:00","end_date":"2017-08-14 00:00:00","remark":"续租","addition":"[1]","created_at":"2017-04-15 10:08:51","updated_at":"2017-04-15 10:08:51"}]}
     */

    private RoomBean room;

    public RoomBean getRoom() {
        return room;
    }

    public void setRoom(RoomBean room) {
        this.room = room;
    }

    public static class RoomBean {
        /**
         * id : 1
         * name : 101
         * building_id : 1
         * rental : 280
         * water_rate : 23
         * electric_rate : 235
         * status : 1
         *    * deposit : 1000
         * rend_date_end : 2017-08-14 00:00:00
         * created_at : 2017-04-14 18:31:13
         * updated_at : 2017-04-15 10:08:51
         * rent_records : [{"id":1,"number":"20170414","user_id":1,"room_id":"1","phone":"15633639393","type":1,"days":0,"prev_water":0,"prev_electric":0,"water":25,"electric":36,"payable":0,"receivable":0,"rent_fee":0,"total_fee":600,"start_date":"2017-04-14 00:00:00","end_date":"2017-05-14 00:00:00","remark":"新租","addition":"[1]","created_at":"2017-04-14 20:29:19","updated_at":"2017-04-14 20:29:19"},{"id":2,"number":"20170415","user_id":1,"room_id":"1","phone":"15864964619","type":2,"days":0,"prev_water":25,"prev_electric":36,"water":258,"electric":368,"payable":36,"receivable":89,"rent_fee":84219,"total_fee":84272,"start_date":"2017-05-14 00:00:00","end_date":"2017-08-14 00:00:00","remark":"续租","addition":"[1]","created_at":"2017-04-15 10:08:51","updated_at":"2017-04-15 10:08:51"}]
         */

        private double id;
        private String name;
        private double building_id;
        private double rental;
        private double water_rate;
        private double electric_rate;
        private double status;
        private double deposit;
        private String rend_date_end;
        private String created_at;
        private String updated_at;
        private List<RentRecordsBean> rent_records;

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public double getId() {
            return id;
        }

        public void setId(double id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(double building_id) {
            this.building_id = building_id;
        }

        public double getRental() {
            return rental;
        }

        public void setRental(double rental) {
            this.rental = rental;
        }

        public double getWater_rate() {
            return water_rate;
        }

        public void setWater_rate(double water_rate) {
            this.water_rate = water_rate;
        }

        public double getElectric_rate() {
            return electric_rate;
        }

        public void setElectric_rate(double electric_rate) {
            this.electric_rate = electric_rate;
        }

        public double getStatus() {
            return status;
        }

        public void setStatus(double status) {
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

        public List<RentRecordsBean> getRent_records() {
            return rent_records;
        }

        public void setRent_records(List<RentRecordsBean> rent_records) {
            this.rent_records = rent_records;
        }

        public static class RentRecordsBean {
            /**
             * id : 1
             * number : 20170414
             * user_id : 1
             * room_id : 1
             * phone : 15633639393
             * type : 1
             * days : 0
             * prev_water : 0
             * prev_electric : 0
             * water : 25
             * electric : 36
             * payable : 0
             * receivable : 0
             * rent_fee : 0
             * total_fee : 600
             * start_date : 2017-04-14 00:00:00
             * end_date : 2017-05-14 00:00:00
             * remark : 新租
             * addition : [1]
             * created_at : 2017-04-14 20:29:19
             * updated_at : 2017-04-14 20:29:19
             */

            private int id;
            private String number;
            private double user_id;
            private String room_id;
            private String phone;
            private double type;
            private double days;
            private double prev_water;
            private double prev_electric;
            private double water;
            private double electric;
            private double payable;
            private double receivable;
            private double rent_fee;
            private double total_fee;
            private String start_date;
            private String end_date;
            private String remark;
            private String addition;
            private String created_at;
            private String updated_at;
            private String sms_content;

            public String getSms_content() {
                return sms_content;
            }

            public void setSms_content(String sms_content) {
                this.sms_content = sms_content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public double getUser_id() {
                return user_id;
            }

            public void setUser_id(double user_id) {
                this.user_id = user_id;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public double getType() {
                return type;
            }

            public void setType(double type) {
                this.type = type;
            }

            public double getDays() {
                return days;
            }

            public void setDays(double days) {
                this.days = days;
            }

            public double getPrev_water() {
                return prev_water;
            }

            public void setPrev_water(double prev_water) {
                this.prev_water = prev_water;
            }

            public double getPrev_electric() {
                return prev_electric;
            }

            public void setPrev_electric(double prev_electric) {
                this.prev_electric = prev_electric;
            }

            public double getWater() {
                return water;
            }

            public void setWater(double water) {
                this.water = water;
            }

            public double getElectric() {
                return electric;
            }

            public void setElectric(double electric) {
                this.electric = electric;
            }

            public double getPayable() {
                return payable;
            }

            public void setPayable(double payable) {
                this.payable = payable;
            }

            public double getReceivable() {
                return receivable;
            }

            public void setReceivable(double receivable) {
                this.receivable = receivable;
            }

            public double getRent_fee() {
                return rent_fee;
            }

            public void setRent_fee(double rent_fee) {
                this.rent_fee = rent_fee;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getAddition() {
                return addition;
            }

            public void setAddition(String addition) {
                this.addition = addition;
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
