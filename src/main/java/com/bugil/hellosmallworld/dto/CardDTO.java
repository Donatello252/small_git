package com.bugil.hellosmallworld.dto;

import com.bugil.hellosmallworld.entity.CardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    private String name;
    private String Property;
    private String species;
    private String level;

    private String atk;
    private String def;

    public static CardDTO toCardDTO(CardEntity cardEntity){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setName(cardEntity.getName());
        cardDTO.setProperty(cardEntity.getProperty());
        cardDTO.setSpecies(cardEntity.getSpecies());
        cardDTO.setLevel(cardEntity.getLevel());
        cardDTO.setAtk(cardEntity.getAtk());
        cardDTO.setDef(cardEntity.getDef());

        return cardDTO;
    }


    public static CardDTO StringtoCardDTO(String cardString){
        CardDTO cardDTO = new CardDTO();

        String[] ArraysStr = cardString.split(",");
        cardDTO.setName(ArraysStr[0]);
        cardDTO.setProperty(ArraysStr[1]);
        cardDTO.setSpecies(ArraysStr[2]);
        cardDTO.setLevel(ArraysStr[3]);
        cardDTO.setAtk(ArraysStr[4]);
        cardDTO.setDef(ArraysStr[5]);

        return cardDTO;
    }




}
