package com.timesinternet.suggestor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table  
public class Record {  

	@Id  
    @GeneratedValue
    private int id;  
  
    @Column
    private String value;  

	public int getId() {  
        return id;  
    }  
  
    public void setId(int id) {  
        this.id = id;  
  
    }

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}  

}  