package edu.mephi.lab5.classes;

public class Food {
    private String name;
    private double protein;
    private double carbo;
    private double fat;

    public Food(String name, double protein, double fat, double carbo) {
        this.name = name;
        this.protein = protein;
        this.carbo = carbo;
        this.fat = fat;
    }
    public void setName(String name) {
		this.name = name;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public void setCarbo(double carbo) {
		this.carbo = carbo;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public String get_name() {
        return this.name;
    };
    public double get_protein() {
        return this.protein;
    };
    public double get_carbo() {
        return this.carbo;
    };
    public double get_fat() {
        return this.fat;
    };
    public double calc_calory(double gram) {
        double  fat_cal = this.fat * 9.29 / 100.0 * gram;
        double  carbo_cal = this.carbo * 4.1 / 100.0 * gram;
        double  prot_cal = this.protein * 4.1 / 100.0 * gram;
        return fat_cal + carbo_cal + prot_cal;
    }
}
