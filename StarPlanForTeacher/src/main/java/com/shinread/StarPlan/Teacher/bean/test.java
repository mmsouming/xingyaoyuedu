package com.shinread.StarPlan.Teacher.bean;

import java.util.List;

/**
 * Created by Reese on 2016/9/20.
 */
public class test {


    /**
     * name : Color
     * value : Black
     */

    private List<SpecListBean> specList;

    public List<SpecListBean> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecListBean> specList) {
        this.specList = specList;
    }

    public static class SpecListBean {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
