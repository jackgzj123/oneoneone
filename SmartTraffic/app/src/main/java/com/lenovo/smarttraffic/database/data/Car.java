package com.lenovo.smarttraffic.database.data;


public class Car {
        //私有属性
        private int id;
        private String name;
        private int chong;

        //无参构造
        public Car(){}

        //有参构造
        public Car(String name, int chong) {
            this.name = name;
            this.chong = chong;

        }

        public Car(int id, String name, int chong) {
            super();
            this.id = id;
            this.name = name;
            this.chong = chong;
        }
        //创建的setter和getter方法
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
        public int getChong() {
            return chong;
        }
        public void setChong(int age) {
            this.chong = age;
        }


}
