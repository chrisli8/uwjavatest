package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  // Write a Person.count() that returns the total number of Person instances created?
  //
  // 
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
  }
  
  // My edits
  public void setAge(int age) {
    if(age < 0) {
      throw new IllegalArgumentException();
    }
    this.age = age;
  }
  
  public void setName(String name) {
    if(name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
  }
  
  public void setSalary(Double sal) {
    this.salary = sal;
  }
  
  public int getAge() {
    return this.age;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Double getSalary() {
    return this.salary;
  }
  
  // Returns a list of 4 people
  // Ted, age 41, salary 250000; 
  // Charlotte, age 43, salary 150000; 
  // Michael, age 22, salary 10000; 
  // and Matthew, age 15, salary 0.
  public static List<Person> getNewardFamily() {
    List<Person> list = new ArrayList<Person>();
    list.add(new Person("Ted", 41, 250000.));
    list.add(new Person("Charlotte", 43, 150000.));
    list.add(new Person("Michael", 22, 10000.));
    list.add(new Person("Matthew", 15, 0.));
    return list;
  }
  
  // not working?
  @Override
  public boolean equals(Object obj) {
    //return this.age == other.age && this.name.equals(other.name);
    if (obj == null) {
        return false;
    }
    if (!Person.class.isAssignableFrom(obj.getClass())) {
        return false;
    }
    final Person other = (Person) obj;
    if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
        return false;
    }
    if (this.age != other.age) {
        return false;
    }
    return true;
  }
  
  // Rich people are shown as less than poor people
  @Override
  public int compareTo(Person other) {
    if (other.salary - this.salary > 0) {
      return 1;
    } else if (other.salary - this.salary < 0) {
      return -1;
    } else {
      return 0;
    }  
  }
  
  
  /*
  public static class YComparator implements Comparator<Point>
	{
		public int compare(Point lhs, Point rhs) {
			return lhs.y - rhs.y;
		}
	}
  */
  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.age - p2.age;
    }
  }
  
  // Create an AgeComparator class that compares two Persons and arranges them by age (age 15 is less than age 25). 
  // This Comparator MUST BE a nested class inside of Person; Person's fields must remain private.
  //
  // Works with Collections.sort
  // End of edits

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  // Edited to print the name of person
  // [Person name:Fird Birfle age:20 salary:195750.22]
  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
