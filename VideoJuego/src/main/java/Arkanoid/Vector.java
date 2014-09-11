package Arkanoid;

public class Vector {

	private double x;
	private double y;
	private double module;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
		this.module = -1;
	}

	@Override
	public String toString() {
		return "("+ x + "," + y + ")";
	}

	public double getModule() {
		if(this.module < 0) {
			this.module = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		}
		return module;
	}
	
	public Vector asVersor(){
		//TODO, usar un delta?
		return this.getModule() != 1 ? new Vector(this.x/this.getModule(), this.y/this.getModule()) : this;
	}
	
	public Vector producto(double valor){
		return new Vector(x * valor , y * valor);
	}

	public Vector suma(Vector vector2d) {
		return new Vector(this.x + vector2d.getX(), this.y + vector2d.getY());
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(module);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(module) != Double
				.doubleToLongBits(other.module))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

}
