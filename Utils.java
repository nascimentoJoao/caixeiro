import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

	private ArrayList<City> cities;
	private Double current_distance;
	private final int MUT_NUMBER = 2;
	
	public Utils() {
		this.cities = new ArrayList<>();
		this.current_distance = 0.0;
	}
	
	public void start(String filename) throws IOException {
		readFile(filename);
		mutations();

        System.out.println("Final distance: " + this.current_distance);
	}
		
	private void readFile(String filename) throws IOException{
		BufferedReader br = null;

		br = new BufferedReader(new FileReader(filename));

		String current_line = br.readLine();
		
		while ((current_line = br.readLine()) != null) {
			
				String[] aux = current_line.split(" ");
				
				cities.add(new City(Double.parseDouble(aux[0]), Double.parseDouble(aux[1]), aux[2]));
			}
		
		this.current_distance = distPath(cities);
		
		br.close();
	}
	
	private Double distPath(ArrayList<City> cities){
		Double result = 0.0;
		
		for(int i = 0; i < cities.size()-1; i++){
			result+= distance_two_cities(cities.get(i),cities.get(i+1));
		}
		result += distance_two_cities(cities.get(cities.size()-1), cities.get(0));
		return result;

	}
	
	private Double distance_two_cities(City c1, City c2){
		Double xs = c1.getX() - c2.getX();
		xs = xs*xs;
		
		Double ys = c1.getY() - c2.getY();
		ys = ys*ys;
		
		return Math.sqrt(xs + ys);
	}
	
	private void mutations() {
		int count = 0;	
		while (true) {
			Random random = new Random();
			switch(random.nextInt(MUT_NUMBER)){
				case 0:	
                if(generate_random_two(cities)) {
					System.out.println("Current distance: " + current_distance);
					count = -1;
				}

				break;
			}

			count++;
		}
	}
	
	private Boolean generate_random_two(ArrayList<City> cities){
		Random random = new Random();
		
		Integer p1 = random.nextInt(cities.size());
		Integer p2 = random.nextInt(cities.size());
		
		City c1 = cities.get(p1);
		City c2 = cities.get(p2);
		
		cities.set(p1, c2);
		cities.set(p2, c1);
		
		Double newDist = distPath(cities);
		
		if(newDist < current_distance) {
			current_distance = newDist;
			return true;
			
		} else {
			cities.set(p1, c1);
			cities.set(p2, c2);
			
			return false;
		}
	}

}