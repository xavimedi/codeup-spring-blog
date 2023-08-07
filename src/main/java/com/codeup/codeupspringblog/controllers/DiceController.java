package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String diceRoller(Model model) {
        int diceRoll = (int) (Math.random() * 6) + 1;
        model.addAttribute("diceRoll", diceRoll);
        model.addAttribute("roll", "");
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{playerChoice}")
    public String rollDice(@PathVariable(name = "playerChoice") Integer playerChoice, Model model) {
        int diceRoll = (int) (Math.random() * 6) + 1;

        String rollResult;
        if (playerChoice == diceRoll){
            rollResult = "Congratulations! You guessed correctly! You rolled a " + diceRoll + "!";
        }else{
            rollResult = "You guessed " + playerChoice + ", but you rolled a " + diceRoll + ".";
        }

        model.addAttribute("diceRoll", diceRoll);
        model.addAttribute("roll", rollResult);
        return "roll-dice";
    }
}
