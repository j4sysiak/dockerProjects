package com.example.springbootapidocker;

//@Entity
//@Data
public class Animal {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    // musi być konstruktor bezparametrowy żeby rest-api chodziło
    // muszą być też settery i gettery do wszystkich pól
    public Animal() {}
    public Animal(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}