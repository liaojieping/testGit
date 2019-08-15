package com.cache.springbootcache.entity;

import java.io.Serializable;

public class Dep  implements Serializable {
      private int id;
      private String departmentName;
   public Dep(int id,String departmentName){
       this.id=id;
       this.departmentName=departmentName;
   }
   public Dep(){
       super();
   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
