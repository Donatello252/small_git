package com.bugil.hellosmallworld.service;

import com.bugil.hellosmallworld.dto.CardDTO;
import com.bugil.hellosmallworld.entity.CardEntity;
import com.bugil.hellosmallworld.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public Page<CardDTO> getCards(Pageable pageable) throws FileNotFoundException, IOException {
        Page<CardEntity> cardEntityList = cardRepository.findAll(pageable);

        if(cardEntityList.isEmpty()) {
            String filename = "C:\\Users\\wonmin\\Desktop\\cardlist.csv";
            List<String> result = new ArrayList<>();
            Charset.forName("UTF-8");

            BufferedReader br = new BufferedReader(new FileReader(filename));
            String str;

            while ((str = br.readLine()) != null){
                result.add(str);
                cardRepository.save(CardEntity.toSaveEntity(CardDTO.StringtoCardDTO(str)));
            }

            cardEntityList = cardRepository.findAll(pageable);
        }


        List<CardDTO> cardDTOList = new ArrayList<>();
        for (CardEntity cardEntity: cardEntityList) {

            cardDTOList.add(CardDTO.toCardDTO(cardEntity));
        }
        return new PageImpl<CardDTO>(cardDTOList);

    }

    public Page<CardDTO> getSmallCenters(String start, String end, Pageable pagable) {
        Page<CardEntity> cardEntityList = cardRepository.findAll(pagable);

        CardDTO startcardDTO = getCard(start);
        CardDTO endcardDTO = getCard(end);

        List<CardDTO> cardDTOList = new ArrayList<>();
        for (CardEntity cardEntity: cardEntityList) {
            if (cardEntity.isSmall(startcardDTO) && cardEntity.isSmall(endcardDTO)){
                cardDTOList.add(CardDTO.toCardDTO(cardEntity));
            }

        }

        return new PageImpl<CardDTO>(cardDTOList);
    }

    @Transactional
    public CardDTO getCard(String name) {

        Optional<CardEntity> optionalBoardEntity = cardRepository.findById(name);
        if (optionalBoardEntity.isPresent()) {
            CardEntity cardEntity = optionalBoardEntity.get();

            CardDTO cardDTO = CardDTO.toCardDTO(cardEntity);

            return cardDTO;
        } else {
            return null;
        }

    }





}
