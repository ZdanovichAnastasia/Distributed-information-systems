package by.bsuir.laba8.main;

import by.bsuir.laba8.service.CalculateService;
import by.bsuir.laba8.service.models.IInputService;
import by.bsuir.laba8.service.InputService;
import by.bsuir.laba8.service.models.ICalculateService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		ICalculateService calculateService = (CalculateService) context.getBean("calculateBean");
		IInputService inputService = (InputService) context.getBean("inputBean");

		System.out.println("Enter the number");
		Double number = inputService.ReadNumber();
		System.out.println("Enter the percent");
		Double percent = inputService.ReadNumber();
		Double result = calculateService.calculatePercent(number, percent);
		System.out.printf("Result: %s", result);

		context.close();
	}
}
