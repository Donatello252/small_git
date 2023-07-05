package com.bugil.hellosmallworld.entity;


import com.bugil.hellosmallworld.dto.CardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card")
public class CardEntity {

    @Id
    private String name;

    @Column()
    private String Property;

    @Column()
    private String species;

    @Column()
    private String level;

    @Column()
    private String atk;

    @Column()
    private String def;

    public static CardEntity toSaveEntity(CardDTO cardDTO){
        CardEntity cardEntity = new CardEntity();
        cardEntity.setName(cardDTO.getName());
        cardEntity.setProperty(cardDTO.getProperty());
        cardEntity.setSpecies(cardDTO.getSpecies());
        cardEntity.setLevel(cardDTO.getLevel());
        cardEntity.setAtk(cardDTO.getAtk());
        cardEntity.setDef(cardDTO.getDef());

        return cardEntity;

    }

    public boolean isSmall(CardDTO another){
        int small_count = 0;

        if (this.getProperty().equals(another.getProperty())){small_count+=1;}
        if (this.getSpecies().equals(another.getSpecies())){small_count+=1;}
        if (this.getLevel().equals(another.getLevel())){small_count+=1;}
        if (this.getAtk().equals(another.getAtk())){small_count+=1;}
        if (this.getDef().equals(another.getDef())){small_count+=1;}

        return small_count == 1;

    }

}
