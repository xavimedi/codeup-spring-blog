package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

	@RequestMapping("/{method}/{num1}/{method2}/{num2}")
	@ResponseBody
	public String returnMath(@PathVariable String method, @PathVariable int num1, @PathVariable String method2, @PathVariable int num2){

		double result; //made result double for divide (i.e. /divide/3/by/6)

		switch(method){
			case "add":
				result = add(num1, num2);
				method2 = "and";
				break;
			case "subtract":
				result = subtract(num2, num1);
				method2 = "from";
				break;
			case "multiply":
				result = multiply(num1, num2);
				method2 = "and";
				break;
			case "divide":
				result = divide(num1, num2);
				method2 = "by";
				break;
			default:
				return "Math machine broke";
		}
		return "Solution: " + result;
	}
	private double add(int num1, int num2) {
		return num1 + num2;
	}
	private double subtract(int num1, int num2) {
		return num1 - num2;
	}
	private double multiply(int num1, int num2) {
		return num1 * num2;
	}
	private double divide(int num1, int num2) {
		return (double) num1 / num2;
	}

}