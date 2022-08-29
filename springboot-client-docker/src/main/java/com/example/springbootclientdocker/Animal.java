package com.example.springbootclientdocker;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Animal(String name) {
        this.name = name;
    }
    // musi być konstruktor bezparametrowy żeby rest-api chodziło
    // muszą być też settery i gettery do wszystkich pól
    public Animal() {}
}
