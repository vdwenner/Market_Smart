package com.techelevator.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Game {

    private Long id;
    private String gameName;
    private Long creatorId;
    private BigDecimal startingAmount;
    private LocalDateTime endDate;

}
