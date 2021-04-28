package com.amazon.core.lambda.collectors;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.amazon.core.lambda.dto.Dish;

public class Grouping {
	enum CaloricLevel {
		DIET, NORMAL, FAT
	};

	public static void main(String[] args) {
		System.out.println("Dishes grouped by type: " + groupDishesByType());
		System.out.println("Dish names grouped by type: " + groupDishNamesByType());
		System.out.println("Dish names grouped by caloric level: " + groupDishesByCaloricLevel());
		System.out.println("Dish names grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
		System.out.println("Counting dishes in group: " + countDishesInGroups());
		System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
		System.out
				.println("Most caloric dishes by type without optional: " + mostCaloricDishesByTypeWithoutOptionals());
		System.out.println("Summing calories by type: " + sumCaloriesByType());
		System.out.println("Calories level by type: " + caloricLevelsByType());
		
	}

	private static Map<Dish.Type, List<Dish>> groupDishesByType() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
	}

	private static Map<Dish.Type, List<String>> groupDishNamesByType() {
		return Dish.menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())));
	}

	// JAVA-9
//	private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
//		return Dish.menu.stream().collect(
//				Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> dishTags.get(dish.getName()).stream(), Collectors.toSet())));
//	}

	// JAVA-9
//	private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType() {
////        return menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
//		return Dish.menu.stream().collect(groupingBy(Dish::getType, Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
//	}

	private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
		return Dish.menu.stream().collect(Collectors.groupingBy(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}));
	}

	private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy((Dish dish) -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		})));
	}

	private static Map<Dish.Type, Long> countDishesInGroups() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
	}

	private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType,
				Collectors.reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
	}

	private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
				Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get)));
	}

	private static Map<Dish.Type, Integer> sumCaloriesByType() {
		return Dish.menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
	}

	private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}, Collectors.toSet())));
	}

}
