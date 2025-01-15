package com.microservice.ms2.microservice2;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Entity
@SequenceGenerator(name = "microservice2_seq", allocationSize = 50)
@Table(name="microservice2")
public class Model {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="column1")
    private String variable1;

    @Column(name="column2")
    private String variable2;

}
