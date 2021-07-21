package com.shekinah.store.serviceproduct.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {

    //@Column(columnDefinition = "serial")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;


}
