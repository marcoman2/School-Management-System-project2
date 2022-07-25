/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5_schoolmanagement;

/**
 *
 * @author MarcoMan
 */
public class Data {

//    MAKE SURE THAT YOU DIDN'T USED THE STATIC HERE!
    private Integer id;
    private String surname;
    private String given;
    private String gender;
    private String image;
    private String current;
    private String date;

//    WE WILL USE THIS CONSTRACTOR
    public Data(Integer id, String surname, String given, String gender, String image) {
//        MAKE SURE THAT THEY ARE SAME POSITION HAH
        this.id = id;
        this.surname = surname;
        this.given = given;
        this.gender = gender;
        this.image = image;

    }
    
//    FOR STUDENT RECORD
    public Data(Integer id, String surname, String given, String gender, String current, String image){
        
        this.id = id;
        this.surname = surname;
        this.given = given;
        this.gender = gender;
        this.current = current;
        this.image = image;
        
    }

    public Integer getId() {

        return id;

    }

    public String getSurname() {

        return surname;

    }

    public String getGiven() {

        return given;

    }

    public String getGender() {

        return gender;

    }

    public String getImage() {

        return image;

    }
    
    public String getCurrent(){
        
        return current;
        
    }
    
    public String getDate(){
        
        return date;
        
    }
    
}
