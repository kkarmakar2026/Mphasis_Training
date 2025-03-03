package com.test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    int age;
    String gender;
    int yoj; 
    double salary;

    public Employee(int id, String name, int age, String gender, int yoj, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.yoj = yoj;
        this.salary = salary;
    }

    // Override equals() and hashCode() to compare based on id and yoj
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id && yoj == employee.yoj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, yoj);
    }

    @Override
    public String toString() {
        return "Employee_id=" + id + ", Name='" + name + "', Age=" + age +
               ", Gender='" + gender + "', Year_of_Joining=" + yoj + ", Salary=" + salary ;
    }
}

public class EmployeeStreamDemo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(101, "Arnobi", 30, "Female", 2018, 50000),
                new Employee(102, "Balai", 35, "Male", 2021, 60000),
                new Employee(103, "Tamal", 28, "Male", 2019, 55000),
                new Employee(104, "Ramesh", 40, "Male", 2022, 65000),
                new Employee(105, "Koyel", 25, "Female", 2023, 70000),
                new Employee(102, "Balai", 35, "Male", 2021, 60000), 
                new Employee(104, "Ramesh", 40, "Male", 2022, 65000),
                new Employee(105, "Koyel", 25, "Female", 2023, 70000)  
        );

        // 1. Print employees whose name starts with character ('A')
        char specificChar = 'A';
        System.out.println("Employees whose name starts with '" + specificChar + "':");
        employees.stream().filter(emp -> emp.name.startsWith(String.valueOf(specificChar))).forEach(System.out::println);

        // 2. Employees who joined after 2020
        System.out.println("\nEmployees who joined after 2020:");
        List<Employee> joinedAfter2020 = employees.stream().filter(emp -> emp.yoj > 2020).collect(Collectors.toList());

        joinedAfter2020.forEach(System.out::println);

        // 3. Employees who joined after 2020 in sorted order (based on yoj)
        List<Employee> sortedJoinedAfter2020 = employees.stream().filter(emp -> emp.yoj > 2020).sorted(Comparator.comparingInt(emp -> emp.yoj)).collect(Collectors.toList());

        System.out.println("\nSorted Employees who joined after 2020:");
        sortedJoinedAfter2020.forEach(System.out::println);

        // 4. Count occurrences of each employee
        System.out.println("\nEmployee count (including duplicates):");
        Map<Employee, Long> employeeCount = employees.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        employeeCount.forEach((emp, count) -> 
            System.out.println(emp + " -> Count: " + count));

        // 5. Print only distinct employees
        System.out.println("\nDistinct Employees:");
        employees.stream().distinct() // Removes duplicates based on equals() and hashCode()
                .forEach(System.out::println);
    }
}