class FoodFactory {
		public Food getFood(String order) {
			Food food;
			if(order.equals("cake"))  
				food = new Cake();
			else 
				food = new Pizza();
			return food;

		}//End of getFood method

}//End of factory class