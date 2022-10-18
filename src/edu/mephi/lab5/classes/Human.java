package edu.mephi.lab5.classes;

public class   Human {
    private String  name;
	private double  height;
    private double  weight;
    private int     age;
    private boolean gender;
    private int     coeff;
    private double  day_eaten_calory = 0;
    private int     default_coeff = 0;

    public Human(String name, double height, double weight, int age,
            boolean gender, int coeff) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        if (coeff <= 0 || coeff >= 6)
            this.coeff = default_coeff;
        else
            this.coeff = coeff;
    }

    public double getDayEatenCalory() {
		return day_eaten_calory;
	}
	public void setDayEatenCalory(double calory) {
		this.day_eaten_calory = calory;
	}
	public void addDayEatenCalory(double calory) {
		this.day_eaten_calory += calory;
	}
    // public void addEatenDishCalory(Food food) {
    //     this.day_eaten_calory += food.calc_calory(100);
    // }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getCoeff() {
		return coeff;
	}
	public void setCoeff(int coeff) {
        if (coeff <= 0 || coeff >= 6)
            this.coeff = default_coeff;
        else
            this.coeff = coeff;
	}
    private double  calc_coeff() {
        switch (this.coeff) {
            case 0: return 1.2;
            case 1: return 1.38;
            case 2: return 1.46;
            case 3: return 1.55;
            case 4: return 1.64;
            case 5: return 1.73;
        }
        return 1.9;
    }
    public double   calc_dci() {
        double  gender_coeff = (this.gender) ? 5.0 : -161.0;

        return this.calc_coeff() * (this.height * 10.0 + this.height * 6.25 - 
                (double)this.age * 5.0 + gender_coeff);
    }
}
