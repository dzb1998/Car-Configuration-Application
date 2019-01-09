package client;

import java.util.Scanner;

import exception.AutoException;
import model.Automobile;

public class SelectCarOption {

	/**
	 * Choose a model from list.
	 * @param modelList model list
	 * @param scanner scanner
	 * @return choice
	 */
	public static String chooseModel(String modelList, Scanner scanner) {
		String[] modelArray = modelList.split("\n");
		System.out.println("List of availalbe models: \n");
		for (String model : modelArray) {
			System.out.println("\t" + model);
		}
		System.out.println("\n---------------------------------------------\n");
		System.out.println("Please choose from the list:");
		String choice = scanner.nextLine();
		return choice;
	}

	/**
	 * Configure the car.
	 * @param auto automobile
	 * @param scanner scanner
	 */
	public static void selectOptions(Automobile auto, Scanner scanner) {
        try {
            System.out.println("Start configure model:");
            auto.print();
            System.out.println("----------------------------------------------");
            boolean toContinue = true;
            while (toContinue) {
            	System.out.println("Please enter option set name: ");
            	String optionSetName = scanner.nextLine();
            	System.out.println("Please enter option name: ");
            	String optionName = scanner.nextLine();
            	auto.setOptionChoice(optionSetName, optionName);
            	System.out.println("Continue? (y/n) ");
            	String str = scanner.nextLine();
            	if (!str.equals("y")) {
            		toContinue = false;
            	}
            }
            System.out.println("----------------------------------------------");
            displayOptions(auto);
        } catch (AutoException e){
            e.fix(e.getErrorNo());
        }
	}

	/**
	 * Display the choices.
	 * @param auto automobile
	 */
	public static void displayOptions(Automobile auto) {
		System.out.println("You made some choices: ");
		System.out.println(auto.choicesInfo());
	}
}
