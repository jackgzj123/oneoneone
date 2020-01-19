package com.lenovo.smarttraffic.database.data;

import java.util.List;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class BusData {

    /**
     * number : 639
     * busName : 901路公交汽车
     * beginTime : 6:00
     * endTime : 21:00
     * platforms : [{"childBeans":[{"number":71,"distance":533,"name":"1号","id":1},{"number":76,"distance":1514,"name":"2号","id":2},{"number":3,"distance":1290,"name":"3号","id":3},{"number":26,"distance":442,"name":"4号","id":4},{"number":23,"distance":679,"name":"5号","id":5},{"number":10,"distance":572,"name":"6号","id":6},{"number":39,"distance":216,"name":"7号","id":7},{"number":5,"distance":1213,"name":"8号","id":8},{"number":18,"distance":1534,"name":"9号","id":9},{"number":62,"distance":1441,"name":"10号","id":10},{"number":27,"distance":1775,"name":"11号","id":11},{"number":52,"distance":650,"name":"12号","id":12},{"number":37,"distance":138,"name":"13号","id":13},{"number":91,"distance":1681,"name":"14号","id":14},{"number":99,"distance":23,"name":"15号","id":15}],"name":"中医院"},{"childBeans":[{"number":71,"distance":733,"name":"1号","id":1},{"number":76,"distance":1714,"name":"2号","id":2},{"number":3,"distance":1490,"name":"3号","id":3},{"number":26,"distance":642,"name":"4号","id":4},{"number":23,"distance":879,"name":"5号","id":5},{"number":10,"distance":772,"name":"6号","id":6},{"number":39,"distance":416,"name":"7号","id":7},{"number":5,"distance":1413,"name":"8号","id":8},{"number":18,"distance":1734,"name":"9号","id":9},{"number":62,"distance":1641,"name":"10号","id":10},{"number":27,"distance":1975,"name":"11号","id":11},{"number":52,"distance":850,"name":"12号","id":12},{"number":37,"distance":338,"name":"13号","id":13},{"number":91,"distance":1881,"name":"14号","id":14},{"number":99,"distance":223,"name":"15号","id":15}],"name":"联想大夏站"}]
     */

    private int number;
    private String busName;
    private String beginTime;
    private String endTime;
    private List<PlatformsBean> platforms;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<PlatformsBean> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformsBean> platforms) {
        this.platforms = platforms;
    }

    public static class PlatformsBean {
        /**
         * childBeans : [{"number":71,"distance":533,"name":"1号","id":1},{"number":76,"distance":1514,"name":"2号","id":2},{"number":3,"distance":1290,"name":"3号","id":3},{"number":26,"distance":442,"name":"4号","id":4},{"number":23,"distance":679,"name":"5号","id":5},{"number":10,"distance":572,"name":"6号","id":6},{"number":39,"distance":216,"name":"7号","id":7},{"number":5,"distance":1213,"name":"8号","id":8},{"number":18,"distance":1534,"name":"9号","id":9},{"number":62,"distance":1441,"name":"10号","id":10},{"number":27,"distance":1775,"name":"11号","id":11},{"number":52,"distance":650,"name":"12号","id":12},{"number":37,"distance":138,"name":"13号","id":13},{"number":91,"distance":1681,"name":"14号","id":14},{"number":99,"distance":23,"name":"15号","id":15}]
         * name : 中医院
         */

        private String name;
        private List<ChildBeansBean> childBeans;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildBeansBean> getChildBeans() {
            return childBeans;
        }

        public void setChildBeans(List<ChildBeansBean> childBeans) {
            this.childBeans = childBeans;
        }

        public static class ChildBeansBean {
            /**
             * number : 71
             * distance : 533
             * name : 1号
             * id : 1
             */

            private int number;
            private int distance;
            private String name;
            private int id;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
